package cz.osu;

public class Main {

    public static void main(String[] args) {

        String text = "Algoritmy a datove struktury - Huffmanovo kodovani 20.4.2020, ALDS2";

        Huffman huffman = new Huffman(text);

        huffman.printHuffmanCode();

        String encodedText = huffman.encode(text);

        int dataCompulsion = (encodedText.length() % 8 == 0) ? encodedText.length() / 8 : encodedText.length() / 8 + 1;

        System.out.println("Encoded Text: " + encodedText + " | Data compulsion " + dataCompulsion);

        String decodedText = huffman.decode(encodedText);
        System.out.println("Decoded Text: " + decodedText + " | Data compulsion " + decodedText.length());
    }
}
