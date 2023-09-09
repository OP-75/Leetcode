
//   Definition for singly-linked list.

import java.util.Stack;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReorderList {

    private static void mySolution(ListNode head) {

        Stack<ListNode> nodesSt = new Stack<>();
        ListNode n = head;
        while (n != null) {
            nodesSt.push(n);
            n = n.next;
        }

        int halfSize = nodesSt.size() / 2;
        int numOfNodesPopped = 0;
        n = head;
        while (n != null && !nodesSt.isEmpty()) {

            if (numOfNodesPopped == halfSize) {
                break;
            }

            ListNode poppedNode = nodesSt.pop();
            nodesSt.peek().next = null; // the node pointing to popped node should become null
            numOfNodesPopped++;

            poppedNode.next = n.next;
            n.next = poppedNode;

            n = n.next.next; //n.next = popped node but we wanna travel to the node after that one

        }

    }

    private static void neetcodeSolution(ListNode head){

        ListNode s = head, f = head.next;

        //move the fast and slow pointers
        while (f!=null) {

            // f moves twice as fast as s
            if (f.next!=null) {
                f = f.next.next;
            }
            else{
                f=null;
            }
            s = s.next;
        }

        //s has reached the midpoint of the LL, start reversing from s
        if(s.next==null){
            //this is if there are <=2 elements in LL in which case the LL is arelardy in the correct order according the question
            return;
        }
        ListNode curr = s, currNext = s.next, currNextNext = s.next.next; //end keeps track of end of the LL for next loop
        while (currNext!=null) {
            
            
            currNext.next = curr;

            curr = currNext;
            currNext = currNextNext;
            if(currNext!=null) currNextNext = currNext.next;
        }

        s.next = null; //make the mid point null since we have reversed the LL
        ListNode end = curr; //if(currNext==null) the loop wouldve stopped and curr would be pointing to a node before currNext was node, ie a end node
        
        //now start popping ListNodes from end
        ListNode p = head;
        while (p!=s) {
            
            ListNode tmpNode = end;
            if(end!=s) end = end.next;
            
            tmpNode.next = p.next;
            p.next = tmpNode;
            
            p = tmpNode.next;
        } 

       
        //terminate the reordered LL
        p.next = null;
        



    }

    public static void reorderList(ListNode head) {
        // mySolution(head);
        neetcodeSolution(head);
    }

    public static void main(String[] args) {
        // int[] arr = { 1, 2, 3, 4, 5 };
        // int[] arr = {1,2,3,4};
        int[] arr = {1};

        ListNode head = generateLinkedList(arr);

        reorderList(head);

        printLinkedList(head);
    }

    static ListNode generateLinkedList(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;

        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    static void printLinkedList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

}
