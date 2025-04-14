import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class StudentIBE extends Student{
	public StudentIBE(int id, String firstName, String lastName, LocalDate dateOfBirth) {
		super(id, firstName, lastName,dateOfBirth);
	}
	public String ConvertName() {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(FirstName.getBytes("UTF-8"));
			StringBuilder result = new StringBuilder();
			
			for(byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if(hex.length() == 1)
					result.append('0');
				result.append(hex);
			}
			return result.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
