import java.io.*;
import java.util.*;

/**
   The interface for the program.
   @author Gary Baldanza
   @author Dalina Beckham
*/

public class BatChatMenu {
/**
   The main function for BatChat, containing all of the different
   menu options and the display
   @param args the command line argument variable
*/
   public static void main (String[] args) {
      // Gotta welcome the user :P
      System.out.println("********************************************************************");
      System.out.println("*00000000000000000000000********************00000000000000000000000*");
      System.out.println("******000000000000000000********0**0********000000000000000000******");
      System.out.println("*********0000000000000000*******0000*******0000000000000000*********");
      System.out.println("***********0000000000000000****000000****00000000000000000**********");
      System.out.println("************000000000000000000000000000000000000000000000***********");
      System.out.println("*************0000000000000000000000000000000000000000000************");
      System.out.println("************000000000000000000000000000000000000000000000***********");
      System.out.println("*********************00000000000000000000000000*********************");
      System.out.println("***************************00000000000000***************************");
      System.out.println("******************************00000000******************************");
      System.out.println("*******************************000000*******************************");
      System.out.println("********************************0000********************************");
      System.out.println("*********************************00*********************************");
      System.out.println("********************************************************************");
      System.out.println("*************************Welcome to BatChat*************************");
      System.out.println("********************************************************************");
      
      // array list for users and messages
      ArrayList<User> userList = new ArrayList<User>();
      ArrayList<Message> messageList = new ArrayList<Message>();
      
      // set up first fileName
      String line = null;
      // load the User information into the array
      try {
         FileReader fileReader = new FileReader("userLog.txt");
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         line = bufferedReader.readLine();
         int lineCounter = 0;
         String addName, addPassword, addBirth, addTown, addAbout;
         addName = null;
         addPassword = null;
         addBirth = null;
         addTown = null;
         addAbout = null;
         ArrayList<String> subList = new ArrayList<String>();
         while ((line != null)) {
            // options by line
            if (lineCounter%6==0) {
               addName = line;
            } else if (lineCounter%6 == 1) {
               addPassword = line;
            } else if (lineCounter%6 == 2) {
               addAbout = line;
            } else if (lineCounter%6 == 3) {
               addBirth = line;
            } else if (lineCounter%6 == 4) {
               addTown = line;
            } else if (lineCounter%6 == 5) {         
               String[] ripped = line.split("\\s");
               subList = new ArrayList<String>(Arrays.asList(ripped));
               User temp = new User(addName, addPassword, subList, addAbout, addBirth, addTown);
               userList.add(temp);
            }
            lineCounter = lineCounter + 1;
            line = bufferedReader.readLine();        
         }   
      } catch (FileNotFoundException ex) {
         System.out.println("The program was unable to import data from userLog.txt");
      } catch (IOException ex) {
         System.out.println("The program was unable to import data from userLog.txt");
      }
      
      line = null;

      // read the Messages into the array
      try {
         FileReader fileReader = new FileReader("messageLog.txt");
         BufferedReader bufferedReader = new BufferedReader(fileReader);
         line = bufferedReader.readLine();
         String addAuthor, boolSet, daMessage, addTarget;
         addAuthor = null;
         boolSet = null;
         daMessage = null;
         addTarget = null;
         ArrayList<String> addHash = new ArrayList<String>();
         boolean stateSet = false;
         int lineCounter = 0;
         while (line != null) {
            if (lineCounter%5==0) {
               addAuthor = line;
            } else if (lineCounter%5 == 1) {
               boolSet = line;
               if (boolSet.equals("true")) {
                  stateSet = true;
               } else {
                  stateSet = false;
               }
            } else if (lineCounter%5 == 2) {
               daMessage = line;
            } else if (lineCounter%5 == 3) {
               addTarget = line;
            } else if (lineCounter%5 == 4) {
               String[] ripped2 = line.split("\\s");
               addHash = new ArrayList<String>(Arrays.asList(ripped2));        
               Message temp = new Message(daMessage, addAuthor, stateSet, addTarget, addHash);
               messageList.add(temp);
               stateSet = false;
            }
            lineCounter = lineCounter + 1;
            line = bufferedReader.readLine();                
         }   
      } catch (FileNotFoundException ex) {
         System.out.println("The program was unable to import data from messageLog.txt");
      } catch (IOException ex) {
         System.out.println("The program was unable to import data from messageLog.txt");
      }

      // variables for the loop
      boolean run = true;
      boolean loggedIn = false;
      boolean adminState = false;
      String currentUser = null;
      ArrayList<String> currentSubs = null;
      String currentPassword = null;
      
      // the BatChat Menu Loop thingy of death(ish)
      while (run) {
         
         // if the user is logged in, display the feed
         if (loggedIn) {
         // display the feed
            System.out.println("");
            System.out.println("******************************BATFEED*******************************");
            for (Message m : messageList) {
               String messageAuthor = m.getAuthor();
               if (currentUser.equals("Batman")) {
                  m.printMessageInfo();
               } else if (m.getPrivacy()) {
                  m.printMessageInfo();
               } else if (currentUser.equals(messageAuthor)) {
                  m.printMessageInfo();
               } else if (m.getTarget().equals(currentUser)) {
                  m.printMessageInfo();
               } else {
                  if (currentSubs != null){
                     for (String s : currentSubs) {
                        if (s.equals(messageAuthor) && ((m.getTarget().equals(" ")) || (m.getTarget().equals("")))) {
                           m.printMessageInfo();
                        }
                     }
                  }
               }
            }
            System.out.println("********************************************************************");
         }
         
         // list the options based on who is logged in
         System.out.println("");
         // interface 1
         if (!loggedIn) {
            System.out.println("V: View the public feed");
            System.out.println("R: Register a new account");
            System.out.println("L: Log in to BatChat");
         }
         // interface 2
         if (loggedIn) {
            System.out.println("P: Post a new message");
            System.out.println("C: Change your password");
            System.out.println("R: Remove a subscription");
            System.out.println("T: View trend statistics");
            System.out.println("H: Search for messages by hashtag");
            System.out.println("L: Log out of BatChat");
         }
         // interface 2.5
         if (loggedIn && !adminState) {
            System.out.println("N: View your notifications");
            System.out.println("A: Add a new subscriber");
            System.out.println("S: View your current subscriptions");
         }
         // interface 3
         if (adminState) {
            System.out.println("V: View all users");
            System.out.println("D: Delete a user");
         }
         // we will always want to be able to quit
         // or search for a user
         System.out.println("U: Search for a user");
         System.out.println("Q: Quit the system");
         
         // getting the user input
         System.out.print("Entry: ");
         Scanner userInput = new Scanner(System.in);
         String uInput = userInput.next();
         
         // all the different options
         if (uInput.equals("V") && !loggedIn) {
         // view feed as public
            System.out.println("******************************BATFEED*******************************");
            for (Message m : messageList) {
               if (m.getPrivacy()) {
                  System.out.println(m.getMessage());
               } 
            }
            System.out.println("********************************************************************");
         
         } else if (uInput.equals("R") && !loggedIn) {
         // register
            System.out.print("Please enter a username: ");
            Scanner credInput = new Scanner(System.in);
            String newName = credInput.next();
            boolean notFound = true;
            for (User u : userList) {
               if (u.getName().equals(newName)) {
                  notFound = false;
                  System.out.println("ERROR: The provided username already exists");
                  break;
               }
            }
            if (notFound) {
               // password
               System.out.print("Please enter a password: ");
               Scanner passInput = new Scanner(System.in);
               String newPass = passInput.next();
               // birthday
               System.out.println("What is your birthday?");
               System.out.print("Entry: ");
               Scanner passInput2 = new Scanner(System.in);
               String newBirth = passInput2.nextLine();
               // hometown
               System.out.println("What is your hometown?");
               System.out.print("Entry: ");
               Scanner passInput3 = new Scanner(System.in);
               String newTown = passInput3.next();
               // personal info
               System.out.println("Tell us about yourself!");
               System.out.print("Entry: ");
               Scanner passInput4 = new Scanner(System.in);
               String newAbout = passInput4.nextLine();
               // create the user!
               ArrayList<String> newSubs = new ArrayList();
               User temp = new User(newName, newPass, newSubs, newAbout, newBirth, newTown);
               userList.add(temp);
               saveUsers(userList);
               System.out.println("Account with username " + newName + " created successfully");
            }
         
         } else if ((uInput.equals("L") || uInput.equals("l")) && !loggedIn) {
         // log in
            System.out.print("Please enter a username: ");
            Scanner credInput = new Scanner(System.in);
            String nameCred = credInput.next();
            System.out.print("Please enter a password: ");
            Scanner passInput = new Scanner(System.in);
            String passCred = passInput.next();
            boolean notFound = true;
            for (User u : userList) {
               if (u.getName().equals(nameCred)) {
                  if (u.getPassword().equals(passCred)) {
                     loggedIn = true;
                     currentUser = u.getName();
                     currentSubs = u.getSubscriptions();
                     currentPassword = u.getPassword();
                     // if you're the administrator, enable advanced options
                     if (nameCred.equals("Batman")) {
                        adminState = true;
                     }
                  } else {
                     System.out.println("ERROR: Incorrect username or password");
                  }
                  notFound = false;
               } 
            }
            if (notFound) {
               System.out.println("ERROR: Incorrect username or password");
            }
         } else if ((uInput.equals("P") || uInput.equals("p")) && loggedIn) {
         // post a message
            System.out.print("Message: ");
            Scanner messageInput = new Scanner(System.in);
            String mInput = messageInput.nextLine();
            if (mInput.length() > 140) {
               System.out.println("ERROR: Message Overflow");
            } else {
               String[] tearApart = mInput.split("\\s");
               ArrayList<String> wordList = new ArrayList<String>(Arrays.asList(tearApart));
               String newTarget = "";
               ArrayList<String> newHash = new ArrayList<String>();
               boolean targetNFound = true;
               for (String s : wordList) {
                  if (s.startsWith("@") && targetNFound) {
                     newTarget = s.substring(1);
                     boolean match = false;
                     for (User u : userList) {
                        if (u.getName().equals(newTarget) && !(u.getName().equals("Batman"))) {
                           match = true;
                           break;
                        }
                     }
                     if (!match) {
                        newTarget = "";
                     }
                     targetNFound = false;
                  }
                  if (s.startsWith("#")) {
                     newHash.add(s.substring(1));
                  }
               }
               System.out.println("Private (P) or Public (O)?");
               Scanner privInput = new Scanner(System.in);
               String pInput = privInput.next();
               boolean tempPrivacy;
               if ((pInput.equals("P")) || (pInput.equals("p"))) {
                  tempPrivacy = false;
               // defaults to public
               } else {
                  tempPrivacy = true;
               }
               Message temp = new Message(mInput, currentUser, tempPrivacy, newTarget, newHash);
               messageList.add(temp);
               saveMessages(messageList);
            }
         
         } else if ((uInput.equals("A") || uInput.equals("a")) && loggedIn && !adminState) {
         // add a subscriber
            System.out.print("Enter Username: ");
            Scanner subInput = new Scanner(System.in);
            String subCred = subInput.next();
            boolean nFound = true;
            if (currentSubs != null) {
               for (String s : currentSubs) {
                  if (subCred.equals(s)) {
                     System.out.println("You are already subscribed to this user.");
                     nFound = false;
                     break;
                  }
               }
            }
            if (nFound) {
               for (User u2 : userList) {
                  if ((subCred.equals(u2.getName())) && !(subCred.equals("Batman")) && !(subCred.equals(currentUser))) {
                     currentSubs.add(subCred);
                     System.out.println("You have added " + subCred + " to your subscription list");
                     saveUsers(userList);
                     nFound = false;
                     break;
                  }
               }
            }
            if (nFound && (subCred.equals(currentUser))) {
               System.out.println("ERROR: You cannot subscribe to yourself");
            }
            if (nFound && !(subCred.equals(currentUser))) {
               System.out.println("ERROR: This user does not exist");
            }
         
         } else if ((uInput.equals("C") || uInput.equals("c")) && loggedIn) {
         // change password
            System.out.println("Please enter your password");
            Scanner passInput = new Scanner(System.in);
            String passCred = passInput.next();
            if (passCred.equals(currentPassword)) {
               System.out.println("Please enter a new password");
               System.out.print("Entry: ");
               Scanner passInput2 = new Scanner(System.in);
               String passCred2 = passInput2.next();
               System.out.println("Please re-enter your new password");
               System.out.print("Entry: ");
               Scanner passInput3 = new Scanner(System.in);
               String passCred3 = passInput3.next();
               if (passCred3.equals(passCred2)) {
                  for (User u : userList) {
                     if (currentUser.equals(u.getName())) {
                        u.changePassword(passCred2);
                        currentPassword = u.getPassword();
                        System.out.println("Password changed successfully!");
                        saveUsers(userList);
                        break;
                     }
                  } 
               } else {
                  System.out.println("ERROR: Passwords do not match");
               }
            } else {
               System.out.println("ERROR: Authentication Failed");
            }
         
         } else if ((uInput.equals("L") || uInput.equals("l")) && loggedIn) {
         // log out
            loggedIn = false;
            adminState = false;
            currentUser = null;
            currentSubs = null;
            currentPassword = null;

         // interface 2.5   
         } else if ((uInput.equals("R") || uInput.equals("r")) && loggedIn) {
         // remove subscriber
            System.out.println("Please enter the user you want to unsubscribe from");
            System.out.print("Entry: ");
            Scanner rInput = new Scanner(System.in);
            String rCred = rInput.next();
            boolean found = false;
            for (String s : currentSubs) {
               if (s.equals(rCred)) {
                  found = true;
                  break;
               }
            }
            if (found) {
               for (User u : userList) {
                  if (u.getName().equals(currentUser)) {
                     u.removeSubscriptions(rCred);
                     currentSubs = u.getSubscriptions();
                     saveUsers(userList);
                  }
               }
            } else {
               System.out.println("ERROR: You are not subscribed to this user");
            }

         // interface 2.5   
         } else if ((uInput.equals("N") || uInput.equals("n")) && loggedIn && !adminState) {
         // view your notifications
            System.out.println("****************************Batification****************************");
            for (Message m : messageList) {
               if (m.getTarget().equals(currentUser)) {
                  m.printMessageInfo();
               }
            }
            System.out.println("********************************************************************"); 
               
         } else if ((uInput.equals("S") || uInput.equals("s")) && loggedIn && !adminState) {
         // view your current subscriptions
            System.out.println("****************************Batscription****************************");
            for (String s : currentSubs) {
               System.out.println(s);
            }
            System.out.println("********************************************************************");
               
         } else if ((uInput.equals("T") || uInput.equals("t")) && loggedIn) {
         // view current trend statistics
            ArrayList<Trends> trending = new ArrayList<Trends>();
            for (Message m : messageList) {
               for (String s1 : m.getHashtag()) {
                  boolean nFound = true;
                  for (Trends t : trending) {
                     if (s1.equals(t.getTrend())) {
                        t.setAmount(t.getAmount() + 1);
                        nFound = false;
                        break;
                     }
                  }
                  if (nFound) {
                     Trends tempT = new Trends(1, s1);
                     trending.add(tempT);
                  }
               }
            }
            System.out.println("******************************BatTrend******************************");
            for (Trends t : trending) {
               System.out.println(t.getTrend());
               for (int i=t.getAmount(); i>0; i--) {
                  System.out.print("*");
               }
               System.out.println("");
            }
            System.out.println("********************************************************************");       
               
         } else if ((uInput.equals("H") || uInput.equals("h")) && loggedIn) {
         // display all messages with the attached hashtag
            System.out.println("Please enter the hashtag");
            System.out.print("Entry: ");
            Scanner hashInput = new Scanner(System.in);
            String hashCred = hashInput.next();
            System.out.println("********************************************************************");
            System.out.println("Messages with hashtag: " + hashCred);
            for (Message m : messageList) {
               String messageAuthor = m.getAuthor();
               for (String s : m.getHashtag()) {
                  if (m.getPrivacy() && hashCred.equals(s)) {
                     m.printMessageInfo();
                  } else if (currentUser.equals(messageAuthor) && hashCred.equals(s)) {
                     m.printMessageInfo();
                  } else {
                     if (currentSubs != null){
                        for (String s1 : currentSubs) {
                           if (s1.equals(messageAuthor) && hashCred.equals(s)) {
                              m.printMessageInfo();
                           }
                        }
                     }
                  }
               }
            }
            System.out.println("********************************************************************");
               
         } else if ((uInput.equals("V") || uInput.equals("v")) && adminState) {
         // view da users
            System.out.println("****************************BatChatUsers****************************");
            for (User u : userList) {
               if ((!u.getName().equals("Batman"))) {
                  System.out.println("User: " + u.getName());
                  System.out.println("Password: " + u.getPassword());
               }
            }
            System.out.println("********************************************************************");
               
         } else if ((uInput.equals("D") || uInput.equals("d")) && adminState) {
         // delete a user
            System.out.println("Please enter the password");
            System.out.print("Entry: ");
            Scanner adminInput = new Scanner(System.in);
            String adminCred = adminInput.next();
            if (adminCred.equals(currentPassword)) {
               System.out.println("Please enter a user to annihilate");
               System.out.print("Entry: ");
               Scanner adminInput2 = new Scanner(System.in);
               String adminCred2 = adminInput2.next();
               boolean nFound = true;
               for (User u : userList) {
                  if (u.getName().equals(adminCred2)) {
                     userList.remove(u);
                     nFound = false;
                     ArrayList<Message> removeM = new ArrayList<Message>();
                     for (Message m : messageList) {
                        if ((m.getAuthor().equals(adminCred2)) || (m.getTarget().equals(adminCred2))) {
                           removeM.add(m);
                        }
                     }
                     messageList.removeAll(removeM);
                     for (User u1 : userList) {
                        u1.removeSubscriptions(adminCred2);
                     }
                     saveUsers(userList);
                     saveMessages(messageList);
                     System.out.println("The user " + adminCred2 + " has been successfully destroyed!");
                     break;
                  }
               }
               if (nFound) {
                  System.out.println("ERROR: This user does not exist!");
               }
            } else {
               System.out.println("ERROR: Admin authentication failed");
            }
         
         // the guaranteed available options regardless of login state
         } else if ((uInput.equals("Q") || uInput.equals("q"))) {
         // quit the system
            saveUsers(userList);
            saveMessages(messageList);
            run = false;
            System.out.println("********************************************************************");
            System.out.println("*00000000000000000000000********************00000000000000000000000*");
            System.out.println("******000000000000000000********0**0********000000000000000000******");
            System.out.println("*********0000000000000000*******0000*******0000000000000000*********");
            System.out.println("***********0000000000000000****000000****00000000000000000**********");
            System.out.println("************000000000000000000000000000000000000000000000***********");
            System.out.println("*************0000000000000000000000000000000000000000000************");
            System.out.println("************000000000000000000000000000000000000000000000***********");
            System.out.println("*********************00000000000000000000000000*********************");
            System.out.println("***************************00000000000000***************************");
            System.out.println("******************************00000000******************************");
            System.out.println("*******************************000000*******************************");
            System.out.println("********************************0000********************************");
            System.out.println("*********************************00*********************************");
            System.out.println("********************************************************************");
            System.out.println("********************Thank you for using BatChat!********************");
            System.out.println("********************************************************************");
            
         } else if ((uInput.equals("U") || uInput.equals("u"))) {
         // search for a user
            System.out.print("Please enter a username: ");
            Scanner aInput = new Scanner(System.in);
            String aCred = aInput.next();
            boolean notFound = true;
            for (User u : userList) {
               if ((aCred.equals(u.getName())) && !(aCred.equals("Batman"))) {
                  notFound = false;
                  System.out.println("******************************UserInfo******************************");
                  u.printUserInfo();
                  System.out.println();
                  System.out.println("*******************************Posts********************************");
                  if (loggedIn) {
                     boolean subscribed = false;
                     for (String s : currentSubs) {
                        if (s.equals(aCred)) {
                           subscribed = true;
                           break;
                        }
                     }
                     for (Message m: messageList) {
                        if (u.getName().equals(m.getAuthor()) && (u.getName().equals(currentUser) || subscribed || m.getPrivacy())) {
                           m.print();
                        }
                     }
                  } else {
                     for (Message m : messageList) {
                        if ((u.getName().equals(m.getAuthor()) && m.getPrivacy())) {
                           m.print();
                        }
                     }
                  }
                  System.out.println("********************************************************************");
                  break;
               }
            }
            if (notFound) {
               System.out.println("ERROR: This user does not exist");
            }
            
         } else {
         // invalid input
            System.out.println("Invalid option, please try again");
         }
      }
   }
/**
   The save users function, designed to save all the users registered
   with BatChat to a file called userLog.txt
   @param uList an ArrayList<User> that holds the users and their
   respective data
*/
   static void saveUsers(ArrayList<User> uList) {
      File file = new File("userLog.txt");
      try {
        FileWriter fw = new FileWriter(file);
        BufferedWriter save = new BufferedWriter(fw);
        for (User u : uList) {
           save.write(u.getName());
           save.newLine();
           save.write(u.getPassword());
           save.newLine();
           save.write(u.getAbout());
           save.newLine();
           save.write(u.getBirthday());
           save.newLine();
           save.write(u.getHome());
           save.newLine();
           ArrayList<String> subList = u.getSubscriptions();
           if (subList == null) {
              save.write("");
           } else {
              for (String sub : subList) {
                save.write(sub + " ");
              }
           }
           save.newLine();
        }
        save.close();
      } catch (IOException ex) {
        System.out.println("ERROR: IOException");
      }
   }
/**
   The save messages function, designed to save all the messages used
   by BatChat to a file called messageLog.txt
   @param mList an ArrayList<Message> that holds the messages and their
   respective data
*/
   static void saveMessages(ArrayList<Message> mList) {
      File file = new File("messageLog.txt");
      try {
        FileWriter fw = new FileWriter(file);
        BufferedWriter save = new BufferedWriter(fw);
        for (Message m : mList) {
           save.write(m.getAuthor());
           save.newLine();
           if (m.getPrivacy()) {
              save.write("true");
           } else {
              save.write("false");
           }
           save.newLine();
           save.write(m.getMessage());
           save.newLine();
           save.write(m.getTarget());
           save.newLine();
           ArrayList<String> hashList = m.getHashtag();
           if (hashList == null) {
              save.write("");
           } else {
              for (String hash : hashList) {
                save.write(hash + " ");
              }
           }
           save.newLine();
        }
        save.close();
      } catch (IOException ex) {
        System.out.println("ERROR: IOException");
      }  
   }  
}
