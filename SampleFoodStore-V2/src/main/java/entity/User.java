package entity;

public class User {

	private String username;
	private String password;
	private int vip;

	public User() {
		
	}
	
	public User(String username, String password, int vip) {
		this.username = username;
		this.password = password;
		this.vip = vip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getVip() {
		return vip;
	}

	public void setVip(int vip) {
		this.vip = vip;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", vip=" + vip + "]";
	}

}
