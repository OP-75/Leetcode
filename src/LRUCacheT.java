//we need a Doubly linked list for this

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class LRUCache {

    class ListNode {
    int key, val;
    ListNode next, prev;

    public ListNode() {

    }

    public ListNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.next = null;
        this.prev = null;
    }

    public ListNode(int key, int val, ListNode next, ListNode prev) {
        this.key = key;
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

}


    int capacity;
    HashMap<Integer, ListNode> map; // store the key - node pair, Node is a node in DLL containing the val
    ListNode DLLHead = null, DLLTail = null;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity + 1);
    }

    public int get(int key) {

        System.out.println("Get DLL beforee");
        printDLL(DLLHead);
        printDLL(DLLTail);

        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            int returnVal = node.val;

            moveNode(node);

            System.out.println("Get DLL after");
            printDLL(DLLHead);
            printDLL(DLLTail);

            return returnVal;
        }

        return -1;
    }

    public void put(int key, int value) {

        System.out.println("put DLL beforee");
        printDLL(DLLHead);
        printDLL(DLLTail);

        if (map.size() >= capacity && !map.containsKey(key)) {
            // the least used node is at the head, since every time we use get() we move
            // that node to the tail
            map.remove(DLLHead.key);
            deleteNode(DLLHead);
        }
        else if(map.containsKey(key)){
            deleteNode(map.get(key));
            map.remove(key); //we need to update the key
        }

        ListNode node = null;
        if (DLLHead == null || DLLTail == null) {
            node = new ListNode(key, value, null, null);
            map.put(key, node);
            DLLHead = node;
            DLLTail = node;
        } else {
            // add the new node to the tail of the DLL since it contains the latest used
            // data/node
            node = new ListNode(key, value, null, DLLTail);
            DLLTail.next = node;
            DLLTail = node;

            map.put(key, node);
            
        }

        System.out.println("HM = "+map.toString()+" size = "+map.size());
        System.out.println("put DLL after");
        printDLL(DLLHead);
        printDLL(DLLTail);

    }

    private void moveNode(ListNode node) {

        /*
         * this method moves the given node and inserts it at the tail of the doubly
         * Linked List (DLL)
         * 
         * @return The new tail
         */

        if (DLLTail.next == null && DLLTail.prev == null && DLLTail == node) {
            // ie there is only 1 node in DLL, ie node==tail
            return;
        }
        else if(node==DLLTail){
            //node is already at tail so do nothing
            return;
        }
        else if(DLLHead==node){
            DLLHead = DLLHead.next;
            DLLHead.prev = null;
        }

        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;

        // make the new connections
        if (prevNode != null)
            prevNode.next = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;

        // move the node to tail
        DLLTail.next = node;
        node.next = null;
        node.prev = DLLTail;

        DLLTail = node;


    }

    private void deleteNode(ListNode node){

        if (node.next == null && node.prev == null) {
            DLLHead = null;
            DLLTail = null;
            return;
        }
        else if(node==DLLTail){
            DLLTail = DLLTail.prev;
            DLLTail.next = null;
            return;
        }
        else if(DLLHead==node){
            DLLHead = DLLHead.next;
            DLLHead.prev = null;
            return;
        }

        ListNode prevNode = node.prev;
        ListNode nextNode = node.next;

        // make the new connections
        if (prevNode != null)
            prevNode.next = nextNode;
        if (nextNode != null)
            nextNode.prev = prevNode;
    }

    public static void printDLL(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print("(" + current.key + ", " + current.val + ") ");
            current = current.next;
        }

        System.out.println();
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

public class LRUCacheT {
    public static List<Integer> callLRUCache(String[] operations, int[][] arguments) {
        LRUCache lRUCache = null;
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < operations.length; i++) {
            String operation = operations[i];
            int[] args = arguments[i];

            if (operation.equals("LRUCache")) {
                lRUCache = new LRUCache(args[0]);
                result.add(null);
            } else if (operation.equals("put")) {
                lRUCache.put(args[0], args[1]);
                result.add(null);
            } else if (operation.equals("get")) {
                result.add(lRUCache.get(args[0]));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] operations = { "LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get" };
        int[][] arguments = { { 2 }, { 1, 1 }, { 2, 2 }, { 1 }, { 3, 3 }, { 2 }, { 4, 4 }, { 1 }, { 3 }, { 4 } };

        

        List<Integer> output = callLRUCache(operations, arguments);

        System.out.println(output); // Output: [null, null, null, 1, null, -1, null, -1, 3, 4]
    }

}
