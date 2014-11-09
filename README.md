

public class GameProtocol {
	boolean Finished;
	Queue <String> pachet = new LinkedList<String>();
	String myCurrentCard, hisCurrentCard;
	String result;
	boolean wonRound;
	
	public GameProtocol (){
	    Finished = true;
	    pachet = deck();
	    wonRound = false;
	}

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

	public int compare(String card1, String card2)
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
		ArrayList<String> message = new ArrayList<String>();
	     StringTokenizer st = new StringTokenizer(str);
	     while (st.hasMoreTokens()) {
	         message.add(st.nextToken());
	     }
	     
	    if (message.get(0) == null)
	    {
	        return "Hello!";
	    }

	    if (message.get(0).compareTo("Hello!") == 0)
	    {
	        return "wanna_play";
	    }
	    
	    if (message.get(0).compareTo("wanna_play") == 0) {
	    	if (Finished) {
	    		return "YES";
	    	} else {
	    		return "NO";
	    	}
	    }
	    
	    if (message.get(0).compareTo("YES") == 0)
	    {
	        Finished = false;
	        myCurrentCard = pachet.remove();
	        return "war " + myCurrentCard;
	    }
	    
	    if (message.get(0).compareTo("NO") == 0)
	    {
	        return "Sleep";
	    }
	    
	    if (message.get(0).compareTo("war") == 0) {
	    	hisCurrentCard = message.get(1);
	    	myCurrentCard = pachet.remove();
	    	return myCurrentCard;   	
	    }
	    
	    if (message.get(0).compareTo("result") == 0) {
	    	if ( message.get(1).compareTo("WIN") == 0) {
	    		pachet.add(hisCurrentCard);
	    		pachet.add(myCurrentCard);
	    		wonRound = true;
	    	} else if (message.get(1).compareTo("LOSE") == 0) {
	    		wonRound = false;
	    	} else if (message.get(1).compareTo("DRAW") == 0) {
	    		
	    	}
	    	
	    	return "ACK";
	    	
	    }
	    
	    if (message.get(0).compareTo("ACK") == 0) {
	    	if (result.compareTo("WIN") == 0) {
	    		pachet.add(hisCurrentCard);
	    		pachet.add(myCurrentCard);
	    		wonRound = true;
	    	} else if (result.compareTo("LOSE") == 0) {
	    		wonRound = false;
	    	} else if (result.compareTo("DRAW") == 0) {
	    		
	    	}
	    	result = "";
	    }
	    
	    // Card
	    hisCurrentCard = message.get(0);
    	int compareResult = compare(myCurrentCard, message.get(0));
    	if (compareResult == 0) {
    		result = "DRAW";
    		return "result DRAW";
    	} else if (compareResult == 1) {
    		result = "WIN";
    		
    		return "result WIN";
    	} else if (compareResult == -1) {
    		result = "LOSE";
    		wonRound = false;
    		return "result LOSE";
    	}
    	
    	if (wonRound) {
	        myCurrentCard = pachet.remove();
	        return "war " + myCurrentCard;
    	}
    	
    	return "";
	}
	public boolean notFinished (){
	    return Finished;
	}
}
