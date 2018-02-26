/**
 * 定义包的种类
 */
package Common;

public interface MessageType {

	String message_succeed="1";//表明是登陆成功
	String message_login_fail="2";//表明登录失败
	String message_comm_mes="3";//私聊
	String message_manypeople="4";//群聊
	String message_image="5";//发送图片
	String message_forFriend="6";//请求返回一个好友队列
	String message_forQun="7";//请求返回一个群名字的队列
	String message_formessage="8";//请求返回一个消息的队列
}
