/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.abstandsSensor_HCSR04;

import abstractions.IMessergebnis;
import gpio.Interfaces.IControler;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.Sensor;
import sensoren.common.konfiguartion.TriggerEchoKonfiguration;

/**
 *
 * @author marian
 */
public class AbstandsSensorHCSR04  extends Sensor<TriggerEchoKonfiguration, MessergebnisMetrisch> 
        implements IAbstandsSenso_HCSR04{

    public AbstandsSensorHCSR04(TriggerEchoKonfiguration konfiguration, IControler gpioControler) throws Exception {
        super(konfiguration, gpioControler);
    }

    @Override
    protected void checkKonfiguration() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected MessergebnisMetrisch triggerMessung() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected MessergebnisMetrisch getFehlerMessung() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
