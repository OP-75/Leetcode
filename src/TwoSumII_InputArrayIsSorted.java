

public class TwoSumII_InputArrayIsSorted {

    private static int[] mySolution(int[] numbers, int target){
        //the prblm mentions there is graunteed one solution
        // so make 2 pointers 1 at 0 and 1 at numbers.len - 1
        // if the 2 number sum > target make the last pointer move left by 1
        //and if the sum is < target make the first ptr go right by 1
        //this only works if arr is sorted

        int first = 0, last = numbers.length-1;
        int[] ans = new int[2];
        while (true) {
            int sum = numbers[first]+numbers[last];

            if(sum==target){
                ans[0] = first;
                ans[1] = last;
                return ans;
            }
            else if(sum>target){
                last--;
            }
            else if(sum<target){
                first++;
            }
        }

    }

    public static void main(String[] args) {
        
    }

}