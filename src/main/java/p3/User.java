package p3;

public class User {
	String userid;
	String password;
	int admin_type;
	public User(String userid, String password, int admin_type) {
		super();
		this.userid = userid;
		this.password = password;
		this.admin_type = admin_type;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAdmin_type() {
		return admin_type;
	}
	public void setAdmin_type(int admin_type) {
		this.admin_type = admin_type;
	}
	public int verifyLogin(String user, String pass)
	{
		if(user.equals( getUserid()) && pass.equals(getPassword()))
		{
			return getAdmin_type();
		}
		return 0;
	}
}
