package src.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import src.impl.Sensor;
import src.impl.Controller;
import src.impl.Motor;
import src.interfaces.*;

public class SimulatorGUI extends JFrame{


    private JFrame frame;
    private JButton sensorButton;
    private JCheckBox toleranceToggle;
    private JTextField sensorField1;
    private JTextField sensorField2;
    private JTextField sensorField3;
    private JTextField sensorField4;
    private JTextField motorDField;
    private JTextField motorSField;

    private SensorInterface s1;
    private SensorInterface s2;
    private SensorInterface s3;
    private SensorInterface s4;
    private List<SensorInterface> sList = new CopyOnWriteArrayList<>();

    private MotorInterface m1;
    private MotorInterface m2;
    private List<MotorInterface> mList = new CopyOnWriteArrayList<>();

    private Controller controller;

    public SimulatorGUI(boolean b) {
        frame = new JFrame();
        initializeWindow();
        createSensor();
        createMotor();


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
        sensorField1 = new JTextField("0");
        sensorField1.setPreferredSize(new Dimension(200, 24));
        // Sensor 2
        sensorField2 = new JTextField("0");
        sensorField2.setPreferredSize(new Dimension(200, 24));
        // Sensor 3
        sensorField3 = new JTextField("80");
        sensorField3.setPreferredSize(new Dimension(200, 24));
        // Sensor 4
        sensorField4 = new JTextField("80");
        sensorField4.setPreferredSize(new Dimension(200, 24));

        sensorButton = new JButton("Übernehmen");
        toleranceToggle = new JCheckBox("Mit Fehlertoleranz");

        calculateValues();

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
        buttonArea.add(toleranceToggle);

        frame.add(buttonArea);

        JPanel motorArea = new JPanel();

        JPanel motorDrive = new JPanel(new FlowLayout());
        JPanel motorSteer = new JPanel(new FlowLayout());

        JLabel motorDLabel = new JLabel("Motor zum Fahren");
        JLabel motorSLabel = new JLabel("Motor zum Lenken");

        // Motor drive
        motorDField = new JTextField();
        motorDField.setPreferredSize(new Dimension(200, 24));
        motorDField.setEditable(false);
        
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                motorDField.setText("" + m1.getDriveValue());
                motorSField.setText("" + m2.getSteerValue());
            }
        };
        Timer timer = new Timer(100, task); // Execute task each 100 miliseconds
        timer.setRepeats(true);
        timer.start();

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
    }

    private void calculateValues() {

        sensorButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int val1 = Integer.parseInt(sensorField1.getText());
                int val2 = Integer.parseInt(sensorField2.getText());
                int val3 = Integer.parseInt(sensorField3.getText());
                int val4 = Integer.parseInt(sensorField4.getText());

                if(toleranceToggle.isSelected()){
                    for(int i = 0; i < (3 - s1.getValueList().size()); i++){
                        s1.populateArray(val1);
                    }
                    for(int i = 0; i < (3 - s2.getValueList().size()); i++){
                        s2.populateArray(val2);
                    }
                    for(int i = 0; i < (3 - s3.getValueList().size()); i++){
                        s3.populateArray(val3);
                    }
                    for(int i = 0; i < (3 - s4.getValueList().size()); i++){
                        s4.populateArray(val4);
                    }

                    s1.populateArray(val1);
                    s2.populateArray(val2);
                    s3.populateArray(val3);
                    s4.populateArray(val4);

                }
                if(!(toleranceToggle.isSelected())){
                    s1.changeValue(val1);
                    s2.changeValue(val2);
                    s3.changeValue(val3);
                    s4.changeValue(val4);
                }

                motorDField.setText("" + m1.getDriveValue());
                motorSField.setText("" + m2.getSteerValue());

            }

        });
    }

    private void createSensor() {
        s1 = new Sensor(0);
        s2 = new Sensor(1);
        s3 = new Sensor(2);
        s4 = new Sensor(3);

        sList.add(s1);
        sList.add(s2);
        sList.add(s3);
        sList.add(s4);
    }

    private void createMotor(){
        m1 = new Motor(0);
        m2 = new Motor(1);

        mList.add(m1);
        mList.add(m2);
    }

    public void addController(Controller c){
        this.controller = c;
        
        for(int i = 0; i < sList.size(); i++){
            sList.get(i).addObserver(controller);
        }
    }

    public List<SensorInterface> getSensorList(){
        return sList;
    }

    public List<MotorInterface> getMotorList(){
        return mList;
    }

}