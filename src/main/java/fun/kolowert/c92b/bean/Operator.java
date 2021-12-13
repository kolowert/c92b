package fun.kolowert.c92b.bean;

public class Operator {
	
	private static String DEFAULT_SALT = "tjuk5i8454i38u3noehi6534494r6rb6tj";
	private static String DEFAULT_PASSHASH = "oehi6534494r6rb6tjuk5i8454i38u3nt87ilu467y";
	
	private int id;
	private String login;
	private String passHash;
	private String role;
	private String salt;

	public Operator() {
		id = -1;
		login = "undefined";
		role = "cashier";
		passHash = DEFAULT_PASSHASH;
		salt = DEFAULT_SALT;
	}

	public Operator(int id, String login, String role) {
		this.id = id;
		this.login = login;
		this.role = role;
	}

	public Operator(int id, String login, String passHash, String role, String salt) {
		this.id = id;
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
		return "Operator [id:" + id + ", login:" + login + ", passHash:" + passHash + ", role:" + role + ", salt:"
				+ salt + "]";
	}

	public String briefInfo() {
		return login + " | " + role + " | " + "id:" + id;
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
