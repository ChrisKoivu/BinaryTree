package project4ckoivu;
import java.io.*;
import java.util.*;
/**
 * This class is an IO class that processes a text file for
 * input
 * @author Chris Koivu
 */
public class FileInterface {
   /** ArrayList storing lines from text input file */
   ArrayList <String> textLine = new ArrayList();
   /** counter for lineNumber being read */
   int lineNumber;
   /** String variable for name of text file */
   String fileName;
   /** FileInputStream fis1 */
   FileInputStream fis1;
   /** Buffered reader br1 */
   BufferedReader br1;
       
   /** Class constructor, fn is name of file read from */
   public FileInterface(String fn){
       fileName = fn;
   }
      
    
    /**
     * This method reads from a text file, stores the lines sequentially in an
     * ArrayList.    
     * @throws IOException 
     */
     private void readTextFile()throws IOException{
         String inString;        
         FileInputStream fis2 = new FileInputStream(fileName);
         BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2)); 
         
         inString = br2.readLine();
	 while (inString != null)
	 {	
           textLine.add(inString);          
           inString = br2.readLine();           
         }
         br2.close();
      } // end readTextFile
     
      /**
       * This method returns a line of text sequentially from ArrayList
       * each time this method is called. The ArrayList contains String 
       * objects from the text file
       * @return String getLine for line of text
       * @exception throws IOException
       */
       public String getLine() throws IOException{
          String temp = null;
          if (isEmpty())      //If ArrayList is empty, read file.
              readTextFile(); 
          if (lineNumber != getSize()){
            temp = textLine.get(lineNumber);
            ++lineNumber;  
          }
          return temp;
      } // end getLine
      
      /**
       * This method returns the number of lines stored in the 
       * ArrayList of Strings read from the text file
       * @return integer for the number of lines in the ArrayList
       */
      private int getSize(){
         return textLine.size();
      } // end getSize
    
      /**
       * indicates whether ArrayList of String objects is empty
       * @return true if ArrayList is empty
       */
      private boolean isEmpty(){
        return (textLine.size()==0);
      }// end isEmpty
      
     
}
