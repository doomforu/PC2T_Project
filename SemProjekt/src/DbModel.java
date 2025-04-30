import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.SupportedValuesAttribute;

public class DbModel {
	private int last_id;
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	
	public DbModel() {
		
	}
	public void Add(boolean field, String firstName, String lastName, LocalDate dateOfBirth) {
		if(field) {
			students.put(last_id+1, new StudentTLI(last_id+1, firstName, lastName, dateOfBirth));
			last_id++;
		}
		if(!field) {
			students.put(last_id+1, new StudentIBE(last_id+1, firstName, lastName, dateOfBirth));
			last_id++;
		}
	}
	
	public void AddGrade(int id, String subjectId, float grade) {
		students.get(id).NewGrade(subjectId, grade, true);
	}
	public void Remove(int id) {
		students.remove(id);
	}
	public String GetInfo(int id) {
		return students.get(id).toString();
	}
	public String GetConvertedName(int id) {
		return students.get(id).ConvertName();
	}
	public void PrintSorted() {
		List<StudentTLI> tli = new ArrayList<StudentTLI>();
		List<StudentIBE> ibe = new ArrayList<StudentIBE>();

		for(Student s : students.values()) {
			if(s.getClass() == StudentTLI.class) {
				tli.add((StudentTLI) s);
			}
			if(s.getClass()==StudentIBE.class) {
				ibe.add((StudentIBE) s);
			}
		}
		Collections.sort(tli);		
		Collections.sort(ibe);
		
		for(Student s : tli) {
			System.out.println(s);
		}
		for(Student s : ibe) {
			System.out.println(s);
		}
	}
}
