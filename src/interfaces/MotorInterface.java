package src.interfaces;
public interface MotorInterface {

    // changes the steerValue value
    void steer(int n);

    // changes the driveValue value
    void drive(int m);

    // retrun steerValue
    int getSteerValue();

    //return driveValue
    int getDriveValue();
}
