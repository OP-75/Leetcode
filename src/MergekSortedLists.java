import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * Definition for singly-linked list.
*/
class ListNode {
int val;
ListNode next;
ListNode() {}
ListNode(int val) { this.val = val; }
ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        return mySolution(lists);
    }

    /*
     * time = O(s*logk) where s = total num of nodes in all lists
     */
    public ListNode mySolution(ListNode[] lists) {

        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new LinkedListComparator()); 

        // initializetmp by adding all heads in the tm
        for (ListNode listNode : lists) {
            if (listNode != null){
                pq.add(listNode);
            }
        }

        if (pq.isEmpty()) {
            // ie all listnode in the given lists (head of list) are null/empty
            return null;
        }

        ListNode ansTmp = null, ansHead = null;

        ListNode poppedNode = pq.poll();
        ansHead = poppedNode;

        ansTmp = ansHead;
        if (ansTmp.next != null) {
            //add the next node in PQ before making its .next null
            pq.add(ansTmp.next);
        }
        
        // make the next null since we have added this to our own new LL
        ansTmp.next = null;

        while (!pq.isEmpty()) {

            // get the smallest entry
            ListNode smallestNode = pq.poll();

            // add the new node to the ans list
            ansTmp.next = smallestNode;
            ansTmp = ansTmp.next;

            if (ansTmp.next != null) {
                // check if the list in array still has nodes after it, if yes add them to
                // treemap
                pq.add(ansTmp.next);

            }
            
            // make the next null since we have added this to our own new LL
            ansTmp.next = null;
            // remove the old entry from the treemap
        }

        return ansHead;
    }

}

class LinkedListComparator implements Comparator<ListNode>{

    @Override
    public int compare(ListNode o1, ListNode o2) {
        return Integer.compare(o1.val, o2.val);
    }

}

public class MergekSortedLists {

    public static void main(String[] args) {

    }

}
