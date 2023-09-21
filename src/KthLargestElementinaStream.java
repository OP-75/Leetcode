import java.util.Collections;
import java.util.Deque;
import java.util.PriorityQueue;

public class KthLargestElementinaStream {
    
}

// https://youtu.be/hOjcdrqMoQ8?si=Uu3hOKgMrKOC9C35
class KthLargest {

    int k;
    PriorityQueue<Integer> pq;
    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>(); //min heap by default
        // FYI for max heap do - new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < nums.length; i++) {
            this.pq.add(nums[i]);
        }

        this.k = k;
        while (this.pq.size()>k) {
            this.pq.poll();
        }
    }
    
    public int add(int val) {
        //this takes log(n) time
        this.pq.add(val);
        while (this.pq.size()>k) {
            this.pq.poll();
        }

        return pq.peek(); //returns the min element of heap, which will allways be kth largest since we removed n-k smallest elements from heap

    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
