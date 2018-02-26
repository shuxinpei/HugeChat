/**
 * 这是一个管理客户端和服务器保持通讯的线程类，进行添加以及提取
 */
package com.qq.client.tools;

import Common.User;
/**
 * 其实也只有一个线程会传递到这边
 * @author SHUXIN
 *
 */
public class ManageClientConServerThread {

	public  static  ClientConServerThread mclientthread;
	public static int UserID;
	public static String Username;
	public static String UserPsw;
	public static  void addClientConServerThread(int id,ClientConServerThread mclientthread){
		UserID=id;
		ManageClientConServerThread.mclientthread=mclientthread;
	}
}
