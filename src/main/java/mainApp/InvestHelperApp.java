package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InvestHelperApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox mainPane = FXMLLoader.load(getClass().getResource("/main.fxml"));
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Invest Helper app by Paweł Konieczny");
        primaryStage.show();
    }
}
