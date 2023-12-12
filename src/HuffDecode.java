import java.util.Map;

public class HuffDecode {
    public static String decode(Map<String, Character> decoder, String compressed) {
        String decoded = "";
        String cur = "";

        for (char bit : compressed.toCharArray()) {
            cur += bit;

            if (decoder.containsKey(cur)) {
                decoded += decoder.get(cur);
                cur = "";
            }
        }
        return decoded;
    }
}

// Why does it make most sense to put the decode method in a separate file? Answer
// in a multi-line comment within this file.

// Separating decoding into its own file simplifies testing and debugging.
// Someone looking at the project for the first time can quickly locate
// and understand the decoding logic without going through other unrelated code, especially when HuffTree.java focuses
// on encoding the string.
