import java.util.*;

public class SerializeandDeserializeBinaryTree {

}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //Preorder traversal 

        if(root==null){
            return "null";
        }

        String leftSubtreeSerialized = serialize(root.left);
        String rightSubtreeSerialized = serialize(root.right);

        return root.val+","+leftSubtreeSerialized+","+rightSubtreeSerialized;
        
    }

    // Decodes your encoded data to tree.
    int ptr = 0;
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");

        TreeNode root = deserializeHelper(nodes);
    
        return root;
    }

    private TreeNode deserializeHelper(String[] nodes){

        if(nodes[ptr].equals("null") || ptr>=nodes.length){
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(nodes[ptr]));
        
        if (node!=null) {
            ptr++;
            node.left = deserializeHelper(nodes);
            ptr++;
            node.right = deserializeHelper(nodes);
        }

        return node;
    }

}
