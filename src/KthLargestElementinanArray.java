import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementinanArray {

    
    private  void swap(int[] arr, int indx1, int indx2) {
		int tmp = arr[indx1];
		arr[indx1] = arr[indx2];
		arr[indx2] = tmp;
	}

	private  int randomRangeRandom(int start, int end) {
		Random random = new Random();
		int number = random.nextInt((end - start) + 1) + start; // .nextInt() genrates num between 0 and (end - start) + 1 (exculsive)
		return number;
	}


    private  int quickselect(int arr[], int k, int start, int end, boolean useRandomPivot){

        //IMP!!! - if arr has only 1 or 2 elements then our code will not work, it'll return wrong ans
        //below 2 are base cases
        if(start==end){
            return arr[start];
        }
        else if(end-start==1){
            if (arr[start]>arr[end]) {
                swap(arr, start, end);
            }
            return arr[k]; //since subarr of len = 2 is sorted just return the i-th element
        }

        
        
        int partionIndx = partition(arr, start, end, useRandomPivot);
        
        

        if (partionIndx==k) { //we have swapped pivot now its in its right position
            return arr[partionIndx];
        }
        else if (partionIndx>k) {
            return quickselect(arr, k, start, partionIndx-1, useRandomPivot); //search in left subarray (start to up-1)
        }
        else{  //if up < i search in subarr starting from up+1 to end
            return quickselect(arr, k, partionIndx+1, end, useRandomPivot);
        }

    }

    private int partition(int arr[], int start, int end, boolean useRandomPivot){
        
        System.out.println("Start = "+start+" End = "+end);
        System.out.println("OG Arr before partition = "+Arrays.toString(arr));
        
        int pivotIndx = end;
        if(useRandomPivot){
            swap(arr, pivotIndx, randomRangeRandom(start, end));
        } 
        //p is where we will partion the arr
        int p = start;
        int pivot = arr[end];
        
        System.out.println("Arr before partition = "+Arrays.toString(arr));

        for (int i = start; i < end; i++) {
            if (arr[i]<=pivot) {
                swap(arr, i, p);
                p++;
            }
        }

        swap(arr, pivotIndx, p);
        //p is where we will partion the arr

        // System.out.println("Arr after swapping pivot = "+Arrays.toString(Arrays.copyOfRange(arr, start, end+1)));
        System.out.println("FULL Arr after swapping pivot = "+Arrays.toString(arr));

        return p;
    }



    private int heapSolution(int[] nums, int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : nums) {

            pq.add(i);
            if (pq.size()>k) {
                pq.poll();
            }
        }
        

        return pq.peek();
    }

    public int findKthLargest(int[] nums, int k) {
        return heapSolution(nums, k);

        //my quick select is giving TLE for some reason!
        // this is neetcode quickselect
        // return quickselect(nums, nums.length-k, 0, nums.length-1, true);
    }

    public static void main(String[] args) {
        KthLargestElementinanArray obj = new KthLargestElementinanArray();

        System.out.println(obj.findKthLargest(new int[]{3,2,1,5,4}, 2));

    }

}
