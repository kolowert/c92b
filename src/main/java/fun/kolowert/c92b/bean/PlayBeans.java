package fun.kolowert.c92b.bean;

public class PlayBeans {

	public static void main(String[] args) {

		for (int i = 0; i < 7; i++) {
			MeasureUnit m = MeasureUnit.values()[i];
			System.out.println("" + i + " : " +  m);
		}
		
		System.out.println("~ ~ ~ " + MeasureUnit.values().length);
		
		MeasureUnit m2 = MeasureUnit.kilogram;
		int ord = MeasureUnit.valueOf(m2.toString()).ordinal();
		System.out.println("" + m2 + " ord: " + ord);
	}
}
