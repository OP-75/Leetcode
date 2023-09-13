
class ListNode {
int val;
ListNode next;
ListNode() {}
ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedListII {

    private static ListNode[] findLeftRightNode(ListNode head, int left, int right){

        ListNode[] ans = new ListNode[4];

        ListNode tmp = head;
        int count = 1;

        int prevLeft = left-1;
        int nextRight = right+1;
        while (tmp != null) {

            
            if (count==prevLeft) {
                ans[2] = tmp;
            }
            if (count==left) {
                ans[0] = tmp;
            }

            if (count==right) {
                ans[1] = tmp;
            }
            if (count==nextRight) {
                ans[3] = tmp;
            }
            tmp = tmp.next;
            count++;
        }

        return ans;
    }
    private static ListNode mySolution(ListNode head, int left, int right){

        ListNode[] ptrs = findLeftRightNode(head, left, right);
        ListNode leftPtr = ptrs[0];
        ListNode prevleftPtr = ptrs[2];
        ListNode rightPtr = ptrs[1];
        ListNode nextrightPtr = ptrs[3];

        ListNode prev, curr, nextNode;
        
        prev = null;
        curr = leftPtr;
        nextNode = curr==null? null: curr.next;

        while (curr!=rightPtr.next) {
            
            curr.next = prev;

            prev = curr;
            curr = nextNode;
            nextNode = nextNode==null? null: nextNode.next;
        }

        if(prevleftPtr!=null){
            prevleftPtr.next = rightPtr;
        } 
        else{
            head = rightPtr;
        }
        if(leftPtr!=null) leftPtr.next = nextrightPtr;

        return head;
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        return mySolution(head, left, right);
    }

    public static void main(String[] args) {
        
    }
}

