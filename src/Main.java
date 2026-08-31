import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String txt = Files.readString(Path.of("input.txt"));
        HashMap<Character, Integer> map = FrequencyMap.buildFrequencyMap(txt);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.frequency));
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }

        Node root = HuffmanTree.buildTree(queue);
        HashMap<Character, String> codeTable = new HashMap<>();
        CodeGenerator.generateCodes(root, "", codeTable);

        String encoded = Encoder.encode(txt, codeTable);
        FileCompressor.compress(encoded, "compressed.bin",map);

        String decompressed = FileCompressor.decompress("compressed.bin");
        System.out.println(decompressed);
    }
}