package pkg;

public class DiceSet {
	private static final int NUM_DICE = 5;
	private Dice[] diceArray;
	
	 public DiceSet() 
	 {
	        diceArray = new Dice[NUM_DICE];
	        for (int i = 0; i < NUM_DICE; i++) 
	        {
	            diceArray[i] = new Dice();
	        }
	 }
	 public void rollAll()
	 {
		 for (int i = 0; i < 5; i++) 
		 {
	            diceArray[i].roll();
	     } 
	 }
	 public void rollUnheld()
	 {
		 for (int i = 0; i < 5; i++) 
		 {
			 if (!diceArray[i].isHeld()) 
			 {
				    diceArray[i].roll();
			 }         
	     } 
	 }
	 public void holdOneDice(int i)
	 {
		 diceArray[i].hold();
	 } 
	 public void unHoldOneDice(int i)
	 {
		 diceArray[i].release();;
	 } 
	 public boolean oneDiceIsHeld(int i) 
	 {
		    return diceArray[i].isHeld();
	 }
	 
	 public Dice getOneDice(int i)
	 {
		 return diceArray[i];
	 }

	public Dice[] getDiceArray() 
	{
		return diceArray;
	}

	public void setDiceArray(Dice[] diceArray) 
	{
		this.diceArray = diceArray;
	}
	
	@Override
	public String toString() {
	    String result = "Dice Set: ";
	    for (int i = 0; i < diceArray.length; i++) {
	        result += diceArray[i].getValue();
	        if (diceArray[i].isHeld()) {
	            result += " (held)";
	        }
	        result += " ";
	    }
	    return result.trim();
	}



}
