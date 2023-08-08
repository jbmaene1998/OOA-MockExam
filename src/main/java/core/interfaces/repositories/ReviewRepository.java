package core.interfaces.repositories;

public interface ReviewRepository {
    void addReview(String username, int movieId, String reviewText, int score);
}