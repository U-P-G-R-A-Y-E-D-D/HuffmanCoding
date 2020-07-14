package cz.osu;

public class Node{

    public String symbol;
    public Integer freq;

    public Node next;
    public Node left;
    public Node right;

    public Node(String symbol, int freq) {
        this.symbol = symbol;
        this.freq = freq;
    }

    public Node(String symbol, int freq, Node left, Node right) {
        this.symbol = symbol;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}
