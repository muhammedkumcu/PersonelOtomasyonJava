module com.example.personalotomasyonu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.personelotomasyonu to javafx.fxml;
    exports com.example.personelotomasyonu;
}