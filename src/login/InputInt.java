package login;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputInt {


	
	public static int inputIntData(Scanner scanner)
	{
		 int num=0;

		try {
			 num = scanner.nextInt();
			}
	    catch (InputMismatchException e)
		{
        System.out.println("Please input integers only.");
        scanner.nextLine();
        }


		return num;
	}


}
