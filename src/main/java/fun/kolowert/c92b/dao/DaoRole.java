package fun.kolowert.c92b.dao;

import java.util.Arrays;
import java.util.List;

public class DaoRole {
	
	private static DaoRole INSTANCE;

	// TODO rewrite this stubs
	private List<String> roles;

	private DaoRole() {
		// This is stub
		String[] preRoles = { "cashier", "senior cashier", "expert" };
		roles = Arrays.asList(preRoles);
	}
	
	public static DaoRole getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DaoRole();
		}
		return INSTANCE;
	}
	
	public List<String> getRoles() {
		return roles;
	}
}
