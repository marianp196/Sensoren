/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import abstractions.ISensor;
import java.util.ArrayList;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
interface ISensorContainer {
    <TSensor extends ISensor> void AddSensor(Class<TSensor> sensorTypeInterface,ISensor sensor) throws Exception;
    <TSensor extends ISensor<?,?>> TSensor GetSensor(Class<TSensor> sensorTyp ,ISensorKonfiguration konfiguration);
        
    ISensor GetSensorByUID(String uid);
    ISensor GetSensorByName(String name);
    <TSensor extends ISensor> ArrayList<TSensor> getSensorenByInterface(Class<TSensor> sensorType);    
}
