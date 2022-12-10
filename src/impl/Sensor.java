package src.impl;

import java.util.ArrayList;
import java.util.List;

import src.interfaces.SensorInterface;
import src.interfaces.SensorObserver;

public class Sensor implements SensorInterface {
    private int id;
    private int sensorValue;
    private List<SensorObserver> osList = new ArrayList<>();
    private List<Integer> valueList = new ArrayList<>();
    private int tolerance = 5;


    public Sensor(int id){
        this.id = id;

        valueList.add(40);  // without these the sensors start with 0 distance which causes the robot to immediately turn
        valueList.add(40);
        valueList.add(40);
    }

    @Override
    public void addObserver(SensorObserver so) {
        osList.add(so);
    }

    @Override
    public void removeObserver(SensorObserver so) {
        osList.remove(so);
    }

    @Override
    public void notifyCon() {
        for (SensorObserver so : osList) {
            so.newValue(sensorValue, id);
        }
    }

    // if the value is the same as the old one, do nothing
    @Override
    public void changeValue(int newSensorValue) {
        if (newSensorValue != sensorValue) {
            sensorValue = newSensorValue;
            notifyCon();
        }
    }

    @Override
    public void populateArray(int newSensorValue) {

        valueList.add(newSensorValue);

        if (valueList.size() >= 3) {
            if (valueList.size() > 3) {
                valueList.remove(0);
            }
            faultTolerance(valueList);
        }
    }

    @Override
    public void faultTolerance(List<Integer> valueList) {
        // check all values and add tolerance
        this.valueList = valueList;
        int difOne = Math.abs(valueList.get(0) - valueList.get(1)); // difference between value 1 and value 2
        int difTwo = Math.abs(valueList.get(0) - valueList.get(2)); // difference between value 1 and value 3
        int difThree = Math.abs(valueList.get(1) - valueList.get(2)); // difference between value 2 and 3

        for (int i = 0; i < valueList.size(); i++) {
            if (valueList.get(i) > 80)
                valueList.set(i, 80);
            if (valueList.get(i) < 5 && !(valueList.get(i) < 0))
                valueList.set(i, 5);
        }
        if (!(valueList.get(0) > 80) || !(valueList.get(1) > 80) || !(valueList.get(2) > 80)) {
            if (difOne == 0) { // change value to most recent possible
                changeValue(valueList.get(1));
            } // value 3 wasn't checked yet so give value 2
            if (difOne <= tolerance) {
                changeValue(valueList.get(1));
            }

            if (difOne > tolerance) {
                if (difTwo <= (tolerance * 2)) { // if the car is driving at 5 speed and one value in between gets
                    // measured wrong the next value should be twice the tolerance [10,
                    // x, 20]
                    changeValue(valueList.get(2));
                    valueList.remove(1);
                } // remove wrong value
                if (!(difTwo <= (tolerance * 2)) && difThree <= tolerance) {
                    changeValue(valueList.get(2));
                }
            }

        }

    }

    @Override
    public int getId(){
        return id;
    }

    @Override
    public int getValue(){
        return sensorValue;
    }

    @Override
    public List<Integer> getValueList(){
        return valueList;
    }
}
