import java.util.HashMap;
import java.util.HashSet;

class Node {
    int val;
    Node next;
    Node random;

    Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}


public class CopyListwithRandomPointer {

    private Node mySolution(Node head){

        HashMap<Node, Node> mapping = new HashMap<>();
        // oldNode: newNode mapping


        Node p = head, prevNode = null, newHead=null;
        while (p!=null) {
            
            int val = p.val;

            Node newNode = new Node(val);

            mapping.put(p, newNode);

            if(prevNode==null){
                newHead = newNode;
                prevNode = newNode;
                p = p.next;
                continue;
            }
            else{
                prevNode.next = newNode;
                p = p.next;
                prevNode = prevNode.next;
            }

        }


        p = head;
        while (p!=null) {
            
            Node correspondingNode = mapping.get(p);

            if(p.random==null){
                correspondingNode.random=null;
            } 
            else{
                correspondingNode.random = mapping.get(p.random);
            }

            p = p.next;

        }

        return newHead;
    }

    public Node copyRandomList(Node head) {
        return head;
    }
}
