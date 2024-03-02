/** allows for the intial read of the first deck provided to us **/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//reads file to then begin the game.
public class ReadFile 
{
    public static void main(String[] args) throws Exception 
    {
       File file = new File("deck1.txt");
       String deck = new String();
       BufferedReader br = new BufferedReader(new FileReader(file));
       deck = br.readLine();
       br.close();
       ScorpionBoard newGame = new ScorpionBoard();
       Moves computer = new Moves();
       computer.soliBegin(newGame, deck);
       computer.beginGame(deck);
    }
}
