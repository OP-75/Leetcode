import java.util.Arrays;

public class MaximumProfitInJobScheduling {

    int[] startTime;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {

        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < profit.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs);
        Arrays.sort(startTime);
        this.startTime = startTime;

        toString(jobs);

        int[] profitMemo = new int[jobs.length];
        Arrays.fill(profitMemo, -1);

        return calcMaxProfit(jobs, 0, startTime, profitMemo);
    }

    private int calcMaxProfit(Job[] jobs, int i, int[] startTime, int[] profitMemo) {
        if (i >= jobs.length) {
            return 0;
        }

        if (profitMemo[i]!=-1) {
            return profitMemo[i];
        }

        int takeProfit = 0, notTakeProfit = 0;

        //!IMP step!!!! otherwise u will get wrong ans
        int nextIndex = Arrays.binarySearch(startTime, jobs[i].endTime);
            
        if (nextIndex<0) {
            nextIndex = -nextIndex - 1;
        }
       
        takeProfit = jobs[i].profit + calcMaxProfit(jobs, nextIndex, startTime, profitMemo);
        // not take curr job
        notTakeProfit = calcMaxProfit(jobs, i + 1, startTime, profitMemo);

        profitMemo[i] = Math.max(notTakeProfit, takeProfit);

        return Math.max(notTakeProfit, takeProfit);
    }

    void toString(Job[] jobs) {
        for (int i = 0; i < jobs.length; i++) {
            System.out.print(jobs[i].startTime + "," + jobs[i].endTime + "," + jobs[i].profit + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MaximumProfitInJobScheduling o = new MaximumProfitInJobScheduling();

        int[] profit = { 1, 2, 8, 10, 4 };
        int[] endTime = { 5, 5, 5, 10, 8 };
        int[] startTime = { 4, 2, 4, 8, 2 };

        System.out.println(o.jobScheduling(startTime, endTime, profit));

    }

}

class Job implements Comparable<Job> {
    int startTime, endTime, profit;

    Job(int startTime, int endTime, int profit) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
    }

    @Override
    public int compareTo(Job o) {
        if (this.startTime==o.startTime) {
            return this.endTime - o.endTime;
        }
        return this.startTime - o.startTime;
    }
}