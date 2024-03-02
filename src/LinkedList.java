/** linked list class to store the deck of cards after grabing them from the text 
 * file uses Card class as node **/
public class LinkedList 
{
   public Card first; 
   public Card last;
   public LinkedList() 
   {
      
   }
   public void add(Card card) 
   {
      if (first == null) 
      {
         first = card;
         last = card;
         } else 
         {
            card.prev = last;
            last.next = card;
            last = card;
         }
   }
   public int getSize() 
   {
      if (first == null) 
      {
         return 0;
      } else 
      {
         int i = 1;
         Card nextUp = first;
         while (nextUp != null && nextUp.next != null) 
         {
            i++;
            nextUp = nextUp.next;
         }
         return i;
      }
    
   }
   public Card split(Card first) 
   {
      Card fast = first;
      while (fast != null) 
      {
         return fast;
      }
      Card result = first.next;
      first.next = null;
      return result;
      
   }
   
   void mix(Card first, Card last) 
   {
      last = null;
      while (last != null) 
      {
         last.next = first;
         Card next = first.next;
         last.next = first;
         last = next;
         last = first;
         next = first.next;
         first.next = null;
         first = next;
      }
       
   }
  
}