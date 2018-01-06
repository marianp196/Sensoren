/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import abstractions.ISensor;
import gpio.Interfaces.IControler;
import java.util.ArrayList;
import sensoren.Thermometer_DS18B20.Thermometer_DS18B20;
import sensoren.common.oneWire.OneWireClient_w1SlaveStruktur;
import sensoren.common.oneWire.OneWireKonfiguration;
import sensoren.Thermometer_DS18B20.IThermometer_DS18B20;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
class SensorProvider implements ISensorProvider {

    public SensorProvider(ISensorContainer sensorContainer, IControler gpioControler) {
        this.sensorContainer = sensorContainer;
        this.gpioControler = gpioControler;
    }   
    
    @Override
    public ISensor GetSensorByUID(String uid) {
        return sensorContainer.GetSensorByUID(uid);
    }

    @Override
    public ISensor GetSensorByName(String name) {
        return sensorContainer.GetSensorByName(name);
    }

    @Override
    public <TSensor extends ISensor> ArrayList<TSensor> GetSensorenByInterface(Class<TSensor> sensorType) {
        return sensorContainer.<TSensor>getSensorenByInterface(sensorType);
    }

    @Override
    public void SetNewKonfiguartion(String uid, ISensorKonfiguration newKonfiguration) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   /*
    *
    Platz für die Registrierung von Sensoren
    *
    */  
    
    @Override
    public IThermometer_DS18B20 RegisterThermometer(OneWireKonfiguration konfig) throws Exception {
        IThermometer_DS18B20 result = sensorContainer.<IThermometer_DS18B20>GetSensor(IThermometer_DS18B20.class, konfig);
        if(result == null)
        {
            result = new Thermometer_DS18B20(konfig, gpioControler, new OneWireClient_w1SlaveStruktur(konfig));
            sensorContainer.<IThermometer_DS18B20>AddSensor(IThermometer_DS18B20.class, result);
        }
        return result;
    }
    
    private ISensorContainer sensorContainer;  
    private IControler gpioControler;
}
