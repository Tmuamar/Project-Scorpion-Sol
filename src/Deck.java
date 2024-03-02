/** depicting the deck of cards as a linked list**/
public class Deck extends LinkedList
{
   Card[]cards;
   public Deck(boolean shuffle)
   {
      if(shuffle)
      {
         this.shuffleDeck(null);
         
      }
      
   }
   public void shuffleDeck(String deck)
   {
      Card current = first;
      int randomValues = (int) (Math.random() * 10 + 1);
      for (int x = 0; x < randomValues; x++) 
      {
         if (current.next != null) 
         {
            current = current.next;
         } 
         else 
         {
            current = first;
            current = current.next;
         }
         if (current.prev != null)
         {
            current.prev = current.next;
            first.prev = current;
            current.prev = first;
         }
         
         first = current;
      }
         
      
   }
      
}
