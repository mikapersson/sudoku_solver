package sudokuapp;

public class TestClass {

	public static void main(String[] args) {
		String s = "1jk2";
		
		if ((!s.equals("1")
				|| !s.equals("2") || !s.equals("3") || !s.equals("4") || !s.equals("5")
				|| !s.equals("6") || !s.equals("7") || !s.equals("8") || !s.equals("9"))) {
			System.out.println("Uttrycket fungerar");
			System.out.println(s);
		} else {
			System.out.println("Fungerar inte");
		}
	}

}
