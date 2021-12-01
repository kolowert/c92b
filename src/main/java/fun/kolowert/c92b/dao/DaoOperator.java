package fun.kolowert.c92b.dao;

import java.util.Arrays;
import java.util.List;

import fun.kolowert.c92b.bean.Operator;

public class DaoOperator {

	private static DaoOperator daoOperator;

	// TODO rewrite this stubs
	private List<Operator> operators;

	private DaoOperator() {
		// This is stub
		Operator[] preOperators = { 
				new Operator(123, "Arnold", "cashier", "rcjjfw=="),
				new Operator(234, "Bruce", "senior cashier", "kaj+Rw=="),
				new Operator(787, "Silvester", "cashier", "TY28Lg=="),
				new Operator(777, "James", "cashier", "fnORkw=="), 
				new Operator(699, "Chack", "expert", "4p3twQ=="),
				new Operator(999, "Admin", "expert", "PbAzIw=="), };
		operators = Arrays.asList(preOperators);
	}

	public static DaoOperator getInstance() {
		if (daoOperator != null) {
			return daoOperator;
		}
		return new DaoOperator();
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

	public boolean putOperator(Operator operator) {
		// TODO rewrite the stub
		operators.add(operator);
		return true;
	}

	public List<Operator> getOperators() {
		return operators;

	}
}
