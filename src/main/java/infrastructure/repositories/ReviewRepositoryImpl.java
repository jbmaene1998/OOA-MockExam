package infrastructure.repositories;

import core.interfaces.repositories.ReviewRepository;
import core.exceptions.MovieException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static infrastructure.helpers.DriverConnection.getConnection;

public class ReviewRepositoryImpl implements ReviewRepository {

    private static final Logger LOGGER = Logger.getLogger(ReviewRepositoryImpl.class.getName());
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("config/queries");
    @Override
    public void addReview(String username, int movieId, String reviewText, int score) {
        score = 5;

        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(RESOURCE_BUNDLE.getString("insert.review"))){

            stmt.setString(1, username);
            stmt.setInt(2, movieId);
            stmt.setString(3, reviewText);
            stmt.setInt(4, score);

            stmt.execute();

        } catch (SQLException ex){
            LOGGER.log(Level.INFO, "Failed to add a review");
            throw new MovieException("Failed to add a review");
        }
    }
}
