// Definition for a binary tree node.

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

// see how to do Level order traversal at:
// https://www.youtube.com/watch?v=EoAsWbO7sqg

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> mySolution(TreeNode root) {
        
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {

            List<TreeNode> levelNodes = new ArrayList<>();
            while (!q.isEmpty()) {
                if (q.peek()==null) {
                    q.poll();
                    continue;
                }
                else{
                    levelNodes.add(q.poll());
                }
            }
            
            
            List<Integer> levelNums = new ArrayList<>();
            for (TreeNode node : levelNodes) {
                levelNums.add(node.val);
                if(node.left!=null) q.add(node.left);
                if(node.right!=null) q.add(node.right);
            }

            if (!levelNums.isEmpty()) {
                ans.add(levelNums);
            }
            
        }

        return ans;

    }
    public List<List<Integer>> neetcodeSolution(TreeNode root) {
        // https://youtu.be/6ZnyEApgFYg?si=kbr6ZRrR5MfQw3IH
        
        List<List<Integer>> ans = new ArrayList<>();

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {

            int currSize = q.size(); //size of queue before we pop any elements from it
            List<Integer> levelNums = new ArrayList<>();
            for (int i = 0; i < currSize; i++) {
                
                TreeNode node = q.poll();
                if (node!=null) {
                    levelNums.add(node.val);
                    q.add(node.left);
                    q.add(node.right);
                }

            }

            if (!levelNums.isEmpty()) {
                ans.add(levelNums);
            }
            
        }

        return ans;

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        // return mySolution(root);
        return neetcodeSolution(root);
    }
    
    
    public static void main(String[] args) {
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        TreeNode root2 = new TreeNode(1);

        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        List<List<Integer>> result1 = solution.levelOrder(root1);
        System.out.println("Result 1: " + result1);  // Output: [[3],[9,20],[15,7]]

        List<List<Integer>> result2 = solution.levelOrder(root2);
        System.out.println("Result 2: " + result2);  // Output: [[1]]

        List<List<Integer>> result3 = solution.levelOrder(null);
        System.out.println("Result 3: " + result3);  // Output: [[1]]
    }


    

}
