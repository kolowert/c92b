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
		Operator[] preOperators = { new Operator(123, "Arnold"), 
				new Operator(234, "Bruce"),
				new Operator(787, "Silvester"),
				new Operator(777, "James"),
				new Operator(699, "Chack") };
		operators =  Arrays.asList(preOperators);
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
