public class SubtreeofAnotherTree {


}



class Solution {

    private boolean traverseTree(TreeNode node, TreeNode subRoot){
        //in this func we are traversing the main tree (i.e root)!
        if (node==null) {
            return false;
        }

        if (node.val == subRoot.val) {
            boolean isSubTree = checkIfSame(node, subRoot);

            if (isSubTree) {
                return true;
            }
        }

        boolean isSubTreeOfLeft = traverseTree(node.left, subRoot);
        if (isSubTreeOfLeft) {
            return true;
        }
        boolean isSubTreeOfRight = traverseTree(node.right, subRoot);
        if (isSubTreeOfRight) {
            return true;
        }

        //if all the above conditions return false then there is no subtree. 
        return false; 
        

    }


    private boolean checkIfSame(TreeNode node, TreeNode subTreeNode) {
        
        if((node==null && subTreeNode!=null) || (subTreeNode==null && node!=null)){
            return false;
        }
        else if((node!=null && subTreeNode!=null) && (node.val != subTreeNode.val)){
            //now we know neither if them is null
            return false;
        }
        else if(node==null && subTreeNode==null){
            return true;
        }

        //below do recursive calls/preorder/DFS traversal of BOTH trees, checks if left & right subtree of both are same
        boolean isLeftSame = checkIfSame(node.left, subTreeNode.left);
        if (!isLeftSame) {
            return false;
        }
        boolean isRightSame = checkIfSame(node.right, subTreeNode.right);
        if (!isRightSame) {
            return false;
        }

        //if both left & right subtree are same & node.val is also smae as subtreenode.val then return true 
        return true;
    }


    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return traverseTree(root, subRoot);
    }
}
