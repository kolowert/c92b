package fun.kolowert.c92b.bean;

public class Operator {
	private int id;
	private String login;

	private String passHash;
	private String role;
	private String salt = "abcdefgh";

	public Operator() {
		id = -1;
		login = "undefined";
		role = "cashier";
		passHash = "ouefhlih6854364v4r9646r464r6b46rtyjyu6kl54i68m435k4uio3l87um3gn4t68k7ui6l8ui4m687myu";
	}

	public Operator(int id, String login) {
		this.id = id;
		this.login = login;
		role = "cashier";
	}

	// TODO debugging part of code
	public Operator(int id, String login, String role, String passHash) {
		this.id = id;
		this.login = login;
		this.role = role;
		this.passHash = passHash;
	}

	// TODO debugging part of code
	public Operator(String login, String role, String passHash, String salt) {
		java.util.Random rnd = new java.util.Random();
		this.id = rnd.nextInt(899) + 100;
		this.login = login;
		this.role = role;
		this.passHash = passHash;
		this.salt = salt;
	}
	
	public static Operator getNullOperator() {
		return new Operator();
	}
	
	@Override
	public String toString() {
		return "Operator [id:" + id + ", login:" + login + ", passHash:" + passHash + ", role:" + role 
				+ ", salt:" + salt + "]";
	}

	public String briefInfo() {
		return login + " -- " + role + " -- " + "id:" + id;
	}

	public String getPassHash() {
		return passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
}
