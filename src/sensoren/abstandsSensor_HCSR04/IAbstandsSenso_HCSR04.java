/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.abstandsSensor_HCSR04;

import abstractions.IMessergebnis;
import abstractions.ISensor;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.konfiguartion.TriggerEchoKonfiguration;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public interface IAbstandsSenso_HCSR04 extends ISensor<TriggerEchoKonfiguration, MessergebnisMetrisch> {
    
}
