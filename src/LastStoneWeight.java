import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    


    private int mySolution(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); //now PQ is max heap

        for (int i : stones) {
            pq.add(i);
        }

        while (pq.size()>1) {
            int y = pq.poll();
            int x = pq.poll();
            
            if(x==y){
                continue;
            }
            else{
                pq.add(y-x);
            }
        }

        //now size can either be 1 or 0
        if (pq.size()==0) {
            return 0;
        }
        else{
            return pq.poll();
        }

    }

    public int lastStoneWeight(int[] stones) {
        return mySolution(stones);
    }
    
}

