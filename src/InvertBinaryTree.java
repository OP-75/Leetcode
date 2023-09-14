
//  Definition for a binary tree node.
class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
 
class Solution {
    private void mySolution(TreeNode root) {
        
        if((root==null) || (root.left==null && root.right==null)){
            return;
        }

        TreeNode tmpLeft = root.left;
        root.left = root.right;
        root.right = tmpLeft;
        tmpLeft = null; //nulling this reduces the memory usage by a small amount

        mySolution(root.left);
        mySolution(root.right);

    }



    public TreeNode invertTree(TreeNode root) {
        mySolution(root);
        return root;
    }

}

public class InvertBinaryTree {
    public static void main(String[] args) {
        
    }
}
