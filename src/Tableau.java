/** basically intializes the tableau to allow only 7 columns and depict the first four
 * columns with 3 flipped over cards 
 * using the array table into the linked list**/
public class Tableau 
{
   public LinkedList []table = new LinkedList[7];
 
   public Tableau()
   {
      for (int i = 0; i < table.length; i++)
      {
         table[i] = new TableauC();
      }
   }
   public void add(Card card, int tab) 
   {
      Card newCard = new Card(card.rank, card.suit);
      if (tab >= 0 && tab < 4 && table[tab].getSize() < 3) 
      {
         newCard.Hidden = true;
      }
      table[tab].add(newCard); 
   }
   
}
