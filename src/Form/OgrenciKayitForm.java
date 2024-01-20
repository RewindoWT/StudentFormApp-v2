package Form;

import Services.DosyaServis;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.TableRowSorter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OgrenciKayitForm extends JFrame {
    private JTextField ogrenciNoField;
    private JTextField ogrenciAdField;
    private JTextField ogrenciSoyadField;
    private JButton kaydetButton;
    private JPanel OgrenciKayitForm;
    private JComboBox bolumComboBox;
    private JComboBox derslerComboBox;
    private JButton gosterButton;
    private JTable table1;
    private JRadioButton erkekRadioButton;
    private JRadioButton kadınRadioButton;
    private JTextField textField1;
    private DefaultTableModel tableModel; // Ekledik
    DosyaServis dosyaServis = new DosyaServis();

    public OgrenciKayitForm() {
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogrenciNo = ogrenciNoField.getText();
                String ogrenciAd = ogrenciAdField.getText();
                String ogrenciSoyad = ogrenciSoyadField.getText();
                String ogrenciBolum = (String) bolumComboBox.getSelectedItem();
                String ogrenciDers = (String) derslerComboBox.getSelectedItem();

                dosyaServis.dosyayaKaydetOgrenci(ogrenciNo, ogrenciAd, ogrenciSoyad, ogrenciDers, ogrenciBolum);

                // Ekledik: Tabloya yeni satır eklemek için
                String[] yeniBilgiler = {ogrenciNo, ogrenciAd, ogrenciSoyad, ogrenciDers, ogrenciBolum};
                tableModel.addRow(yeniBilgiler);
            }
        });

        gosterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                derslerComboBox.removeAllItems();

                List<String> dersList = dosyaServis.getDersIsimleri("dersler.txt");
                if (dersList != null) {
                    for (String ders : dersList) {
                        derslerComboBox.addItem(ders);
                    }
                } else {
                    System.out.println("Ders listesi bulunamadı.");
                }

                // Tabloyu güncelle
                List<String[]> ogrenciBilgileri = dosyaServis.dersBilgileriniGetir("Ogrenciler.txt");
                tableModel.setRowCount(0); // Tabloyu temizle

                for (String[] bilgiler : ogrenciBilgileri) {
                    tableModel.addRow(bilgiler);
                }
            }
        });

        setContentPane(OgrenciKayitForm);
        setTitle("Öğrenci Kayıt Formu");
        setMinimumSize(new Dimension(1000, 600));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        // Ekledik: Tablo modelini oluşturduk
        String[] columnNames = {"Öğrenci No", "Öğrenci Adı", "Öğrenci Soyadı", "Öğrenci Dersi", "Öğrenci Bölümü"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1.setModel(tableModel);

        // Ekledik: Öğrenci bilgilerini tabloya eklemek için
        List<String[]> ogrenciBilgileri = dosyaServis.dersBilgileriniGetir("Ogrenciler.txt");
        for (String[] bilgiler : ogrenciBilgileri) {
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
        OgrenciKayitForm ogrenciKayitForm = new OgrenciKayitForm();
    }
}
