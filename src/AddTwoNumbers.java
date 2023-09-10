
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

public class AddTwoNumbers {

    private static ListNode mySolution(ListNode l1, ListNode l2) {

        int val1=0, val2=0, carry=0, addition=0;
        ListNode ans = null;

        val1 = l1 == null ? 0 : l1.val;
        val2 = l2 == null ? 0 : l2.val;

        if ((val1 + val2) >= 10) {
            addition = (val1 + val2) - 10;
            carry = 1;
        } else {
            addition = val1 + val2;
            carry = 0;
        }

        ans = new ListNode(addition);
        ListNode ansHead = ans;
        l1 = l1.next;
        l2 = l2.next;

        while (l1 != null || l2 != null) {

            val1 = l1 == null ? 0 : l1.val;
            val2 = l2 == null ? 0 : l2.val;

            if ((val1 + val2 + carry) >= 10) {
                addition = (val1 + val2 + carry) - 10;
                System.out.println(addition);
                carry = 1;
            } else {
                addition = val1 + val2 + carry;
                carry = 0;
            }

            ListNode tmpAnsNode = new ListNode(addition); 
            if(l1!=null) l1 = l1.next;
            if(l2!=null) l2 = l2.next;
            ans.next = tmpAnsNode;
            ans = ans.next;
        }

        if (carry>0) {
            ListNode tmpAnsNode = new ListNode(carry);
            ans.next = tmpAnsNode;
            ans = ans.next;
        }


        return ansHead;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return mySolution(l1, l2);
    }
    
    public static void main(String[] args) {
        
        int[] a1, a2;
        ListNode l1, l2, ans;
        
        a1 = new int[] {9,9,9,9,9,9,9};
        a2 = new int[] {9,9,9,9};
        l1 = generateLinkedList(a1);
        l2 = generateLinkedList(a2);
    
        ans = addTwoNumbers(l1, l2);
    
        printLinkedList(ans); //expected: 8,9,9,9,0,0,0,1

        a1 = new int[] { 2, 4, 3 };
        a2 = new int[] { 5, 6, 4 };
        l1 = generateLinkedList(a1);
        l2 = generateLinkedList(a2);

        ans = addTwoNumbers(l1, l2);

        printLinkedList(ans);

        a1 = new int[] {0};
        a2 = new int[] {0};
        l1 = generateLinkedList(a1);
        l2 = generateLinkedList(a2);

        ans = addTwoNumbers(l1, l2);

        printLinkedList(ans);

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
