
package project4ckoivu;

/**
 * Stack class for iterative binary tree traversals
 * @author Chris Koivu
 */
public class Stack {
    private int maxSize;
    private Node[] stackArray;
    private int top;

    public Stack(int s) // constructor
    {
       maxSize = s;
       stackArray = new Node[maxSize];
       top = -1;
    }
    
    public void push(Node j) // put item on top of stack
    {
       stackArray[++top] = j;
    }
 
    public Node pop() // take item from top of stack
    {
       return stackArray[top--];
    }
    
    public Node peek() // peek at top of stack
    {
       return stackArray[top];
    }

    public boolean isEmpty() // true if stack is empty
    {
      return (top == -1);
    }
    
    public void clearStack()
    {
       top = -1;
    }

} // end Stack class
