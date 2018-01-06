/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.Thermometer_DS18B20;

import abstractions.ISensor;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.oneWire.OneWireKonfiguration;

/**
 *
 * @author marian
 */
public interface IThermometer_DS18B20 extends ISensor<OneWireKonfiguration, MessergebnisMetrisch>{
    
}
