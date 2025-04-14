import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StudentTLI extends Student {
	
	public StudentTLI(int id, String firstName, String lastName, LocalDate dateOfBirth) {
		super(id, firstName, lastName,dateOfBirth);
	}

	public String ConvertName() {
		if(morseDictionary.isEmpty())
			MapMorseCode();
		StringBuilder result = new StringBuilder();
		for(char c : FirstName.toLowerCase().toCharArray()) {
			result.append(morseDictionary.get(c) );
			result.append(" ");
		}
		result.append("\t");
		for(char c : LastName.toLowerCase().toCharArray()) {
			result.append(morseDictionary.get(c));
		}
		return result.toString();
	}
	
	private final Map<Character, String> morseDictionary = new HashMap<Character, String>();
	private final String[] morseCode = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", 
            ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.","...", "-", "..-", "...-",
            ".--", "-..-", "-.--", "--.."};
	private final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	private void MapMorseCode() {
		int i =0;
		for (char c : alphabet) {
			morseDictionary.put(c, morseCode[i]);
			i++;
		}
	}
}
