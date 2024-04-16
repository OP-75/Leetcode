public class AddOneRowToTree {
    
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        
        if(depth==1){
            // if have to add new node at the start ie before root
            root = new TreeNode(val, root, null);
            return root;
        }


        myAddRow(root, val, depth, 1);
        return root;
    }

    private void myAddRow(TreeNode root, int val, int depth, int currRowNum) {
        if (root==null) {
            return;
        }

        if (root!=null && currRowNum==depth-1) {
            root.left = new TreeNode(val, root.left, null); //new left node
            root.right = new TreeNode(val, null, root.right); //new right node
            return;
        }

        myAddRow(root.left, val, depth, currRowNum+1);
        myAddRow(root.right, val, depth, currRowNum+1);
    }

    
}