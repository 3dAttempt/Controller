package src.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.impl.Sensor;

public class SimulatorGUI extends JFrame {

    JFrame frame;
    int sensorCount = 0;

    public SimulatorGUI() {
        frame = new JFrame();
        initializeWindow();

        JPanel sensorArea = new JPanel();
        JPanel motorArea = new JPanel();

        sensorArea.setBackground(Color.cyan);
        motorArea.setBackground(Color.magenta);

        JButton button = new JButton("Press me!");

        sensorArea.add(button);

        frame.add(sensorArea,BorderLayout.WEST);
        frame.add(motorArea, BorderLayout.EAST);
    }

    private void initializeWindow(){
        frame.setTitle("Sensor und Motor Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 900);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        
        Image image = new ImageIcon(this.getClass().getResource("/resources/mac256.png")).getImage();
        frame.setIconImage(image);
    }

    Sensor createSensor(){
        Sensor s = new Sensor(sensorCount);
        sensorCount += 1;

        return s;
    }

}