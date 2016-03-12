import java.util.*;
import java.io.*;

/**
   The Message Class for the program.
   @author Kenneth Cassine
   @author Gary Baldanza
   @author Dalina Beckham
*/

class Message {

// the variables

   boolean privateState;
   String author;
   String message;
   String target;
   ArrayList hashtag = new ArrayList();
   
/**
 Constructor
@param message, author, privateState to include with a message.
*/
   public Message(String message, String author, boolean privateState, String target, ArrayList hashtag){
      this.message = message;
      this.author = author;
      this.privateState = privateState;
      this.target = target;
      this.hashtag = hashtag;
   }
/**
   The set privacy method
   @param privateState the boolean to which the privacy is set
*/ 
   public void setPrivacy(boolean privateState){this.privateState=privateState;}
/**
   The get target method
   @return target the username to which the message is directed (if any)
*/    
   public String getTarget() {return target;}
/**
   The set target method
   @param target the String username to which the target is set
*/    
   public void setTarget(String target) {this.target = target;}
/**
   The get hashtag/trend method
   @return ArrayList<String> the array of hashtags attached to this message
*/    
   public ArrayList<String> getHashtag() {return hashtag;}
/**
   The add hashtag method, which can attach a hashtag to the message
   @param y the hashtag we want to add
*/   
   public void addHashtag(String y) {hashtag.add(y);}
/**
   The get message
   @return String the message
*/   
   public String getMessage(){return message;}
/**
   The get privacy method
   @return boolean the current privacy setting of the message
*/   
   public boolean getPrivacy(){return privateState;}
/**
   The get author method
   @return String the author of the message
*/   
   public String getAuthor(){return author;}
/**
   The print message with info method, which prints out author followed by the message
*/   
   public void printMessageInfo(){System.out.println(getAuthor()+ ": " + getMessage());}
/**
   The print message method, which prints the method and nothing else
*/    
   public void print() {System.out.println(message);}
}
