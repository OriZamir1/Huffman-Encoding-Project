import java.util.HashMap;

public class Encoder {
    public static String encode(String text, HashMap<Character, String> codeTable) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(codeTable.get(text.charAt(i)));
        }
        return result.toString();
    }
}
