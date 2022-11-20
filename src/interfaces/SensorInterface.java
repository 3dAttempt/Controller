package src.interfaces;
import java.util.List;

public interface SensorInterface {

    void addObserver(SensorObserver so);

    void removeObserver(SensorObserver so);

    // notifies controller
    void notifyCon();

    // changes the sensor value
    void changeValue(int newSensorValue);

    // add elements to an array until it has 4 elements, after that delete the first element at insertion
    void populateArray(int sensorValue);

    // tolerance in which wrong sensor values are disposed off
    void faultTolerance(List<Integer> valueList);

    // return id
    int getId();

    // return sensorValue
    int getValue();

    // retrun valueList
    List<Integer> getValueList();
}
