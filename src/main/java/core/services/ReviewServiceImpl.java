package core.services;

import core.interfaces.repositories.ReviewRepository;
import core.interfaces.services.ReviewService;
import core.helpers.LoginState;
import infrastructure.repositories.ReviewRepositoryImpl;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository _reviewRepository;

    public ReviewServiceImpl() {
        _reviewRepository = new ReviewRepositoryImpl();
    }

    @Override
    public void addReview(int movieId, String reviewText) {
        _reviewRepository.addReview(LoginState.getUser(), movieId, reviewText, 5);
    }
}
