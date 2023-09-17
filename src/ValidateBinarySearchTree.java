public class ValidateBinarySearchTree {

    long previosVal = Long.MIN_VALUE;
    private boolean mySoltion(TreeNode node){
        //do inorder traversal to validate BST
        //store previous node in a instance variable
        // if any node is in incorrect place then as compared to previous node (which smaller node we will find it)

        if(node==null){
            return true;
        }

        boolean isLeftValid = mySoltion(node.left);
        if(node.val<=previosVal || !isLeftValid){
            //during inorder traversal of BST we always get previousVal < current node val (ie sorted order), if this property breaks anywhere then tree is not a valid BST
            return false;
        }
        previosVal = node.val;
        boolean isRightValid = mySoltion(node.right);
        if(!isRightValid){
            return false;
        }

        //if all the above conditions are valid (ie if conditions didnt execute) then return true
        return true;


    }



    private boolean neetcodeSolution(TreeNode node, long left, long right){

        if (node==null) {
            return true;
        }

        if (left>=node.val || node.val>=right) {
            return false;
        }
        

        boolean isLeftValid = neetcodeSolution(node.left, left, node.val);
        boolean isRightValid = neetcodeSolution(node.right, node.val, right);

        if(!isLeftValid || !isRightValid){
            return false;
        }
        else{
            return true;
        }


    }

    public boolean isValidBST(TreeNode root) {
        // return mySoltion(root);      
        return neetcodeSolution(root, Long.MIN_VALUE, Long.MAX_VALUE);  
    }
}
