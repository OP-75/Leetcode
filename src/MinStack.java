import java.util.Stack;

public class MinStack {
    private static class Node{
        int val;
        int currMin;
        Node(int val, int currMin){
            this.val = val;
            this.currMin = currMin;
        }
    }

    int globalMin = Integer.MAX_VALUE;
    Stack<Node> st = null;
    public MinStack() {

        st = new Stack<>();
        
    }
    
    public void push(int val) {
        int min = Integer.MAX_VALUE;
        if(!this.st.isEmpty()) min = this.getMin();

        st.push(new Node(val, Math.min(val, min)));
        // globalMin = Math.min(val, globalMin);
    }
    
    public void pop() {
        st.pop();
    }
    
    public int top() {
        return st.peek().val;
    }
    
    public int getMin() {
        return st.peek().currMin;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
