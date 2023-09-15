
//  Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}




public class MaximumDepthofBinaryTree {

    private static int mySolution(TreeNode root, int depth) {
        if (root==null) {
            return depth;
        }

        int depthLeft = mySolution(root.left, depth+1);
        int depthRight = mySolution(root.right, depth+1);

        return Math.max(depthLeft, depthRight);
    }

    public static int maxDepth(TreeNode root) {
        return mySolution(root, 0);
    }
    public static void main(String[] args) {
        
    }
    
}
