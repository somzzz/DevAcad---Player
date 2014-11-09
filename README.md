
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class GameProtocol {
	boolean Finished;
	Queue<String> myQueue = new LinkedList<String>();
	//String[] type = {"hearts", "clubs", "diamonds", "spades" };
	//int cardNumber; //random generated between 1 an 14
	
	public GameProtocol (){
		Finished = true;
	}
	
	/*public String cardGenerator ()
	{
		int cardNumber = (int) (Math.random() * 14);
		int cardIndexType = (int) (Math.random() * 4);
		String card;
		card = cardNumber + type[cardIndexType - 1];
		return card;
	}*/
	
	public ArrayList<String> deckGenerator (){
		ArrayList<String> deck = new ArrayList<String>();
		for (int i = 2; i <= 14; i++){
			deck.add( i + "-hearts");
			deck.add( i + "-clubs");
			deck.add( i + "-diamonds");
			deck.add( i + "-spades");
		}
		return deck;
	}
	
	public Queue<String> deck ()
	{
		int randomIndex;
		ArrayList<String> pack = new ArrayList<String>();
		Queue<String> myPack = new LinkedList<String>();
		pack = deckGenerator();
		while (!pack.isEmpty())
		{
			randomIndex = (int) (Math.random() * pack.size());
			myPack.add(pack.get(randomIndex));
			pack.remove(randomIndex);
		}
		return myPack;
	}
	
	public int compare (String card1, String card2)
	{
		StringTokenizer st1 = new StringTokenizer(card1, "-");
		StringTokenizer st2 = new StringTokenizer(card2, "-");
		if (  Integer.parseInt(st1.nextToken()) >  Integer.parseInt(st2.nextToken()) || Integer.parseInt(st1.nextToken()) == 11 && Integer.parseInt(st2.nextToken()) != 11)
		{
			return 1;
		}
		else
			if ( Integer.parseInt(st1.nextToken()) < Integer.parseInt(st2.nextToken()) || Integer.parseInt(st2.nextToken()) == 11 && Integer.parseInt(st1.nextToken()) != 11)
			{
				return -1;
			}
	     return 0;
	}
	
	public String processInput (String str)
	{
		if (str == null)
		{
			return "Hello!";
		}
		
		System.out.println("mesaj" + str);
		if (str.compareTo("Hello!") == 0)
		{
			return "wanna_play";
		}
		if (str.compareTo("YES") == 0)
		{
			Finished = false;
			return "YES";
		}
		if (str.compareTo("NO") == 0)
		{
			return "Sleep";
		}
		return "";
	}
	public boolean notFinished (){
		return Finished;
	}
	
}
