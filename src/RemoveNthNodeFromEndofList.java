


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

public class RemoveNthNodeFromEndofList {


    private static ListNode mySolution(ListNode head, int n){
        // use 2 ptrs and maintain the dist of "n" between them

        ListNode left = head, right = head;
        int counter = 0;
        while (counter<n-1 && right!=null) { //caz if we want to delete the 2nd node from end then dist between 2 ptrs sould be = 1
            right = right.next;
            counter++;
        }   


        ListNode prev = null;
        while (right.next!=null) { 
            //move ptrs till right reaches last node
            prev = left;
            right = right.next;
            left = left.next;
        }

        // now right has reached its last node, left points to the node we have to delete & prev pts to the node left of left  

        if(prev==null && left==head){
            // if left = head
            head = left.next;
            return head;
        }

        prev.next = left.next;
        return head;

    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        return mySolution(head, n);
    }

    public static void main(String[] args) {
        int[] arr;
        int n;

        arr = new int[] { 1, 2,};
        n = 1;

        arr = new int[] { 1, 2, 3};
        n = 2;

        // arr = new int[] {1};
        // n = 1;

        // arr = new int[] {1, 2, 3, 4, 5};
        // n = 2;
        
        

        ListNode head = generateLinkedList(arr);

        head = removeNthFromEnd(head, n);

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
