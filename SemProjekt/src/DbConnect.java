import java.sql.*;
import java.time.LocalDate;
import java.util.Map;

public class DbConnect {
	private static Connection conn = null;
	
	public static void Load(DbModel db) throws SQLException {
		conn = DriverManager.getConnection("jdbc:sqlite:unidb.db");		
		
		String query = "SELECT * FROM Students";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			db.Add(rs.getString("field").toLowerCase(), rs.getString("FirstName"), rs.getString("LastName"),
					LocalDate.parse(rs.getString("DateOfBirth")));
		}
		query = "SELECT seq FROM sqlite_sequence WHERE name='Students'";
		st = conn.createStatement();
		rs = st.executeQuery(query);
		db.SetLastId(rs.getInt("seq"));	
		query = "SELECT * FROM Grades";
		
		st = conn.createStatement();
		rs = st.executeQuery(query);
		while(rs.next()) {
				db.AddGrade(rs.getInt("StudentId"), rs.getString("SubjectId"), rs.getFloat("Grade"));
		}
		conn.close();		
	}
	
	public static void Save(DbModel db) throws SQLException{
		conn = DriverManager.getConnection("jdbc:sqlite:unidb.db");
		String query ="DELETE FROM Students; DELETE FROM Grades";
		Statement st = conn.createStatement();
		st.executeUpdate(query);	
		
		query = "INSERT INTO Students(Id,Field,FirstName,LastName,DateOfBirth) VALUES(?,?,?,?,?)";
		PreparedStatement prst = conn.prepareStatement(query);
		for(Student s : db.GetTLI()) {
			prst.setInt(1, s.Id);
			prst.setString(2, "tli");
			prst.setString(3, s.FirstName);
			prst.setString(4, s.LastName);
			prst.setString(5, s.DateOfBirth.toString());
			prst.executeUpdate();
		}
		for(Student s : db.GetIBE()) {
			prst.setInt(1, s.Id);
			prst.setString(2, "ibe");
			prst.setString(3, s.FirstName);
			prst.setString(4, s.LastName);
			prst.setString(5, s.DateOfBirth.toString());
			prst.executeUpdate();
		}
		query = "INSERT INTO Grades(StudentId,SubjectId,Grade) VALUES(?,?,?)";
		prst = conn.prepareStatement(query);
		for(Student s : db.students.values()) {
			for(Map.Entry<String, Float> e : s.GetGrades().entrySet()) {
				prst.setInt(1, s.Id);
				prst.setString(2, e.getKey());
				prst.setFloat(3, e.getValue());
				prst.executeUpdate();
			}
		}
		conn.close();
	}
}
