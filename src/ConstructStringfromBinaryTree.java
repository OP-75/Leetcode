public class ConstructStringfromBinaryTree {
    
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
    public String tree2str(TreeNode root) {

        StringBuilder ans = new StringBuilder("");

        preOrder(root, ans);

        return ans.toString();

    }

    private void preOrder(TreeNode root, StringBuilder ans) {

        if (root==null) {
            return;
        }

        ans.append(root.val);

        if (root.left!=null) {
            ans.append('(');
        }
        
        if (root.left==null && root.right!=null) {
            ans.append("()");
        }

        preOrder(root.left, ans);
        
        if (root.left!=null) {
            ans.append(')');
        }


        if (root.right!=null) {
            ans.append('(');
        }
        
        preOrder(root.right, ans);
        
        if (root.right!=null) {
            ans.append(')');
        }
        
    }



}
