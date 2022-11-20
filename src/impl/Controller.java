package src.impl;

import java.util.List;

import src.interfaces.SensorObserver;

public class Controller implements SensorObserver {

    Sensor sensor;
    Motor motor;

    List<Sensor> sensorList;
    List<Motor> motorList;

    public Controller(Sensor sensor, Motor motor) {
        this.sensor = sensor;
        this.motor = motor;
    }

    public Controller(List<Sensor> sensorList, List<Motor> motorList) {
        this.sensorList = sensorList;
        this.motorList = motorList;
    }

    @Override
    public void newValue(int n, int id) {

        if(sensorList.size() == 0){
            if (n <= 5 && !(n < 0)) {
                motor.steer(n); // I assume that the sensor value is the distance to a obstacle
                motor.drive(0); // That's why if the distance is 5 or less the driving motor stops and the
                                // steering motor starts to steer
            }
            if (n > 5) {
                motor.steer(0); // If the distance is bigger than 5 drive forward without steering
                motor.drive(n);
            }
            if (n < 0) {
                motor.steer(0); // If the value is negative stop both motors
                motor.drive(0);
            }
        }
        if(sensorList.size() != 0){
            newValueList();
        }
        
    }

    @Override
    public void newValueList() {

        int valCalc = 0;
        int valFront = 0;
        int valRear = 0;
        int val1 = sensorList.get(0).getValue();
        int val2 = sensorList.get(1).getValue();
        int val3 = sensorList.get(2).getValue();
        int val4 = sensorList.get(3).getValue();

        if (val1 <= val2) {
            valFront = val1;
        }
        if (val1 > val2) {
            valFront = val2;
        }
        if (val3 <= val4) {
            valRear = val3;
        }
        if (val3 > val4) {
            valRear = val4;
        }
        if(valFront <= valRear){
            valCalc = valFront;
        }
        if(valFront > valRear){
            valCalc = valRear;
        }

        if (valCalc <= 5 && !(valCalc < 0)) {
            motorList.get(0).drive(0);
            motorList.get(1).steer(valCalc);
        }
        if (valCalc > 5) {
            motorList.get(0).drive(valCalc);
            motorList.get(1).steer(0);
        }
        if (valCalc < 0) {
            motorList.get(0).drive(0);
            motorList.get(1).steer(0);
        }
    }
}
