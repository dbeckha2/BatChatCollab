import java.util.*;
import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;

/**
   The User class for the program.
   @author Dalina Beckham
*/

public class User {
/**
 Non logged user Constructor
 */
	public User(){
	  username = "Anonymous";
	}
/**
 Logged user constructor
 @param name the username of the logged user
 @param password the password of the user
 @param subscriptions the arraylist of subscriptions.
*/
	public User(String username, String password, ArrayList subscriptions, String aboutMe, String birthday, String homeTown){
	  this.username = username;
	  this.password = password;
	  this.subscriptions = subscriptions;
	  this.aboutMe = aboutMe;
	  this.birthday = birthday;
	  this.homeTown = homeTown;
   } 
/**
Get name of user.
*/
	public String getName(){
         return username;
	}
/**
Get birthday of user.
*/
	public String getBirthday(){
         return birthday;
	}
/**
Get  aboutMe of user.
*/
	public String getAbout(){
         return aboutMe;
	}
/**
Get  homeTown of user.
*/
	public String getHome(){
         return homeTown;
	}

/**
Get password of user.
*/
	public String getPassword(){
         return password;
	}
/**
 Compare two names to see if they are equal based on name.
 @return true if the names match and false otherwise
   */
   public boolean equals(User other) {
      return (username.equals(other.username));
   }

/**
Get subscriptions of the user
*/
	public ArrayList getSubscriptions(){
	 return subscriptions;
	}
/**
Add subscription of the user
@param subscribeTo added user to subscribeto
*/
	public void addSubscriptions(String subscribeTo){
	 subscriptions.add(subscribeTo);
	}
/**
Remove a subscription from the user
@param removeSub the user to remove
*/
	public void removeSubscriptions(String removeSub){
	 subscriptions.remove(removeSub);
	} 
/**
Change password of the user
@param passwordChange change the password of the user
*/
	public void changePassword(String passwordChange){
	  this.password = passwordChange;
	}
   
/**
Print info of the user
*/
   public void printUserInfo(){
      System.out.print("Username: "+ getName() + "\n" + "Birthday: "+getBirthday()+ "\n"+ "Hometown: "+getHome()+ "\n"+ "About: " +getAbout()+ " \n"+ "Subscribers: " + getSubscriptions());
	}
   
//Variables
	public String username;
	private String password;
	public String homeTown;
	public String birthday;
	public String aboutMe;
	public ArrayList subscriptions = new ArrayList();
 	//public int numSubscriptions;
}


