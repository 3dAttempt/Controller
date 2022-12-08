package src.ev3;

import src.impl.Sensor;

import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.SensorPort;

public class EV3Sensor extends Sensor{

    NXTUltrasonicSensor sensor;
    SampleProvider distance;

    int sensorValue;
    int newSensorValue;
    boolean isRunning = true;

    public EV3Sensor(int id, String port) {
        super(id);

        if(port.equals("S1")) sensor = new NXTUltrasonicSensor(SensorPort.S1);
        if(port.equals("S2")) sensor = new NXTUltrasonicSensor(SensorPort.S2);
        if(port.equals("S3")) sensor = new NXTUltrasonicSensor(SensorPort.S3);
        if(port.equals("S4")) sensor = new NXTUltrasonicSensor(SensorPort.S4);

        distance = sensor.getMode("Distance");
    }

    Thread sThread() {
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
    
    void scanDistance() {
        float[] sample = new float[distance.sampleSize()];
        distance.fetchSample(sample, 0);
        newSensorValue = (int) Math.round(sample[0]);

        super.populateArray(newSensorValue);
    }
}
