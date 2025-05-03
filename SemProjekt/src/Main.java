import java.sql.SQLException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		CLIHandler cli = new CLIHandler();
		DbModel db = new DbModel();
		Scanner sc = new Scanner(System.in);
		try {
			DbConnect.Load(db);
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. pridani noveho studenta");
			System.out.println("2 .. zadani znamky");
			System.out.println("3 .. propusteni studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. vypis vsech studentu podle abecedy");
			System.out.println("6 .. spusteni dovednosti studenta");
			System.out.println("7 .. vypis prumeru v oborech");
			System.out.println("8 .. vypis poctu studentu");
			System.out.println("9 .. ulozeni studenta do souboru");
			System.out.println("10 .. nacteni studenta ze souboru");
			System.out.println("11 .. konec");
			int opt;
			while(!sc.hasNextInt()) {
				System.out.println("Zadejte cele cislo");
				sc.next();
			}
			opt = sc.nextInt();
			switch(opt)
			{
			case 1:
				cli.AddStudent(db);
				break;
			case 2:
				cli.AddGrade(db);
				break;
			case 3:
				cli.RemoveStudent(db);
				break;
			case 4:
				cli.PrintStudentInfo(db);
				break;
			case 5:
				cli.PrintAllStudents(db);
				break;
			case 6:
				cli.PrintConvertedName(db);
				break;
			case 7:
				cli.PrintAvgGrades(db);
				break;
			case 8:
				cli.PrintStudentCount(db);
				break;
			case 9:
				cli.SaveToFile(db);
				break;
			case 10:
				cli.LoadFromFile(db);
				break;
			case 11:
				run = false;
				break;
			}
		}
		try {
			DbConnect.Save(db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
