package project4ckoivu;
import java.io.*;
/**
 * This class contains the array of State objects we are using to build a
 * binary tree, and functions to perform on the binary tree.
 * @author Chris Koivu
 */

public class State_Controller {
    /** array of State objects */
    private State[] stateArray;
    /** counter for State objects passed to stateArray */
    private int numItems;
    /** binary tree for delete with zero children */
    Tree bt = new Tree();
    /** binary tree for delete with one child */
    Tree bt2 = new Tree();
    /** binary tree for delete with two children */
    Tree bt3 = new Tree();
    
    /** Class constructor */
    public State_Controller (int maxSize){
      stateArray = new State [maxSize]; 
    } // end constructor
    
    /**
     * method to build array of State Objects. return void.
     * @throws IOException  
    */
    public void buildArray() throws IOException{
       FileInterface myIO = new FileInterface("Tree_States_Fall2012.txt");
       String inString;
       
       inString = myIO.getLine();
       while (inString != null){         
         stateArray[numItems] = new State(inString,"","",0,"",0);
         ++numItems;    
         inString = myIO.getLine();
       } // end while loop
     } // end buildArray method 
    
    /**
     * method for building binary trees that we will be performing
     * insert, traversal, and delete operations on. No returns.
     */
    public void buildTrees(){        
        int i = 0;
        while(stateArray[i]!=null){
           bt.insert(stateArray[i]);
           bt2.insert(stateArray[i]);
           bt3.insert(stateArray[i]); 
           ++i;
        }
    } // end buildTree method
    
    /**
     * method to call recursive and iterative traversal
     * methods on the binary trees. no returns
     */
    public void treeTraversals(){
        bt.iterativeScans();
        bt.recursiveScans();        
    }
   
    /**
     * calls method in Tree class to perform deletions on nodes with zero 
     * children, one child, and two children. no returns.
     */
    public void deleteNodes(){    
        System.out.println(String.format("\n \n"));
        System.out.println("After deleting nodes with zero children");
        bt.deleteNode("Maryland");
        bt.iterLNR();
        
        System.out.println(String.format("\n \n"));
        System.out.println("After deleting nodes with one child");
        bt2.deleteNode("Massachusetts");
        bt2.deleteNode("New_Hampshire");
        bt2.iterLNR();
        
        System.out.println(String.format("\n \n"));
        System.out.println("After deleting nodes with two children");
        bt3.deleteNode("Vermont");
        bt3.deleteNode("New_York");
        bt3.iterLNR();
    }
            
    
}// end State_Controller class
