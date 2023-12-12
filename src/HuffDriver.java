import java.util.Map;
import java.util.Queue;

public class HuffDriver {
    public static void main(String[] args) {

        // Lithuanian Hymn obviously
        testGetCounts(
                "Lietuva, Tėvyne mūsų,\n" +
                        "Tu didvyrių žeme,\n" +
                        "Iš praeities Tavo sūnūs\n" +
                        "Te stiprybę semia.\n" +
                        "\n" +
                        "Tegul Tavo vaikai eina\n" +
                        "Vien takais dorybės,\n" +
                        "Tegul dirba Tavo naudai\n" +
                        "Ir žmonių gėrybei.\n" +
                        "\n" +
                        "Tegul saulė Lietuvoj\n" +
                        "Tamsumas prašalina,\n" +
                        "Ir šviesa, ir tiesa\n" +
                        "Mūs žingsnius telydi.\n" +
                        "\n" +
                        "Tegul meilė Lietuvos\n" +
                        "Dega mūsų širdyse,\n" +
                        "Vardan tos Lietuvos\n" +
                        "Vienybė težydi!");

        // random case
        testGetCounts("aaaaaabbffeferds");

        // empty string
        testGetCounts("");

        // big Pink Floyd fan
        testSortedCounts("Ticking away the moments that make up a dull day\n" +
                "You fritter and waste the hours in an offhand way\n" +
                "Kicking around on a piece of ground in your hometown\n" +
                "Waiting for someone or something to show you the way\n" +
                "Tired of lying in the sunshine\n" +
                "Staying home to watch the rain\n" +
                "And you are young and life is long\n" +
                "And there is time to kill today\n" +
                "And then one day you find\n" +
                "Ten years have got behind you\n" +
                "No one told you when to run\n" +
                "You missed the starting gun\n" +
                "And you run, and you run to catch up with the sun\n" +
                "But it's sinking\n" +
                "Racing around to come up behind you again\n" +
                "The sun is the same in a relative way\n" +
                "But you're older\n" +
                "Shorter of breath, and one day closer to death\n" +
                "Every year is getting shorter\n" +
                "Never seem to find the time\n" +
                "Plans that either come to naught\n" +
                "Or half a page of scribbled lines\n" +
                "Hanging on in quiet desperation\n" +
                "Is the English way\n" +
                "The time is gone, the song is over\n" +
                "Thought I'd something more to say\n" +
                "Home, home again\n" +
                "I like to be here when I can\n" +
                "When I come home cold and tired\n" +
                "It's good to warm my bones beside the fire\n" +
                "Far away, across the field\n" +
                "The tolling of the iron bell\n" +
                "Calls the faithful to their knees\n" +
                "To hear the softly spoken magic spells");

        // same frequency -> sorts alphabetically
        testSortedCounts("bbbaaadddpppkkklll");

        // empty string
        testSortedCounts("");

        // makeTree() test cases:
        System.out.println("makeTree() TEST");
        System.out.println("=======================");

        testMakeTree("aaaaabbbbbbbcccccccccccccdfg");

        System.out.println();
        System.out.println("=======================");

        testMakeTree("AYUSYvdoyvadfyaASfa84f}{}!(#&$Q(&ASBiuGASFT%(*AS%FD*&TFAYisgdiyGAS8fd*AS*F&%ASFDtAGsf");

        System.out.println();
        System.out.println("=======================");

        testMakeTree("");

        System.out.println("=======================");
        System.out.println();

        // getEncoder() test cases:

        testGetEncoder("aaaabbbccd");

        testGetEncoder("ASUOGDCVASUTDFVAOUSyasvvdouasvduoavsda6365859^%(*^%(^%+-");

        testGetEncoder("");

        // getDecoder() test cases:

        testGetDecoder("aaaabbbccd");

        testGetDecoder("ASUOGDCVASUTDFVAOUSyasvvdouasvduoavsda6365859^%(*^%(^%+-");

        testGetDecoder("");

        // compress() test cases:

        testCompress("aaabbc");

        testCompress("Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                "Sed ac velit a libero facilisis consequat. Nunc ut ligula ac turpis sagittis scelerisque. " +
                "Vivamus lacinia, ex vitae dapibus auctor, libero tortor rhoncus dui, et gravida lectus est vel lorem. " +
                "Maecenas at nulla nec metus convallis hendrerit. Quisque non felis ac dolor tincidunt accumsan. " +
                "Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. " +
                "Fusce ac nisl eget ipsum vulputate vestibulum. Sed varius odio nec velit accumsan, a " +
                "luctus risus pharetra. Sed nec facilisis dui. Proin et nisl vel lectus ultrices ultrices. Nullam " +
                "bibendum velit vel mauris rhoncus, et tristique justo lacinia. Integer fringilla, metus id sagittis " +
                "bibendum, quam purus accumsan dui, eu cursus dolor lectus vel dolor.\n");

        testCompress("");

        // compareBits() test cases:

        testCompareBits("aaabbccd");

        testCompareBits("Pink Floyd - Green is The Colour\n" +
                "Heavy hung the canopy of blue\n" +
                "Shade my eyes and I can see you\n" +
                "White was the light that shines\n" +
                "Through the dress that you wore\n" +
                "She lay in the shadow of a wave\n" +
                "Hazy were the visions overplayed\n" +
                "Sunshine in her eyes\n" +
                "But moonshine made her blind every time\n" +
                "Green is the colour of her kind\n" +
                "Quickness of the eye deceives the mind\n" +
                "Envy is the bond between the hopeful\n" +
                "And the damned");

        testCompareBits("");

        // decode() test cases:

        test1Decode();

        test2Decode();

        test3Decode();
    }

