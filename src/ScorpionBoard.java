/** Allows for the intializing of the board and allowing for the board to show moves that occurs
 * checks for previous and next items**/
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class ScorpionBoard
{
    private static Deck deckOfCards = new Deck(false);
    private Deck hFoundation;
    private Deck sFoundation;
    private Deck dFoundation;
    private Deck Last3Cards;
    private Deck cFoundation;
    public Tableau tableau;

    public int Move = 0;
    PrintStream ps;
    public ScorpionBoard() throws FileNotFoundException 
    {
       ps = new PrintStream("Output.txt");
    }
    public void board(String deck) 
    {
       inputDeck(deck);
       TableauC();
       last3();
       foundations();
    }
    

    public void display(int Move) 
    {
       System.out.println("Number of Moves: " + ++Move);
       ps.println("Number of Moves: " + ++Move);
       
       for (int i = 0; i < Last3Cards.getSize(); i++) 
       {
          System.out.print("Card ");
          ps.print("Card");
       }
       System.out.print("Foundation: ");
       ps.print("Foundation: ");
       if (hFoundation.first == null && hFoundation.last == null) 
       {
          System.out.print("* ");
          ps.print("* ");
          
       } else {
          System.out.print("Hearts");
          ps.print("Hearts");
       }

        if (sFoundation.first == null && sFoundation.last == null) 
        {
           System.out.print("* ");
           ps.print("* ");
        } else {
           System.out.print("Spades");
           ps.print("Spades");
        }
        if (dFoundation.first == null && dFoundation.last == null)
        {
           System.out.print("* ");
           ps.print("* ");
        } else {
           System.out.print("Diamonds");
           ps.print("Diamonds");
        }

        if (cFoundation.first == null && cFoundation.last == null) 
        {
           System.out.print("* ");
           ps.print("* ");
        } else {
           System.out.print("Clubs ");
           ps.print("Clubs");
        }
        
        System.out.println();
        ps.println();

        int longCol = -1;
        for (int i = 0; i < tableau.table.length; i++) 
        {
           while (tableau.table[i].getSize() > longCol) 
           {
              longCol = tableau.table[i].getSize();
           }
        }
        Card[] col = new Card[]{tableau.table[0].first, tableau.table[1].first, tableau.table[2].first, tableau.table[3].first, tableau.table[4].first, tableau.table[5].first, tableau.table[6].first};
        for (int x = 0; x < longCol; x++) 
        {
           for (int y = 0; y < col.length; y++) 
           {
              if (col[y] != null) 
              {
                 Card printed = col[y];
                 if (printed.Hidden) 
                 {
                    System.out.print("[ ]");
                    ps.print("[ ]");
                    
                 } else {
                    System.out.print(printed.rank + printed.suit + " ");
                    ps.print(printed.rank + printed.suit + " ");
                 }
                 col[y] = col[y].next;
                 
              } else {
                 System.out.print("   ");
                 ps.print("   ");
              }
            }
           System.out.println();
           ps.println(); 
        }
        System.out.println();
        ps.println();
    }
    
    public void moveCard(String firstRank, String firstSuit, String nextLocRank, String nextLocSuit) 
    {
       Card originalCard = new Card("", "");
       Card nextLocCard = new Card("", "");
       int pickedLocation = 0;
       int toOtherLocation = 0;
       
       for (int i = 0; i < tableau.table.length; i++) 
       {
           Card c = tableau.table[i].first;
           for (int j = 0; j < tableau.table[i].getSize(); j++) 
           {
              // Check for condition where selectedCard is found
              if (firstRank.equals(c.rank) && firstSuit.equals(c.suit)) 
              {
                 originalCard = c;
                 pickedLocation = i;
              }
              else if (nextLocRank.equals(c.rank) && nextLocSuit.equals(c.suit)) 
              {
                 nextLocCard = c;
                 toOtherLocation = i;
              }
              c = c.next;
            }
        }
       Card c2 = originalCard;
       while (c2 != null && c2.next != null) 
       {
          c2 = c2.next;
       }
       // The original card the was selected to move will check if it was not the first card and then set the next value for the card above it to null
       if (originalCard.prev == null) 
       {
          tableau.table[pickedLocation] = new TableauC();
       }
       else if(originalCard.prev != null)
       {
          originalCard.prev.next = null;
          tableau.table[pickedLocation].last = originalCard.prev;
       }
       originalCard.prev = nextLocCard;
       nextLocCard.next = originalCard;
       tableau.table[toOtherLocation].last = c2;
       
       // what moves the computer has been making to print out on output file
       System.out.println("Move " + firstRank + firstSuit + " to " + nextLocRank + nextLocSuit + ".");
       ps.print("Move " + firstRank + firstSuit + " to " + nextLocRank + nextLocSuit + ".");
       System.out.println();
       ps.println();
    }
    
    public void moveCard(String firstRank, String firstSuit, int columnLoc) 
    {
       Card currentSel = new Card("", "");
       int originalLoc = 0;
       int nextLoc = columnLoc;
       // traverse through the column to find the first card to be moved
       for (int i = 0; i < tableau.table.length; i++) 
       {
          Card c1 = tableau.table[i].first;
          for (int j = 0; j < tableau.table[i].getSize(); j++) 
          {
             // checking for the conditions of the card selected and then using the previous variables
             if (firstRank.equals(c1.rank) && firstSuit.equals(c1.suit)) 
             {
                currentSel = c1;
                originalLoc = i;
             }
             c1 = c1.next;
          }
       }
       
       // creating another to allow access to cards underneath that are selected
       Card c2 = currentSel;
       
       // traverse through the cards underneath the selected card
       while (c2 != null && c2.next != null) 
       {
          c2 = c2.next;
       }
       //making the changes to the selected cards location
       currentSel.prev.next = null;
       tableau.table[originalLoc].last = currentSel.prev;
       currentSel.prev = null;
       tableau.table[nextLoc].first = currentSel;
       tableau.table[nextLoc].last = c2;
       // Communicate what move the computer is making
       System.out.println("Move " + firstRank + firstSuit + " to empty column" + (nextLoc + 1));
       ps.print("Move " + firstRank + firstSuit + " to empty column" + (nextLoc + 1));
       System.out.println();
       ps.println();
    }
    public void foundations() 
    {
       hFoundation = new Deck(false);
       sFoundation = new Deck(false);
       dFoundation = new Deck(false);
       cFoundation = new Deck(false);
    }
    // method for the foundations for were the cards are placed when user has a set from K to A
    public void addToFoundation(int x, String suit) 
    {
       tableau.table[x] = new TableauC();
       if (suit.equals("H")) 
       {
          hFoundation.first = tableau.table[x].first;
       } else if (suit.equals("S")) {
          sFoundation.first = tableau.table[x].first;
       } else if (suit.equals("D")) {
          dFoundation.first = tableau.table[x].first;
       } else if (suit.equals("C")) {
           cFoundation.first = tableau.table[x].first;

       }
    }
    //goes through the deck of cards and then loops through to make sure to add the 3 cards from the deck
    private void last3() 
    {
       Last3Cards = new Deck(false);
       Card c3 = deckOfCards.first;
       int tableauMax = 49;
       do 
       {
          c3 = c3.next;
          tableauMax--;
       }while (tableauMax > 0);
       do
       {
            Last3Cards.add(c3);
            c3 = c3.next;
        }while (c3 != null);
        Last3Cards.first.prev = null;
        Last3Cards.last.next = null;
    }
    // allows for the user to use the last 3 cards of the deck that were not dealt to tabealu 
    public void useLast3Cards() 
    {

       System.out.println();
       ps.println();
       System.out.println("Out of moves Dealing Last 3 Cards");
       ps.print("Out of moves Dealing Last 3 Cards");
       System.out.println();
       ps.println();
       Card nextUp = Last3Cards.last;
       int x = 0;
       
       do 
       {
          tableau.table[x].add(new Card(nextUp.rank, nextUp.suit));
          nextUp = nextUp.prev;
          x++;
       }while(nextUp != null);
       Last3Cards = new Deck(false);
    }
    // intializes the tableau which is the 7 rows and adds cards to them
    private void TableauC() 
    {
       this.tableau = new Tableau();
       Card card = deckOfCards.first;
       
       
       for (int i = 0; i < 7; i++) 
       {
          int col = 0;
          do
          {
             tableau.add(card, col);
             card = card.next;
             col++;
          }while (col < 7);
       }
    }
    


    // converts the input text strings into a deck that can be used
    public static void inputDeck(String deck) 
    {
       deckOfCards = new Deck(false);
    
       for (int x = 0; x < deck.length(); x=x+3) 
       {
          if (x == 157) 
          {
             String rank = Character.toString(deck.charAt(x));
             String suit = Character.toString(deck.charAt(x + 1));
             deckOfCards.add(new Card(rank, suit));
          }
          
          else if (x + 2 < deck.length()) 
          {
             if (deck.charAt(x + 2) == ' ') {
                String rank = Character.toString(deck.charAt(x));
                String suit = Character.toString(deck.charAt(x + 1));
                deckOfCards.add(new Card(rank, suit));
                } else {
                   String rank = Character.toString(deck.charAt(x)) + Character.toString(deck.charAt(x + 1));
                   String suit = Character.toString(deck.charAt(x + 2));
                   deckOfCards.add(new Card(rank, suit));
                   x++;
                   
                }
            }
        }
      System.out.println(deck);
      deck = "";
    }
}
