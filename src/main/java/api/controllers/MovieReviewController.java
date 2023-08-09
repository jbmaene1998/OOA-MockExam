package api.controllers;


import core.entities.Movie;
import core.interfaces.services.MovieService;
import core.interfaces.services.ReviewService;
import core.services.MovieServiceImpl;
import core.services.ReviewServiceImpl;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class MovieReviewController {
    @FXML
    public Label lblErrorMessage;
    @FXML private TextField txtSearch;
    @FXML private ListView<Movie> lstResults;
    @FXML private ImageView imgCover;
    @FXML private TextField txtReviewText;

    private final MovieService _movieService;
    private final ReviewService _reviewService;

    public MovieReviewController() {
        _movieService = new MovieServiceImpl();
        _reviewService = new ReviewServiceImpl();
    }

    public void onSearch(ActionEvent actionEvent) {
        lstResults.setItems(
                FXCollections.observableArrayList(_movieService.getMovies(txtSearch.getText()))
        );
        clearErrorMessage();
    }

    public void onDisplayMovie(ActionEvent actionEvent) {
        if (lstResults.getSelectionModel().isEmpty()){
            lblErrorMessage.setText("No movie selected");
        }
        else{
            Movie movie = lstResults.getSelectionModel().getSelectedItem();
            imgCover.setImage(new Image(movie.getCoverUrl()));
        }
    }

    public void onAddReview(ActionEvent actionEvent) {
        if (txtReviewText.getText().isEmpty()){
            lblErrorMessage.setText("Review text is empty");
        }
        else{
            _reviewService.addReview(
                    lstResults.getSelectionModel().getSelectedItem().getId(),
                    txtReviewText.getText()
            );
            clearErrorMessage();
        }
    }

    void clearErrorMessage(){
        lblErrorMessage.setText("");
    }
}
