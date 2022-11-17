package src.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.impl.Sensor;

public class SimulatorGUI extends JFrame {

    JFrame frame;
    int sensorCount = 0;

    public SimulatorGUI() {
        frame = new JFrame();
        initializeWindow();

        JPanel sensorArea = new JPanel();
        sensorArea.setLayout(new GridLayout(1, 1));

        JPanel sensor1 = new JPanel(new FlowLayout());
        JPanel sensor2 = new JPanel(new FlowLayout());
        JPanel sensor3 = new JPanel(new FlowLayout());
        JPanel sensor4 = new JPanel(new FlowLayout());

        JLabel sensorLabel1 = new JLabel("Sensor 1");
        JLabel sensorLabel2 = new JLabel("Sensor 2");
        JLabel sensorLabel3 = new JLabel("Sensor 3");
        JLabel sensorLabel4 = new JLabel("Sensor 4");

        JTextField sensorField1 = new JTextField();
        sensorField1.setPreferredSize(new Dimension(200, 24));
        JTextField sensorField2 = new JTextField();
        sensorField2.setPreferredSize(new Dimension(200, 24));
        JTextField sensorField3 = new JTextField();
        sensorField3.setPreferredSize(new Dimension(200, 24));
        JTextField sensorField4 = new JTextField();
        sensorField4.setPreferredSize(new Dimension(200, 24));


        JButton sensorButton = new JButton("Übernehmen");

        sensorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Sensor 1");
            }
        });


        sensor1.add(sensorLabel1);
        sensor1.add(sensorField1);

        sensor2.add(sensorLabel2);
        sensor2.add(sensorField2);

        sensor3.add(sensorLabel3);
        sensor3.add(sensorField3);

        sensor4.add(sensorLabel4);
        sensor4.add(sensorField4);

        sensorArea.add(sensor1);
        sensorArea.add(sensor2);
        sensorArea.add(sensor3);
        sensorArea.add(sensor4);

        frame.add(sensorArea);

        JPanel buttonArea = new JPanel();
        buttonArea.add(sensorButton);

        frame.add(buttonArea);

        JPanel motorArea = new JPanel();

        JPanel motorDrive = new JPanel(new FlowLayout());
        JPanel motorSteer = new JPanel(new FlowLayout());

        JLabel motorDLabel = new JLabel("Motor zur Fahrt");
        JLabel motorSLabel = new JLabel("Motor zum Lenken");

        JTextField motorDField = new JTextField();
        motorDField.setPreferredSize(new Dimension(200, 24));
        JTextField motorSField = new JTextField();
        motorSField.setPreferredSize(new Dimension(200, 24));

        motorDrive.add(motorDLabel);
        motorDrive.add(motorDField);

        motorSteer.add(motorSLabel);
        motorSteer.add(motorSField);

        motorArea.add(motorDrive);
        motorArea.add(motorSteer);

        frame.add(motorArea);

        frame.setVisible(true);
    }

    private void initializeWindow() {
        frame.setTitle("Sensor und Motor Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLayout(new GridLayout(3, 1));
        frame.setResizable(false);

        Image image = new ImageIcon(this.getClass().getResource("/resources/mac256.png")).getImage();
        frame.setIconImage(image);
    }

    Sensor createSensor() {
        Sensor s = new Sensor(sensorCount);
        sensorCount += 1;

        return s;
    }

}