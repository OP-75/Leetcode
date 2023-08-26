import java.util.*;

public class ThreeSum {

    private static void insertInMap(int val, Map<Integer, Integer> hm) {

        if (hm.containsKey(val)) {
            int freq = hm.get(val);
            hm.put(val, freq + 1);
        } else {
            hm.put(val, 1);
        }

    }

    private static List<List<Integer>> mySolution(int[] nums) {
            //this approach doesnt work & has time of n^2
        
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for (int i : nums) {
            insertInMap(i, freqMap);
        }
    


        HashMap<Integer, List<Integer>> memo = new HashMap<>(freqMap.size());
    
        for (Integer integer : freqMap.keySet()) {
            memo.put(integer*-1, null);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Integer c : freqMap.keySet()) {
            
            int negC = c*-1; //caz a+b+c=0, so a+b=-c, so now we have a 2sum problem
            
            for (Integer b : freqMap.keySet()) {


               

                int a = negC - b;
                if (freqMap.containsKey(a)) {

                    if (memo.get(c)!=null && memo.get(a)!=null && memo.get(b)!=null) {
                        //if all a,b,-c are not null that means this combination has be previously checked & put in ans
                        continue; 
                    }
                    //check if any of the 2 nums are duplicate
                    if (c==a && a==b && freqMap.get(a)<=2) continue;
                    if (b==a && freqMap.get(a)<=1) continue;
                    if (b==c && freqMap.get(b)<=1) continue; //cant pick same element 2 times
                    if (c==a && freqMap.get(a)<=1) continue;
                    

                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(c);
                    tmp.add(b);
                    tmp.add(a);

                    memo.put(c, tmp);
                    memo.put(b, tmp);
                    memo.put(a, tmp);

                    

                    ans.add(tmp);
                    break;
                }
                
            }

        }

        return ans;

    }

    private static HashSet<Integer> addToSet(List<Integer> nums){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.size(); i++) {
            set.add(nums.get(i));
        }
        return set;
    }



    private static List<List<Integer>> neetcodeSolution(int[] nums){
        //to use the 2 pointer approach u have to sort the nums like in 2sum-II


        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            
            int up = nums.length-1;
            int down = i+1;

            int start = down, end = up;

            if(i>0 && nums[i]==nums[i-1]) continue;

            while (down<=end && up>=start && up>down) {
                //[-2,0,0,2,2] in this testcase we are getting we will get duplicate output so

                if (nums[i]+nums[down]+nums[up]==0){
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[i]);
                    tmp.add(nums[down]);
                    tmp.add(nums[up]);

                    if (!ans.isEmpty()) {
                        //since the "nums" arr is sorted the duplicate ans are going to be next to each other. so u can remove/skip adding them here itself
                        if (ans.get(ans.size()-1).equals(tmp)) {
                            up--;
                            down++;
                            continue;   
                        }
                    }

                    ans.add(tmp);

                    //IMPORTANT do up-- and down++ since curr up&down nums are already added to ans
                    up--;
                    down++;
                } 

                else if (nums[i]+nums[down]+nums[up]>0){
                    up--;
                } 
                else if (nums[i]+nums[down]+nums[up]<0){
                    down++;
                } 

            }

        }
        

        //remove duplicates (this was giving TLE)
        // for (int i = 0; i < ans.size(); i++) {
        //     HashSet<Integer> s1 = addToSet(ans.get(i));

        //     for (int j = i+1; j < ans.size(); j++) {
        //         HashSet<Integer> s2 = addToSet(ans.get(j));

        //         if (s1.equals(s2)) { //remove duplicate
        //             ans.remove(j);
        //             j--; //IMPORTANT DO j-- caz when we remove jth ele all elements to right are shifted by one to left ie j+1 becomes j, j+2 becomes j+1...
        //             //so we have to check the new element at "j" again
        //         }

        //     }


        // }





        return ans;
        
    }
    
    public static void main(String[] args) {

        List<List<Integer>> ans;

        System.out.println();
        ans = neetcodeSolution(new int[]{-2,0,3,-1,4,0,3,4,1,1,1,-3,-5,4,0});
        for (List<Integer> list : ans) {
            System.out.print(list.toString()+", ");
        }
        
        ans = neetcodeSolution(new int[]{-2,0,0,2,2});
        for (List<Integer> list : ans) {
            System.out.print(list.toString()+", ");
        }

        System.out.println();
        ans = neetcodeSolution(new int[]{-1,0,1,2,-1,-4});
        for (List<Integer> list : ans) {
            System.out.print(list.toString()+", ");
        }
        System.out.println();
        ans = neetcodeSolution(new int[]{0,0,0});
        for (List<Integer> list : ans) {
            System.out.print(list.toString()+", ");
        }
    }
}
