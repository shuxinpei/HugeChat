/**
 * 这是客户端和服务器端保持通讯的线程.
 */
package com.qq.client.tools;

import java.io.*;
import java.net.*;

import Common.*;
import View.ChatPanel;

public class ClientConServerThread extends Thread {

	private Socket s;
	public User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	// 构造函数
	public ClientConServerThread(Socket s) {
		this.s = s;
		this.user=new User();
	}
/**
 * 发送请求返回好友列表的包
 * @throws IOException
 */
	public  void sendMessageForFriend() throws IOException{
	ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
	Message msg=new Message();
	msg.setMesType(MessageType.message_forFriend);//发送请求返回好友列表的包
	msg.setSender(ManageClientConServerThread.UserID);
	oos.writeObject(msg);
	}

	/**
	 * 发送请求返回群列表名字的消息包
	 * @throws IOException
	 */
	public void sendMessageForQun() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		Message msg=new Message();
		msg.setMesType(MessageType.message_forQun);//发送请求返回好友列表的包
		msg.setSender(ManageClientConServerThread.UserID);
		oos.writeObject(msg);
	}

	public void sendMessageToSingle(Message MsgtoOne) throws IOException {//请求额返回一个消息的队列的函数都是通过这个来调用
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(MsgtoOne);
	}


	public void run() {
/**
	String message_succeed="1";//表明是登陆成功
	String message_login_fail="2";//表明登录失败
	String message_comm_mes="3";//私聊
	String message_manypeople="4";//群聊
	String message_image="5";//发送图片
	String message_forFriend="6";//请求返回一个好友队列
	String message_forQun="7";//请求返回一个群名字的队列
	String message_formessage="8";//请求返回一个消息的队列
 */
		while (true) {
			// 不停的读取从服务器端发来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getMesType().equals(MessageType.message_comm_mes)) {//私聊返回的数据包，这边是发送那边，服务器会进行天机一个内容
					System.out.println("客户端接受到服务器返回的message包");
					ManageQqFriendList.setMessage(m.getMessage());//将返回的message队列存储到我们的静态变量中
					ChatPanel.addJLabeltoPanel();
					System.out.println(m.getMessage()+"赋值后的数据包的内容");
				}
				if (m.getMesType().equals(MessageType.message_formessage)) {//私聊返回的数据包
					ManageQqFriendList.message=m.getMessage();//将返回的message队列存储到我们的静态变量中
				}
				if (m.getMesType().equals(MessageType.message_manypeople)) {//群聊返回的数据包
					System.out.println("服务器返回群聊数据包");
					ManageQqFriendList.setMessage(m.getMessage());
					ChatPanel.addJLabeltoPanel();
				}
				if (m.getMesType().equals(MessageType.message_forFriend)) {//群聊返回的数据包
					//System.out.println("接受好友的列表 ");
					//System.out.println(m.getFriend().size());
					ManageQqFriendList.setFriend(m.getFriend());

				}
				if (m.getMesType().equals(MessageType.message_forQun)) {//群聊返回的数据包
					//System.out.println("接受群名字列表");
					ManageQqFriendList.setQunName(m.getQunName());

				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
