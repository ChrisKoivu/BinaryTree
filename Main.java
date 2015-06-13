package project4ckoivu;
import java.io.*;
/**
 * binary tree application. implements a binary tree and performs
 * recursive scans, inserts, and deletes.
 * @author Chris Koivu
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{       
        State_Controller sc = new State_Controller(50);
        sc.buildArray();
        sc.buildTrees();
        sc.treeTraversals();     
        sc.deleteNodes();
       // sc.iterTraversal();
        //sc.displayStates();e
    }
}
