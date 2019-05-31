package obj;

public class User {
	private String username;
	private String userType;
	private String password;
	private String mailBox;
	public User() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public User(String username, String userType, String password, String mailBox) {
		super();
		this.username = username;
		this.userType = userType;
		this.password = password;
		this.mailBox = mailBox;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailBox() {
		return mailBox;
	}
	public void setMailBox(String mailBox) {
		this.mailBox = mailBox;
	}
	
	
}
