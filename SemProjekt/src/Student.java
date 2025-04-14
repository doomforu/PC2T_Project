import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Student {
	
	private int Id;
	protected String FirstName;
	protected String LastName;
	private LocalDate DateOfBirth;
	private Map<String, Float> Grades = new HashMap<String, Float>();
	
	public Student() {
	}
	public Student(int id, String firstName, String lastName, LocalDate dateOfBirth) {
		Id = id;
		FirstName = firstName;
		LastName = lastName;
		DateOfBirth = dateOfBirth;
	}
	
	public abstract String ConvertName();
	
	@Override
	public String toString() {
		return String.format("%d, %s %s, %tF",Id,FirstName, LastName, DateOfBirth);
	}
}
