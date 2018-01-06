/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import abstractions.ISensor;
import java.util.ArrayList;
import sensoren.common.oneWire.OneWireKonfiguration;
import sensoren.Thermometer_DS18B20.IThermometer_DS18B20;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public interface ISensorProvider {
    ISensor GetSensorByUID(String uid);
    ISensor GetSensorByName(String name);
    <TSensor extends ISensor> ArrayList<TSensor> GetSensorenByInterface(Class<TSensor> sensorType);
    
    void SetNewKonfiguartion(String uid, ISensorKonfiguration newKonfiguration);
    
    //Platz für Registratur
    IThermometer_DS18B20 RegisterThermometer(OneWireKonfiguration konfig) throws Exception;
}
