package p3;
/**
 * 
 * 
 * A class for the users of this app
 */
public class Admin extends User{
	String name;
	String email;
	/**
	 * 
	 * @param userid  user name
	 * @param password user password
	 * @param admin_type  the user frame autority
	 * @param name data about the user
	 * @param email contact data about the user
	 */
	public Admin(String userid, String password, int admin_type, String name, String email) {
		super(userid, password, admin_type);
		this.name = name;
		this.email = email;
	}
	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 * @param name name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
