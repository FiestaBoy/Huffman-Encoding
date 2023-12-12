import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class HuffTreeNode implements Comparable<HuffTreeNode>{
    char data;
    int freq;
    HuffTreeNode left;
    HuffTreeNode right;

    // Constructor for leaf nodes
    public HuffTreeNode(char data, int freq) {
        this.data = data;
        this.freq = freq;
        this.left = null;
        this.right = null;
    }

    // implements Comparable interface
    @Override
    public int compareTo(HuffTreeNode other) {
        // compare frequencies first
        int frequencyComparison = Integer.compare(this.freq, other.freq);

        // if frequencies are equal, compare characters
        if (frequencyComparison == 0) {
            return Character.compare(this.data, other.data);
        }

        return frequencyComparison;
    }

    @Override
    public String toString() {
        String toString = data + "=" + freq;

        return toString;
    }
}
public class HuffTree {
    String to_compress;
    HuffTreeNode root;

    // Constructor
    public HuffTree(String to_compress) {
        this.to_compress = to_compress;
        root = null;
    }

    // returns a map of characters to their frequencies
    public Map<Character, Integer> getCounts() {
        Map<Character, Integer> charFreq = new HashMap<>();

        // count frequencies of characters
        for (char character : to_compress.toCharArray()) {
            Integer frequency = charFreq.get(character);
            charFreq.put(character, frequency != null ? frequency + 1 : 1);
        }

        return charFreq;
    }

    // returns a priority queue of leaf nodes sorted by frequency
    public Queue<HuffTreeNode> sortedCounts(Map<Character, Integer> charFreq) {
        Queue<HuffTreeNode> q = new PriorityQueue<>();

        // add leaf nodes to priority queue
        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            char character = entry.getKey();
            int frequency = entry.getValue();

            q.add(new HuffTreeNode(character, frequency));
        }

        return q;
    }

    // returns the root of the Huffman tree
    public HuffTreeNode makeTree() {
        Map<Character, Integer> charFreq = getCounts();

        Queue<HuffTreeNode> queue = sortedCounts(charFreq);

        // build tree
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

    // returns a map of characters to their binary codes
    public Map<Character, String> getEncoder() {
        HuffTreeNode root = makeTree();

        Map<Character, String> encoderMap = new HashMap<>();

        buildEncoderMap(root, "", encoderMap);

        return encoderMap;
    }

    // helper method for getEncoder()
    // recursively build encoder map
    public void buildEncoderMap(HuffTreeNode root, String code, Map<Character, String> encoderMap) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                encoderMap.put(root.data, code);
            }

            buildEncoderMap(root.left, code + "0", encoderMap);
            buildEncoderMap(root.right, code + "1", encoderMap);
        }
    }

    // returns a map of binary codes to their characters
    public Map<String, Character> getDecoder() {
        HuffTreeNode root = makeTree();

        Map<String, Character> decoderMap = new HashMap<>();

        buildDecoderMap(root, "", decoderMap);

        return decoderMap;
    }

    // helper method for getDecoder()
    // recursively build decoder map
    public void buildDecoderMap(HuffTreeNode root, String code, Map<String, Character> decoderMap) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                decoderMap.put(code, root.data);
            }

            buildDecoderMap(root.left, code + "0", decoderMap);
            buildDecoderMap(root.right, code + "1", decoderMap);
        }
    }

    // returns the compressed string
    public String compress() {
        String compressed = "";
        Map <Character, String> encoderMap = getEncoder();

        // iterate through string and add binary codes to compressed string
        for (char character : to_compress.toCharArray()) {
            compressed += encoderMap.get(character);
        }

        return compressed;
    }

    // returns the original string
    public void compareBits() {
        int uncompressedBits = to_compress.length() * 7;
        int compressedBits = compress().length();

        System.out.println("Uncompressed string's number of bits: " + uncompressedBits);
        System.out.println("Compressed string's number of bits: " + compressedBits);
        System.out.println("The compressed string is " + (uncompressedBits - compressedBits) + " bits less");
    }

}
