
package project4ckoivu;

/**
 * Node class for binary tree. Key value is a State object
 * @author Chris Koivu
 */
public class Node {
    State state; // data used as key value    
    Node leftChild; // this node’s left child
    Node rightChild; // this node’s right child
        
   
    public void displayNode()
    {
        System.out.println(state.getState());
    }
}