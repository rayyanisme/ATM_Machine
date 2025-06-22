package login;

import java.util.Scanner;

import AccountType.accnType;
import login.*;

public class Welcome {


public userSession methodCall(userSession user_session)
{
	
	
	InputInt inputData=new InputInt();
	accnType accnTypemethod=new accnType();
	Scanner scanner=new Scanner(System.in);
	System.out.println("\n\nWelcome to the ATM");
	System.out.println("Enter Your Customer ID:");
	boolean a=true;
	int customerId=0;
	int pin=0;
	while(customerId==0)
	{
	customerId=InputInt.inputIntData(scanner);
	}

	System.out.println("Enter Your PIN Number: \n");
	while(pin==0)
	{
		pin=InputInt.inputIntData(scanner);
	}
	
	user_session.setSession(customerId,pin);
	
	return user_session;
}

	public static void main(String[] args)
	{
	
}
}
