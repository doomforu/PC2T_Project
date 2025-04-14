import java.time.LocalDate;

public class Main {

	public static void main(String[] args) {
		
		Student[] studenti = new Student[2];
		
		studenti[0] = new StudentTLI(256737, "Filip", "Hojer", LocalDate.parse("2002-09-10"));
		studenti[1] = new StudentIBE(256739, "Petr", "Mrazek", LocalDate.parse("1992-02-14"));
		System.out.println(studenti[0]);
		System.out.println(studenti[1]);
		System.out.println(studenti[0].ConvertName());
		System.out.println(studenti[1].ConvertName());
	}

}
