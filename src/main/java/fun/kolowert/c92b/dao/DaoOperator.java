package fun.kolowert.c92b.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fun.kolowert.c92b.bean.Operator;
import fun.kolowert.c92b.utility.PasswordUtils;

public final class DaoOperator {

	private static DaoOperator INSTANCE;

	// TODO rewrite this stubs
	private List<Operator> operators = new ArrayList<Operator>();

	private DaoOperator() {
		// This is stub
		Operator[] preOperators = { new Operator(123, "Arnold", "cashier", "rcjjf/lKuvNapHjHisBrFQ=="),
				new Operator(234, "Bruce", "senior cashier", "kaj+RzVqa1L52KPqRtHouw=="),
				new Operator(787, "Silvester", "cashier", "TY28LhZVRK8meBZKFYlbWA=="),
				new Operator(777, "James", "cashier", "fnORkwQN/L+qSG9hcS68gQ=="),
				new Operator(699, "Chack", "expert", "4p3twfMVnNVenFltKeH75A=="),
				new Operator(999, "Admin", "expert", "PbAzIz6JYQ9kWv31SrQKlA==") };

		for (Operator oper : preOperators) {
			operators.add(oper);
		}
	}

	public static DaoOperator getInstance() {

		System.out.println("DaoOperator#getInstance >> daoOperator: " + INSTANCE); // |||||||||||||||||||||||||||

		if (INSTANCE == null) {
			INSTANCE = new DaoOperator();
			System.out.println("DaoOperator#getInstance >> ~~~ NEW ~~~"); // |||||||||||||||||||||||||||
		}
		return INSTANCE;
	}

	public Operator getOperatorById(int id) {
		Operator result = null;
		// TODO rewrite the stub
		for (Operator operator : operators) {
			if (operator.getId() == id) {
				return operator;
			}
		}
		return result;
	}

	public void createNewInDataBase(String login, String role, String password) {
		// TODO master the stub
		String salt = PasswordUtils.generateSalt(16);
		System.out.println("DaoOperator# >> salt " + salt); // ||||||||||||||||||||||||||||||||||||||
		String passHass = PasswordUtils.hashTextPassword(password, salt).get();
		System.out.println("DaoOperator# >> passHass " + passHass); // ||||||||||||||||||||||||||||||||||||||
		Operator entrant = new Operator(login, role, passHass, salt);
		System.out.println("DaoOperator# >> entrant " + entrant); // ||||||||||||||||||||||||||||||||||||||
		operators.add(entrant);
		System.out.println("DaoOperator# >> after adding"); // ||||||||||||||||||||||||||||||||||||||
	}

	public boolean putOperator(Operator operator) {
		// TODO rewrite the stub
		operators.add(operator);
		return true;
	}

	public void deleteOperator(int id) {
		// TODO rewrite the stub
		Operator vanishing = getOperatorById(id);
		if (vanishing != null) {
			operators.remove(vanishing);
		}
	}

	public List<Operator> getOperators() {
		// TODO rewrite the stub
		operators.sort(new Comparator<Operator>() {
			@Override
			public int compare(Operator o1, Operator o2) {
				return o1.getLogin().compareTo(o2.getLogin());
			}
		});
		return operators;
	}
}
