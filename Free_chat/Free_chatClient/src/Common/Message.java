package Common;

import java.util.ArrayList;

public class Message implements java.io.Serializable{

	private String mesType;
	private int id;
	private int sender;
	private int getter;
	private String con;
	private String sendTime;
	private ArrayList<User> friend;
	private ArrayList <String>QunName;
	private ArrayList<Message>message;
	private String qunname;

	public String getQunname() {
		return qunname;
	}
	public void setQunname(String qunname) {
		this.qunname = qunname;
	}
	public String  toString(){
		return "类型"+mesType+"发送者"+sender+"接受者"+getter+"内容"+con+"时间"+sendTime;
	}
	public ArrayList<User> getFriend() {
		return friend;
	}
	public void setFriend(ArrayList<User> friend) {
		this.friend = friend;
	}
	public ArrayList<String> getQunName() {
		return QunName;
	}
	public void setQunName(ArrayList<String> qunName) {
		QunName = qunName;
	}
	public ArrayList<Message> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<Message> message) {
		this.message = message;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSender() {
		return sender;
	}
	public void setSender(int sender) {
		this.sender = sender;
	}
	public int getGetter() {
		return getter;
	}
	public void setGetter(int getter) {
		this.getter = getter;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getMesType() {
		return mesType;
	}
	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
