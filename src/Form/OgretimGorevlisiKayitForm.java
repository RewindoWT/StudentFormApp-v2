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

public class OgretimGorevlisiKayitForm extends JFrame {
    private JTextField ogretmenNoField;
    private JTextField ogretmenAdField;
    private JTextField ogretmenSoyadField;
    private JButton kaydetButton;
    private JPanel ogretimGorevlisiKayitFormPanel;
    private JComboBox<String> bolumComboBox;
    private JComboBox<String> derslerComboBox;
    private JButton gosterButton;
    private JTable table1;
    private JTextField textField1;
    private DefaultTableModel tableModel;
    private DosyaServis dosyaServis = new DosyaServis();

    public OgretimGorevlisiKayitForm() {
        initializeComponents();

        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ogretmenNo = ogretmenNoField.getText();
                String ogretmenAd = ogretmenAdField.getText();
                String ogretmenSoyad = ogretmenSoyadField.getText();
                String ogretmenBolum = (String) bolumComboBox.getSelectedItem();

                dosyaServis.dosyayaKaydetOgretimGorevlisi(ogretmenNo, ogretmenAd, ogretmenSoyad, ogretmenBolum);

                // Ekledik: Tabloya yeni satır eklemek için
                String[] yeniBilgiler = {ogretmenNo, ogretmenAd, ogretmenSoyad, ogretmenBolum};
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
                List<String[]> ogretimGorevlisiBilgileri = dosyaServis.ogretimGorevlisiBilgileriniGetir("OgretimGorevlileri.txt");
                tableModel.setRowCount(0); // Tabloyu temizle

                for (String[] bilgiler : ogretimGorevlisiBilgileri) {
                    tableModel.addRow(bilgiler);
                }
            }
        });

        setContentPane(ogretimGorevlisiKayitFormPanel);
        setTitle("Öğretim Görevlisi Kayıt Formu");
        setMinimumSize(new Dimension(1250,850 ));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });
    }

    private void initializeComponents() {
        ogretimGorevlisiKayitFormPanel = new JPanel();
        ogretmenNoField = new JTextField();
        ogretmenAdField = new JTextField();
        ogretmenSoyadField = new JTextField();
        kaydetButton = new JButton("Kaydet");
        bolumComboBox = new JComboBox<>(new String[]{"Bilgisayar Mühendisliği", "Elektrik Mühendisliği"}); // Example data
        derslerComboBox = new JComboBox<>();
        gosterButton = new JButton("Göster");

        ogretimGorevlisiKayitFormPanel.setLayout(new GridLayout(10, 3));
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Öğretmen No:"));
        ogretimGorevlisiKayitFormPanel.add(ogretmenNoField);
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Öğretmen Ad:"));
        ogretimGorevlisiKayitFormPanel.add(ogretmenAdField);
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Öğretmen Soyad:"));
        ogretimGorevlisiKayitFormPanel.add(ogretmenSoyadField);
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Bölüm:"));
        ogretimGorevlisiKayitFormPanel.add(bolumComboBox);
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Dersler:"));
        ogretimGorevlisiKayitFormPanel.add(derslerComboBox);
        ogretimGorevlisiKayitFormPanel.add(kaydetButton);
        ogretimGorevlisiKayitFormPanel.add(new JLabel());
        ogretimGorevlisiKayitFormPanel.add(gosterButton);


        // Ekledik: Tablo modelini oluşturduk
        String[] columnNames = {"Öğretmen No", "Öğretmen Adı", "Öğretmen Soyadı", "Öğretmen Bölümü"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table1 = new JTable(tableModel);
        ogretimGorevlisiKayitFormPanel.add(new JScrollPane(table1));

        textField1 = new JTextField();
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Arama Tum Columlara Gore Yapilabilir:"));
        ogretimGorevlisiKayitFormPanel.add(textField1);
        ogretimGorevlisiKayitFormPanel.add(new JLabel("Goster Diyerek Dersleri ve Jtablea veriyi cekebilirsiniz:"));




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
        OgretimGorevlisiKayitForm ogretimGorevlisiKayitForm = new OgretimGorevlisiKayitForm();
    }
}
