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
import javax.swing.border.EmptyBorder;

import src.impl.Sensor;

public class SimulatorGUI extends JFrame {

    JFrame frame;
    JButton sensorButton;
    JTextField sensorField1;
    JTextField sensorField2;
    JTextField sensorField3;
    JTextField sensorField4;
    JTextField motorDField;
    JTextField motorSField;
    int sensorCount = 0;

    public SimulatorGUI() {
        frame = new JFrame();
        initializeWindow();

        JPanel sensorArea = new JPanel();
        sensorArea.setLayout(new GridLayout(1, 1));
        sensorArea.setBorder(new EmptyBorder(50, 0, 0, 0));

        JPanel sensor1 = new JPanel(new FlowLayout());
        JPanel sensor2 = new JPanel(new FlowLayout());
        JPanel sensor3 = new JPanel(new FlowLayout());
        JPanel sensor4 = new JPanel(new FlowLayout());

        JLabel sensorLabel1 = new JLabel("Sensor 1");
        JLabel sensorLabel2 = new JLabel("Sensor 2");
        JLabel sensorLabel3 = new JLabel("Sensor 3");
        JLabel sensorLabel4 = new JLabel("Sensor 4");

        // Sensor 1
        sensorField1 = new JTextField();
        sensorField1.setPreferredSize(new Dimension(200, 24));
        // Sensor 2
        sensorField2 = new JTextField();
        sensorField2.setPreferredSize(new Dimension(200, 24));
        // Sensor 3
        sensorField3 = new JTextField();
        sensorField3.setPreferredSize(new Dimension(200, 24));
        // Sensor 4
        sensorField4 = new JTextField();
        sensorField4.setPreferredSize(new Dimension(200, 24));

        sensorButton = new JButton("Übernehmen");

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

        // Motor drive
        motorDField = new JTextField();
        motorDField.setPreferredSize(new Dimension(200, 24));
        motorDField.setEditable(false);
        // Motor Steer
        motorSField = new JTextField();
        motorSField.setPreferredSize(new Dimension(200, 24));
        motorSField.setEditable(false);

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

    private void calculateValues(){
        
        sensorButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int val1 = Integer.parseInt(sensorField1.getText());
                int val2 = Integer.parseInt(sensorField2.getText());
                int val3 = Integer.parseInt(sensorField3.getText());
                int val4 = Integer.parseInt(sensorField4.getText());
                
            }
            
        });
    }

    Sensor createSensor() {
        Sensor s = new Sensor(sensorCount);
        sensorCount += 1;

        return s;
    }

}