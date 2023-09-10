
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */


 // Floyds Hare & tortise algo!!!!
class Solution {
        private boolean mySolution(ListNode head){
        
        if(head==null) return false;

        //u have to start s & f from diff pos caz if they are on the same position the if(f==s) will be activated at start of loop & the code will always return true!!!
        ListNode s=head,f=head.next;

        while (f!=null && s!=null) {
            
            if(f==s){ 
                // System.out.println(s.val);
                // System.out.println(f.val);
                return true; 
            }

            s = s.next;

            if(f.next==null) f = null;
            else f = f.next.next;

        }

        return false;

    }

    public boolean hasCycle(ListNode head) {
        return mySolution(head);
    }

}

public class LinkedListCycle {

    
    public static void main(String[] args) {
        
    }
}
