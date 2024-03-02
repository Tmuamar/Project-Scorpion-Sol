/** Basically the node class for the linked list and 
 * to set the suits and rank for the cards in the deck **/
public class Card 
{
   public Card nextC;
   public String rank;
   public String suit;
   public boolean Hidden;
   
   public Card next;
   public Card prev;
   
   public Card(String rank, String suit) 
   {
      this.rank = rank;
      this.suit = suit;
      this.Hidden = false;
   }
   public String getSuit()
   {
      return this.suit;
   }
   
   public String getRank()
   {
      return this.rank;
   }
    
}