package com.qq.server.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import Common.Message;
import Common.MessageType;
import Common.User;

public class DBUtil {
	private final static String url = "jdbc:mysql://localhost:3306/hugechat";// url:// 数据库地址// jdbc:mysql://连接主机IP:端口号//数据库名字
	private final static String username = "root"; // 用户名和密码用自己的
	private final static String password = "SHUXIN0506";// 获得数据库连接// DriverManager类中静态方法
	private static Connection con;

	static {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password); // 返回值是Connection接口的实现类,在mysql驱动程序
			}catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 返回我们的连接对象
	 * @return
	 */
	public static Connection getCon() {
		return con;
	}
/**
 * 通过我们的用户名字来得到他的ID
 * @param name
 * @return
 * @throws SQLException
 */
	public static int QueryGetUserID(String name) throws SQLException {
		int ID = 0;
		String sql = "SELECT * FROM users WHERE UserName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			ID = rs.getInt(1);
		}
		rs.close();
		pst.close();
		return ID;
	}
/**
 * 通过我们用户的id得到他的用户名
 * @param id
 * @return
 * @throws SQLException
 */
	public static String QueryGetUserName(int id) throws SQLException {//----------------------------------------------------
		String name=null;
		String sql = " SELECT * FROM users WHERE UserId=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			name = rs.getString(2);
		}
		rs.close();
		pst.close();
		return name;
	}

	/**
	 * 通过我们用户的id得到他的密码
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static String QueryGetUserPsw(int id) throws SQLException {
			String psw=null;
			String sql = "SELECT * FROM users WHERE UserId=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setObject(1, id);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				psw = rs.getString(3);
			}
			rs.close();
			pst.close();
			return psw;
		}

	/**
	 * 这个函数用来查找我们的用户名是否已经被注册，返回true则已经被注册了
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public static boolean Query(String name) throws SQLException {
		String sql = "SELECT * FROM users WHERE UserName=? "; // 方法中参数,SQL语句中的参数全部采用问号占位符
		PreparedStatement pst = con.prepareStatement(sql); // 调用Connection接口的方法prepareStatement,获取PrepareStatement接口的实现类
		pst.setObject(1, name); // 调用pst对象set方法,设置问号占位符上的参数
		ResultSet rs = pst.executeQuery();// 调用方法,执行SQL,获取结果集
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
			if (rs.getString("UserName").equals(name)) {
				return true;
			}
		}
		rs.close();
		pst.close();
		return false;
	}

	/**
	 * 这个函数用来帮助我们验证是否成功登陆,验证用户名
	 *
	 * @param name
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public static boolean Query(String name, String password) throws SQLException {
		String sql = "SELECT * FROM users WHERE UserName=? "; // 方法中参数,SQL语句中的参数全部采用问号占位符
		PreparedStatement pst = con.prepareStatement(sql); // 调用Connection接口的方法prepareStatement,获取PrepareStatement接口的实现类
		pst.setObject(1, name); // 调用pst对象set方法,设置问号占位符上的参数
		ResultSet rs = pst.executeQuery();// 调用方法,执行SQL,获取结果集
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
			if (rs.getString("UserPsw").equals(password)) {
				return true;
			} else {
				return false;
			}
		}
		rs.close();
		pst.close();
		return false;
	}

	/**
	 * 往用户表中添加用户
	 *
	 * @param name
	 * @param password
	 * @throws SQLException
	 */
	public static void Add(String name, String password,int image ) throws SQLException {
		String sql = "INSERT INTO users (UserName,UserPsw,UserIamge)VALUES(?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		pst.setObject(2, password);
		pst.setObject(3, image);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}

//------------------------好友操作-------------------------------------------------------------
/**
 * 通过用户的id进行添加好友，绑定关系
 * @param User1
 * @param User2
 * @throws SQLException
 */
	public static void AddFriend(int User1,int User2 ) throws SQLException {
		String sql = "INSERT INTO relation  (User1,User2 )VALUES(?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, User1);
		pst.setObject(2, User2);
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
/**
 * 通过传入用户的id返回一个他好友的列表，其中包含了好友的id,名字，密码
 * @param id
 * @return
 * @throws SQLException
 */
	public static ArrayList<User> QueryAllFriends(int id) throws SQLException{
		ArrayList<User> list=new ArrayList();
		String sql = "SELECT * FROM relation  WHERE User1=? OR User2=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
			if (rs.getInt(1)!=id) {
				User  u =new User();
				u.setUsername(QueryGetUserName(rs.getInt(1)));//通过id的到名字
				u.setPasswd(QueryGetUserPsw(rs.getInt(1)));//通过id得到密码
				u.setUserId(rs.getInt(1));
				list.add(u);
			}
			if(rs.getInt(2)!=id){
				User  u =new User();
				u.setUsername(QueryGetUserName(rs.getInt(2)));//通过id的到名字
				u.setPasswd(QueryGetUserPsw(rs.getInt(2)));//通过id得到密码
				u.setUserId(rs.getInt(2));
				list.add(u);
			}
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);
		return list;
	}
/**
 * 查询表中和指定id为好友关系的id号码，并排序利用list进行返回
 * 返回的是一个没有重复id的升序list
 * 好友操作
 * @param id
 * @return
 * @throws SQLException
 */
	public static  ArrayList QueryRalation(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM relation  WHERE User1=? OR User2=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
			if (rs.getInt(1)!=id) {
				list.add(rs.getInt(1));
			}
			if(rs.getInt(2)!=id){
				list.add(rs.getInt(2));
			}
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);
		return list;
	}

