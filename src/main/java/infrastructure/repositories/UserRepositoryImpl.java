package infrastructure.repositories;

import core.interfaces.repositories.UserRepository;
import core.exceptions.MovieException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static infrastructure.helpers.DriverConnection.getConnection;

public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class.getName());
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config/queries");
    @Override
    public boolean addUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(RESOURCE_BUNDLE.getString("insert.user"))){

            stmt.setString(1, username);
            stmt.setString(2, password);

            stmt.execute();
            return true;

        } catch (SQLException ex){
            LOGGER.log(Level.INFO, "Failed to create user profile");
            throw new MovieException("Failed to create user profile");
        }
    }

    @Override
    public boolean loginUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(RESOURCE_BUNDLE.getString("select.user"))){

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()){
                if (rs.next()){
                    return rs.getString("password").equals(password);
                }
                throw new MovieException("Failed to retrieve user");
            }

        } catch (SQLException ex){
            LOGGER.log(Level.INFO, "Failed to retrieve username");
            throw new MovieException("Failed to retrieve username");
        }
    }
}
