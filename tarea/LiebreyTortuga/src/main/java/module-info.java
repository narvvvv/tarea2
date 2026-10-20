module com.javier.liebreytortuga {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.javier.liebreytortuga to javafx.fxml;
    exports com.javier.liebreytortuga;
}