/**
 * 刪除list中的重複元素
 * @param list
 */
	public static void removeDuplicateWithOrder(List list){
		    Set set=new HashSet();
		    List newList=new ArrayList();
		    for(Iterator iter=list.iterator(); iter.hasNext();){
		    	Object element=iter.next();
		    	if(set.add(element))
		    		newList.add(element);
		    }
	  		list.clear();
		    list.addAll(newList);
		}
//------------群操作------------------------------------------------------------------------
	public static ArrayList<Message> queryQunMessage(String name)throws SQLException{
		ArrayList<Message> list=new ArrayList();
		String sql = "SELECT * FROM message  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			Message msg=new Message();
			msg.setMesType(MessageType.message_manypeople);
			msg.setSender(rs.getInt(3));
			msg.setCon(rs.getString(5));
			msg.setSendTime(rs.getString(2));
			list.add(msg);
		}

		return list;
	}


	/**
	 * 通过我们群的名字来进行查找返回群中所有用户id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQun(String name) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
				list.add(rs.getInt(2));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(5));
				list.add(rs.getInt(6));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//群聊也还是要去除我们的重复元素
		return list;
	}
	/**
	 * 通过群的id进行查找,返回我们群中用户所有id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQun(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE QunID=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
				list.add(rs.getInt(2));
				list.add(rs.getInt(3));
				list.add(rs.getInt(4));
				list.add(rs.getInt(5));
				list.add(rs.getInt(6));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//群聊也还是要去除我们的重复元素
		return list;
	}
	/**
	 * 通过用户的id得到用户在的所有群的id
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList QueryQunByUserId(int id) throws SQLException {
		ArrayList list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE User1=? OR User2=? OR User3=? OR User4=? OR  User5=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		pst.setObject(3, id);
		pst.setObject(4, id);
		pst.setObject(5, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
				list.add(rs.getInt(1));
		}
		rs.close();
		pst.close();
		list.sort(null);
		removeDuplicateWithOrder(list);//群聊也还是要去除我们的重复元素
		return list;
	}
	/**
	 * 通过传入我们的用户ID，返回一个群名字的队列
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public static  ArrayList<String> QueryQunAllNameByUserId(int id) throws SQLException {
		ArrayList<String> list=new ArrayList();
		String sql = "SELECT * FROM manyrelation  WHERE User1=? OR User2=? OR User3=? OR User4=? OR  User5=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, id);
		pst.setObject(2, id);
		pst.setObject(3, id);
		pst.setObject(4, id);
		pst.setObject(5, id);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
				list.add(rs.getString(7));
		}
		rs.close();
		pst.close();
		removeDuplicateWithOrder(list);//群聊也还是要去除我们的重复元素
		return list;
	}
/**
 * 通过群名字返回对应群的ID
 * @param name
 * @return
 * @throws SQLException
 */
	public static int QueryQunIDByName(String name) throws SQLException {
		int ID=0;
		String sql = "SELECT * FROM manyrelation  WHERE QunName=? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, name);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			// rs.getString("UserName")//通过当前行中指定列的值
			// rs.getString (2)//通过查到当前行中指定数字的列
			ID= rs.getInt(1);
		}
		rs.close();
		pst.close();
		return ID;
	}
/**
 * 将message 传入到我们的message表中
 * @param time发送的时间，格式是XXXX/XX/XX XX-XX 年-月-日-小时-分钟，之后使用split或者是直接显示就可以了
 * @param sender信息的发送者
 * @param receiver信息的接受者
 * @param content信息的内容
 * @throws SQLException
 */
	public static void AddMessage(String time,int sender,int receiver,String content,String MessageType ) throws SQLException {
		String sql = "INSERT INTO message  (time,sender,receiver,content,MessageType)VALUES(?,?,?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, time);
		pst.setObject(2, sender);
		pst.setObject(3, receiver);
		pst.setObject(4, content);
		pst.setObject(5, MessageType);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
	/**
	 * 添加一个群消息，String qunname来标识
	 * @param time
	 * @param sender
	 * @param content
	 * @param qunname
	 * @param MessageType
	 * @throws SQLException
	 */
	public static void AddQunMessage(String time,int sender,String content,String qunname,String MessageType ) throws SQLException {
		String sql = "INSERT INTO message  (time,sender,content,Qunname,MessageType)VALUES(?,?,?,?,?) ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, time);
		pst.setObject(2, sender);
		pst.setObject(3, content);
		pst.setObject(4, qunname);
		pst.setObject(5, MessageType);
		// pst.setObject(1, "shuxin");
		int rs = pst.executeUpdate();
		System.out.println(rs);
		pst.close();
	}
/**
 *  通过两个用户的id,进行查找他们之间的聊天记录，并以一个LIST返回
 * @param User1用户1ID
 * @param User2用户2ID
 * @return
 * @throws SQLException
 */
	public static   ArrayList<Common.Message> GetMessage(int User1,int User2)throws SQLException{
		ArrayList<Common.Message> messages=new ArrayList();
		String sql = "SELECT * FROM message  WHERE sender=?  OR sender=? AND receiver=? OR receiver=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setObject(1, User1);
		pst.setObject(2, User2);
		pst.setObject(3, User1);
		pst.setObject(4, User2);
		ResultSet rs = pst.executeQuery();
		while(rs.next()){
			Common.Message message=new Common.Message();
			message.setId(rs.getInt(1));
			message.setSender(rs.getInt(3));
			message.setGetter(rs.getInt(4));
			message.setSendTime(rs.getString(2));
			message.setCon(rs.getString(5));
			message.setMesType("3");

			messages.add(message);
		}
		return messages;
	}



}
