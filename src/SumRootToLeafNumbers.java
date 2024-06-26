public class SumRootToLeafNumbers {
    
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

    public int sumNumbers(TreeNode root) {
        mySumTree(root, "");
        return global_sum;
    }
    int global_sum = 0;

    private void mySumTree(TreeNode root, String numStr) {
        
        

        if (root.right!=null) {
            mySumTree(root.right, numStr+Integer.toString(root.val));
        }

        if (root.left!=null) {
            mySumTree(root.left, numStr+Integer.toString(root.val));
        }
        
        if (root.left==null && root.right==null) {
            System.out.println(numStr+Integer.toString(root.val));
            int tmp = Integer.parseInt(numStr+Integer.toString(root.val));
            global_sum += tmp;
        }

    }
}
