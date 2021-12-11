package fun.kolowert.c92b.utility;

import java.util.Arrays;
import java.util.List;

public class Role {
	
	public static List<String> getAll() {
		String[] roles = {"cashier", "senior cashier", "expert"};
		return Arrays.asList(roles);
	}
	
}
