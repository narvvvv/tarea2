module com.javier.conteoclick {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javier.conteoclick to javafx.fxml;
    exports com.javier.conteoclick;
}
