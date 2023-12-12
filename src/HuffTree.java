import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class HuffTreeNode implements Comparable<HuffTreeNode>{
    char data;
    int freq;
    HuffTreeNode left;
    HuffTreeNode right;

    public HuffTreeNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(HuffTreeNode other) {
        int frequencyComparison = Integer.compare(this.freq, other.freq);

        if (frequencyComparison == 0) {
            return Character.compare(this.data, other.data);
        }

        return frequencyComparison;
    }

    public String toString() {
        String toString = data + "=" + freq;

        return toString;
    }
}
public class HuffTree {
    String to_compress;
    HuffTreeNode root;

    public HuffTree(String to_compress) {
        this.to_compress = to_compress;
        root = null;
    }

    public Map<Character, Integer> getCounts() {
        Map<Character, Integer> charFreq = new HashMap<>();

        for (char character : to_compress.toCharArray()) {
            Integer frequency = charFreq.get(character);
            charFreq.put(character, frequency != null ? frequency + 1 : 1);
        }

        return charFreq;
    }

    public Queue<HuffTreeNode> sortedCounts(Map<Character, Integer> charFreq) {
        Queue<HuffTreeNode> q = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();

            q.add(new HuffTreeNode(character, frequency));
        }

        return q;
    }

    public HuffTreeNode makeTree() {
        Map<Character, Integer> charFreq = getCounts();

        Queue<HuffTreeNode> queue = sortedCounts(charFreq);

        while (queue.size() > 1) {
            HuffTreeNode left = queue.poll();
            HuffTreeNode right = queue.poll();

            HuffTreeNode internalNode = new HuffTreeNode('\0', left.freq + right.freq);

            internalNode.left = left;
            internalNode.right = right;

            queue.add(internalNode);
        }

        return queue.poll();
    }

    public Map<Character, String> getEncoder() {
        HuffTreeNode root = makeTree();

        Map<Character, String> encoderMap = new HashMap<>();

        buildEncoderMap(root, "", encoderMap);

        return encoderMap;
    }

    public void buildEncoderMap(HuffTreeNode root, String code, Map<Character, String> encoderMap) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                encoderMap.put(root.data, code);
            }

            buildEncoderMap(root.left, code + "0", encoderMap);
            buildEncoderMap(root.right, code + "1", encoderMap);
        }
    }

    public Map<String, Character> getDecoder() {
        HuffTreeNode root = makeTree();

        Map<String, Character> decoderMap = new HashMap<>();

        buildDecoderMap(root, "", decoderMap);

        return decoderMap;
    }

    public void buildDecoderMap(HuffTreeNode root, String code, Map<String, Character> decoderMap) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                decoderMap.put(code, root.data);
            }

            buildDecoderMap(root.left, code + "0", decoderMap);
            buildDecoderMap(root.right, code + "1", decoderMap);
        }
    }

    public String compress() {
        String compressed = "";
        Map <Character, String> encoderMap = getEncoder();

        for (char character : to_compress.toCharArray()) {
            compressed += encoderMap.get(character);
        }

        return compressed;
    }

    public void compareBits() {
        int uncompressedBits = to_compress.length() * 7;
        int compressedBits = compress().length();

        System.out.println("Uncompressed string's number of bits: " + uncompressedBits);
        System.out.println("Compressed string's number of bits: " + compressedBits);
        System.out.println("The compressed string is " + (uncompressedBits - compressedBits) + " bits less");
    }

}
