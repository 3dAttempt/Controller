package src.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.gui.SimulatorGUI;
import src.interfaces.MotorInterface;
import src.interfaces.SensorInterface;

public class Main {
    public static void main(String[] args) {
        if (args[0].equals("SilTest") && args[1].equals("SilTest")) {   // Input values at least 3 times so the array gets filled.
            SensorInterface s = new SilTest.Sensor(0);
            MotorInterface m = new SilTest.Motor(0);

            Controller c = new Controller(s, m);
            s.addObserver(c);

            while (true) {
                Scanner sc = new Scanner(System.in);
                int input = Integer.parseInt(sc.nextLine());
                s.populateArray(input);

                System.out.println(m.getDriveValue() + " | " + m.getSteerValue());
            }
        }
        if (args[0].equals("GUI") && args[1].equals("SilTest")) {
            SimulatorGUI s = new SimulatorGUI(true);
            MotorInterface m1 = new SilTest.Motor(0);
            MotorInterface m2 = new SilTest.Motor(1);

            List<MotorInterface> mList = new ArrayList<>();
            mList.add(m1);
            mList.add(m2);

            Controller c = new Controller(s.getSensorList(), mList);
            s.addController(c);


            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(mList.get(0).getDriveValue() + " | " + mList.get(1).getSteerValue());
            }

        }
        if (args[0].equals("SilTest") && args[1].equals("GUI")) {   // Insert values at least 3 times <sensor1> <sensor2> <sensor3> <sensor4>
            SensorInterface s1 = new SilTest.Sensor(0);
            SensorInterface s2 = new SilTest.Sensor(1);
            SensorInterface s3 = new SilTest.Sensor(2);
            SensorInterface s4 = new SilTest.Sensor(3);

            List<SensorInterface> sList = new ArrayList<>();
            sList.add(s1);
            sList.add(s2);
            sList.add(s3);
            sList.add(s4);

            SimulatorGUI m = new SimulatorGUI(false);

            Controller c = new Controller(sList, m.getMotorList());
            s1.addObserver(c);
            s2.addObserver(c);
            s3.addObserver(c);
            s4.addObserver(c);

            while(true){
                Scanner sc = new Scanner(System.in);
                int input1 = Integer.parseInt(sc.next());
                int input2 = Integer.parseInt(sc.next());
                int input3 = Integer.parseInt(sc.next());
                int input4 = Integer.parseInt(sc.next());

                s1.populateArray(input1);
                s2.populateArray(input2);
                s3.populateArray(input3);
                s4.populateArray(input4);

                System.out.println(m.getMotorList().get(0).getDriveValue() + " | " + m.getMotorList().get(1).getSteerValue());
            }

        }
        if (args[0].equals("GUI") && args[1].equals("GUI")) {
            new SimulatorGUI(false);
        }
    }

}
