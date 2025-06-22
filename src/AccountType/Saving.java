package AccountType;

import login.*;
import java.sql.*;
import java.util.Scanner;

public class Saving {

 
    private boolean hasSavingsAccount(int customerId) {
        try (Connection conn = databaseConnect.getConnection()) {
            String sql = "SELECT savings_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double bal = rs.getDouble("savings_balance");
                return !rs.wasNull(); 
            }
        } catch (SQLException e) {
            System.out.println("Database error while checking savings account.");
            e.printStackTrace();
        }

        return false;
    }

   
    private void viewBalance(int customerId,userSession user_session) {
        try (Connection conn = databaseConnect.getConnection()) {
        	Saving newSaving=new Saving();
            String sql = "SELECT savings_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("savings_balance");
                if (rs.wasNull()) {
                    System.out.println("No savings account found.");
                    newSaving.execute(user_session);
                } else {
                    System.out.println("Your savings balance is: ₹" + balance );
                    newSaving.execute(user_session);
                }
            } else {
                System.out.println("Customer not found.");
                newSaving.execute(user_session);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching savings balance.");
            e.printStackTrace();
        }
    }

  
    public void execute(userSession user_session) {
        Scanner scanner = new Scanner(System.in);
        accnType newType = new accnType();
        int customerId = user_session.getCustomerId();

        
        if (!hasSavingsAccount(customerId)) {
            System.out.println("You do not have a savings account.\n\n");
            newType.execute(user_session);
            return;
        }

        System.out.println("\n\n\nSaving Account Menu");
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
                    System.out.print("Enter amount to withdraw: ");
                    int amountToWithdraw = InputInt.inputIntData(scanner);
                    Withdraw withdraw = new Withdraw();
                    withdraw.withdrawFromSaving(amountToWithdraw, user_session);
                    break;
                case 3:
                    running = false;
                    System.out.print("\nEnter amount to deposit: ");
                    int depositAmount = InputInt.inputIntData(scanner);
                    Deposit deposit = new Deposit();
                    deposit.depositToSaving(depositAmount, user_session);
                    break;
                case 4:
                    running = false;
                    System.out.println("\nExiting Saving Account menu.\n\n");
                    try {
                        Thread.sleep(2000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    newType.execute(user_session); 
                    break;
                default:
                    System.out.println("❗ Invalid option. Please choose 1-4.");
            }
        }
    }

    public static void main(String[] args) {
        
    }
}
