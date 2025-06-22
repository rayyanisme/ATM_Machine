package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckIdPin {

    public static boolean verifyCustomer(int customerId, int pin) {
        boolean isValid = false;

        try {
            Connection conn = databaseConnect.getConnection(); // Reuse connection
            if (conn == null) {
                System.out.println("Cannot proceed without DB connection.");
                return false;
            }

            String sql = "SELECT name FROM customers WHERE customer_id = ? AND pin = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, customerId);
            stmt.setInt(2, pin);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                System.out.println("Login successful. Welcome, " + name + "!\n\n");
                isValid = true;
            } else {
                System.out.println("\nInvalid Customer ID or PIN.\n");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error while verifying login.");
            e.printStackTrace();
        }

        return isValid;
    }
    
    
//    public static void main(String []ags)
//    {
//    	int id=2;
//    	int pin=5678;
//    	
//    	boolean ans=verifyCustomer(id,pin);
//    	
//    	
//    	
//    	System.out.println("ans:"+ans);
//    	
//    }
}
