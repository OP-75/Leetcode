import java.util.Collections;
import java.util.PriorityQueue;

class MedianFinder {
    
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        
        maxHeap.add(num);
    


        //rebalance the heaps if they are imbalanced
        while((Math.abs(minHeap.size()-maxHeap.size())>1) || (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek()>minHeap.peek())) {
            
            if (minHeap.size()>maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
            else if(minHeap.size()<maxHeap.size()){
                minHeap.add(maxHeap.poll());
            }
            else if(!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek()>minHeap.peek()){
                minHeap.add(maxHeap.poll());
            }

        }
    }
    
    public double findMedian() {
        
        if (minHeap.size()==maxHeap.size()) {
            return (double)(minHeap.peek()+maxHeap.peek())/2.0;
        } 
        else if(minHeap.size()>maxHeap.size()){
            return minHeap.peek();
        }
        else{
            return maxHeap.peek();
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */

public class FindMedianfromDataStream {
 
}