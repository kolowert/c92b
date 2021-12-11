package fun.kolowert.c92b.bean;

public class PlayBeans {

	public static void main(String[] args) {

		for (int i = 0; i < 8; i++) {
			MeasureUnit m = MeasureUnit.values()[i];
			System.out.println("" + i + " : " +  m);
		}
		
		System.out.println("~ ~ ~" + MeasureUnit.values().length);
		
		
	}
}
