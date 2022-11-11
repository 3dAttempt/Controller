import java.util.ArrayList;
import java.util.List;

public class SilTest {

    static class Sensor implements SensorInterface {

        int id;
        int sensorValue;
        List<SensorObserver> osList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        int tolerance = 5;


        public Sensor(int id) {
            this.id = id;
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

        public void populateArray(int sensorValue){
            this.sensorValue = sensorValue;

            if(valueList.size() >= 4){
                valueList.remove(0);
                valueList.add(sensorValue);
            }
            else valueList.add(sensorValue);
        }

        @Override
        public void faultTolerance(List<Integer> valueList){
            //check all values and add tolerance
            for(int i = 0; i < valueList.size(); i++){
                if(valueList.get(i) == valueList.get(i + 1))
                    changeValue(i);
                if(valueList.get(i) <= valueList.get(i + 1) + tolerance || valueList.get(i) >= valueList.get(i + 1) - tolerance)
                    changeValue(i);
                
            }
        }

    }

    static class Motor implements MotorInterface {

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

    }

    public static class SensorThread extends Thread {
        boolean isRunning = true;
        Sensor sensor;

        SensorThread(Sensor sensor) {
            this.sensor = sensor;
        }

        @Override
        public void run() {
            System.out.println("Sensor " + sensor.id + " gestartet");
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                sensor.changeValue(20);
                try {
                    sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                sensor.changeValue(5);
                try {
                    sleep(100);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
                sensor.changeValue(-20);
        }
    }


    static void test1() {
        Sensor sensor1 = new Sensor(1);
        Motor motor1 = new SilTest.Motor(1);
        Controller controller1 = new Controller(sensor1, motor1);
        sensor1.addObserver(controller1);

        SensorThread s = new SilTest.SensorThread(sensor1);
        s.start();
        // Test 1
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 1:");
        assertEquals(motor1.driveValue, 20); // steering
        assertEquals(motor1.steerValue, 0);  // driving
        // Test 2
        try{
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 2:");
        assertEquals(motor1.driveValue, 0);
        assertEquals(motor1.steerValue, 5);
        // Test 3
        try{
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 3:");
        assertEquals(motor1.driveValue, 0);
        assertEquals(motor1.steerValue, 0);
    }

    static void assertEquals(int isValue, int shouldValue) {

        if (isValue == shouldValue)
            System.out.println("Test successful!");
        else
            System.out.println("Test failed! The value is: " + isValue);
    }

    public static void main(String[] args) {
        //test1();
          Sensor s = new Sensor(0);
          s.populateArray(10);
          s.populateArray(40);
          s.populateArray(60);
          s.populateArray(80);
          s.populateArray(100);
    }

}
