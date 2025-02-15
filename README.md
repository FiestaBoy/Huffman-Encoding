## Overview
This project is an implementation of the **Huffman Coding Algorithm** for lossless data compression in Java. Huffman Coding is a widely used compression technique that assigns shorter binary codes to frequently occurring characters and longer codes to less frequent ones, reducing the overall size of the encoded data.

## Features
- **Encodes text using Huffman coding** to produce a compressed binary representation.
- **Decodes the compressed binary string** back to the original text using a Huffman tree.
- **Supports frequency-based tree construction** for optimal encoding.
- **Comparator-based sorting for priority queue handling.**
- **Bit comparison for efficiency analysis.**
- **Fully tested with various input cases.**

## Project Structure
- `HuffTree.java` - Core implementation of Huffman encoding, including:
  - Huffman tree construction (`makeTree()`)
  - Encoding map generation (`getEncoder()`)
  - Decoding map generation (`getDecoder()`)
  - Compression function (`compress()`)
  - Bit comparison function (`compareBits()`)
- `HuffTreeNode.java` - A helper class representing nodes in the Huffman tree.
- `HuffDriver.java` - Contains test cases to validate encoding and decoding.
- `HuffDecode.java` - Implements the decoding function.

## How It Works
### 1. **Building the Huffman Tree**
- **Step 1:** Compute the frequency of each character in the input string.
- **Step 2:** Construct a priority queue of `HuffTreeNode` elements sorted by frequency.
- **Step 3:** Merge nodes iteratively to form a binary tree, where each leaf node represents a character.

### 2. **Encoding Process**
- Traverse the Huffman tree to assign binary codes to each character.
- Replace each character in the input text with its corresponding binary code.

### 3. **Decoding Process**
- Use the binary code table to reconstruct the original text from the compressed binary string.

## Example Usage
```java
String input = "hello world";
HuffTree tree = new HuffTree(input);
String compressed = tree.compress();
System.out.println("Compressed: " + compressed);

String decompressed = HuffDecode.decode(tree.getDecoder(), compressed);
System.out.println("Decompressed: " + decompressed);
```

## Performance Analysis
The `compareBits()` function provides a comparison of the original string's bit count vs. the compressed representation:
```java
tree.compareBits();
```

## Future Enhancements
- Implement file-based input/output for compression.
- Support for handling large text files efficiently.
- Optimize memory usage for better performance.
