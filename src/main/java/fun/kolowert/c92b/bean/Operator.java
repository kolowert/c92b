package fun.kolowert.c92b.bean;

public class Operator {
	private final int id;
	private final String login;

	private String passHash;
	private String role;
	private String salt = "abcdefgh";

	public Operator() {
		super();
		id = -1;
		login = "undefined";
		role = "cashier";
	}

	public Operator(int id, String login) {
		super();
		this.id = id;
		this.login = login;
		role = "cashier";
	}

	// TODO debugging part of code
	public Operator(int id, String login, String role, String passHash) {
		super();
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

	public String getLogin() {
		return login;
	}
}
