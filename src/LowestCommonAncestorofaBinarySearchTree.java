public class LowestCommonAncestorofaBinarySearchTree {

}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


class Solution {

    TreeNode LCAnode = null;
    private boolean LCA(TreeNode node, TreeNode p, TreeNode q){

        if(node==null){
            return false;
        }
        
        boolean leftSubtreeContains = LCA(node.left, p, q);
        boolean rightSubtreeContains = LCA(node.right, p, q);

        // System.out.println(node.val+" - leftBool ="+leftNode+" rightBool="+rightNode);

        if(leftSubtreeContains && rightSubtreeContains){
            if(LCAnode==null){
                LCAnode = node;
            }
            return true;
        }
        else if((leftSubtreeContains || rightSubtreeContains) && (q==node || p==node)){
            if(LCAnode==null){
                LCAnode = node;
            }
            return true;
        }
        else if(leftSubtreeContains || rightSubtreeContains){
            //ie we have only found 1 node up until node
            return true;
        }
        else{
            if(p==node || q==node){
                
                return true;
            }
            else{
                //this condition should never occur
                return false;
            }
        }

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        LCA(root, p, q);
        return LCAnode;
    }
}