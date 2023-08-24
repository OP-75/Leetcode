import java.util.*;

public class TopKFrequentElements {

    private static void insertInMap(int val, Map<Integer, Integer> hm) {

        if (hm.get(val) != null) {
            int freq = hm.get(val);
            hm.put(val, freq + 1);
        } else {
            hm.put(val, 1);
        }

    }

    private static int[] mySolution(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            insertInMap(nums[i], hm);
        }

        ArrayList<Integer> freq_list = new ArrayList<Integer>();
        freq_list.addAll(hm.values());
        Collections.sort(freq_list);
        Collections.reverse(freq_list); // caz we need it in dcreasing order

        int[] ans = new int[k];
        int ans_counter = 0;

        Set<Integer> keys = hm.keySet();

        System.out.println(keys);
        for (int i = 0; i < k; i++) {

            int f = freq_list.get(i);

            for (int key : keys) {
                if (hm.get(key) == f) {
                    System.out.println(key);
                    ans[ans_counter] = key;
                    ans_counter++;

                    keys.remove(key); // since curr key is aready added in ans[] remove it so as to avaoid repeatition
                                      // if freq is same

                    break;
                }
            }
        }

        return ans;

    }

    private static int[] neetCodeSolution(int[] nums, int k) {

        //this solution uses a variation of bucket sort/counting sort, see neetcode's video

        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            insertInMap(nums[i], hm);
        }

        return topK(hm, nums, k);
        
    }

    private static int[] topK(Map<Integer, Integer> hm, int[] nums, int k){

        List<Integer> freqValArr[] = new List[nums.length+1];
        Arrays.fill(freqValArr, null);
        //this arr stores the list of numbers that occur with freq "f" at indx "f" in arr
        //also in worst case if all elements are same in all their freq will be nums.length which need to be put at indx nums.length+1

        for (Integer val : hm.keySet()) {
            int freq = hm.get(val);
            if (freqValArr[freq]==null) {
                freqValArr[freq] = new ArrayList<Integer>();
            }
            freqValArr[freq].add(val);
        }


        //now to get the top K elements traverse the freqValArr from end
        int[] ans = new int[k];
        int count = 0;
        for (int i = freqValArr.length-1; i > 0 && count<k; i--) {
            if (freqValArr[i]!=null) {
                Iterator<Integer> it = freqValArr[i].iterator();
                while (it.hasNext() && count<k) {
                    ans[count] = it.next();
                    count++;
                }
            }
        }


        return ans;

    }

    public static void main(String[] args) {
        
        System.out.println(Arrays.toString(neetCodeSolution(new int[]{1,1,1,2,2,3}, 2)));
        System.out.println(Arrays.toString(neetCodeSolution(new int[]{1}, 1)));

    }
}
