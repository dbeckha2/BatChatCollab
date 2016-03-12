import java.util.*;
import java.io.*;

/**
   The Trends class, used to help create statistical data
   @author Gary Baldanza
*/

public class Trends {

// The variables, what is trending and how often

   String trend;
   int amount;

/**
   The constructor
   @param x the current amount of the trend
   @param y the hashtag that is trending 
*/ 
   public Trends(int x, String y) {
      amount = x;
      trend = y;
   }
/**
   The get trend method
   @return trend the String trend 
*/   
   public String getTrend() {
      return trend;
   }
/**
   The get amount method
   @return amount the int amount 
*/   
   public int getAmount() {
      return amount;
   }
/**
   The set amount method
   @param x the new integer to which to set the amount
*/   
   public void setAmount(int x) {
      amount = x;
   }
/**
   The set trend method (this method was not needed)
   @param y the new String to which to set the trend
*/ 
   public void setTrend (String y) {
      trend = y;
   }

}

