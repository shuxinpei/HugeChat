/**
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;
import com.qq.client.tools.*;
import java.util.*;
import java.net.*;
import java.io.*;
import Common.*;
public class QqClientConServer {


	public  Socket s;
	public void QqClientConServer(){

	}
	//发送第一次请求
	/**该函数将user对象传入，之后通过返回的B进行操作，如果说没有成功那么就会返回0，反之返回的是新开线程的ID
	 * @param o
	 * @return
	 */
	public  int sendLoginInfoToServer(Object o)//这边是一个User对象
	{
		int b=0;
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);

			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();

			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1"))
			{
				//就创建一个该qq号和服务器端保持通讯连接得线程
				ClientConServerThread ccst=new ClientConServerThread(s);

				ManageClientConServerThread.Username=ms.getCon();//得到我们该账号的名字
				ManageClientConServerThread.UserPsw=ms.getSendTime();//得到我们用户的密码
				ManageClientConServerThread.UserID=ms.getId();//得到	我们用户账号的id

				//启动该通讯线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(ms.getId(), ccst);
				b=ms.getId();
				ccst.sendMessageForFriend();
				ccst.sendMessageForQun();
			}else{
				//关闭Scoket
				s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		}
		return b;
	}

	public void SendInfoToServer(Object o)
	{
	}
}
