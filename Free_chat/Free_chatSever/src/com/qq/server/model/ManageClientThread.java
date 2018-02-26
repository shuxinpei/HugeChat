package com.qq.server.model;

import java.util.*;
/**
 * 这一个类进行存储和提取我们用线程控制的客户端对象
 * @author SHUXIN
 *
 */
public class ManageClientThread {
	//id独一无二,在客户端的某一个类中的user id和这个是对应的
	public static HashMap hm=new HashMap<String, SerConClientThread>();

	//向hm中添加一个客户端通讯线程
	public static  void addClientThread(int uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}

	public static SerConClientThread getClientThread(int uid)
	{
		return (SerConClientThread)hm.get(uid);
	}

	//返回当前在线的人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
