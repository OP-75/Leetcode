import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Map.Entry;

//This question is VERY HARD. See: https://youtu.be/s8p8ukTyA2I?si=F5wS1hAp3bPVowFA


class TaskFreqPair{
    char c;
    int freq;
    int nextTimeToAdd; //this is time next time we can add this task fack to Priority Queue
    TaskFreqPair(char c, int freq){
        this.c = c;
        this.freq = freq;
        this.nextTimeToAdd = 0;
    }
    TaskFreqPair(char c, int freq, int nextTimeToAdd){
        this.c = c;
        this.freq = freq;
        this.nextTimeToAdd = nextTimeToAdd;
    }
}

class TaskFreqPairComparator implements Comparator<TaskFreqPair>{

    @Override
    public int compare(TaskFreqPair o1, TaskFreqPair o2){
        return Integer.compare(o1.freq, o2.freq);
    }
}

public class TaskScheduler {
  
    private void addToHM(char c, HashMap<Character, Integer> hm){
        
        if (hm.containsKey(c)) {
            hm.put(c, hm.get(c)+1);            
        } else {
            hm.put(c, 1);  
        }
    }
    

    private int neetcodeSolution(char[] tasks, int n) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : tasks) {
            addToHM(c, hm);
        }
        
        PriorityQueue<TaskFreqPair> pq = new PriorityQueue<>(Collections.reverseOrder(new TaskFreqPairComparator()));
        for (Entry<Character, Integer> e: hm.entrySet()) {
            pq.add(new TaskFreqPair(e.getKey(), e.getValue()));
        }
        
        int time = 0;
        Queue<TaskFreqPair> q = new LinkedList<>();
        while (!pq.isEmpty() || !q.isEmpty()) {
            time++;
            
            if(!pq.isEmpty()){
                TaskFreqPair t = pq.poll();
                t.freq -= 1;
                t.nextTimeToAdd = time + n;

                if(t.freq!=0){
                    q.add(t);
                }
            }

            if (!q.isEmpty() && q.peek().nextTimeToAdd==time) {
                pq.add(q.poll());
            }
        }

        return time;
        
    }

    private int neetcodeSolutionOptimized(char[] tasks, int n) {
        HashMap<Character, Integer> hm = new HashMap<>();
        for (char c : tasks) {
            addToHM(c, hm);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (Entry<Character, Integer> e: hm.entrySet()) {
            pq.add(e.getValue());
        }
        
        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!pq.isEmpty() || !q.isEmpty()) {
            time++;
            
            if(!pq.isEmpty()){
                int freq = pq.poll();
                freq -= 1;
                int nextTimeToAdd = time + n;

                if(freq!=0){
                    q.add(new int[]{freq, nextTimeToAdd});
                }
            }

            if (!q.isEmpty() && q.peek()[1]==time) {
                pq.add(q.poll()[0]);
            }
        }

        return time;
        
    }

    public int leastInterval(char[] tasks, int n) {
        // return neetcodeSolution(tasks, n);
        return neetcodeSolutionOptimized(tasks, n);
    }

    public static void main(String[] args) {
        TaskScheduler o = new TaskScheduler();
        char[] arr;

        arr = "AAABBB".toCharArray();
        System.out.println(o.leastInterval(arr, 2));
        arr = "AAABBB".toCharArray();
        System.out.println(o.leastInterval(arr, 0));
        arr = "AAAAAABCDEFG".toCharArray();
        System.out.println(o.leastInterval(arr, 2));
        
    }
}
