import java.util.*;

// Definition for a Node.

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

public class CloneGraph {

    HashMap<Node, Node> mapping;

    private Node mySolution(Node node) {

        if(node==null){
            return null;
        }

        // do BFS to map oldNode to newNode
        Queue<Node> q = new LinkedList<>();
        q.add(node);

        Node oldNode, newNode;
        while (!q.isEmpty()) {

            oldNode = q.remove();
            if (mapping.containsKey(oldNode)) {
                continue;
            }
            newNode = new Node(oldNode.val);

            mapping.put(oldNode, newNode);

            for (Node connectedNode : oldNode.neighbors) {
                if (!mapping.containsKey(connectedNode)) {
                    q.add(connectedNode);
                }
            }
        }

        q.clear();

        // now we have a mapping of all nodes do BFS once again on old graph
        q.add(node);
        HashSet<Node> visitedNodes = new HashSet<>();
        
        while (!q.isEmpty()) {

            oldNode = q.remove();
            if (visitedNodes.contains(oldNode)) {
                continue;
            }
            Node correspondingNode = mapping.get(oldNode);

            visitedNodes.add(oldNode);

            for (Node oldNeighbor : oldNode.neighbors) {

                Node correspondingNeighbor = mapping.get(oldNeighbor);
                correspondingNode.neighbors.add(correspondingNeighbor);
                
                if(!visitedNodes.contains(oldNeighbor)){
                    // if old neighbor hasnt been visited then only add it to queue for exploration
                    q.add(oldNeighbor);
                }
                
            }
            
            
        }
        
        Node cloneRoot = mapping.get(node);
        return cloneRoot;
    }

    public Node cloneGraph(Node node) {
        mapping = new HashMap<>();
        return mySolution(node);
    }

}
