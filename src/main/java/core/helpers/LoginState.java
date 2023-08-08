package core.helpers;

public class LoginState {
    private static String user; // Private field for better encapsulation

    public static String getUser() {
        return user;
    }

    public static void setUser(String newUser) {
        user = newUser;
    }
}