    public static void testGetCounts(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);
        Map<Character, Integer> charFreq = huffTest.getCounts();

        System.out.println("getCounts() TEST");
        System.out.println("INPUT: " + to_compress);
        System.out.println("=======================");


        for (Map.Entry<Character, Integer> entry : charFreq.entrySet()) {
            System.out.println("CHARACTER: " + entry.getKey() + " FREQUENCY: " + entry.getValue());
        }
        System.out.println("=======================");
        System.out.println();
    }

    public static void testSortedCounts(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);
        Map<Character, Integer> charFreq = huffTest.getCounts();
        Queue<HuffTreeNode> queue = huffTest.sortedCounts(charFreq);

        System.out.println("sortedCounts() TEST");
        System.out.println("INPUT: " + to_compress);
        System.out.println("=======================");
        for (int i = 0; i < charFreq.size() ; i++) {
            System.out.print(queue.remove() + " ");
        }
        System.out.println("\n=======================");
        System.out.println();
    }

    public static void testMakeTree(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);
        printInOrder(huffTest.makeTree(), "");
    }

    public static void testGetEncoder(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);

        System.out.println("getEncoder() TEST");
        System.out.println("=======================");

        for (Map.Entry<Character, String> entry : huffTest.getEncoder().entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }

        System.out.println();
        System.out.println("=======================");
        System.out.println();
    }

    public static void testGetDecoder(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);

        System.out.println("getDecoder() TEST");
        System.out.println("=======================");

        for (Map.Entry<String, Character> entry : huffTest.getDecoder().entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }

        System.out.println();
        System.out.println("=======================");
        System.out.println();
    }

    public static void testCompress(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);

        System.out.println("compress() TEST");
        System.out.println("=======================");

        System.out.println("Original string: " + to_compress);
        System.out.println("Compressed string: " + huffTest.compress());

        System.out.println("=======================");
        System.out.println();
    }

    public static void testCompareBits(String to_compress) {
        HuffTree huffTest = new HuffTree(to_compress);

        System.out.println("compareBits() TEST");
        System.out.println("=======================");

        huffTest.compareBits();

        System.out.println("=======================");
        System.out.println();
    }

    public static void test1Decode() {
        String originalString = "aaaaabbbccd";
        HuffTree huffTree = new HuffTree(originalString);
        String compressedString = huffTree.compress();

        Map<String, Character> decoderMap = huffTree.getDecoder();

        String decodedResult = HuffDecode.decode(decoderMap, compressedString);

        System.out.println("decode() TEST1");
        System.out.println("=======================");
        System.out.println("Original String: " + originalString);
        System.out.println("Compressed String: " + compressedString);
        System.out.println("Decoded String: " + decodedResult);
        System.out.println("=======================");
        System.out.println();
    }

    public static void test2Decode() {
        String originalString = "My name is Walter Hartwell White. I live at 308 Negra Arroyo Lane, Albuquerque, New Mexico, 87104. This is my confession. If you're watching this tape, I'm probably dead– murdered by my brother-in-law, Hank Schrader. Hank has been building a meth empire for over a year now, and using me as his chemist. Shortly after my 50th birthday, he asked that I use my chemistry knowledge to cook methamphetamine, which he would then sell using connections that he made through his career with the DEA. I was... astounded. I... I always thought Hank was a very moral man, and I was particularly vulnerable at the time – something he knew and took advantage of. I was reeling from a cancer diagnosis that was poised to bankrupt my family. Hank took me in on a ride-along and showed me just how much money even a small meth operation could make. And I was weak. I didn't want my family to go into financial ruin, so I agreed. Hank had a partner, a businessman named Gustavo Fring. Hank sold me into servitude to this man. And when I tried to quit, Fring threatened my family. I didn't know where to turn. Eventually, Hank and Fring had a falling-out. Things escalated. Fring was able to arrange – uh, I guess... I guess you call it a \"hit\" – on Hank, and failed, but Hank was seriously injured. And I wound up paying his medical bills, which amounted to a little over $177,000. Upon recovery, Hank was bent on revenge. Working with a man named Hector Salamanca, he plotted to kill Fring. The bomb that he used was built by me, and he gave me no option in it. I have often contemplated suicide, but I'm a coward. I wanted to go to the police, but I was frightened. Hank had risen to become the head of the Albuquerque DEA. To keep me in line, he took my children. For three months, he kept them. My wife had no idea of my criminal activities, and was horrified to learn what I had done. I was in hell. I hated myself for what I had brought upon my family. Recently, I tried once again to quit, and in response, he gave me this. [Walt points to the bruise on his face left by Hank in \"Blood Money.\"] I can't take this anymore. I live in fear every day that Hank will kill me, or worse, hurt my family. All I could think to do was to make this video and hope that the world will finally see this man for what he really is.";
        HuffTree huffTree = new HuffTree(originalString);
        String compressedString = huffTree.compress();

        Map<String, Character> decoderMap = huffTree.getDecoder();

        String decodedResult = HuffDecode.decode(decoderMap, compressedString);

        System.out.println("decode() TEST2");
        System.out.println("=======================");
        System.out.println("Original String: " + originalString);
        System.out.println("Compressed String: " + compressedString);
        System.out.println("Decoded String: " + decodedResult);
        System.out.println("=======================");
        System.out.println();
    }

    public static void test3Decode() {
        String originalString = "";
        HuffTree huffTree = new HuffTree(originalString);
        String compressedString = huffTree.compress();

        Map<String, Character> decoderMap = huffTree.getDecoder();

        String decodedResult = HuffDecode.decode(decoderMap, compressedString);

        System.out.println("decode() TEST3");
        System.out.println("=======================");
        System.out.println("Original String: " + originalString);
        System.out.println("Compressed String: " + compressedString);
        System.out.println("Decoded String: " + decodedResult);
        System.out.println("=======================");
        System.out.println();
    }

    public static void printInOrder(HuffTreeNode root, String spaces) {
        if (root == null) {
            System.out.println(spaces + "\tnull");
            return;
        }

        if (root.left != null) printInOrder(root.left, spaces + "\t");
        else System.out.println(spaces + "\tnull");

        System.out.println(spaces + root.data);

        if (root.right != null) printInOrder(root.right, spaces + "\t");
        else System.out.println(spaces + "\tnull");
    }

}