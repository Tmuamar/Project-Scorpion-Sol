/** Allows for the computer to make moves.
 * basic alogrithm to go through the rows and check whether
 * the targeted card can be moved to the locations at the start of the columns**/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Moves 
{
   private ScorpionBoard startGame;
   PrintStream ps;
   static List<String> Id = new ArrayList<String>();
   private Scanner inputFile;
   private BufferedReader br;
   String deck1 = new String("");
   String deck = new String("");
   public Moves() throws FileNotFoundException 
   { 
      ps = new PrintStream("shuffled.txt");
   }
   public void soliBegin(ScorpionBoard newGame, String deck) 
   {
      startGame = newGame;
      newGame.board(deck);
      newGame.display(0);
   }
   
   public void beginGame(String deck) throws IOException 
   {
      //checking weather if there is another move
      boolean nextMoves = true;
      // Finding the next move and allowing the individual to see what is happening
      while (nextMoves)
      {
         nextMoves = findingMoves();
         startGame.display(startGame.Move++);
         traverseColumns();
      }
        startGame.useLast3Cards();
        startGame.display(0);
        nextMoves = true;
        while(nextMoves) 
        {
           // Search iteration for moves, currently executes the first move it finds
           nextMoves = findingMoves();
           
           if (!nextMoves) 
           {
               deck = "";
               System.out.println();
               System.out.println("Computer Out of Moves: ");
               System.out.println();
               startGame.display(startGame.Move++);
               System.out.print("Would you Like to play again(Y or N): " );
               Scanner nextDeck = new Scanner(System.in);
               String nextDeck1 = nextDeck.nextLine();
               
               if(nextDeck1.equals("Y"))
               {
                 
                  File file = new File("deck1.txt");
                  inputFile = new Scanner(file);
                  br = new BufferedReader(new FileReader(file));
                  deck = br.readLine();
                  
                  br.close();
                  while (inputFile.hasNext()) {
                     deck = inputFile.next();
                     Id.add(deck);
                     
                  }
                     
                      Collections.shuffle(Id);
                      String list = "";
                      list = Arrays.toString(Id.toArray()).replace("[","").replace("]","").replace(",","");
                      Id.clear();
                  ScorpionBoard newGame = new ScorpionBoard();
                  Moves computer = new Moves();
                  
                  computer.soliBegin(newGame, list);
                  computer.beginGame(list);
                  
               }else if(nextDeck1.equals("N")) {
                  System.exit(0);
               }
               nextDeck.close();
               
           } else {
              //show computer's moves
              startGame.display(startGame.Move++);
              // Check if a column is complete
              traverseColumns();
            }
        }
    }
   private boolean findingMoves() 
   {
      int card1 = 0;
      int card2 = 0;
      // First loop is for getting the last card in each Column, which could be played on.
      for (int y = 0; y < startGame.tableau.table.length; y++) 
      {
         // If there are one or more cards in the Column in focus
         if (startGame.tableau.table[y].last != null)
         {
            // Get last card in current Column (tableau.columns[i].lastCard)
            Card targetCard = startGame.tableau.table[y].last;
            // specifying the ranks
            if (targetCard.rank.equals("K")) 
            {
               card1 = 13;
            } else if (targetCard.rank.equals("Q")) {
               card1 = 12;
            } else if (targetCard.rank.equals("J")) {
               card1 = 11;
            } else if (targetCard.rank.equals("A")) {
               card1 = 1;
            }else if (targetCard.rank.equals("2")) {
               card1 = 2;
            } else if (targetCard.rank.equals("3")) {
               card1 = 3;
            }else if (targetCard.rank.equals("4")) {
               card1 = 4;
            } else if (targetCard.rank.equals("5")) {
               card1 = 5;
            } else if (targetCard.rank.equals("6")) {
               card1 = 6;
            } else if (targetCard.rank.equals("7")) {
               card1 = 7;
            } else if (targetCard.rank.equals("8")) {
               card1 = 8;
            } else if (targetCard.rank.equals("9")) {
               card1 = 9;
            } else if (targetCard.rank.equals("10")) {
               card1 = 10;
            }  else {
            }
            
            //accessing the columns in order
            
            for (int x = 0; x < startGame.tableau.table.length; x++) 
            {
               // checks for columns that is not were the card current selected is.
               if (y == x) 
               {
                  continue;
               } else {
                  Card c2 = startGame.tableau.table[x].first;
                  for (int k = 0; k < startGame.tableau.table[x].getSize(); k++) 
                  {
                     // specifying the ranks
                     if (c2.rank.equals("K")) 
                     {
                        card2 = 13;
                     } else if (c2.rank.equals("Q")) {
                        card2 = 12;
                     } else if (c2.rank.equals("J")) {
                        card2 = 11;
                     } else if (c2.rank.equals("A")) {
                        card2 = 1;
                     }else if (c2.rank.equals("2")) {
                        card2 = 2;
                     } else if (c2.rank.equals("3")) {
                        card2 = 3;
                     }else if (c2.rank.equals("4")) {
                        card2 = 4;
                     } else if (c2.rank.equals("5")) {
                        card2 = 5;
                     } else if (c2.rank.equals("6")) {
                        card2 = 6;
                     } else if (c2.rank.equals("7")) {
                        card2 = 7;
                     } else if (c2.rank.equals("8")) {
                        card2 = 8;
                     } else if (c2.rank.equals("9")) {
                        card2 = 9;
                     } else if (c2.rank.equals("10")) {
                        card2 = 10;
                     }  else {
                     }

                     // Check whether it is allowed for card2 to be moved to targetCard.
                     if (card2 == card1 - 1 && c2.suit.equals(targetCard.suit) && c2.Hidden == false) 
                     {
                        startGame.moveCard(c2.rank, c2.suit, targetCard.rank, targetCard.suit);
                        return true;
                     }
                     // If not allowed then check the next card in the column
                     c2 = c2.next; 
                  }   
               } 
            } 
            
         } else {            
            for (int x = 0; x < startGame.tableau.table.length; x++) 
            {
               if (y == x) 
               {
                  continue;
               } else {
                  Card c2 = startGame.tableau.table[x].first;
                  for (int k = 0; k < startGame.tableau.table[x].getSize(); k++) 
                  {
                     if (c2.rank.equals("K")) {
                        card2 = 13;
                     } else if (c2.rank.equals("Q")) {   
                        card2 = 12;
                     } else if (c2.rank.equals("J")) {      
                        card2 = 11;
                     } else if (c2.rank.equals("A")) {
                        card2 = 1;
                     }else if (c2.rank.equals("2")) {
                        card2 = 2;
                     } else if (c2.rank.equals("3")) {
                        card2 = 3;
                     }else if (c2.rank.equals("4")) {
                        card2 = 4;
                     } else if (c2.rank.equals("5")) {
                        card2 = 5;
                     } else if (c2.rank.equals("6")) {
                        card2 = 6;
                     } else if (c2.rank.equals("7")) {
                        card2 = 7;
                     } else if (c2.rank.equals("8")) {
                        card2 = 8;
                     } else if (c2.rank.equals("9")) {
                        card2 = 9;
                     } else if (c2.rank.equals("10")) {
                        card2 = 10;
                     }  else {
                     
                     }
                     // checking whether the card can be moved to empty column
                     if (c2.rank.equals("K") && c2.Hidden == false && c2.prev != null) 
                     {
                        startGame.moveCard(c2.rank, c2.suit, y);
                        return true;
                     }
                     c2 = c2.next;   
                  }
                    
               }
                
            }
            
         }
        
      }
      return false;
    }
   //traverses through columns and checks if done
   private void traverseColumns() 
   {
      for (int y = 0; y < startGame.tableau.table.length; y++) 
      {
         Card card = startGame.tableau.table[y].first;
       while(card != null)
         {
            if (card.Hidden && card.next == null) 
            {
               card.Hidden = false;
            }
            card = card.next;
         }
         
         if (startGame.tableau.table[y].getSize() != 13) 
         {
            break;
         }
         
         card = startGame.tableau.table[y].first;
         boolean done = true;
         int cardCounter = 0;
         do {
            if (card.rank != "K" && cardCounter == 0) {   
               done = false; 
               break;
            } else if (card.rank != "Q" && cardCounter == 1) {
                done = false;
                break;
            } else if (card.rank != "J" && cardCounter == 2) {
               done = false;
               break;
            } else if (card.rank != "10" && cardCounter == 3) {
               done = false;  
               break;
            } else if (card.rank != "9" && cardCounter == 4) { 
               done = false; 
               break;
            } else if (card.rank != "8" && cardCounter == 5) {      
               done = false; 
               break;
            } else if (card.rank != "7" && cardCounter == 6) {
               done = false;  
               break;
            } else if (card.rank != "6" && cardCounter == 7) {
               done = false;
               break;
            }  else if (card.rank != "5" && cardCounter == 8) {   
               done = false;  
               break;
            }  else if (card.rank != "4" && cardCounter == 9) {  
               done = false;   
               break;
            }  else if (card.rank != "3" && cardCounter == 10) {   
               done = false;   
               break;
            }  else if (card.rank != "2" && cardCounter == 11) {   
               done = false;   
               break; 
            }  else if (card.rank != "A" && cardCounter == 12) {
               done = false;  
               break;
            }
            cardCounter++;
            card = card.next;
            }while (card != null);
         if (done) 
         {
            startGame.addToFoundation(y, card.prev.suit);
         }
        
      }
    
   }

}