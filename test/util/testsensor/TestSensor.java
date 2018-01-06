/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.testsensor;

import gpio.Interfaces.IControler;
import sensoren.common.messergebnis.EEinheit;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.Sensor;

/**
 *
 * @author marian
 */
public class TestSensor extends Sensor<TestsensorKonfiguration, MessergebnisMetrisch> 
        implements ITestSensor{

    public TestSensor(TestsensorKonfiguration konfiguration, IControler gpioControler) throws Exception {
        super(konfiguration, gpioControler);
    }    


    @Override
    public boolean IsActive() {
        return true;
    }

    int i = 0;

    @Override
    protected void checkKonfiguration() throws Exception {
        
    }

    @Override
    protected MessergebnisMetrisch triggerMessung() {
         i++;
        return new MessergebnisMetrisch((double)i, EEinheit.Meter);
    }
    
     @Override
    protected MessergebnisMetrisch getFehlerMessung() {
        return new MessergebnisMetrisch(0);
    }
   
}
