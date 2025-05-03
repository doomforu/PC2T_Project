import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbModel {
	private int last_id;
	private Map<Integer, Student> students = new HashMap<Integer, Student>();
	
	public DbModel() {
		
	}
	public void Add(String field, String firstName, String lastName, LocalDate dateOfBirth) {
		if(field.equals("tli")) {
			students.put(last_id+1, new StudentTLI(last_id+1, firstName, lastName, dateOfBirth));
			last_id++;
		}
		if(field.equals("ibe")) {
			students.put(last_id+1, new StudentIBE(last_id+1, firstName, lastName, dateOfBirth));
			last_id++;
		}
	}
	
	public boolean AddGrade(int id, String subjectId, float grade) {
		if(grade >=1&&grade <=4) {
			students.get(id).NewGrade(subjectId, grade, true);
			return true;
		}
		return false;
	}
	public void Remove(int id) {
		students.remove(id);
	}
	public String GetInfo(int id) {
		return students.get(id).toString().replaceAll(";", "");
	}
	public String GetConvertedName(int id) {
		return students.get(id).ConvertName();
	}
	public String GetSorted() {
		List<StudentTLI> tli = this.GetTLI();
		List<StudentIBE> ibe = this.GetIBE();
		Collections.sort(tli);		
		Collections.sort(ibe);
		StringBuilder sorted = new StringBuilder();
		sorted.append("Studenti TLI:\n------------");
		for(Student s : tli) {
			sorted.append("\n"+s.toString().replaceAll(";", ""));
			
		}
		sorted.append("\n\nStudenti IBE:\n------------");
		for(Student s : ibe) {
			sorted.append("\n"+s.toString().replaceAll(";", ""));
		}
		
		return sorted.toString();
	}
	public float AvgTLI() {
		int count = 0;
		float total = 0;
		List<StudentTLI> tli = this.GetTLI();
		for(StudentTLI s : tli) {
				count++;
				total += s.AvgGrade();
			
		}
		return total/count;
	}
	public float AvgIBE() {
		int count = 0;
		float total = 0;
		List<StudentIBE> ibe = this.GetIBE();
		for(StudentIBE s : ibe) {
				count++;
				total += s.AvgGrade();
			
		}
		return total/count;
	}
	public int CountTLI() {
		int count = 0;
		for(Student s : students.values()) 
			if(s.getClass() == StudentTLI.class) 
				count++;
		return count;		
	}
	public int CountIBE() {
		int count = 0;
		for(Student s : students.values()) 
			if(s.getClass() == StudentIBE.class) 
				count++;
		return count;
	}
	
	private List<StudentTLI> GetTLI(){
		List<StudentTLI> tli = new ArrayList<StudentTLI>();
		for(Student s : students.values())
			if(s.getClass() == StudentTLI.class) 
				tli.add((StudentTLI) s);
		return tli;	
	}
	private List<StudentIBE> GetIBE(){
		List<StudentIBE> ibe = new ArrayList<StudentIBE>();
		for(Student s : students.values())
			if(s.getClass() == StudentIBE.class) 
				ibe.add((StudentIBE) s);
		return ibe;	
	}
	
	public void SaveToFile(String filename, int id) {
		try {
			FileWriter fw = new FileWriter(filename);
			StringBuilder sb = new StringBuilder();
			if(students.get(id).getClass()==StudentTLI.class)
				sb.append("tli;");
			if(students.get(id).getClass()==StudentIBE.class)
				sb.append("ibe;");
			sb.append(students.get(id).toString());
			for(Map.Entry<String, Float> e : students.get(id).GetGrades().entrySet())
				sb.append(String.format("%s;%.1f;",e.getKey(), e.getValue()));
			fw.write(sb.toString());
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Cannot create file");
		}
	}
	public void LoadFromFile(String filename) {
		FileReader fr=null;
		BufferedReader br=null;
		try {
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			String line=br.readLine();
			line = line.replaceAll("\\s", "");
			String separator = ";";
			String[] buffer = line.split(separator);
			int id = Integer.parseInt(buffer[1]);
			if(buffer[0].equals("tli")) 
				students.put(id, new StudentTLI(id,buffer[2], buffer[3],LocalDate.parse(buffer[4])));
			if(buffer[0].equals("ibe")) 
				students.put(id, new StudentIBE(id,buffer[2], buffer[3],LocalDate.parse(buffer[4])));	
			if(buffer.length>6) {
				int i = 6;
				while(buffer.length>i) {				
					this.AddGrade(id, buffer[i], Float.parseFloat(buffer[i+1]));
					i+=2;
				}
			}
		}
		catch (IOException e) {
			System.out.println("Cannot open file");

		} 
		catch (NumberFormatException e) {
			System.out.println("Incorrect data format in file");
		} 
		finally
		{
			try
			{	if (br!=null)
				{
					br.close();
					fr.close();
				}
			}
			catch (IOException e) {
				System.out.println("Cannot close file");
			} 
		}
	}
}
