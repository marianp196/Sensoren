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
class SensorContainer implements ISensorContainer {

    public SensorContainer() {
        sensoren = new ArrayList<>();
    }

    @Override
    public <TSensor extends ISensor> void AddSensor(Class<TSensor> sensorTypeInterface,ISensor sensor) throws Exception{
        if(this.<TSensor>sensorExists(sensorTypeInterface,sensor))
            throw new Exception("Sensor Exists allready");
        sensoren.add(sensor);
    }
     @Override
    public ISensor GetSensorByUID(String uid) {
        if(uid == null)
            throw new NullPointerException("uid");
        
        for(ISensor sensor : sensoren)
        {
            if(sensor.GetUid().equals(uid))
            {
                return sensor;
            }
        }
        
        return null;    
    }

    @Override
    public ISensor GetSensorByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public <TSensor extends ISensor<?,?>> TSensor GetSensor(Class<TSensor> sensorTyp,ISensorKonfiguration konfiguration) 
    {
        if(konfiguration == null)
            throw new NullPointerException("konfiguartion war null");
                
        for (ISensor s : sensoren) {      
            if(!typeImplementsInterface(s.getClass(), sensorTyp))
                continue;
                    
            TSensor sensor = sensorTyp.cast(s);//Ich will c#!!
            if (sensor.GetKonfiguartion().equals(konfiguration)) {
                return sensor;
            }           
        }        
        return null;      
    }

    @Override
    public <TSensor extends ISensor> ArrayList<TSensor> getSensorenByInterface(Class<TSensor> sensorType) {
        ArrayList<TSensor> result = new ArrayList<>();
        for(ISensor sensor : sensoren)
        {
            if(typeImplementsInterface(sensor.getClass(), sensorType))
                result.add(sensorType.cast(sensor));
        }
        return result;
    }
    
    private <TSensor extends ISensor> boolean sensorExists(Class<TSensor> sensorInterfaceType ,ISensor sensor)
    {
        return this.<TSensor>GetSensor(sensorInterfaceType, sensor.GetKonfiguartion()) != null;
    }
    
    private boolean typeImplementsInterface(Class sensor, Class iFace)
    {
        Class[] interfaces = sensor.getInterfaces();
        
        for(Class i : interfaces)
        {
            if(i.equals(iFace))
                return true;
        }
        return false;
    }
    
    private ArrayList<ISensor> sensoren;    
}
