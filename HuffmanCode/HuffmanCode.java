/*
Name: Angelina Boudro
Class: Algorithm and Analysis

 */
package com.company;
import java.util.*;
import java.util.stream.IntStream;
import static java.lang.System.*;


// Abstract implementation of Huffman.
abstract class Huffman implements Comparable<Huffman> {
    public final int frequency; // Frequency Of This Tree
    public Huffman(int freq) {
        frequency = freq;
    }

    public int compareTo(Huffman tree) {
        return frequency - tree.frequency; // comaring the code.
    }
}


class HuffmanLeaf extends Huffman {
    public final char value;
    public HuffmanLeaf(int freq, char val) { // leaf representations.
        super(freq);
        value = val;
    }
}
class HuffmanNode extends Huffman {
    public final Huffman left, right; // tree computation
    public HuffmanNode(Huffman l, Huffman r) {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}

public class Huffman_Code {
    public static Huffman buildTree(int[] charFreqs) {
        PriorityQueue<Huffman> trees = new PriorityQueue<Huffman>();

        IntStream.range(0, charFreqs.length).filter(i -> charFreqs[i] > 0).mapToObj(i -> new HuffmanLeaf(charFreqs[i], (char) i)).forEach(trees::offer);

        if (trees.size() > 0) {
            while (trees.size() > 1) {
                Huffman a = trees.poll();
                Huffman b = trees.poll();

                trees.offer(new HuffmanNode(a, b));
            }
            return trees.poll();
        }
        throw new AssertionError();

    }

    public static void printCodes(Huffman tree, StringBuffer prefix) {
        assert tree != null;
        if (!(tree instanceof HuffmanLeaf)) {
            if (tree instanceof HuffmanNode) {
                HuffmanNode node = (HuffmanNode)tree;

                prefix.append('0');
                printCodes(node.left, prefix);
                prefix.deleteCharAt(prefix.length()-1); //left

                prefix.append('1');
                printCodes(node.right, prefix);
                prefix.deleteCharAt(prefix.length()-1); //right
            }
        } else {
            HuffmanLeaf leaf = (HuffmanLeaf)tree;
            out.println(leaf.value + "\t\t" + leaf.frequency // Frquency of repittitions
                    + "\t\t" + prefix);

        }
    }

    public static void main(String[] args) {


        String test;
        Scanner s = new Scanner(in);
        out.print("\n Enter String( nospace) \n");
        out.print("\n Enter: \t");
        test = s.next();

        int[] charFreqs = new int[1024];

        for (char c : test.toCharArray())
            charFreqs[c]++;

        var tree = buildTree(charFreqs);

        out.println("Character \t Frequency \t Codeword");
        printCodes(tree, new StringBuffer()); // print columns .
    }
}
