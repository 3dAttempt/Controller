import src.interfaces.SensorObserver;

class Controller implements SensorObserver{

    SilTest.Sensor sensor;
    SilTest.Motor motor;

    public Controller(SilTest.Sensor sensor, SilTest.Motor motor){
        this.sensor = sensor;
        this.motor = motor;
    }

    @Override
    public void newValue(int n, int id) {
        if(n <= 5 && !(n < 0) ){
            motor.steer(n);         // I assume that the sensor value is the distance to a obstacle
            motor.drive(0);       // That's why if the distance is 5 or less the driving motor stops and the steering motor starts to steer
        }
        if(n > 5){
            motor.steer(0);         // If the distance is bigger than 5 drive forward without steering
            motor.drive(n);
        }
        if(n < 0) {
            motor.steer(0);         // If the value is negative stop both motors
            motor.drive(0);
        }
    }
    
}