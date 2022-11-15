package src.interfaces;
public interface SensorObserver {

    // sends the new value to the controller
    void newValue(int n, int id);
    
}
