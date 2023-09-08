
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

    public static void reorderList(ListNode head) {
        mySolution(head);
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };

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
