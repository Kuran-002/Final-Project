package pkg;

public class Test {

	public static void main(String[] args) {
		
		Dice dice = new Dice();
		
		
		int num =0;
		System.out.println(dice.getValue());
		DiceSet diceset = new DiceSet();
		System.out.println(diceset);
		diceset.holdOneDice(2);
		System.out.println(diceset.oneDiceIsHeld(2));
		diceset.unHoldOneDice(2);
		System.out.println(diceset.oneDiceIsHeld(2));
		

	}

}
