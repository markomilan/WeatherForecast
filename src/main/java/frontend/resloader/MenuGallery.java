package frontend.resloader;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class MenuGallery {
    public BufferedImage imageChrome;
    public BufferedImage imageFirefox;
    public BufferedImage imageBackground;
    public BufferedImage imageSaveButton;
    public BufferedImage imageSendButton;


    public ImageIcon iconChrome;
    public ImageIcon iconFirefox;
    public ImageIcon iconBackground;
    public ImageIcon iconSaveButton;
    public ImageIcon iconSendButton;


    public MenuGallery(){
        try {
            imageChrome = ImageLoader.readImage("/chrome_icon.png");
            imageFirefox = ImageLoader.readImage("/firefox_icon.png");
            imageBackground = ImageLoader.readImage("/background.png");
            imageSaveButton = ImageLoader.readImage("/save_button.png");
            imageSendButton = ImageLoader.readImage("/send_button2.png");


            iconChrome = new ImageIcon(imageChrome);
            iconFirefox = new ImageIcon(imageFirefox);
            iconBackground = new ImageIcon(imageBackground);
            iconSaveButton = new ImageIcon(imageSaveButton);
            iconSendButton = new ImageIcon(imageSendButton);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
