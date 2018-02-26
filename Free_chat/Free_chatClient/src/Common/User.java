/**
 * 这是用户信息类
 */
package Common;

public class User implements java.io.Serializable {

	private int userId;
	private String username;
	private String passwd;
	private boolean ifregiste;

	public String getUsername() {
		return username;
	}
	public boolean isIfregiste() {
		return ifregiste;
	}
	public void setIfregiste(boolean ifregiste) {
		this.ifregiste = ifregiste;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
