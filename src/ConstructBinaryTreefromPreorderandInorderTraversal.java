import java.util.Arrays;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


//This solution is incomplete

public class ConstructBinaryTreefromPreorderandInorderTraversal {


    public TreeNode neetcodeSolution(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null){
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);

        int rootIndxInInorder = -1;
        for (int i = 0; i < inorder.length; i++) {
            if(inorder[i]==root.val){
                rootIndxInInorder = i;
                break;
            }
        }

        int rightMostOfLeftSubtreeIndxInInorder = rootIndxInInorder-1;
        int rightMostOfLeftSubtreeIndxInPreorder = -1;
        for (int i = 0; i < preorder.length; i++) {
            if(rightMostOfLeftSubtreeIndxInInorder>=0 && preorder[i]==inorder[rightMostOfLeftSubtreeIndxInInorder]){
                rightMostOfLeftSubtreeIndxInPreorder = i;
                break;
            }
        }

        int[] leftSubtreePreorder = rightMostOfLeftSubtreeIndxInPreorder>1? Arrays.copyOfRange(preorder, 1, rightMostOfLeftSubtreeIndxInPreorder+1): null;
        int[] leftSubtreeInorder = rightMostOfLeftSubtreeIndxInInorder>=0? Arrays.copyOfRange(inorder, 0, rightMostOfLeftSubtreeIndxInInorder+1): null;
        
        int[] rightSubtreePreorder = rightMostOfLeftSubtreeIndxInPreorder+1<preorder.length? Arrays.copyOfRange(preorder, rightMostOfLeftSubtreeIndxInPreorder+1, preorder.length): null;
        int[] rightSubtreeInorder = rootIndxInInorder+1<inorder.length? Arrays.copyOfRange(inorder, rootIndxInInorder+1, inorder.length): null;

        root.left = neetcodeSolution(leftSubtreePreorder, leftSubtreeInorder);
        root.right = neetcodeSolution(rightSubtreePreorder, rightSubtreeInorder);

        return root;


    }


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return neetcodeSolution(preorder, inorder);
    }

}



