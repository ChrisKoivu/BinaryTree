package project4ckoivu;

/**
 * Tree class for implementing the binary tree
 * @author Chris Koivu
 */
public class Tree {
    /** root pointer for Tree */
    private Node root; // the only data field in Tree
    /** Node pointer for current node */
    private Node current;
    /** Node pointer for parent node */
    private Node parent;
    /** Stack for iterative traversals */
    Stack iStack = new Stack(50);
    /** boolean flag indicating left child */
    private boolean isLeftChild;    
    
    /** Constructor for Tree class */
    public Tree(){
       root = null;
    }
    
    /**
     * Find node whose State matches the key
     * @param key state name we are searching for 
     * @return Node that matches the state name 
     */
    public Node find(String key)
    {
        current = root; // start at root
        isLeftChild = true;
        
        while(current.state.getState().equals(key)== false) // while not found
        {
           if(key.compareTo(current.state.getState()) < 0){ 
              parent = current;
              isLeftChild = true;
              current = current.leftChild;
           }else {
              parent = current;
              isLeftChild = false;
              current = current.rightChild;
           }
           if(current == null) 
              return null; // node not found
         }
         return current;
     }// end find method
        
      /**
       * create Node containing State object, and insert it into Tree
       * @param s State object we are inserting into Node
       */
      public void insert(State s)
      {
         Node newNode = new Node(); // make new node
         newNode.state = s; // insert data
         if(root==null) // no node in root
             root = newNode;
         else // root occupied
         {
            current = root; // start at root           
            while(true) 
            {
               parent = current;
               if(s.getState().compareTo(current.state.getState()) < 0)//go left
               {
                  current = current.leftChild;
                  if(current == null) 
                  { // insert on left
                    parent.leftChild = newNode;
                    return;
                  }
                } // end if go left
               else 
               {
                  current = current.rightChild;
                  if(current == null) // if end of the line
                  { // insert on right
                     parent.rightChild = newNode;
                     return;
                  }
               } // end else go right
             } // end while
         } // end else not root
     }  // end insert method
    
     /**
      * Deletion driver to delete node that matches State name requested
      * based on 0, 1, or 2 children. no returns. 
      * @param s String representing name of State
      */
     public void deleteNode(String s)
     {
        Node t;
        t = find(s);    
        
        // if node exists
        if (t != null){
           //if zero children
           if(t.leftChild == null && t.rightChild == null)
               zeroDelete();
           //if one child
           if(t.leftChild == null || t.rightChild == null){
               oneDelete();
           // must be two children
           }else{
              twoDelete();
           }
         }         
       } // end deleteNode method
    
       /**
        * performs deletion on nodes with zero children, by changing
        * parent leftChild or rightChild pointer to null. no returns
        */
       private void zeroDelete(){
          if (current == root)
              current = null;
          if(isLeftChild){
              parent.leftChild = null;
          }else{
              parent.rightChild = null;    
          }
       } //zeroDelete
    
       /**
        * performs deletion on nodes with a left or right child,
        * by changing the pointer of the parent left child or right
        * child pointer to current left child or right child pointer
        * no returns
        */
       private void oneDelete(){
          // check if node has left subtree
          if (current.rightChild == null){
            if (current == root)
              root = current.leftChild;
            if (isLeftChild){
              parent.leftChild = current.leftChild;
            }else {
              parent.rightChild = current.leftChild;
            }
          } // end check for left subtree
        
          // check if node has right subtree
          if (parent.leftChild == null){
             if (current == root)
                 root = current.rightChild;
             if (isLeftChild){
                 parent.leftChild = current.rightChild;               
             }else{
                 parent.rightChild = current.rightChild;
             } 
          }   //end check for right subtree   
      } // end one delete method
      
      
       /**
       * Performs deletion on Nodes with 2 children
       * @return boolean true if successful
       */
      private boolean twoDelete(){
         Node successor = getSuccessor(current);
         // if current node is root, change root to successor
         if(current == root)
            root = successor;
         // if node is left child, parents left child points to successor
         else if(isLeftChild)
            parent.leftChild = successor;
         else
           //node is a right child, parents right child points to successor
            parent.rightChild = successor;
         successor.leftChild = current.leftChild; 
         return true;    
      } //end two delete method
      
