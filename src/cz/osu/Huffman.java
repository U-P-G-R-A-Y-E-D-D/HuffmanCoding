package cz.osu;

import java.util.HashMap;

public class Huffman {

    private Node root;
    private HashMap<String, String> huffmanCode;

    public Huffman(String text){

        buildHuffmanTree(text);
    }

    public void printHuffmanCode(){

        for (String symbol : huffmanCode.keySet()) {

            System.out.println(symbol + " > " + huffmanCode.get(symbol));
        }
    }

    public String encode(String textToEncode){

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < textToEncode.length(); i++) {
            sb.append(huffmanCode.get(textToEncode.substring(i, i + 1)));
        }

        return sb.toString();
    }

    public String decode(String textToDecode){

        StringBuilder sb = new StringBuilder();

        int index = -1;
        while (index < textToDecode.length() - 2) {

            index = findSymbol(root, index, textToDecode, sb);
        }

        return sb.toString();
    }

    private void buildHuffmanTree(String text){

        //cetnost znaku
        HashMap<Character, Node> freqMap = new HashMap<>();

        for(int i = 0; i < text.length(); i++){

            Node temp = freqMap.get(text.charAt(i));

            if(temp == null){

                freqMap.put(text.charAt(i), new Node(text.substring(i, i+ 1), 1));

            }else {

                temp.freq += 1;
            }
        }

        //prioritniFronta
        PriorityQueue priorityQueueHuffman = new PriorityQueue();
        for (Node node: freqMap.values()) priorityQueueHuffman.addNode(node);

        //vytvoreni stromu
        while (priorityQueueHuffman.getNodesCount() > 1){

            Node left = priorityQueueHuffman.pop();
            Node right = priorityQueueHuffman.pop();

            priorityQueueHuffman.addNode(new Node("", left.freq + right.freq, left, right));
        }

        root = priorityQueueHuffman.pop();

        // prochazeni stromu a generovani kodu
        huffmanCode = new HashMap<>();
        fillHuffmanCode(root, "", huffmanCode);
    }

    private void fillHuffmanCode(Node actualNode, String str, HashMap<String, String> huffmanCode) {

        if (actualNode == null) return;

        if (actualNode.left == null && actualNode.right == null) {

            huffmanCode.put(actualNode.symbol, str);
        }

        fillHuffmanCode(actualNode.left, str + "0", huffmanCode);
        fillHuffmanCode(actualNode.right, str + "1", huffmanCode);
    }

    private int findSymbol(Node actualNode, int index, String textToDecode, StringBuilder decodedText) {

        if (actualNode == null)
            return index;

        if (actualNode.left == null && actualNode.right == null)
        {
            decodedText.append(actualNode.symbol);
            return index;
        }

        index++;

        if (textToDecode.charAt(index) == '0')
            index = findSymbol(actualNode.left, index, textToDecode, decodedText);
        else
            index = findSymbol(actualNode.right, index, textToDecode, decodedText);

        return index;
    }
}
