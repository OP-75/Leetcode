



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}



public class CountGoodNodesinBinaryTree {



    public int goodNodes(TreeNode root) {
        mySolution(root, root.val);
        return goodNodeCount;
    }

    int goodNodeCount = 0;
    private void mySolution(TreeNode node, int previosMax){
         //using preorder traversal/DFS


        if(node==null){
            //base condition
            return;
        }

        if(node.val>=previosMax){
            goodNodeCount++;
        }

        mySolution(node.left, Math.max(previosMax, node.val));
        mySolution(node.right, Math.max(previosMax, node.val));

    }
}
