module BFI.app {
    requires javafx.controls;
    requires org.jsoup;
    requires javafx.fxml;
    requires htmlunit;
    requires org.json;

    opens bfiDataModel to javafx.base;
    opens bfiController to javafx.fxml;

    exports mainApp to javafx.controls, javafx.fxml, javafx.graphics;
    exports bfiController to javafx.fxml;
}