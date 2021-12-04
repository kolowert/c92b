package fun.kolowert.c92b.dao;

import java.util.Arrays;
import java.util.List;

public class DaoRole {
	
	private static DaoRole daoRole;

	// TODO rewrite this stubs
	private List<String> roles;

	private DaoRole() {
		// This is stub
		String[] preRoles = { "cashier", "senior cashier", "expert" };
		roles = Arrays.asList(preRoles);
	}
	
	public static DaoRole getInstance() {
		if (daoRole != null) {
			return daoRole;
		}
		return new DaoRole();
	}
	
	public List<String> getRoles() {
		return roles;
	}
}
