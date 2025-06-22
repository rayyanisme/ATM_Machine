package AccountType;

import login.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Withdraw {

    public void withdrawFromSaving(int amount,userSession user_session) {
        int customerId = user_session.getCustomerId(); // globally stored ID
        Connection conn = databaseConnect.getConnection();
        Saving newSaving=new Saving();

        if (conn == null) {
            System.out.println("Could not connect to database.");
            return;
        }

        try {
            // Step 1: Check if savings account exists and get balance
            String query = "SELECT savings_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("savings_balance");

                if (rs.wasNull()) {
                    System.out.println("No savings account found.\n\n");
                } else if (balance < amount) {
                    System.out.println("Insufficient funds!!\n\n\n");
                } else {
                    // Step 2: Withdraw and update
                    double newBalance = balance - amount;
                    String updateQuery = "UPDATE customers SET savings_balance = ? WHERE customer_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setDouble(1, newBalance);
                    updateStmt.setInt(2, customerId);

                    int updated = updateStmt.executeUpdate();
                    if (updated > 0) {
                        System.out.println(" ₹" + amount + " withdrawn\n\n\n\n");
                    }

                    updateStmt.close();
                }
            } else {
                System.out.println("Customer not found.");
            }

            rs.close();
            stmt.close();
            conn.close();
            
            newSaving.execute(user_session);

        } catch (SQLException e) {
            System.out.println("Database error during withdrawal.");
            e.printStackTrace();
        }
    }
    
    public void withdrawFromCurrent(int amount, userSession user_session) {
        int customerId = user_session.getCustomerId();
        Current newCurrent=new Current();
        Connection conn = databaseConnect.getConnection();

        if (conn == null) {
            System.out.println("Could not connect to database.");
            return;
        }

        try {
            String query = "SELECT current_balance FROM customers WHERE customer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("current_balance");

                if (rs.wasNull()) {
                    System.out.println("No current account found.\n\n\n\n");
                } else if (balance < amount) {
                    System.out.println("Insufficient funds in Current Account.\n\n\n\n");
                } else {
                    double newBalance = balance - amount;
                    String updateQuery = "UPDATE customers SET current_balance = ? WHERE customer_id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
                    updateStmt.setDouble(1, newBalance);
                    updateStmt.setInt(2, customerId);

                    int updated = updateStmt.executeUpdate();
                    if (updated > 0) {
                        System.out.println("₹" + amount + " withdrawn.\n\n\n\n");
                    }

                    updateStmt.close();
                }
            } else {
                System.out.println("Customer not found.");
            }

            rs.close();
            stmt.close();
            conn.close();
            newCurrent.execute(user_session);

        } catch (SQLException e) {
            System.out.println("Database error during withdrawal from current account.\n\n\n\n\n");
            e.printStackTrace();
        }
    }
}
