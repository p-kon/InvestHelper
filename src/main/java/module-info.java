module InvestHelper.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires htmlunit;
    requires org.json;

    opens ihDataModel to javafx.base;
    opens ihController to javafx.fxml;

    exports mainApp to javafx.controls, javafx.fxml, javafx.graphics;
    exports ihController to javafx.fxml;
}