public class BinaryTreeMaximumPathSum {

    int maxSum = Integer.MIN_VALUE;
    private int mySolution(TreeNode node){
        //use postorder/DFS traversal

        if(node==null){
            return 0;
        }

        //if any one of the paths returns a -ve ans then just put it as 0!!!!
        int maxPathSumLeft = Math.max(0, mySolution(node.left));
        int maxPathSumRight = Math.max(0, mySolution(node.right));

        int pathSumThroughCurrNode = maxPathSumLeft+maxPathSumRight+node.val;
        if (pathSumThroughCurrNode>=maxSum) {
            maxSum = pathSumThroughCurrNode;
        }
        else if (node.val>=maxSum && maxPathSumRight+node.val<node.val && maxPathSumLeft+node.val<node.val) {
            //ie sum trough both left & right paths are < curr node val,, so path starts through curr node.

            maxSum = node.val;
            return node.val;
        }

        return Math.max(maxPathSumRight+node.val, maxPathSumLeft+node.val);
    }

    public int maxPathSum(TreeNode root) {
        mySolution(root);
        return maxSum;
    }

}
