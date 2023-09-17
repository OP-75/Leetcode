import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}


public class BinaryTreeRightSideView {



    public List<Integer> rightSideView(TreeNode root) {
        return mySolution(root);
    }

    private List<Integer> mySolution(TreeNode root) {
        
        List<Integer> rightView = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            
            int qsize  = q.size();
            Integer rightMost = null;
            for (int i = 0; i < qsize; i++) {
                TreeNode node = q.poll();

                if (node!=null) {
                    
                    rightMost = node.val;
                    q.add(node.left);
                    q.add(node.right);
                }

            }

            if(rightMost!=null) rightView.add(rightMost);

        }

        return rightView;

    }


    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(1);

        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();

        List<Integer> result1 = solution.rightSideView(root1);
        System.out.println("Result 1: " + result1);  // Output: [[3],[9,20],[15,7]]

        List<Integer> result2 = solution.rightSideView(root2);
        System.out.println("Result 2: " + result2);  // Output: [[1]]

        List<Integer> result3 = solution.rightSideView(null);
        System.out.println("Result 3: " + result3);  // Output: [[1]]
    }

        
     
}
