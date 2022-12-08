package src.nxt;

import src.impl.MyMotor;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class NXTMotor extends MyMotor{

    NXTRegulatedMotor driveMotor1;
    NXTRegulatedMotor driveMotor2;
    NXTRegulatedMotor steerMotor;

    int steerValue;
    int driveValue;

    public NXTMotor(int id, String type) {
        super(id);

        if(type.equals("drive")){
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

        driveMotor1.setSpeed(driveValue);
        driveMotor2.setSpeed(driveValue);
        driveMotor1.backward();             // motors are installed backwards, thus they drive forwards when backward() method is used
        driveMotor2.backward();
    }
    

    
}
