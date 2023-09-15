public class SameTree {

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
    boolean isSame = true;

    private void mySolution(TreeNode node1, TreeNode node2){
                
        if((node1==null && node2!=null) || (node2==null && node1!=null)){
            isSame = false;
            return;
        }
        else if(node1==null && node2==null){
            return;
        }
        else if(node1.val != node2.val){
            isSame = false;
            return;
        }
        
        mySolution(node1.left, node2.left);
        mySolution(node1.right, node2.right);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        mySolution(p, q);
        return isSame;   
    }
}

    public static void main(String[] args) {
        
    }

}
