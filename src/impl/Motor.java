package src.impl;
import src.interfaces.MotorInterface;

public class Motor implements MotorInterface {
    int id;
    int steerValue;
    int driveValue;

    public Motor(int id) {
        this.id = id;
    }

    @Override
    public void steer(int steerValue) {
        this.steerValue = steerValue;
    }

    @Override
    public void drive(int driveValue) {
        this.driveValue = driveValue;
    }

    @Override
    public int getSteerValue(){
        return steerValue; 
    }

    @Override
    public int getDriveValue(){
        return driveValue;
    }

}
