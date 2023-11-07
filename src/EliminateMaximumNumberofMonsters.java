import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class EliminateMaximumNumberofMonsters {
    public int eliminateMaximum(int[] dist, int[] speed) {
        // return mySolution(dist, speed);
        // return mySolution2(dist, speed);
        return mySolution3(dist, speed);
    }

    private int mySolution(int[] dist, int[] speed) {

        PriorityQueue<Double> timeToArriveHeap = new PriorityQueue<>(dist.length + 1);

        for (int i = 0; i < dist.length; i++) {
            timeToArriveHeap.add((double) dist[i] / (double) speed[i]);
        }

        int monstersKilled = 0;
        int currTime = 0;
        //chose the monster we lowest ETA to kill (greedy approach)
        while (!timeToArriveHeap.isEmpty()) {

            double monsterETA = timeToArriveHeap.poll() - currTime;

            if (monsterETA <= 0) {
                break;
                // ie if monsterETA = 0 then monster is already here so we've lost (given in
                // question)
            } else {
                monstersKilled++;
            }

            currTime++;

        }

        return monstersKilled;
    }

    private int mySolution2(int[] dist, int[] speed) {

        ArrayList<Double> timeToArriveList = new ArrayList<>(dist.length + 1);

        for (int i = 0; i < dist.length; i++) {
            timeToArriveList.add((double) dist[i] / (double) speed[i]);
        }

        int monstersKilled = 0;
        int currTime = 0;

        Collections.sort(timeToArriveList);

        //chose the monster we lowest ETA to kill (greedy approach)
        for (int i = 0; i < timeToArriveList.size(); i++) {
            
            double monsterETA = timeToArriveList.get(i) - currTime;

            if (monsterETA <= 0) {
                break;
                // ie if monsterETA = 0 then monster is already here so we've lost (given in
                // question)
            } else {
                monstersKilled++;
            }

            currTime++;

        }

        return monstersKilled;
    }
    private int mySolution3(int[] dist, int[] speed) {
        //Wrong ans
        ArrayList<Double> timeToArriveList = new ArrayList<>(dist.length + 1);

        for (int i = 0; i < dist.length; i++) {
            timeToArriveList.add((double) dist[i] / (double) speed[i]);
        }

        Collections.sort(timeToArriveList);

        for (int i = 1; i < speed.length; i++) {

            if (timeToArriveList.get(i)==timeToArriveList.get(i-1)) {
                return i;
            }
        }

        return timeToArriveList.size();
    }
}
