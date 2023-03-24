module com.example.lab_2_shvarts {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab_2_shvarts to javafx.fxml;
    exports com.example.lab_2_shvarts;
}