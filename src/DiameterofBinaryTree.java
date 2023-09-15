

//more efficient solution using neetcode's method
public class DiameterofBinaryTree {

    int maxDiameter = 0;

    /*
     * @returns height of tree till node
    */
    private int neetcodeSolution(TreeNode node){

        if (node==null) {
            return 0;
        }

        int leftSubtreeHeight = neetcodeSolution(node.left);
        int rightSubtreeHeight = neetcodeSolution(node.right);

        int diameterPassingThroghtCurrNode = leftSubtreeHeight + rightSubtreeHeight + 2;
        if (diameterPassingThroghtCurrNode>maxDiameter) {
            maxDiameter = diameterPassingThroghtCurrNode;
        }

        return 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight); //height of tree till node

    }

    public int diameterOfBinaryTree(TreeNode root) {
        neetcodeSolution(root);
        return maxDiameter;
    }
    
}
