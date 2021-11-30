package fun.kolowert.c92b.bean;

public class Operator {
	private final int id;
	private final String login;

	private String eMail;
	private String passHash;
	private String role;
	private String salt = "abcdefgh";
	
	public Operator() {
		super();
		id = -1;
		login = "undefined";
		role = "cashier";
		eMail = "undefined";
	}
	
	public Operator(int id, String login) {
		super();
		this.id = id;
		this.login = login;
		role = "cashier";
		eMail = "undefined";
	}
	
	// TODO debugging part of code
	public Operator(int id, String login, String passHash) {
		super();
		this.id = id;
		this.login = login;
		this.passHash = passHash;
		role = "cashier";
		eMail = "undefined";
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
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
