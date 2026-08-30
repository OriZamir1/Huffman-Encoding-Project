import java.util.PriorityQueue;

public class HuffmanTree {
    public static Node buildTree(PriorityQueue<Node> queue) {
        while (queue.size()>1){
            Node left = queue.poll();
            Node right = queue.poll();
            Node node = new Node('\0',left.frequency + right.frequency);
            node.left = left;
            node.right = right;
            queue.add(node);
        }
        return queue.poll();
    }
}
