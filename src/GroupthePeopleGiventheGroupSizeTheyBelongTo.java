import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupthePeopleGiventheGroupSizeTheyBelongTo {

    private static void addToHM(int grpSize, int indx, HashMap<Integer, List<Integer>> hm, List<List<Integer>> ans){

        List<Integer> tmpList = null;
        

        if(hm.containsKey(grpSize)){
            tmpList = hm.get(grpSize);

            if(tmpList.size()==grpSize){
                ans.add(tmpList);

                tmpList = new ArrayList<>();
                tmpList.add(indx);
            }
            else{
                tmpList.add(indx);
            }
        }
        else{
           tmpList = new ArrayList<>();
           tmpList.add(indx);
        }
        
        hm.put(grpSize, tmpList);
    }

    private static List<List<Integer>> mySolution(int[] groupSizes){

        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        List<List<Integer>> ans = new ArrayList<>();


        for (int i = 0; i < groupSizes.length; i++) {
            addToHM(groupSizes[i], i, hm, ans);
        }

        for(Integer key: hm.keySet()){
            List<Integer> val = hm.get(key);
            
            ans.add(val);
           
        }



        return ans;
    }
    
    public static List<List<Integer>> groupThePeople(int[] groupSizes) {
        return mySolution(groupSizes);
    }


    public static void main(String[] args) {
        System.out.println(groupThePeople(new int[]{2,1,3,3,3,2}));
    }
}
