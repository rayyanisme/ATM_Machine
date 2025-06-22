package AccountType;

import login.*;
import java.sql.*;
import java.util.Scanner;

public class Current {

   
    private boolean hasCurrentAccount(int customerId) {
        try (Connection conn = databaseConnect.getConnection()) {
            String sql = "SELECT current_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double bal = rs.getDouble("current_balance");
                return !rs.wasNull();
            }
        } catch (SQLException e) {
            System.out.println("Database error while checking current account.");
            e.printStackTrace();
        }

        return false;
    }

    
    private void viewBalance(int customerId,userSession user_session) {
    	Current newCurrent=new Current();
        try (Connection conn = databaseConnect.getConnection()) {
        	
            String sql = "SELECT current_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("current_balance");
                if (rs.wasNull()) {
                    System.out.println("No current account found.");
                    newCurrent.execute(user_session);
                } else {
                    System.out.println("Your current balance is: ₹" + balance);
                    newCurrent.execute(user_session);
                }
            } else {
                System.out.println("Customer not found.");
                newCurrent.execute(user_session);
            }
            newCurrent.execute(user_session);

        } catch (SQLException e) {
            System.out.println("Error fetching current balance.");
            e.printStackTrace();
        }
    }

    
    public void execute(userSession user_session) {
        Scanner scanner = new Scanner(System.in);
        accnType new_accnType=new accnType();
        int customerId = user_session.getCustomerId();
        accnType newType=new accnType();

        
        if (!hasCurrentAccount(customerId)) {
            System.out.println("You do not have a current account.\n\n");
            new_accnType.execute(user_session);
            return;
        }

        System.out.println("\n\n\nCurrent Account Menu");
        System.out.println("1: View Balance");
        System.out.println("2: Withdraw Funds");
        System.out.println("3: Deposit Funds");
        System.out.println("4: Exit\n");

        boolean running = true;

        while (running) {
            System.out.print("Choose an option: ");
            int option = InputInt.inputIntData(scanner);

            switch (option) {
                case 1:
                	running = false;
                    viewBalance(customerId,user_session);
                    break;
                case 2:
                	running = false;
                    System.out.print("\nEnter amount to withdraw: ");
                    int amountToWithdraw = InputInt.inputIntData(scanner);
                    Withdraw withdraw = new Withdraw();
                    withdraw.withdrawFromCurrent(amountToWithdraw, user_session);
                    break;
                case 3:
                	running = false;
                    System.out.print("\nEnter amount to deposit: ");
                    int depositAmount = InputInt.inputIntData(scanner);
                    Deposit deposit = new Deposit();
                    deposit.depositToCurrent(depositAmount, user_session);
                    break;
                case 4:
                	running = false;
                    System.out.println("Exiting Current Account menu.\n\n");
                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    newType.execute(user_session);
                    
                    break;
                default:
                    System.out.println("❗ Invalid option. Please choose 1-4.\n");
            }
        }
    }

    public static void main(String[] args) {
       
    }
}
