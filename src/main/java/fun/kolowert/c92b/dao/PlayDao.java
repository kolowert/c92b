package fun.kolowert.c92b.dao;

import java.util.List;

import fun.kolowert.c92b.bean.Operator;

public class PlayDao {

	public static void main(String[] args) {

		// receive from form
		String inputName = "someName";
		String inputRole = "role-pole";
		String inputPassword = "password";

		// create new operator in database
		DaoOperator daoOperator = DaoOperator.getInstance();
		
		List<Operator> opers = daoOperator.getOperators();
		System.out.println(opers);
		
		daoOperator.createNewInDataBase(inputName, inputRole, inputPassword);
		inputPassword = "erased";

	}

}
