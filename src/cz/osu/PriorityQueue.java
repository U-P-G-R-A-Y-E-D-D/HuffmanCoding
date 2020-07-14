package cz.osu;

public class PriorityQueue {

    private Node head;
    private int nodesCount;

    public int getNodesCount() {
        return nodesCount;
    }

    public PriorityQueue(){

        head = null;
        nodesCount = 0;
    }

    public void addNode(Node nodeToAdd){

        nodesCount++;

        if(head == null){

            head = nodeToAdd;
            return;
        }

        if(head.freq > nodeToAdd.freq){

            nodeToAdd.next = head;
            head = nodeToAdd;
            return;
        }

        Node start = head;

        while (start.next != null && start.next.freq < nodeToAdd.freq) {
            start = start.next;
        }

        nodeToAdd.next = start.next;
        start.next = nodeToAdd;

    }

    public Node pop(){

        if(head == null) return null;

        nodesCount--;

        Node temp = head;
        head = head.next;

        return temp;
    }

    public void printAll(){

        Node start = head;

        while (start != null) {

            System.out.println(start);
            start = start.next;
        }
    }
}
