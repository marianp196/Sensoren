/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.testSensor2;

import gpio.Interfaces.IControler;
import sensoren.common.messergebnis.EEinheit;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.Sensor;

/**
 *
 * @author marian
 */
public class TestSensor2 extends Sensor<TestSensor2Konfiguration, MessergebnisMetrisch> 
        implements ITestSensor2 {

    public TestSensor2(TestSensor2Konfiguration konfiguration, IControler gpioControler) throws Exception {
        super(konfiguration, gpioControler);
    }

    @Override
    public boolean IsActive() {
        return true;
    }

    @Override
    protected void checkKonfiguration() throws Exception {
        
    }

    @Override
    protected MessergebnisMetrisch triggerMessung() {
         return new MessergebnisMetrisch(12.0, EEinheit.Sekunden);
    } 

    @Override
    protected MessergebnisMetrisch getFehlerMessung() {
        return new MessergebnisMetrisch(0);
    }
  
    
}
