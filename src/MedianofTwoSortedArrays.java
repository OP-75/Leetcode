public class MedianofTwoSortedArrays {

    // see this video before reading the code: https://youtu.be/q6IEA26hvXc
    // all test cases passed!
    private static double neetcodeSolution(int[] nums1, int[] nums2){

        final int total_size = nums1.length + nums2.length;
        final int half_size = total_size/2;

        int[] smaller_arr, bigger_arr;
        if (nums1.length<=nums2.length) {
            smaller_arr = nums1;
            bigger_arr = nums2;
        } else {
            smaller_arr = nums2;
            bigger_arr = nums1;
        }

        //now we do binary search on smaller arr
        int up = smaller_arr.length-1, down = 0;
        int mid;
        
        while (true) {
            // mid = (up+down)/2;
            mid = Math.floorDiv(up+down, 2);

            //these are indxes of ending of left subarr of bigger and smaller Array
            int tmpSmallerArrsLeftEnd = mid;
            int tmpBiggerArrsLeftEnd = (half_size - (mid+1) - 1)<0? -1: (half_size - (mid+1) - 1);

            int Sleft = tmpSmallerArrsLeftEnd<0? Integer.MIN_VALUE : smaller_arr[tmpSmallerArrsLeftEnd];
            int Sright = tmpSmallerArrsLeftEnd+1>smaller_arr.length-1? Integer.MAX_VALUE :  smaller_arr[tmpSmallerArrsLeftEnd+1];

            int Bleft = tmpBiggerArrsLeftEnd<0? Integer.MIN_VALUE : bigger_arr[tmpBiggerArrsLeftEnd];
            int Bright = tmpBiggerArrsLeftEnd+1>bigger_arr.length-1? Integer.MAX_VALUE :  bigger_arr[tmpBiggerArrsLeftEnd+1];

            if (Sleft <= Bright && Bleft <= Sright) {
                //ie we have found the left half of the merged array (ie left half of merged arr is formed from 0 to tmpBiggerArrsLeftEnd from bigger array & 0 to tmpSmallerArrsLeftEnd from smaller array)
                if (total_size%2==0) {
                    //after meging the two arr the resultangt arr will be of even size
                    double m1 = Math.max(Sleft, Bleft); //get the maximum ie edge/rightmost element from left half in the merged sorted arr
                    double m2 = Math.min(Sright, Bright); //get the minimum ie edge/leftmost element from right half in the merged sorted arr
                    return (m1+m2)/2.0;
                }
                else{
                    double median = Math.min(Sright, Bright); //get the minimum ie edge/leftmost element from right half in the merged sorted arr
                    return median;
                }
            }
            else if (Sleft > Bright) {
                up = mid - 1;
            }
            else if (Bleft > Sright) {
                down = mid + 1;
            }
        }

    }

   

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length==0){
            if(nums2.length%2!=0) return nums2[(nums2.length-1)/2];
            else return (double)(nums2[(nums2.length-1)/2]+nums2[((nums2.length-1)/2)+1])/2.0;
        } 
        if(nums2.length==0){
            if(nums1.length%2!=0) return nums1[(nums1.length-1)/2];
            else return (double)(nums1[(nums1.length-1)/2]+nums1[((nums1.length-1)/2)+1])/2.0;
        } 
        
        return neetcodeSolution(nums1, nums2);
    }

    public static void main(String[] args) {
        System.out.println(findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
        System.out.println(findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        // System.out.println(findMedianSortedArrays(new int[]{}, new int[]{2}));
    }
}
