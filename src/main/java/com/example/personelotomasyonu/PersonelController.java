package com.example.personelotomasyonu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class PersonelController {

    @FXML
    private TableView<Personel> tablo;

    @FXML
    private TableColumn<Personel, String> sutun_isim;

    @FXML
    private TableColumn<Personel, String> sutun_seviye;

    @FXML
    private TableColumn<Personel, String> sutun_soyisim;

    @FXML
    private TableColumn<Personel, String> sutun_telefon;

    @FXML
    private TableColumn<Personel, Integer> sutun_maas;

    @FXML
    private TableColumn<Personel, Integer> sutun_izin;

    @FXML
    private TextField isim;

    @FXML
    private TextField soyisim;

    @FXML
    private TextField telefon;

    @FXML
    private TextField arama;

    @FXML
    private ChoiceBox<String> seviyeSecimi;

    @FXML
    private ChoiceBox<String> seviyeSecimi2;

    @FXML
    private TextField zam;

    ObservableList<Personel> globalPersonalList = FXCollections.observableArrayList();


    @FXML
    private void initialize() {
        // ChoiceBox'a seçenekleri ekle
        ObservableList<String> secenekler = FXCollections.observableArrayList("Jr Dev", "Mid Dev", "Senior Dev");
        seviyeSecimi.setItems(secenekler);
        seviyeSecimi2.setItems(secenekler);

        // Varsayılan seçenekleri belirle
        seviyeSecimi.setValue("Jr Dev");
        seviyeSecimi2.setValue("Jr Dev");

        // TableColumn özelliklerini yapılandır
        sutun_isim.setCellValueFactory(new PropertyValueFactory<>("Isim"));
        sutun_soyisim.setCellValueFactory(new PropertyValueFactory<>("Soyisim"));
        sutun_telefon.setCellValueFactory(new PropertyValueFactory<>("Telefon"));
        sutun_seviye.setCellValueFactory(new PropertyValueFactory<>("Seviye"));
        sutun_maas.setCellValueFactory(new PropertyValueFactory<>("Maas"));
        sutun_izin.setCellValueFactory(new PropertyValueFactory<>("İzin"));

        tabloYenile();
    }

    @FXML
    protected void personelEkle() { // Personel Ekle butonu ile çalışır
        // Girdiler ile Personel nesnesi oluştur ve listeye ekle
        String isim1 = isim.getText();
        String soyisim1 = soyisim.getText();
        String telefon1 = telefon.getText();
        String seviye1 = seviyeSecimi.getValue();
        Personel personel = new Personel(isim1, soyisim1, telefon1, seviye1);

        globalPersonalList.add(personel);
        dosyaYazdirma();
        tabloYenile();
    }

    @FXML
    protected void personelSil() { // Tablodan bir satır seçildiğinde ve Sil butonuna basıldığında çalışır
        // Seçilen satırı al ve o satırdaki personeli sil
        Personel seciliPersonel = tablo.getSelectionModel().getSelectedItem();
        globalPersonalList.remove(seciliPersonel);

        dosyaYazdirma();
        tabloYenile();
    }

    @FXML
    protected void zamYap(){ // Zam Yap butonu ile çalışır
        String seviye2 = seviyeSecimi2.getValue();
        String zamMiktari = zam.getText();

        for (Personel personel : globalPersonalList) {
            if (personel.getSeviye().equals(seviye2)) {
                personel.zamYapma(zamMiktari);
            }
        }

        dosyaYazdirma();
        tabloYenile();
    }

    protected void dosyaOkuma() {
        // Personel listesini oluştur
        ObservableList<Personel> personalList = FXCollections.observableArrayList();

        // Dosyadan verileri oku, Personal nesnelerini oluştur ve listeye ekle
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/Data/personeller.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String isim = parts[0].trim();
                String soyisim = parts[1].trim();
                String telefon = parts[2].trim();
                String seviye = parts[3].trim();
                int maas = Integer.parseInt(parts[4].trim());
                int izin = Integer.parseInt(parts[5].trim());

                Personel personel = new Personel(isim, soyisim, telefon, seviye, maas, izin);
                personalList.add(personel);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Global personel listesini güncelle
        globalPersonalList = personalList;
    }

    protected void dosyaYazdirma() {
        // Dosyayı temizle
        try (BufferedWriter cleanWriter = new BufferedWriter(new FileWriter("src/main/Data/personeller.txt", false))) {
            cleanWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Dosyaya yazdır
        for (Personel personel : globalPersonalList) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/Data/personeller.txt", true))) {
                writer.write(personel.getIsim() + "," + personel.getSoyisim() + "," + personel.getTelefon() + "," + personel.getSeviye()
                        + "," + personel.getMaas() + "," + personel.getİzin());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected void tabloYenile() {
        // Son güncel dosyayı oku
        dosyaOkuma();
        // Tabloya güncel verileri ekle
        tablo.setItems(globalPersonalList);
    }

    @FXML
    private void aramaYap() { // Arama butonuna her yeni bir şey yazıldığında çalışır
        String yazi = arama.getText().toLowerCase();

        // Personel listesini filtrele
        ObservableList<Personel> filtrelenmisListe = FXCollections.observableArrayList();
        for (Personel personel : globalPersonalList) {
            if (personel.getIsim().toLowerCase().contains(yazi)
                    || personel.getSoyisim().toLowerCase().contains(yazi)
                    || personel.getTelefon().toLowerCase().contains(yazi)
                    || personel.getSeviye().toLowerCase().contains(yazi)) {
                filtrelenmisListe.add(personel);
            }
        }
        // Filtrelenmiş listeyi tabloya ekle
        tablo.setItems(filtrelenmisListe);

        if (yazi.isEmpty()) {
            filtrelenmisListe.clear();
            tabloYenile();
        }
    }
}
