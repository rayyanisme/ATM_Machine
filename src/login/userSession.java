package login;

public class userSession {

	private static int customerId;
    private static int pin;

    // Setters
    public static void setSession(int id, int userPin) {
        customerId = id;
        pin = userPin;
    }

    // Getters
    public static int getCustomerId() {
        return customerId;
    }

    public static int getPin() {
        return pin;
    }

    // Clear session (optional)
    public static void clearSession() {
        customerId = 0;
        pin = 0;
    }
}
