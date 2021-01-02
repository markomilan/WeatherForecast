package frontend.view;

import backend.datastore.Datastore;
import backend.sending.Sending;
import frontend.resloader.MenuGallery;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import backend.connection.Connection;

public class MainMenu extends JPanel implements ActionListener {

    private static final String DEFAULT_EMAIL_FIELD_MESSAGE = "E-mail address";
    private static final String DEFAULT_CITY_FIELD_MESSAGE = "City name";

    private MenuGallery menuGallery;

    private JFrame mainMenuFrame;

    private JButton saveButton;
    private JButton sendButton;
    private JButton chromeButton;
    private JButton firefoxButton;

    private JTextField emailField;
    private JTextField cityField;

    private JLabel mainMenuLabel;


    public MainMenu(JFrame mainMenuFrame){
        this.menuGallery = new MenuGallery();
        this.mainMenuLabel = new JLabel(menuGallery.iconBackground);
        this.mainMenuFrame = mainMenuFrame;

        buildMainMenu();
    }

    private void buildMainMenu() {
        saveButton = createButton(this.menuGallery.iconSaveButton,150,400,200,50);
        sendButton = createButton(this.menuGallery.iconSendButton,400,30,80,50);
        chromeButton = createButton(this.menuGallery.iconChrome,50,50,80,50);
        firefoxButton = createButton(this.menuGallery.iconFirefox,50,110,80,50);

        emailField = createFieldEmail(80,330,150,50);
        cityField = createFieldCity(270,330,150,50);

        mainMenuLabel.add(saveButton);
        mainMenuLabel.add(sendButton);
        mainMenuLabel.add(chromeButton);
        mainMenuLabel.add(firefoxButton);

        mainMenuLabel.add(emailField);
        mainMenuLabel.add(cityField);

        saveButton.addActionListener(this);
        sendButton.addActionListener(this);
        chromeButton.addActionListener(this);
        firefoxButton.addActionListener(this);

        mainMenuFrame.setSize(500,500);
        mainMenuFrame.setContentPane(mainMenuLabel);
        mainMenuFrame.setVisible(true);
        mainMenuFrame.setResizable(false);

    }

    public JButton createButton(ImageIcon icon,int x,int y,int width,int height){
        JButton button = new JButton(icon);
        //button.setBorder(null);
        button.setBounds(x,y,width,height);
        button.setVisible(true);
        return button;
    }

    public JTextField createFieldEmail(int x,int y,int width,int height){
        JTextField field = new JTextField(DEFAULT_EMAIL_FIELD_MESSAGE);
        field.setBackground(Color.white);
        field.setBounds(x,y,width,height);
        field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                field.setText("");
            }
            public void focusLost(FocusEvent e) {
                if (emailField.getText().equals("") && e.getSource() == emailField) {
                    field.setText(DEFAULT_EMAIL_FIELD_MESSAGE);
                }
            }
        });
        return field;
    }

    public JTextField createFieldCity(int x,int y,int width,int height){
        JTextField field = new JTextField(DEFAULT_CITY_FIELD_MESSAGE);
        field.setBackground(Color.white);
        field.setBounds(x,y,width,height);
        field.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                field.setText("");
            }
            public void focusLost(FocusEvent e) {
                if (cityField.getText().equals("") && e.getSource() == cityField) {
                    field.setText(DEFAULT_CITY_FIELD_MESSAGE);
                }
            }
        });
        return field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveButton){
            if(!emailField.getText().equals(DEFAULT_EMAIL_FIELD_MESSAGE) && !cityField.getText().equals(DEFAULT_EMAIL_FIELD_MESSAGE)) {
                storeData(emailField.getText(), cityField.getText());
            }
        }
        else if(e.getSource() == sendButton){
            Sending sending = new Sending();
        }
        else if(e.getSource() == chromeButton) {
            Connection connection = new Connection();
            try {
                connection.setChromeDriver();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
        else if(e.getSource() == firefoxButton) {
            Connection connection = new Connection();
            try {
                connection.setFireFoxDriver();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    public void storeData(String email, String city){
        Datastore datastore = new Datastore(email, city);
    }
}
