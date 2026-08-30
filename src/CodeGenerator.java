import java.util.HashMap;

public class CodeGenerator {
    public static void generateCodes(Node node, String code, HashMap<Character, String> codeTable) {
        if (node == null){
            return;
        }
        if (node.left == null && node.right == null){
            codeTable.put(node.character,code);
        } else {
            generateCodes(node.left,code + "0",codeTable);
            generateCodes(node.right,code + "1" , codeTable);
        }
    }
}
