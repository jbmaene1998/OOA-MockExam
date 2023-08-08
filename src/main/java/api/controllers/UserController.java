package api.controllers;

import api.main.Program;
import core.helpers.Crypto;
import core.interfaces.services.UserService;
import core.services.UserServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserController {
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtUsername;
    @FXML private Label lblErrorMessage;

    private final UserService _userService;

    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());

    public UserController(){
        _userService = new UserServiceImpl();
    }

    public void onLogin(ActionEvent actionEvent) {
        if (_userService.login(txtUsername.getText(), txtPassword.getText())){
            showMovieReviewScreen();
        } else {
            showError("Failed to login");
        }
    }

    private void showMovieReviewScreen(){
        try {
            FXMLLoader loader = new FXMLLoader(Program.class.getResource("/fxml/MovieReview.fxml"));
            loader.setResources(ResourceBundle.getBundle("internationalization/i22n", Locale.forLanguageTag("nl")));
            Parent parent = loader.load();
            Stage secondStage = new Stage();
            secondStage.setScene(new Scene(parent));
            secondStage.show();
        } catch (IOException ex) {
            LOGGER.log(Level.INFO, "Failed to show second screen", ex);
            showError("Failed to show second screen");
        }
    }

    private void showError(String error){
        lblErrorMessage.setText(error);
    }

    public void onRegister(ActionEvent actionEvent) {
        if (Objects.equals(txtUsername.getText(), "") || Objects.equals(txtPassword.getText(), "")){
            LOGGER.log(Level.INFO, "Cannot register without username or password");
            showError("Cannot register without username or password");
            return;
        }

        if (_userService.register(txtUsername.getText(), Crypto.getInstance().encrypt(txtPassword.getText()))){
            clearErrorMessage();
            showMovieReviewScreen();
        } else {
            LOGGER.log(Level.INFO, "Failed to register");
            showError("Failed to register");
        }
    }

    void clearErrorMessage(){

        lblErrorMessage.setText("");
    }
}
