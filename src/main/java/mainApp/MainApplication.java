package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BFIApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Image image = new Image("bfi_c2.jpg");
//        ImageView iv1 = new ImageView();
//        iv1.setImage(image);


        VBox mainPane = FXMLLoader.load(getClass().getResource("/bfimain.fxml"));
        //AnchorPane mainPane = FXMLLoader.load(getClass().getResource("/tableviewtest.fxml"));
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("BFI app by Paweł Konieczny");
        primaryStage.show();
        //AnchorPane mainPane = FXML
    }
}
