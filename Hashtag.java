import java.util.*;
import java.io.*;
public class Hashtag{

   String hashtag_word;
   ArrayList messages = new ArrayList();
   int num_usage;
   
   
   public Hashtag(String hashtag_word, Message first){
      this.messages.add(first);
      this.hashtag_word = hashtag_word;
      num_usage = 1;
   }

   public ArrayList getMessages(){return messages;}
   public String getKeyWord(){return hashtag_word;}
   public void addMessage(Message add){this.messages.add(add);}
}