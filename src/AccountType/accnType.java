package AccountType;
import java.util.Scanner;

import login.*;
import Main.*;


public class accnType {



//	public static int InputInt(Scanner scanner)
//	{
//		int num=0;
//		num=scanner.nextInt();
//
//		return num;
//	}

	public void execute(userSession session)
	{
		InputInt inputType=new InputInt();
		Scanner scanner=new Scanner(System.in);
		Saving saving_accn=new Saving();
		Current current_accn=new Current();
		main newMain=new main();
		System.out.println("\n\nChoose Your Account Type");
		System.out.println("1.Current Account");
		System.out.println("2.Saving Account");
		System.out.println("3. Exit\n");
		boolean a=true;
		while(a)
		{
		int type=InputInt.inputIntData(scanner);

		switch(type)
			{
			case 1:
				current_accn.execute(session);
				a=false;
				break;
			case 2:
				saving_accn.execute(session);
				a=false;
				break;
			case 3:
				a=false;
				System.out.println("Thank You for visiting\n\n\n");

				try {
				    Thread.sleep(2000);
				} catch (InterruptedException e) {
				    e.printStackTrace();
				}
				session.clearSession();
				newMain.execute();
				
				
				a=false;
				break;
			default:
				System.out.println("\nEnter Correct Type:");
				break;
			
				}
		}
	}
		
	public static void main(String [] args)
	{
		}
	
}
