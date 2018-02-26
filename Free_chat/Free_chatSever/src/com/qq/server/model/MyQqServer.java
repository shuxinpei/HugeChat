/**
 * 这是qq服务器，它在监听，等待某个qq客户端，来连接
 */
package com.qq.server.model;
import com.qq.server.db.DBUtil;

import Common.Message;
import Common.User;

import java.net.*;
import java.io.*;
import java.util.*;
public class MyQqServer {
	public MyQqServer()
	{
		try {
			//在9999监听
			System.out.println("我是服务器，在9999监听");
			ServerSocket ss=new ServerSocket(9999);
			//阻塞,等待连接
			while(true)//注册与登录
			{
				Socket s=ss.accept();
				//接收客户端发来的信息.
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				System.out.println("客户端来的User对象"+u.toString());
				System.out.println("服务器接收到用户名:"+u.getUsername()+"  密码:"+u.getPasswd());
				Message m=new Message();
				System.out.println("测试");
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				System.out.println("测试2");
				if(u.isIfregiste()){//如果是注册
					System.out.println(u.isIfregiste());
					System.out.println("数据库查询结果"+DBUtil.Query(u.getUsername()));
					if(DBUtil.Query(u.getUsername())){//如果查到了已经有名字的
						m.setMesType("2");
						oos.writeObject(m);
						s.close();
					}else{//成功注册
						DBUtil.Add(u.getUsername(), u.getPasswd(),1);//注册成功将用户加到数据库中----头像再改改
						m.setMesType("1");
						m.setId(DBUtil.QueryGetUserID(u.getUsername()));//在我们回传的数据包中将用户的id进行传输,那边进行接收
						m.setCon(u.getUsername());//使用数据包进行中转，传输名字
						m.setSendTime(u.getPasswd());//使用数据包进行中转，传输密码
						oos.writeObject(m);

						SerConClientThread scct=new SerConClientThread(s);
						ManageClientThread.addClientThread(DBUtil.QueryGetUserID(u.getUsername()), scct);//u在注册的时候是没有id的
						//启动与该客户端通信的线程.
						scct.start();
					}
				}else{//不是注册的话
					if(DBUtil.Query(u.getUsername(), u.getPasswd()))
					{
					//返回一个成功登陆的信息报
					m.setMesType("1");
					m.setId(DBUtil.QueryGetUserID(u.getUsername()));
					m.setCon(u.getUsername());//使用数据包进行中转，传输名字
					m.setSendTime(u.getPasswd());//使用数据包进行中转，传输密码
					System.out.println("还没出问题");
					oos.writeObject(m);

					//这里就单开一个线程，让该线程与该客户端保持通讯.
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(DBUtil.QueryGetUserID(u.getUsername()), scct);
					//启动与该客户端通信的线程.
					scct.start();

					//并通知其它在线用户.
					//	scct.notifyOther(u.getUserId());//这边需要改改-----------------------------------
					}else{
					m.setMesType("2");
					oos.writeObject(m);
					//关闭Socket
					s.close();
				}
			}
		}
	}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
		}
	}
}
