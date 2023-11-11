import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DesignGraphWithShortestPathCalculator {
    
}

class Graph {

    int[][] adjacencyMatrix;
    public Graph(int n, int[][] edges) {
        adjacencyMatrix = new int[n][n];

        for (int[] edge : edges) {
            //this is a directed graph ie edge[0] -> edge[1] the cost = edge[2]
            adjacencyMatrix[edge[0]][edge[1]] = edge[2];             
        }

        //if edge cost = 0 then no edge exits since all the give edges have edge cost between 1 & 10^6
    }
    
    public void addEdge(int[] edge) {
        adjacencyMatrix[edge[0]][edge[1]] = edge[2];     
    }
    
    public int shortestPath(int node1, int node2) {
        
        //IMP see: https://youtu.be/V6H1qAeB-l4?si=A3j_9AnfgDDiqCsc
        PriorityQueue<GraphNode> pq = new PriorityQueue<>(new GraphNodeComparator());

        int[] dist = new int[adjacencyMatrix.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[node1] = 0;

        pq.add(new GraphNode(node1, 0));

        while (!pq.isEmpty()) {
            
            GraphNode nodeObj = pq.poll();

            for (int nextNode = 0; nextNode < adjacencyMatrix[nodeObj.node].length; nextNode++) {
                
                int distFromNodeObjToNextNode = nodeObj.distanceTillNode+adjacencyMatrix[nodeObj.node][nextNode]; //the distance to nodeObj + dist from nodeObj to nextnode

                //check if edge exist between nodeObj -> next node AND check if that total distance is less than prev distance throght previos paths taken stored in `dist` array
                if (adjacencyMatrix[nodeObj.node][nextNode]!=0 && distFromNodeObjToNextNode<dist[nextNode]) {
                    dist[nextNode] = distFromNodeObjToNextNode;
                    pq.add(new GraphNode(nextNode, distFromNodeObjToNextNode));
                }

            }

        }


        int ans = dist[node2]==Integer.MAX_VALUE? -1 : dist[node2];

        return ans;
    }

}


class GraphNode {

    int node, distanceTillNode;

    GraphNode(int node, int distanceTillNode){
        this.node = node;
        this.distanceTillNode = distanceTillNode;
    }
    
}
class GraphNodeComparator implements Comparator<GraphNode> {

    @Override
    public int compare(GraphNode o1, GraphNode o2){
        return Integer.compare(o1.distanceTillNode, o2.distanceTillNode);
    }
}

/**
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 */
