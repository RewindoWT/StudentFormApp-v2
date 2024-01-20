package Form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuForm extends JFrame{
    private JButton ogrenciKayıtFormButton;
    private JButton dersKayıtFormButton;
    private JPanel MenuForm;
    private JButton ogretimGorevlisiKayitFormButton;

    public MenuForm() {

        ogrenciKayıtFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgrenciKayitForm ogrenciKayitForm = new OgrenciKayitForm();
                ogrenciKayitForm.setVisible(true);
            }
        });
        dersKayıtFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DersKayitForm dersKayitForm = new DersKayitForm();
                dersKayitForm.setVisible(true);
            }
        });
        ogretimGorevlisiKayitFormButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OgretimGorevlisiKayitForm ogretimGorevlisiKayitForm = new OgretimGorevlisiKayitForm();
                ogretimGorevlisiKayitForm.setVisible(true);
            }
        });

        setContentPane(MenuForm);
        setTitle("Main Menu Page");
        setMinimumSize(new Dimension(500,300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        MenuForm menuForm = new MenuForm();
    }
}
