package src.impl;

import src.gui.SimulatorGUI;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("Gui") && args[1].equals("Gui")){
            SimulatorGUI s = new SimulatorGUI();
           // new Controller(s, s); 
        }
        if(args[0].equals("SilTest") && args[1].equals("Gui")){
            SimulatorGUI s = new SimulatorGUI();
            SilTest t = new SilTest();
           // new Controller(s, t); 
        }
        
    }
    
}
