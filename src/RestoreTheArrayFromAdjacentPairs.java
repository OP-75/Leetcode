import java.util.*;

import javax.swing.RowFilter.Entry;

public class RestoreTheArrayFromAdjacentPairs {
    public int[] restoreArray(int[][] adjacentPairs) {
        return mySolution(adjacentPairs);
    }

    private int[] mySolution(int[][] adjacentPairs) {
        HashMap<Integer, List<Integer>> adjacentMap = new HashMap<>();

        for (int[] pair : adjacentPairs) {
            addToMap(adjacentMap, pair);
        }

        // System.out.println(map);

        // now lets get the edge of the array (the edge of the array will only have 1
        // adjacent number/element)
        int edge = Integer.MIN_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry : adjacentMap.entrySet()) {
            if (entry.getValue().size()==1) {
                edge = entry.getKey();
                break;
            }
        }

        int[] ans = new int[adjacentMap.size()];
        HashSet<Integer> alreadyAdded = new HashSet<>();

        ans[0] = edge;
        alreadyAdded.add(edge);

        for (int i = 1; i < ans.length; i++) {
            
            List<Integer> previousEleEdges = adjacentMap.get(ans[i-1]);

            for (Integer e : previousEleEdges) {
                if (!alreadyAdded.contains(e)) {
                    ans[i] = e;
                    alreadyAdded.add(e);
                }
            }

        }

        // System.out.println(Arrays.toString(ans));

        return ans;
    }

    private void addToMap(HashMap<Integer, List<Integer>> map, int[] pair) {

        // put pair[0] as key
        if (map.containsKey(pair[0])) {
            map.get(pair[0]).add(pair[1]);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(pair[1]);
            map.put(pair[0], list);
        }

        // put pair[1] as key
        if (map.containsKey(pair[1])) {
            map.get(pair[1]).add(pair[0]);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(pair[0]);
            map.put(pair[1], list);
        }

    }

    public static void main(String[] args) {
        RestoreTheArrayFromAdjacentPairs o = new RestoreTheArrayFromAdjacentPairs();

        int[][] pairs = { { 2, 1 }, { 3, 4 }, { 3, 2 } };

        o.restoreArray(pairs);
    }
}
