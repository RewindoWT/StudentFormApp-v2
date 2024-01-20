package Form;

import Models.Ders;
import Services.DosyaServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

public class DersKayitForm extends JFrame {
    private JTextField dersKoduField;
    private JTextField dersAdField;
    private JTextField dersDonemField;
    private JComboBox HocaListesi;
    private JButton kaydetButton;
    private JPanel DersKayitForm;
    private JTable table1;
    private JTextField textField1;
    private DefaultTableModel tableModel; // Ekledik
    Ders ders = new Ders();
    String dersKod = ders.DersKod;
    String dersAd = ders.DersAd;
    String dersDonem = ders.DersDonem;

    DosyaServis dosyaServis = new DosyaServis();

    public DersKayitForm() {
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dersKod = dersKoduField.getText();
                dersAd = dersAdField.getText();
                dersDonem = dersDonemField.getText();
                String hocaListe = (String) HocaListesi.getSelectedItem();

                System.out.println("Ders Bilgileri:");
                System.out.println("Ders Kodu: " + dersKod);
                System.out.println("Ders Adı: " + dersAd);
                System.out.println("Ders Dönemi: " + dersDonem);
                System.out.println("Seçilen Hoca: " + hocaListe);
                System.out.println();

                dosyaServis.dosyayaKaydetDers(dersKod, dersAd, dersDonem, hocaListe);

                // Ekledik: Tabloya yeni satır eklemek için
                String[] yeniBilgiler = {dersKod, dersAd, dersDonem, hocaListe};
                tableModel.addRow(yeniBilgiler);
            }
        });


        setContentPane(DersKayitForm);
        setTitle("Ders Kayit Formu");
        setMinimumSize(new Dimension(1000, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Ekledik: Tablo modelini oluşturduk
        String[] columnNames = {"Ders Kodu", "Ders Adı", "Ders Dönemi", "Seçilen Hoca"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);

        // Ekledik: Ders bilgilerini tabloya eklemek için
        List<String[]> dersBilgileri = dosyaServis.dersBilgileriniGetir("dersler.txt");

        for (String[] bilgiler : dersBilgileri) {
            tableModel.addRow(bilgiler);
        }

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String arananKelime = textField1.getText().toLowerCase().trim();
                TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(tableModel);
                table1.setRowSorter(rowSorter);

                // Tüm sütunlarda arama yapmak için
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + arananKelime + ".*"));
            }
        });


    }

    public static void main(String[] args) {
        DersKayitForm dersKayitForm = new DersKayitForm();
    }
}
