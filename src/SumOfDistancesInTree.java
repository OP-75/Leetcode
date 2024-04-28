import java.util.*;

class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        // MLE :(


        // node - node map:
        HashMap<Integer, ArrayList<Integer>> nodeConnMap = new HashMap<>();

        //put the tree in a HM for easy traversal
        for (int node = 0; node < n; node++) {

            ArrayList<Integer> connectionsToCurrNode = new ArrayList<>();

            for (int[] edge : edges) {

                if (edge[0] == node) {
                    connectionsToCurrNode.add(edge[1]);
                } else if (edge[1] == node) {
                    connectionsToCurrNode.add(edge[0]);
                }

            }
            nodeConnMap.put(node, connectionsToCurrNode);
        }



        int[] distances = new int[n];
        boolean[] isVisited = new boolean[n];
        int[][] dp = new int[n][n];

        for (int node = 0; node < distances.length; node++) {
            
            for (int endPointNode = 0; endPointNode < n; endPointNode++) {
                
                distances[node] += getDist(node, endPointNode, nodeConnMap, isVisited, dp);

            }

        }

        return distances;

    }

    private int getDist(int currNode, int endNode, HashMap<Integer, ArrayList<Integer>> nodeConnMap, boolean[] isVisited, int[][] dp) {
        if (currNode==endNode) {
            return 0;
        }

        if (dp[currNode][endNode]!=0) {
            return dp[currNode][endNode];
        }

        isVisited[currNode] = true;
        ArrayList<Integer> connectionsFromCurrNode = nodeConnMap.get(currNode);

        
        for (int i = 0; i < connectionsFromCurrNode.size(); i++) {
            
            int nextNode = connectionsFromCurrNode.get(i);

            if (!isVisited[nextNode]) {
                int distFromNextToEnd = getDist(nextNode, endNode, nodeConnMap, isVisited, dp);

                if (distFromNextToEnd!=-1) {
                    isVisited[currNode] = false;
                    dp[currNode][endNode] = 1+distFromNextToEnd; 
                    return 1+distFromNextToEnd;
                }
                
            } 
        }
        
        isVisited[currNode] = false;
        return -1; //ie no path exists from currNode to endNode
        



    }
}