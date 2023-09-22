import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementinanArray {

    
    private static void swap(int[] arr, int indx1, int indx2) {
		int tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}

	private static int randomRangeRandom(int start, int end) {
		Random random = new Random();
		int number = random.nextInt((end - start) + 1) + start; // .nextInt() genrates num between 0 and (end - start) + 1 (exculsive)
		return number;
	}


    private static int quickselect(int arr[], int i, int start, int end, boolean useRandomPivot){

        //IMP!!! - if arr has only 1 or 2 elements then our code will not work, it'll return wrong ans
        if(start==end){
            return arr[start];
        }
        else if(end-start==1){
            if (arr[start]>arr[end]) {
                swap(arr, start, end);
            }
            return arr[i]; //since subarr of len = 2 is sorted just return the i-th element
        }

        int pivot = start;
        int up = end, down = pivot+1;

        
        if(useRandomPivot){
            swap(arr, pivot, randomRangeRandom(start, end));
        } 
        
        

        while (up>down) {
            
            while(up>start && arr[up]>=arr[pivot]){
                up--;
            }
            while(down<end && arr[down]<arr[pivot]){
                down++;
            }
            
            if(up>down && arr[up]<arr[down]) swap(arr, up, down); //Very inportant if condition!!!!!!!!!!!

            // System.out.println("Arr at end of loop = "+Arrays.toString(Arrays.copyOfRange(arr, start, end+1)));
        }

        
        if(arr[pivot]>=arr[up]) {
            swap(arr, pivot, up);
        }
        else{ 
            throw new IllegalStateException("arr[up] = "+arr[up] + " arr[pivot] = "+arr[pivot]);
        }

        // System.out.println("Arr after swapping pivot = "+Arrays.toString(Arrays.copyOfRange(arr, start, end+1)));

        if (up==i) { //we have swapped pivot now its in its right position
            return arr[up];
        }
        else if (up>i) {
            return quickselect(arr, i, start, up-1, useRandomPivot); //search in left subarray (start to up-1)
        }
        else{  //if up < i search in subarr starting from up+1 to end
            return quickselect(arr, i, up+1, end, useRandomPivot);
        }

    }


    private int heapSolution(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(nums.length, Collections.reverseOrder());
        for (int i : nums) {
            pq.add(i);
        }
        
        while (k>1) {
            pq.poll();
            k--;
        }

        return pq.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        return heapSolution(nums, k);

        //my quick select is giving TLE for some reason!
        // return quickselect(nums, nums.length-k, 0, nums.length-1, true);
    }

}
