
//this is a pretty tricky question
public class FindtheDuplicateNumber {

    private static int mySolution(int[] nums) {

        // This problem is extrmly hard given the constraints, watch this vid:
        // https://youtu.be/wjYnzkAhcNk?si=OfPcP5x3KHpPWLBD

        if (nums.length <= 2) {
            return nums[0]; // there are only 2 nums and 1 has to be repeating so both r same
        }

        // use Floyds Hare & tortise algo, since atleast 1 num & nums are bet [1,n] is
        // repeating it'll form a cycle (conider the num in num[i] as ptr to some indx)

        int s = 0, f = 0;
        int count = 0;
        while (true) {

            s = nums[s]; // f.next

            f = nums[nums[f]]; // f.next.next

            if (f == s) {
                break; // f & s have intersected
            }

            count++;
            // System.out.println("Iter - " + count + " f=" + f + " s=" + s + " nums[f]=" + nums[f] + " nums[s]=" + nums[s]);
        }

        int secondS = 0;
        while (true) {
            secondS = nums[secondS];
            s = nums[s];

            if (s==secondS) {
                return secondS; //s wil eventially  = secondS caz in the while list there are 2 nodes pointing at the start of the cycle (1 node outside cycle (we can find this using secondS) & 1 node inside cycle which will be found by "s")
            }
        }

    }

    public static int findDuplicate(int[] nums) {
        return mySolution(nums);
    }

    public static void main(String[] args) {
        int[] arr;

        arr = new int[] { 1, 3, 4, 2, 2 };
        System.out.println(findDuplicate(arr));
        arr = new int[] { 3, 1, 3, 4, 2 };
        System.out.println(findDuplicate(arr));
        arr = new int[] { 2, 5, 9, 6, 9, 3, 8, 9, 7, 1 };
        System.out.println(findDuplicate(arr));

    }

}
