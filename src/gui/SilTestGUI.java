package src.gui;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.Image;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class SilTestGUI extends JFrame {

    public SilTestGUI() {
        initializeWindow();

        // Layout
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);

        //Menubar

        // Button
        JButton button = new JButton("Quit");
        button.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        gl.setVerticalGroup(gl.createSequentialGroup().addComponent(button));
        gl.setHorizontalGroup(gl.createSequentialGroup().addComponent(button));
        gl.setAutoCreateContainerGaps(true);
    }

    private void initializeWindow(){
        setTitle("Sensor und Motor Test");
        setSize(1400, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Image image = new ImageIcon(this.getClass().getResource("/resources/mac256.png")).getImage();
        setIconImage(image);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                SilTestGUI s = new SilTestGUI();
                s.setVisible(true);
            }
        });
    }


}