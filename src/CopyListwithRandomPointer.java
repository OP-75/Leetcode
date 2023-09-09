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


        Node oldNode = head, prevNode = null, newHead=null;
        while (oldNode!=null) {
            
            int val = oldNode.val;

            Node newNode = new Node(val);

            mapping.put(oldNode, newNode);

            if(prevNode==null){
                newHead = newNode;
                prevNode = newNode;
                oldNode = oldNode.next;
                continue;
            }
            else{
                prevNode.next = newNode;
                oldNode = oldNode.next;
                prevNode = prevNode.next;
            }

        }


        oldNode = head;
        while (oldNode!=null) {
            
            Node correspondingNode = mapping.get(oldNode);

            if(oldNode.random==null){
                correspondingNode.random=null;
            } 
            else{
                correspondingNode.random = mapping.get(oldNode.random);
            }

            oldNode = oldNode.next;

        }

        return newHead;
    }

    public Node copyRandomList(Node head) {
        return head;
    }
}
