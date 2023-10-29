import java.util.ArrayList;
import java.util.Arrays;

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int start = -1, end = -1;

        int lowerBound = newInterval[0], upperBound = newInterval[1];

        //algo: we need to delete all intervals that intersect with newInterval, then form all those deleted intervals(intersected) we will find a lowerbound & upper bound, then we put/insert a new interval {lowerBound, upperBound}, ie we have merged the intersected interval

        for (int i = 0; i < intervals.length; i++) {
            
            int[] currInterval = intervals[i];

            if (newInterval[0]<=currInterval[1]) {

                lowerBound = Math.min(lowerBound, currInterval[0]);
                start = i;

                for (int j = start; j < intervals.length; j++) {

                    if (intervals[j][0]>newInterval[1]) {
                        break;
                    }

                    end = j;
                    upperBound = Math.max(intervals[j][1], upperBound);
                }

                break;
            }

        }

        if (start==-1 || end==-1) {
            //this is for the case like intervals = [[2,4] , [7, 9]] & newInterval = [15,18] ie cases where we dont want to delete any element
            ArrayList<int[]> ansList = new ArrayList<>(); //could be that we just have to insert the interval
            ansList.addAll(Arrays.asList(intervals));

            if (ansList.size()==0) {
                ansList.add(newInterval);
                return toIntArray(ansList);
            }
            if (ansList.size()==1) {
                if (ansList.get(0)[1]<newInterval[0]) {
                    ansList.add(newInterval);
                }
                else if (ansList.get(0)[0]>newInterval[1]) {
                    ansList.add(0, newInterval);
                }
                return toIntArray(ansList);
            }
            
            for (int i = 0; i < intervals.length; i++) {
                
                if(i==0 && upperBound < intervals[i][0]){
                    ansList.add(i, new int[]{lowerBound, upperBound});
                    break;
                }

                if(i==intervals.length-1 && lowerBound > intervals[i][1]){
                    ansList.add(i+1, new int[]{lowerBound, upperBound});
                    break;
                }
                
                if (intervals[i][1]<lowerBound && intervals[i+1][0]>upperBound) {
                    ansList.add(i+1, new int[]{lowerBound, upperBound});
                    break;
                }                

            }

            return toIntArray(ansList);

        }

        int elementsToDelete = end==-1 && start==-1? 0 : end - start + 1;
        int size = intervals.length - elementsToDelete + 1; //if intervals.length = 4 and end = 1, start = 0 then after deletion of indxs 0,1 the size = 2 and we have to insert 1 interval so final size = 3  
        int[][] ans = new int[size][2];

        //now we need to delete all intervals from `start` to `end` indx (inclusive) and put a new interval from {lowerBound, upperBound}
        for (int i = 0, j = 0; i < ans.length && j < intervals.length; i++, j++) {
            
            if (j < intervals.length && j!=start) { 
                ans[i] = intervals[j];
            }
            else{ 
                ans[i] = new int[]{lowerBound, upperBound};
                j = end; //since j++ the next iteration will start from end+1
            }

        }

        return ans;

    }

    private int[][] toIntArray(ArrayList<int[]> ansList) {
        int[][] ans = new int[ansList.size()][2];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ansList.get(i);
        }
        return ans;
    }

}
