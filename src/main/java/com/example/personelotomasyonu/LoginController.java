package com.example.personelotomasyonu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button button1;

    @FXML
    private Label bilgilendirme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button1.setOnAction(e -> {
            String kullaniciAdi = username.getText();
            String sifre = password.getText();

            if (girisYap(kullaniciAdi, sifre)) {
                try {
                    changeScene();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                bilgilendirme.setText("Giriş Başarısız");
            }
        });
    }

    private boolean girisYap(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/Data/kullanicilar.txt"))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] parts = satir.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void changeScene() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("personel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = Login.getPrimaryStage();
        stage.hide();
        stage.setTitle("Personel Otomasyonu");
        stage.setScene(scene);
        stage.show();
    }


}
