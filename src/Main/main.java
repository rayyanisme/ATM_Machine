package Main;
import login.*;
import AccountType.*;

public class main {
	
	public void execute()
	{
		userSession user_session=new userSession();
		Welcome welcome_main=new Welcome();
		CheckIdPin check=new CheckIdPin();
		accnType accn=new accnType();
		
		boolean user_check=false;
		
		while(user_check==false)
		{
		welcome_main.methodCall(user_session);
		user_check=check.verifyCustomer(user_session.getCustomerId(),user_session.getPin());
		
		if(user_check==true)
		{
			
			accn.execute(user_session);
			
		}
		}
	}
	public static void main(String []args)
	{
    	main Main_session=new main();
    	Main_session.execute();
	}

}