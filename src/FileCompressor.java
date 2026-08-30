import java.io.*;
import java.util.*;

public class FileCompressor {
    public static void compress(String encoded, String outputPath, HashMap<Character,Integer> hashMap) throws IOException {
        FileOutputStream fos = new FileOutputStream(outputPath);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(hashMap.size());
        dos.writeInt(encoded.length());
        for (Map.Entry<Character,Integer> entry : hashMap.entrySet()){
            dos.writeChar(entry.getKey());
            dos.writeInt(entry.getValue());
        }
        BitSet bitSet = new BitSet();
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '1') {
                bitSet.set(i);
            }
        }
        byte[] bytes = bitSet.toByteArray();

        dos.write(bytes);
        dos.close();
    }

    public static String decompress(String inputPath) throws IOException {
        FileInputStream fis = new FileInputStream(inputPath);
        DataInputStream dis = new DataInputStream(fis);
        int size = dis.readInt();
        int originalLength = dis.readInt();
        HashMap<Character,Integer> hashMap = new HashMap<Character,Integer>();

        for (int i = 0; i<size ;i++){
            hashMap.put(dis.readChar(),dis.readInt());
        }
        byte[] bytes = fis.readAllBytes();
        fis.close();


        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));
        for (Map.Entry<Character, Integer> entry : hashMap.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }
        Node root = HuffmanTree.buildTree(queue);
        BitSet bitSet = BitSet.valueOf(bytes);

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < originalLength; i++) {
            if (bitSet.get(i)){
                result.append('1');
            } else{
                result.append('0');
            }

        }
        return Decoder.decode(result.toString(),root);
    }
}