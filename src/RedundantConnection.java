import java.util.Arrays;

class DisjointSet{

    int[] size, parents;

    DisjointSet(int[][] edges){

        size = new int[edges.length+1];
        parents = new int[edges.length+1];

        Arrays.fill(size, 1);
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }
    
    /*
     * Does path compression for "node" after finding ultimate parent
     * @params node for which we need the ultimate 
     * @returns Ultimate paraent of any node
    */
    public int findParent(int node){

        if (parents[node]==node) { 
            //ie parent of the node is node itself ie ultimate parent is found
            return node;
        }
        else{

            int ultimateParent = findParent(parents[node]);
            //performing path compression
            parents[node] = ultimateParent;
            return ultimateParent;
        }

    }


    /*
     *  Makes a union between 2 nodes (join 2 nodes) depending on their size 
     * (node with smaller size if joined with bigger size so this.find(node) func takes less size) 
     * @params node1 & node
    */
    public void union(int[] edge){

        int e1 = edge[0], e2 = edge[1];

        int pe1 = this.findParent(e1);
        int pe2 = this.findParent(e2);

        int pe1size = size[pe1];
        int pe2size = size[pe2];

        if (pe1size>pe2size) {
            
            parents[pe2] = pe1;
            size[pe1] += size[pe2];

        } else {

            parents[pe1] = pe2;
            size[pe2] += size[pe1];

        }
    }
}




public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        DisjointSet ds = new DisjointSet(edges);

        for (int[] edge : edges) {
            if (ds.findParent(edge[0])==ds.findParent(edge[1])) {
                return edge;
            }
            else{
                ds.union(edge);
            }
        }

        return null;

    }

}
