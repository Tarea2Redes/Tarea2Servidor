package Domain;


import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

/* Huffman coding and decoding 

@autor:       ahmedengu
@repo:        https://gist.github.com/ahmedengu/aa8d85b12fccf0d08e895807edee7603

 */
public class Huffman {

    static final boolean readFromFile = false;
    static final boolean newTextBasedOnOldOne = false;

    PriorityQueue<Node> nodes = new PriorityQueue<>((o1, o2) -> (o1.value < o2.value) ? -1 : 1);
    TreeMap<Character, String> codes = new TreeMap<>();
    String text = "";
    String encoded = "";
    String decoded = "";
    int ASCII[] = new int[128];

    public Huffman(String texto) {
        this.text = texto;
    }

    public boolean comprimir() {

        ASCII = new int[128];
        nodes.clear();
        codes.clear();
        encoded = "";
        decoded = "";
        System.out.println("Text: " + text);
        calculateCharIntervals(nodes, true);
        buildTree(nodes);
        generateCodes(nodes.peek(), "");

        System.out.println("-- Encoding/Decoding --");
        encodeText();
        decodeText();
        return false;

    }

    public String decodeText() {
        decoded = "";
        Node node = nodes.peek();
        for (int i = 0; i < encoded.length();) {
            Node tmpNode = node;
            while (tmpNode.left != null && tmpNode.right != null && i < encoded.length()) {
                if (encoded.charAt(i) == '1') {
                    tmpNode = tmpNode.right;
                } else {
                    tmpNode = tmpNode.left;
                }
                i++;
            }
            if (tmpNode != null) {
                if (tmpNode.character.length() == 1) {
                    decoded += tmpNode.character;
                } else {
                    System.out.println("Input not Valid");
                }
            }

        }
        System.out.println("Decoded Text: " + decoded);
        return decoded;
    }

    public String encodeText() {
        encoded = "";
        for (int i = 0; i < text.length(); i++) {
            encoded += codes.get(text.charAt(i));
        }
        System.out.println("Encoded Text: " + encoded);
        return encoded;
    }

    private static void buildTree(PriorityQueue<Node> vector) {
        while (vector.size() > 1) {
            vector.add(new Node(vector.poll(), vector.poll()));
        }
    }


    private void calculateCharIntervals(PriorityQueue<Node> vector, boolean printIntervals) {
        if (printIntervals) {
            System.out.println("-- intervals --");
        }

        for (int i = 0; i < text.length(); i++) {
            ASCII[text.charAt(i)]++;
        }

        for (int i = 0; i < ASCII.length; i++) {
            if (ASCII[i] > 0) {
                vector.add(new Node(ASCII[i] / (text.length() * 1.0), ((char) i) + ""));
                if (printIntervals) {
                    System.out.println("'" + ((char) i) + "' : " + ASCII[i] / (text.length() * 1.0));
                }
            }
        }
    }

    private void generateCodes(Node node, String s) {
        if (node != null) {
            if (node.right != null) {
                generateCodes(node.right, s + "1");
            }

            if (node.left != null) {
                generateCodes(node.left, s + "0");
            }

            if (node.left == null && node.right == null) {
                codes.put(node.character.charAt(0), s);
            }
        }
    }
}

class Node {

    Node left, right;
    double value;
    String character;

    public Node(double value, String character) {
        this.value = value;
        this.character = character;
        left = null;
        right = null;
    }

    public Node(Node left, Node right) {
        this.value = left.value + right.value;
        character = left.character + right.character;
        if (left.value < right.value) {
            this.right = right;
            this.left = left;
        } else {
            this.right = left;
            this.left = right;
        }
    }
}
