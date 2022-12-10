package src.ev3;

import java.util.ArrayList;
import java.util.List;

import src.impl.Controller;
import src.impl.SilTest;
import src.interfaces.MotorInterface;
import src.interfaces.SensorInterface;

public class SilEV3Main {
    public static void main(String[] args) {
        SilTest.Sensor s1 = new SilTest.Sensor(0);
        SilTest.Sensor s2 = new SilTest.Sensor(1);
        SilTest.Sensor s3 = new SilTest.Sensor(2);
        SilTest.Sensor s4 = new SilTest.Sensor(3);

        List<SensorInterface> sList = new ArrayList<>();
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);
        sList.add(s4);

        EV3Motor dm = new EV3Motor(0, "drive");
        EV3Motor sm = new EV3Motor(1, "steer");

        List<MotorInterface> mList = new ArrayList<>();
        mList.add(dm);
        mList.add(sm);

        Controller c = new Controller(sList, mList);
        s1.addObserver(c);
        s2.addObserver(c);
        s3.addObserver(c);
        s4.addObserver(c);

        s1.frontSensorThread();
        s2.frontSensorThread();
        s3.rearSensorThread();
        s4.rearSensorThread();
    }
}