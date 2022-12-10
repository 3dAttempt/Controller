package src.nxt;

import java.util.ArrayList;
import java.util.List;

import src.impl.Controller;
import src.interfaces.MotorInterface;
import src.interfaces.SensorInterface;

public class NXTMain {
    public static void main(String[] args) {

        NXTSensor s1 = new NXTSensor(0, "S1");
        NXTSensor s2 = new NXTSensor(1, "S2");
        NXTSensor s3 = new NXTSensor(2, "S3");
        NXTSensor s4 = new NXTSensor(3, "S4");

        List<SensorInterface> sList = new ArrayList<>();
        sList.add(s1);
        sList.add(s2);
        sList.add(s3);
        sList.add(s4);

        NXTMotor dm = new NXTMotor(0, "drive");
        NXTMotor sm = new NXTMotor(1, "steer");

        List<MotorInterface> mList = new ArrayList<>();
        mList.add(dm);
        mList.add(sm);

        Controller c = new Controller(sList, mList);
        s1.addObserver(c);
        s2.addObserver(c);
        s3.addObserver(c);
        s4.addObserver(c);

        s1.sThread();
        s2.sThread();
        s3.sThread();
        s4.sThread();
    }
}
