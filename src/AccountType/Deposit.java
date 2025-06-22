package AccountType;

import login.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit {

    
    public void depositToSaving(int amount,userSession user_session) {
        int customerId = user_session.getCustomerId();
        Saving newSaving=new Saving();

        try (Connection conn = databaseConnect.getConnection()) {
            // Check if savings account exists
            String checkSql = "SELECT savings_balance FROM customers WHERE customer_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, customerId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("savings_balance");

                if (rs.wasNull()) {
                    System.out.println("No savings account found.");
                    return;
                }

                double newBalance = balance + amount;

                // Update balance
                String updateSql = "UPDATE customers SET savings_balance = ? WHERE customer_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDouble(1, newBalance);
                updateStmt.setInt(2, customerId);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("₹" + amount + " deposited into Savings.");
                }

                updateStmt.close();
                newSaving.execute(user_session);
            } else {
                System.out.println("Customer not found.");
            }

            rs.close();
            checkStmt.close();
        } catch (SQLException e) {
            System.out.println("Database error while depositing into savings.");
            e.printStackTrace();
        }
    }

 
    public void depositToCurrent(int amount,userSession user_session) {
        int customerId = user_session.getCustomerId();
        Current newCurrent=new Current();
        
        try (Connection conn = databaseConnect.getConnection()) {
            // Check if current account exists
            String checkSql = "SELECT current_balance FROM customers WHERE customer_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, customerId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("current_balance");

                if (rs.wasNull()) {
                    System.out.println("No current account found.");
                    return;
                }

                double newBalance = balance + amount;

                // Update balance
                String updateSql = "UPDATE customers SET current_balance = ? WHERE customer_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDouble(1, newBalance);
                updateStmt.setInt(2, customerId);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("₹" + amount + " deposited into Current Account.");
                }

                updateStmt.close();
                newCurrent.execute(user_session);
            } else {
                System.out.println("Customer not found.");
            }

            rs.close();
            checkStmt.close();
        } catch (SQLException e) {
            System.out.println("Database error while depositing into current account.");
            e.printStackTrace();
        }
    }
}
