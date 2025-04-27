package pkg;

import java.util.Scanner;

public class BaseGame {
	static int rollremain = 5;
		public static void main(String[] args) {
			Scanner Scan = new Scanner(System.in);
		DiceSet diceset = new DiceSet();
		System.out.println(diceset);
	
			System.out.println("Which Dice you want to hold Dice 1 ?");
			String choose1 = Scan.nextLine();
			if (choose1.equals("yes"))
			{
				diceset.holdOneDice(0);
			}
			System.out.println("Which Dice you want to hold Dice 2 ?");
			String choose2 = Scan.nextLine();
			if(choose2.equals("yes"))
			{
				diceset.holdOneDice(1);
			}
			System.out.println("Which Dice you want to hold Dice 3 ?");
			String choose3 = Scan.nextLine();
			 if(choose3.equals("yes"))
			{
				diceset.holdOneDice(2);
			}
			 System.out.println("Which Dice you want to hold Dice 4 ?");
		     String choose4 = Scan.nextLine();
			if(choose4.equals("yes"))
			{
				diceset.holdOneDice(3);
			}
			System.out.println("Which Dice you want to hold Dice 5 ?");
			String choose5 = Scan.nextLine();
			if(choose5.equals("yes"))
			{
				diceset.holdOneDice(4);
			}
			
		
		System.out.println("type Roll to roll");
		
        String roll = Scan.nextLine();
		
		
		if (roll.equals("Roll"))
		{
			diceset.rollUnheld();
			rollremain--;
			System.out.println("roll remain: " +rollremain);
			System.out.println(diceset);
		}
		
		
			
		
		
		
		
		
		
		
	}

}

