package src.ev3;

import java.util.ArrayList;
import java.util.List;

import src.impl.Controller;
import src.impl.SilTest;
import src.interfaces.MotorInterface;
import src.interfaces.SensorInterface;

public class EV3Main {
        
    public static void main(String[] args) {
        EV3Sensor s1 = new EV3Sensor(0, "S1");
        EV3Sensor s2 = new EV3Sensor(1, "S2");
        EV3Sensor s3 = new EV3Sensor(2, "S3");
        EV3Sensor s4 = new EV3Sensor(3, "S4");

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

        s1.sThread();
        s2.sThread();
        s3.sThread();
        s4.sThread();

    }
}
