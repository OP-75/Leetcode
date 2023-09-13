import java.util.ArrayList;
import java.util.List;

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

public class ReverseNodesinkGroup {

    private static ListNode[] newLeftRight(ListNode head, ListNode[] prevNodes, int k) {

        ListNode[] nodes = new ListNode[4];
        // contains left, right, nextRight (in this order)

        ListNode tmp;

        int count = 1;
        if (prevNodes == null) {
            nodes[0] = head; // left = head
            nodes[2] = null; // preleft = null

            tmp = head;

            while (count < k && tmp.next != null) {
                tmp = tmp.next;
                count++;
            }
            nodes[1] = tmp;
            nodes[3] = tmp.next;

            return nodes;
        } else if (prevNodes[1] != null && prevNodes[3] == null) { // right is not null & nextRight is null ie we are at
                                                                   // end of LL
            return null;
        } else {
            nodes[0] = prevNodes[3]; // left = nextRight of prev sub list
            nodes[2] = prevNodes[0]; // new preLeft = left of previous sublist since sublist is now flipped

            tmp = nodes[0];

            while (count < k && tmp.next != null) {
                tmp = tmp.next;
                count++;
            }

            if (count < k) {
                return null;
            }

            nodes[1] = tmp; // right
            nodes[3] = tmp.next; // nextRight

            return nodes;
        }

    }

    private static ListNode mySolution(ListNode head, int k) {

        ListNode[] nodes = newLeftRight(head, null, k);

        ListNode newhead = nodes[1]; // after reversal right will be the head of LL
        printLinkedList(head);

        while (nodes != null) {

            ListNode left = nodes[0];
            ListNode right = nodes[1];
            ListNode preLeft = nodes[2];
            ListNode nextRight = nodes[3];

            ListNode prevNode = left; // preleft
            ListNode currNode = prevNode != null ? left.next : null; // left.next
            ListNode nextNode = currNode != null ? currNode.next : null;

            while (currNode != nextRight && currNode != null) {
                currNode.next = prevNode;

                prevNode = currNode;
                currNode = nextNode;
                nextNode = currNode == null ? null : currNode.next;
            }
            // whole LL is reversed
            // now update the left node to point to nextRight
            left.next = nextRight;
            if (preLeft != null) {
                preLeft.next = right;
            }
            nodes = newLeftRight(head, nodes, k);

            printLinkedList(newhead);
        }

        return newhead;

    }

    public static ListNode mySolution2(ListNode head, int k) {

        List<ListNode> nodes = new ArrayList<>();
        nodes.add(0, null);
        ListNode newHead = null;

        ListNode tmp = head;
        while (tmp != null) {
            nodes.add(tmp);
            tmp = tmp.next;
        }

        if (k == 1 || nodes.size() == 1) {
            return head;
        }

        // all notes are in AL
        int count = 1;
        int lastGrpSize = (nodes.size() - 1) % k; // need to do size - 1 caz at 0th indx I've put a null
        for (int i = 1; i < nodes.size(); i++, count++) {
            ListNode currNode = nodes.get(i);
            
            ListNode prev = nodes.get(i - 1);

            
            
            if ((i > (nodes.size() - 1) - lastGrpSize) && lastGrpSize!=0) {
                continue; // need to do size - 1 caz at 0th indx I've put a null
            } 
            else {

                if(count!=1){
                    //caz of count is 1 the prev = i-1 = 0 then 1st node of the grp will point to previous node of last grp!!! which should not be the case
                    currNode.next = prev;
                } 

                if (k == count) {
                    ListNode leftNodeOfGrp = nodes.get(i - (k - 1));
                    leftNodeOfGrp.next = i + 1 < nodes.size() ? nodes.get(i + 1) : null;
                    
                    //indx of right node after reversal of previous grp would be i - 2k since the indxs are before reversal
                    int indxOfNodeOfRightOfPreviosGrp = i - k - (k - 1);
                    if(indxOfNodeOfRightOfPreviosGrp>=1 && indxOfNodeOfRightOfPreviosGrp<nodes.size()){
                        ListNode rightNodeOfPreviousGrp = nodes.get(i - k - (k - 1));
                        rightNodeOfPreviousGrp.next = currNode;
                    }

                    if (newHead == null) {
                        newHead = currNode;
                    }

                    count = 0;
                    printLinkedList(newHead);
                }
            }



        }

        return newHead;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode ans = mySolution(head, k);
        // ListNode ans = mySolution2(head, k); //this was supposed to be efficient but unlimately it is SLOWER!!!
        return ans;
    }

    public static void main(String[] args) {

        int a[] = { 1, 2, 3, 4, 5, };
        ListNode head = generateLinkedList(a);
        head = reverseKGroup(head, 3);
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
