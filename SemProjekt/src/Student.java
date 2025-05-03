import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Student implements Comparable<Student>{
	
	protected int Id;
	protected String FirstName;
	protected String LastName;
	protected LocalDate DateOfBirth;
	protected Map<String, Float> Grades = new HashMap<String, Float>();
	
	public Student() {
	}
	public Student(int id, String firstName, String lastName, LocalDate dateOfBirth) {
		Id = id;
		FirstName = firstName;
		LastName = lastName;
		DateOfBirth = dateOfBirth;
	}
	public boolean NewGrade(String id, float grade, boolean overwrite) {
		if(Grades.containsKey(id)&&!overwrite)
			return false;
		if(Grades.containsKey(id)&&overwrite) {
			Grades.put(id,  grade);
			return true;
		}
		Grades.put(id,  grade);
		return true;
	}
	public abstract String ConvertName();
	public float AvgGrade() {
		int count = 0;
		float total = 0;
		for(float g : Grades.values()) {
			count++;
			total +=g;
		}
		return total/count;
	}
	public Map<String, Float> GetGrades(){
		return Grades;
	}
	@Override
	public String toString() {
		return String.format("%d;\t%s;\t%s;\t%tF;\t%.2f;",Id,FirstName, LastName, DateOfBirth, this.AvgGrade());
	}
	@Override
	public int compareTo(Student other) {
		return this.LastName.compareTo(other.LastName);
	}
}
