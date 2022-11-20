package src.interfaces;

public interface SensorObserver {

    // sends the new value to the controller
    void newValue(int n, int id);

    // like newValue method but uses a sensor list if there are multiple sensors
    void newValueList();
    
}
