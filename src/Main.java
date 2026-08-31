import java.io.File;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String txt = Files.readString(Path.of(args[0]));
        HashMap<Character, Integer> map = FrequencyMap.buildFrequencyMap(txt);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        Node root = HuffmanTree.buildTree(queue);
        HashMap<Character, String> codeTable = new HashMap<>();
        CodeGenerator.generateCodes(root, "", codeTable);

        String encoded = Encoder.encode(txt, codeTable);
        FileCompressor.compress(encoded, args[1],map);
        int originalSize = txt.getBytes().length;
        File file = new File(args[1]);
        int compressedSize = (int) file.length();

        System.out.println("original size: " + originalSize + " bytes");
        System.out.println("Compressed size: " + compressedSize + " bytes");
        double percentSaved = (1-(double) compressedSize/originalSize)*100;
        System.out.println("Space saved: " + percentSaved +"%");

        String decompressed = FileCompressor.decompress(args[1]);
        System.out.println(decompressed);
    }
}