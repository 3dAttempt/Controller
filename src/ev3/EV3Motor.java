package src.ev3;

import src.impl.MyMotor;

import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class EV3Motor extends MyMotor{

    NXTRegulatedMotor driveMotor1;
    NXTRegulatedMotor driveMotor2;
    NXTRegulatedMotor steerMotor;

    int steerValue;
    int driveValue;


    public EV3Motor(int id, String type) {
        super(id);

        if(type.equals("drive")) {
            driveMotor1 = new NXTRegulatedMotor(MotorPort.A);
            driveMotor2 = new NXTRegulatedMotor(MotorPort.B);
        }
        if(type.equals("steer")){
            steerMotor = new NXTRegulatedMotor(MotorPort.C);
        }
    }

    public void steer(int steerValue) {
        this.steerValue = steerValue;

        steerMotor.setSpeed(steerValue);
        steerMotor.forward();
    }

    public void drive(int driveValue) {
        this.driveValue = driveValue;

        setDriveSpeed(driveValue);
        driveMotor1.forward();
        driveMotor2.forward();
    }

    public void setDriveSpeed(int speed) {
        driveMotor1.setSpeed(speed);
        driveMotor2.setSpeed(speed);
    }
    
    
}
