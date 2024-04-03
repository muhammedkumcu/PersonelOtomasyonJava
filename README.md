
Personel Otomasyonu

Bu proje, bir yöneticinin personellerini tek bir tablo üzerinden takip etmesini, arama yapmasını, maaşlarına zam yapmasını, yeni personel eklemesini veya silmesini sağlayan özellikler sunan bir bilgisayar programıdır.

Proje İçeriği:

Program, iki aşamadan oluşmaktadır:

Giriş Ekranı: Kullanıcı, kullanıcı adı ve şifresini girerek ana menüye ulaşır.
Ana Menü: Proje konusunda belirtilen özelliklere erişim sağlar.
Dosyalar:

Data Klasörü: İçerisinde iki adet .txt dosyası bulunmaktadır.

kullanicilar.txt: 1. aşamada kullanılan giriş bilgilerini içerir.
Personeller.txt: Personellerin isim, soyisim, telefon numarası, seviyesi, maaşı ve izin günlerini içerir. Bu bilgiler virgül(,) ile ayrılmıştır.
Java Klasörü: İçerisinde Login, LoginController, PersonalController, Personal class dosyaları bulunmaktadır.

Resources Klasörü: İçerisinde login.fxml ve Personal.fxml dosyaları yer almaktadır.

Classlar ve Fonksiyonlar:

Login Class: Uygulama, login.fxml'i kaynak alarak başlatılır ve kullanıcıya giriş ekranını sunar.

LoginController Class: Gerekli tanımlamalar yapıldıktan sonra, kullanıcının giriş yapmasını sağlayan fonksiyonlar bulunur.

girisYap(): kullanicilar.txt dosyasını okuyarak kullanıcı adı ve şifreyi doğrular.
changeScene(): 1. aşamadaki sekmeyi saklar ve 2. aşamadaki sekmeyi açar.
Personel Class: Personellerin özelliklerini ve işlemlerini içerir.

İki adet constructor bulunur.
zamYapma: Personelin maaşına zam yapılmasını sağlayan fonksiyonlar içerir.
PersonelController Class: Programın ana işlevlerini sağlar.

initialize(): Seviye seçimleri için seçenekler oluşturulur ve tablo yapılandırılır.
dosyaOkuma(): Dosyadan personel bilgilerini okur ve listeye ekler.
dosyaYazdirma(): Değişiklikleri dosyaya kaydeder.
tabloYenile(): Tabloyu günceller.
personelEkle(): Yeni personel ekler.
PersonelSil(): Personel siler.
zamYap(): Personel maaşlarına zam yapar.
aramaYap(): Personel arama işlemlerini gerçekleştirir.

Not: Projenin çalışabilmesi için kullanicilar.txt dosyasına örnek olarak "admin,123" bilgilerini ekledim. Bu bilgilerle giriş yapabilirsiniz (Kullanıcı adı: admin, Şifre: 123).
