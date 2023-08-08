package api.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.ResourceBundle;

public class Program extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("internationalization/i22n");
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Program.class.getResource("/fxml/Login.fxml")), resourceBundle);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }
}
