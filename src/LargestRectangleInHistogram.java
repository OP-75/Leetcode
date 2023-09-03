import java.util.Stack;


//my solution is not working check out neetcodes solution

// https://youtu.be/zx5Sw9130L0?si=79lWeV1xHcdTWbPt

class Node{
    int height;
    int indx;
    Node(int height, int indx){
        this.height = height;
        this.indx = indx;
    }
}

public class LargestRectangleInHistogram {

    //my solution is wrong!!!!
    private static int mySolution(int[] heights) {
        int maxArea = 0;

        Stack<Integer> st = new Stack<>();
        int minHeightInSt = 0;
        for (int i = 0; i < heights.length; i++) {

            int currHeight = heights[i];
            

            if (!st.isEmpty()) {
                int areaWithStack = (st.size() + 1) * Math.min(minHeightInSt, currHeight);

                int areaOfCurrBar = currHeight * 1; // width = 1

                if (areaOfCurrBar >= areaWithStack) {
                    while (!st.isEmpty()) {
                        st.pop();
                    }
                    st.add(currHeight);
                    
                    minHeightInSt = currHeight;
                } else if (areaWithStack > areaOfCurrBar) {

                    st.add(currHeight);
                    minHeightInSt = Math.min(minHeightInSt, currHeight);
                }

            } else {
                st.add(currHeight);
                minHeightInSt = currHeight;
            }

            // the st is updated so check & update max area
            int currArea = st.size() * minHeightInSt;
            maxArea = Math.max(currArea, maxArea);
        }


       

        return maxArea;
    }



    private static int neetcodeSolution(int[] heights){

        int maxArea = 0;
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < heights.length; i++) {

            Node currNode = new Node(heights[i], i);

            if (st.isEmpty()) {
                st.add(currNode);
                maxArea = Math.max(maxArea, currNode.height);
                continue;
            }

            if(currNode.height<st.peek().height){

                //we need this to keep the track of the last node that was > currNode that was popped from the stack, caz when we are pop bigger nodes from the stack we are sort of extending the currNode that is smaller behind, this is to cover cases like [2,1,2] if we dont do this the maxArea = 2, but its acually = 3
                int startAfterExtention = i;

                while (!st.isEmpty() && currNode.height<st.peek().height) {
                    //we can this since we will only be inserting elements that are >= top in the stack

                    Node poppedNode = st.pop();
                    

                    int breadth = i - poppedNode.indx;
                    int height = poppedNode.height;
                    int nodeArea = breadth*height;

                    maxArea = Math.max(maxArea, nodeArea);

                    startAfterExtention  = poppedNode.indx;
                }
                
                currNode = new Node(heights[i], startAfterExtention);
                st.add(currNode);

            }
            else if(currNode.height>=st.peek().height){
                st.add(currNode);
            }


        }


        //pop all the remaing elements, these elements can make a rectangle till the end of the arr
        int end = heights.length;

        while (!st.empty()) {
            Node poppedNode = st.pop();
                
            int breadth = end - poppedNode.indx;
            int height = poppedNode.height;
            int nodeArea = breadth*height;

            maxArea = Math.max(maxArea, nodeArea);
        }

        return maxArea;

    }

    public static int largestRectangleArea(int[] heights) {
        // return mySolution(heights);
        return neetcodeSolution(heights);
    }

    public static void main(String[] args) {

       
        System.out.println(largestRectangleArea(new int[] {2, 1, 2}));
        System.out.println(largestRectangleArea(new int[] { 1, 2, 3, 4, 5 }));
        System.out.println(largestRectangleArea(new int[] { 2, 1, 5, 6, 2, 3 }));
        System.out.println(largestRectangleArea(new int[] { 2, 4 }));
    }
}
