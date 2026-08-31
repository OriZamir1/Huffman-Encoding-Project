public class Decoder {
    public static String decode(String encoded, Node root) {
        if (root == null){
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node current = root;

        if (current.right == null && current.left == null){
            for (int i = 0; i<encoded.length();i++){
                result.append(current.character);
            }
            return result.toString();
        }

        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '0'){
                current = current.left;
            } else{
                current = current.right;
            }
            if (current.right == null && current.left == null){
                result.append(current.character);
                current = root;
            }
        }
        return result.toString();
    }
}
