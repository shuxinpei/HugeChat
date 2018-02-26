/**
 * 好友管理类,群聊管理类
 */
package com.qq.client.tools;

import java.util.ArrayList;

import Common.Message;
import Common.User;
public class ManageQqFriendList {
static{
	friend=new ArrayList();
	QunName=new ArrayList();
	message=new ArrayList();
}
	public static ArrayList<User> friend;
	public static ArrayList<String> QunName;
	public static ArrayList	<Message> message;

	public static ArrayList<User> getFriend() {
		return friend;
	}
	public static void setFriend(ArrayList<User> friend) {
		ManageQqFriendList.friend = friend;
	}
	public static ArrayList<String> getQunName() {
		return QunName;
	}
	public static void setQunName(ArrayList<String> qunName) {
		QunName = qunName;
	}
	public static ArrayList<Message> getMessage() {
		return message;
	}
	public static void setMessage(ArrayList<Message> message) {
		ManageQqFriendList.message = message;
	}

}
