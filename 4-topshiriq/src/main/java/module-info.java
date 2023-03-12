module com.example.topshiriq_4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.topshiriq_4 to javafx.fxml;
    exports com.example.topshiriq_4;
}