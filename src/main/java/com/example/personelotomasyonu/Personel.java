package com.example.personelotomasyonu;

public class Personel {
    private String isim, soyisim, telefonNumarasi, seviye;
    private int izin, maas;

    public Personel(String isim, String soyisim, String telefonNumarasi, String seviye) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.telefonNumarasi = telefonNumarasi;
        this.seviye = seviye;

        if (seviye == "Jr Dev") {
            this.izin = 12;
            this.maas = 25000;
        } else if (seviye == "Mid Dev") {
            this.izin = 20;
            this.maas = 50000;
        } else if (seviye == "Senior Dev") {
            this.izin = 30;
            this.maas = 100000;
        }
    }

    public Personel(String isim, String soyisim, String telefonNumarasi, String seviye, int maas, int izin) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.telefonNumarasi = telefonNumarasi;
        this.seviye = seviye;
        this.izin = izin;
        this.maas = maas;
    }

    public void zamYapma(int zamMiktari) {
        maas += zamMiktari;
        setMaas(maas);
    }

    public void zamYapma(double zamOrani) {
        double zamMiktari = getMaas() * zamOrani / 100.0;
        maas += (int) zamMiktari;
        setMaas(maas);
    }

    public void zamYapma(String netZam) {
        if (netZam.contains(".")) {
            // Eğer nokta içeriyorsa yüzdeli zam
            double ZamOrani = Double.parseDouble(netZam);
            zamYapma(ZamOrani);
        } else {
            // Eğer nokta içermiyorsa doğrudan zam
            int zamMiktari = Integer.parseInt(netZam);
            zamYapma(zamMiktari);
        }
    }

    public void setMaas(int maas) {
        this.maas = maas;
    }

    public String getIsim() {
        return isim;
    }

    public String getSoyisim() {
        return soyisim;
    }

    public String getTelefon() {
        return telefonNumarasi;
    }

    public String getSeviye() {
        return seviye;
    }

    public int getMaas() {
        return maas;
    }

    public int getİzin() {
        return izin;
    }
}
