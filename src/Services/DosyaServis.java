package Services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DosyaServis {
    public void dosyayaKaydetDers(String dersKodu, String dersAdi, String dersDonemi, String secilenHoca) {
        try (FileWriter writer = new FileWriter("dersler.txt", true)) {
            String data = "Ders Kod: " + dersKodu + "," + "Ders Ad: " + dersAdi + "," + "Ders Dönem:" + dersDonemi + "," + "Secilen Hoca:" + secilenHoca + System.lineSeparator();
            writer.write(data);
            System.out.println("Ders kaydedildi: " + dersKodu + ", " + dersAdi + ", " + dersDonemi + ", Hoca: " + secilenHoca);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dosyayaKaydetOgrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciDers, String ogrenciBolum) {
        try (FileWriter writer = new FileWriter("Ogrenciler.txt", true)) {
            String data = "Ogrenci No: " + ogrenciNo + "," + "Ogrenci Ad:" + ogrenciAd + "," + "Ogrenci Soyad: " + ogrenciSoyad + "," + "Ogrenci Ders: " + ogrenciDers + "," + "Ogrenci Bolum:" + ogrenciBolum + System.lineSeparator();
            writer.write(data);
            System.out.println("Ogrenciler kaydedildi: " + ogrenciNo + ", " + ogrenciAd + ", " + ogrenciSoyad + ", Hoca: " + ogrenciDers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dosyayaKaydetOgretimGorevlisi(String ogretmenNo, String ogretmenAd, String ogretmenSoyad, String ogretmenBolum) {
        try (FileWriter writer = new FileWriter("OgretimGorevlileri.txt", true)) {
            String data = "Ogretim Gorevlisi No: " + ogretmenNo + "," + "Ogretim Gorevlisi Ad:" + ogretmenAd + "," + "Ogretim Gorevlisi Soyad: " + ogretmenSoyad + "," + "Ogretim Gorevlisi Bolum:" + ogretmenBolum + System.lineSeparator();
            writer.write(data);
            System.out.println("Ogretim Gorevlisi kaydedildi: " + ogretmenNo + ", " + ogretmenAd + ", " + ogretmenSoyad + ", Bolum: " + ogretmenBolum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> dersBilgileriniGetir(String dosyaYolu) {
        List<String[]> dersBilgileri = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                // Burada dosya formatına göre parçalama işlemleri
                String[] bilgiler = satir.split(",");
                dersBilgileri.add(bilgiler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dersBilgileri;
    }

    public static List<String> getDersIsimleri(String dosyaYolu) {
        List<String> dersIsimleri = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;
            Pattern pattern = Pattern.compile("Ders Ad: (.*?),");

            while ((satir = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(satir);
                if (matcher.find()) {
                    String dersIsim = matcher.group(1);
                    dersIsimleri.add(dersIsim);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dersIsimleri;

    }
    public List<String[]> ogretimGorevlisiBilgileriniGetir(String dosyaYolu) {
        List<String[]> ogretimGorevlisiBilgileri = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(dosyaYolu))) {
            String satir;

            while ((satir = br.readLine()) != null) {
                // Burada dosya formatına göre parçalama işlemleri
                String[] bilgiler = satir.split(",");
                ogretimGorevlisiBilgileri.add(bilgiler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ogretimGorevlisiBilgileri;
    }

    public static void main(String[] args) {
        DosyaServis dosyaServis = new DosyaServis();

        List<String> dersler = dosyaServis.getDersIsimleri("dersler.txt");

        System.out.println("Ders Isimleri:");
        for (String ders : dersler) {
            System.out.println(ders);
        }
    }
}
