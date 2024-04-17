public class SmallestStringStartingFromLeaf {
    
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    String smallestStrLexographically;

    public String smallestFromLeaf(TreeNode root) {

        traverseTree(root, "");

        return smallestStrLexographically;
    
    }

    private void traverseTree(TreeNode root, String currStr) {


        if (root.left==null && root.right==null) {
            //ie leaf node
            smallestStrLexographically = getSmallerStrLexographically(Character.toString('a'+root.val) + currStr, smallestStrLexographically);    
            
            return;
        }

        if (root.left!=null) {
            traverseTree(root.left, Character.toString('a'+root.val) + currStr);
        }
        if (root.right!=null) {
            traverseTree(root.right, Character.toString('a'+root.val) + currStr);
        }

        
    }

    private String getSmallerStrLexographically(String s1, String s2){

        if (s2==null) {
            return s1;
        }
        else if(s1==null){
            return s2;
        }
        System.out.println(s1+" , "+s2);

        if (s1.startsWith(s2)) {
            return s2; 
        }
        else if(s2.startsWith(s1)){
            return s1;
        }

        int n = Math.min(s1.length(), s2.length());
        char[] s1Arr = s1.toCharArray();
        char[] s2Arr = s2.toCharArray();

        for (int i = 0; i < n; i++) {
            if (s1Arr[i]==s2Arr[i]) {
                continue;
            }
            else if (s1Arr[i]<s2Arr[i]) {
                return s1;
            }
            else {
                return s2;
            }
        }


        return null;

    }

}