      /**
       * Returns Node of successor for twoDelete method
       * @param delNode the node we want to delete
       * @return Node that is the successor
       */
       private Node getSuccessor(Node delNode)
       {
          Node successorParent = delNode;
          Node successor = delNode;
          Node current = delNode.rightChild; // go to right child
          while(current != null) // until no more
          { // left children,
             successorParent = successor;
             successor = current;
             current = current.leftChild; // go to left child
          }
          // if successor is not right child, make connections
          if(successor != delNode.rightChild) // right child,
          {  
             successorParent.leftChild = successor.rightChild;
             successor.rightChild = delNode.rightChild;
          }
          return successor;
       } // end get successor method
      
       /**
        * This method traverses the Binary tree in preorder
        * using an iterative routine by implementing a Stack
        * no return
        */
       public void iterNLR(){        
          iStack.clearStack();
          Node current = root;
          iStack.push(current);
          while (!iStack.isEmpty()){
             current = iStack.pop();
             current.displayNode();                
             if (current.rightChild != null)  
                 iStack.push(current.rightChild);
             if (current.leftChild != null)  
                 iStack.push(current.leftChild);            
          }  // end while loop 
        } // end iterNLR method
    
       /**
       * this method performs an inorder traversal of binary tree using an
       * iterative routine implementing a Stack
       */
       public void iterLNR(){       
         iStack.clearStack();
         Node current = root;        
         while (true){
            if (current != null){
                iStack.push(current);
                current = current.leftChild;                
            } else {
              if (iStack.isEmpty())
                  return;
              current = iStack.pop();
              current.displayNode();
              current = current.rightChild;
            }
        }  // end while loop 
    } // end iterLNR method
    
     /**
      * This method performs a interative postorder traversal
      * of a binary tree implementing a Stack
      */
      public void iterLRN(){    
        if(root == null) 
           return; 
        Node current = root; 
        while(true) { 
        if(current != null) {
            if(current.rightChild != null) 
              iStack.push(current.rightChild);
            iStack.push(current);
            current = current.leftChild;
            continue;
         } // end if block for left subtree
 
         if(iStack.isEmpty()) 
           return;
         current = iStack.pop(); 
            if(current.rightChild != null && !iStack.isEmpty() && 
                    current.rightChild == iStack.peek()) {
              iStack.pop( );
              iStack.push(current);
              current = current.rightChild;
            } else {
              current.displayNode();
              current = null;
            }
          }  // end while loop
        }  // end iterLRN method
     
        /**
         * perform recursive scans on the binary tree
         */
        public void recursiveScans(){
            System.out.println("");
            System.out.println("Recursive NLR Scan");
            recNLR(root);
            System.out.println("");
            System.out.println("Recursive LNR Scan");
            recLNR(root);
            System.out.println("");
            System.out.println("Recursive LRN Scan");
            recLRN(root);             
        } // end recursive scans
        
        /** 
         * perform iterative scans on the binary tree
         */        
         public void iterativeScans(){
            System.out.println("Iterative NLR Scan");
            System.out.println("State Name");
            iterNLR();
       
            System.out.println(String.format("\n\n"));
            System.out.println("Iterative LNR Scan");
            iterLNR();
        
            System.out.println(String.format("\n \n"));
            System.out.println("Iterative LRN Scan");
            iterLRN();        
         }
          
          /**
           * performs a recursive preorder scan on
           * binary tree
           * @param localRoot the parent node of the tree or subtree
           */
          private void recNLR (Node localRoot){ 
             if(localRoot!= null ) {
                 localRoot.displayNode();
                 recNLR( localRoot.leftChild );
                 recNLR( localRoot.rightChild );            
              }
           } // end recNLR
            
            /**
             * performs a recursive inorder scan on binary tree
             * @param localRoot the parent node of the tree or subtree
             */
            private void recLNR (Node localRoot) 
	    {					
                if (localRoot != null)
	        { 
		   recLNR (localRoot.leftChild);
		   localRoot.displayNode();
		   recLNR (localRoot.rightChild);
	        }
	    }  // end recLNR()  
            
            /**
             * performs a recursive post order scan of the binary tree
             * @param localRoot the parent node of the tree or subtree
             */
            private void recLRN (Node localRoot){ 
               if(localRoot!= null ) {
                  recLRN( localRoot.leftChild );
                  recLRN( localRoot.rightChild );
                  localRoot.displayNode();
               }
            } // end recLRN
} // end Tree class
