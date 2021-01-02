package frontend.view;

import javax.swing.JFrame;

public class Main {
    private static JFrame mainMenuFrame;

    public static void main(String[] args){
        mainMenuFrame = new JFrame("Program4Caster");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setVisible(true);
        MainMenu mainMenu = new MainMenu(mainMenuFrame);
    }
}
