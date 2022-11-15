import java.util.ArrayList;
import java.util.List;

import src.interfaces.MotorInterface;
import src.interfaces.SensorInterface;
import src.interfaces.SensorObserver;

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

            for(int i = 0; i < valueList.size(); i++){
                if ( valueList.get(i) > 80)
                    valueList.set(i, 80);
                if ( valueList.get(i) < 5 && !(valueList.get(i) < 0))
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

        /*
         * @Override
         * public void run() {
         * System.out.println("Sensor " + sensor.id + " gestartet");
         * try {
         * sleep(100);
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * sensor.changeValue(20);
         * try {
         * sleep(100);
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * sensor.changeValue(5);
         * try {
         * sleep(100);
         * } catch (InterruptedException e) {
         * e.printStackTrace();
         * }
         * sensor.changeValue(-20);
         * }
         * }
         */

        @Override
        public void run() {
            System.out.println("Sensor " + sensor.id + " gestartet");
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(20);
            sensor.populateArray(25);
            sensor.populateArray(60);
            try {
                sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(35);

            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(40);

            try {
                sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(-45);

            try {
                sleep(90);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(45);

            try {
                sleep(110);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.valueList.clear(); // clear list for new test
            sensor.populateArray(81);
            sensor.populateArray(85);
            sensor.populateArray(90);

            try {
                sleep(220);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.valueList.clear(); //clear list for new test
            sensor.populateArray(20);
            sensor.populateArray(15);
            sensor.populateArray(10);
            try {
                sleep(280);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(50);
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sensor.populateArray(5);
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
        assertEquals(motor1.steerValue, 0); // driving
        // Test 2
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 2:");
        assertEquals(motor1.driveValue, 0);
        assertEquals(motor1.steerValue, 5);
        // Test 3
        try {
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

    static void test2() {
        Sensor sensor1 = new Sensor(1);
        Motor motor1 = new SilTest.Motor(1);
        Controller controller1 = new Controller(sensor1, motor1);
        sensor1.addObserver(controller1);

        SensorThread s = new SilTest.SensorThread(sensor1);
        s.start();
        // Test 1
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test driving forward:");
        System.out.println("Test 1:");
        assertEquals(motor1.driveValue, 25);
        // Test 2
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 2:");
        assertEquals(motor1.driveValue, 35);
        // Test 3
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 3:");
        assertEquals(motor1.driveValue, 40);
        // Test 4
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 4:");
        assertEquals(motor1.driveValue, 40);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Test values above 80:");
        System.out.println("Test 5:");
        assertEquals(motor1.driveValue, 80);

        try {
            Thread.sleep(250);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Test driving backwards:");
        System.out.println("Test 6:");
        assertEquals(motor1.driveValue, 15);

        try {
            Thread.sleep(270);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 7:");
        assertEquals(motor1.driveValue, 10);

        try {
            Thread.sleep(290);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 8:");
        assertEquals(motor1.steerValue, 5);

        try {
            Thread.sleep(310);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Test 9:");
        assertEquals(motor1.steerValue, 5);

        try {
            Thread.sleep(330);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //public static void main(String[] args) {
    //    // test1();
    //    test2();
    //}

}
