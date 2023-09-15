public class BalancedBinaryTree {

    boolean isBalanced = true;

    private int mySolution(TreeNode node){

        if(node==null){
            return 0;
        }

        int leftSubtreeHeight = mySolution(node.left);
        int rightSubtreeHeight = mySolution(node.right);

        if(Math.abs(leftSubtreeHeight-rightSubtreeHeight)>1){
            isBalanced = false;
        }

        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);


    }

    public boolean isBalanced(TreeNode root) {
        mySolution(root);
        return isBalanced;    
    }
}
