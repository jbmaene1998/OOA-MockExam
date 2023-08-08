package infrastructure.helpers;

import core.helpers.Config;
import core.helpers.Crypto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverConnection {

    private DriverConnection(){
        /* utility class */
    }

    public static Connection getConnection() throws SQLException {
        String decryptedUser = Crypto.getInstance().decrypt(Config.get("db.user"));
        String decryptedPassword = Crypto.getInstance().decrypt(Config.get("db.password"));

        return DriverManager.getConnection(
                Config.get("db.url"),
                decryptedUser,
                decryptedPassword
        );
    }
}