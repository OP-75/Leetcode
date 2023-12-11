import java.util.HashMap;

public class ElementAppearingMoreThan25PercentInSortedArray {

    public int findSpecialInteger(int[] arr) {
        // return mySolution(arr);
        return mySolution2(arr);
    }



    private int mySolution2(int[] arr) {
        
        int startIndx = 0;
        

        while (startIndx<arr.length) {
            
            int target = arr[startIndx];

            int lastIndx = binarySearchLastIndx(arr, target, startIndx);

            if ((lastIndx-startIndx+1)>arr.length/4) {
                return target;
            }

            startIndx = lastIndx+1;
        }

        return -1;
    }



    private int binarySearchLastIndx(int[] arr, int target, int startIndx) {
        
        int l = startIndx, r = arr.length-1;
        
        while (l<=r) {
            
            int mid = (l+r)/2;

            if (arr[mid]==target && (mid+1>=arr.length || arr[mid+1]>target)) {
                return mid;
            }
            else if(arr[mid]<=target){
                l = mid+1;
            }
            else if(arr[mid]>target){
                r = mid-1;
            }
        }

        
        return -1;
    }



    private int mySolution(int[] arr) {

        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i : arr) {
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }

        for (Integer key : hm.keySet()) {

            if (hm.get(key) > (arr.length / 4)) {
                return key;
            }

        }

        return -1;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,2,6,6,6,6,7,10};
        // int[] arr = {1,1};

        ElementAppearingMoreThan25PercentInSortedArray o = new ElementAppearingMoreThan25PercentInSortedArray();
        System.out.println(o.findSpecialInteger(arr));
    }

}
