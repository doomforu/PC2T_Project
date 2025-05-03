import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CLIHandler {
	public CLIHandler() {
		
	}
	Scanner sc = new Scanner(System.in);
	public void AddStudent(DbModel db) {
		System.out.println("Zadejte obor (tli/ibe), jmeno, prijmeni a rok narozeni (YYYY-MM-DD)");
		String field = sc.next();
		String firstName = sc.next();
		String lastName = sc.next();
		LocalDate dob = null;
		boolean valid = false;
		while(!valid){
			valid = true;
			try {
				dob = LocalDate.parse(sc.next());
			}
			catch(DateTimeParseException e) {
				sc.next();
				valid = false;
				System.out.println("Nespravny format, datum zadavejte ve formatu YYYY-MM-DD vcetne pomlcek");
			}
		}
		if(!db.Add(field, firstName, lastName, dob))
			System.out.println("Nespravny format oboru");
	}
	public void AddGrade(DbModel db) {
		System.out.println("Zadejte id studenta, zkratku predmetu a znamku");
		while(!sc.hasNextInt()) {
			System.out.println("Zadejte cele cislo");
			sc.next();
		}
		int id = sc.nextInt();
		String subjectId = sc.next();
		while(!sc.hasNextFloat()) {
			System.out.println("Zadejte cislo");
			sc.next();
		}
		float grade = sc.nextFloat();
		try {
		if(!db.AddGrade(id,subjectId, grade))
			System.out.println("Zadejte znamku v rozsahu 1-4");
		}
		catch(NullPointerException e) {
			System.out.println("Student se zadanym id nenalezen");
		}
	}
	public void RemoveStudent(DbModel db) {
		System.out.println("Zadejte id studenta, ktereho chcete odebrat");
		while(!sc.hasNextInt()) {
			System.out.println("Zadejte cele cislo");
			sc.next();
		}
		int id = sc.nextInt();
		try {
			db.Remove(id);
		}
		catch(NullPointerException e) {
			System.out.println("Student se zadanym id nenalezen");
		}
	}
	public void PrintStudentInfo(DbModel db) {
		System.out.println("Zadejte id studenta");
		while(!sc.hasNextInt()) {
			System.out.println("Zadejte cele cislo");
			sc.next();
		}
		int id = sc.nextInt();
		try {
			System.out.println(db.GetInfo(id));
		}
		catch(NullPointerException e) {
			System.out.println("Student se zadanym id nenalezen");
		}
	}
	public void PrintAllStudents(DbModel db) {
		System.out.println(db.GetSorted());
	}
	public void PrintConvertedName(DbModel db) {
		System.out.println("Zadejte id studenta");
		while(!sc.hasNextInt()) {
			System.out.println("Zadejte cele cislo");
			sc.next();
		}
		int id = sc.nextInt();
		try {
			System.out.println(db.GetConvertedName(id));
		}
		catch(NullPointerException e) {
			System.out.println("Student se zadanym id nenalezen");
		}
	}
	public void PrintAvgGrades(DbModel db) {
		System.out.println(String.format("Obecny prumer studentu TLI:\t%.2f\nObecny prumer studentu IBE:\t%.2f",
				db.AvgTLI(),db.AvgIBE()));
	}
	public void PrintStudentCount(DbModel db) {
		System.out.println(String.format("Pocet zapsanych studentu TLI:\t%d\nPocet zapsanych studentu IBE:\t%d",
				db.CountTLI(),db.CountIBE()));
	}
	public void SaveToFile(DbModel db) {
		System.out.println("Zadejte id studenta");
		while(!sc.hasNextInt()) {
			System.out.println("Zadejte cele cislo");
			sc.next();
		}
		int id = sc.nextInt();
		System.out.println("Zadejte nazev souboru");
		String filename =sc.next();
		try {
			db.SaveToFile(filename, id);
		}
		catch(NullPointerException e) {
			System.out.println("Student se zadanym id nenalezen");
		}
	}
	public void LoadFromFile(DbModel db) {
		System.out.println("Zadejte nazev souboru");
		String filename =sc.next();
		db.LoadFromFile(filename);
	}
	
}
