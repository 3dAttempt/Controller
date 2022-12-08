package src.nxt;

import src.impl.Sensor;

import lejos.nxt.UltrasonicSensor;
import lejos.nxt.SensorPort;

public class NXTSensor extends Sensor{

    UltrasonicSensor sensor;

    int sensorValue;
    int newSensorValue;
    boolean isRunning = true;

    public NXTSensor(int id, String port) {
        super(id);

        if(port.equals("S1")) sensor = new UltrasonicSensor(SensorPort.S1);
        if(port.equals("S2")) sensor = new UltrasonicSensor(SensorPort.S2);
        if(port.equals("S3")) sensor = new UltrasonicSensor(SensorPort.S3);
        if(port.equals("S4")) sensor = new UltrasonicSensor(SensorPort.S4);

        sensor.continuous();
    }

    void scanDistance() {
        int newSensorValue = sensor.getDistance();

        super.populateArray(newSensorValue);
    }

    public Thread sThread() {
        Thread t = new Thread(){
            public void run() {
                while(isRunning){
                    scanDistance();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();
        return t;
    }

    
}
