/**
 * 功能：是服务器和某个客户端的通信线程
 */
package com.qq.server.model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import com.qq.server.db.DBUtil;

import Common.Message;
import Common.MessageType;
import Common.User;
/**
 * 这一个类负责将每一个开线程的客户端对象进行监听，以及发送消息的处理
 * @author SHUXIN
 *这边应写 私聊 群聊 收发图片
 */
public class SerConClientThread  extends Thread{

	Socket s;

	public SerConClientThread(Socket s)
	{
		//把服务器和该客户端的连接赋给s
		this.s=s;
	}

	public void run()
	{

		while(true)
		{
			//这里该线程就可以接收客户端的信息.
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();

				//对从客户端取得的消息进行类型判断，然后做相应的处理
				if(m.getMesType().equals(MessageType.message_comm_mes))//私聊
				{
					//一会完成转发.
					//取得接收人的通信线程
					System.out.println("服务器接受到我们的私聊数据包");
					System.out.println(m);
					DBUtil.AddMessage(m.getSendTime(),m.getSender(),m.getGetter(),m.getCon(),m.getMesType());
					ArrayList<Message> list=DBUtil.GetMessage(m.getSender(), m.getGetter());
					System.out.println("服務器返回的消息队列"+list);
					Message msg=new Message();
					msg.setMesType(MessageType.message_comm_mes);
					msg.setMessage(list);//返回一个消息的标志是我们的私聊返回，同时将队列加入，之后客户端那边可以进行解析，传参给哪个共有的

					SerConClientThread sc=ManageClientThread.getClientThread(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(msg);//将数据包发送出去

					SerConClientThread sc1=ManageClientThread.getClientThread(m.getGetter());//发送到接受端
					ObjectOutputStream oos1=new ObjectOutputStream(sc1.s.getOutputStream());
					oos1.writeObject(msg);//将数据包发送出去
					System.out.println("服务器将数据包发送出来了");
				}
				if(m.getMesType().equals(MessageType.message_formessage))//请求返回一个消息的队列
				{
					//一会完成转发.
					//取得接收人的通信线程
					ArrayList list=DBUtil.GetMessage(m.getSender(), m.getGetter());
					Message msg=new Message();
					msg.setMesType(MessageType.message_formessage);
					msg.setMessage(list);//返回一个消息的标志是我们的私聊返回，同时将队列加入，之后客户端那边可以进行解析，传参给哪个共有的

					SerConClientThread sc=ManageClientThread.getClientThread(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(msg);//将数据包发送出去
				}
				if(m.getMesType().equals(MessageType.message_manypeople))//群聊，群聊那个getter就用群聊的id进行控制
				{
					//message 	中的getter在群聊的时候就是表示我们群的iD
					//把在服务器的好友给该客户端返回.
					DBUtil.AddQunMessage(m.getSendTime(),m.getSender(),m.getCon(),m.getQunname(),m.getMesType());
					ArrayList<Integer> list=DBUtil.QueryQun(m.getQunname());//返回的我们群中用户的id
					System.out.println("服务器返回的群中用户"+list);
					ArrayList<Message> messages=DBUtil.queryQunMessage(m.getQunname());//返回一个群中消息列表
					System.out.println(messages.size());
					for(int i:list){//这边就没有自给自己发了
							if(ManageClientThread.getClientThread(i)!=null)
							{
								SerConClientThread sc=ManageClientThread.getClientThread(i);
								ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
								Message msg=new Message();
								msg.setMesType(MessageType.message_manypeople);
								msg.setMessage(messages);
								oos.writeObject(msg);
							}
					}

					System.out.println("执行群聊");
				}
				if(m.getMesType().equals(MessageType.message_forFriend)){//返回好友id，账号的列表
					Message backmsg=new Message();
					ArrayList<Integer> list=DBUtil.QueryRalation(m.getSender());//这边没有问题-------------------------------------
					ArrayList<User> friends=new ArrayList();
					for(int i=0;i<list.size();i++){
						User u=new User();
						u.setUserId(list.get(i));
						u.setUsername(DBUtil.QueryGetUserName(list.get(i)));

						friends.add(u);
					}
					backmsg.setFriend(friends);
					backmsg.setMesType(MessageType.message_forFriend);

					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(backmsg);
					System.out.println("发送好友");
				}
				if(m.getMesType().equals(MessageType.message_forQun)){
					Message backmsg=new Message();
					//返回一个用户所在群的id队列
					ArrayList<String> qun=DBUtil.QueryQunAllNameByUserId(m.getSender());
					backmsg.setQunName(qun);//返回的是一个群的队列
					backmsg.setMesType(MessageType.message_forQun);
					System.out.println("群聊的发送端ID"+m.getSender());
					for(String s:qun){
						System.out.println(s+"群聊查询结果");
					}

					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(backmsg);
					System.out.println("发送群聊");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
