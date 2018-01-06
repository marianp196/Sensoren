/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.Thermometer_DS18B20;

import gpio.Interfaces.IControler;
import sensoren.common.messergebnis.EEinheit;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import sensoren.common.oneWire.IOneWireClient;
import sensoren.common.oneWire.OneWireKonfiguration;
import sensoren.common.Sensor;

/**
 *
 * @author marian
 */
public class Thermometer_DS18B20 extends Sensor<OneWireKonfiguration, MessergebnisMetrisch> 
        implements IThermometer_DS18B20 {

    public Thermometer_DS18B20(OneWireKonfiguration konfiguration, 
                IControler gpioControler, IOneWireClient oneWireClient) throws Exception {
        
        super(konfiguration, gpioControler);  
        if(oneWireClient == null)
            throw new NullPointerException("onewireClient");
        this.oneWireClient = oneWireClient; 
        checkKonfiguration();
             
    }

    @Override
    protected void checkKonfiguration() throws Exception 
    {
        if(oneWireClient == null)//Damit nicht im Superkonstruktor aufgerufen wird
            return;
        oneWireClient.SetKonfiguration(super.GetKonfiguartion());
    }   

    @Override
    protected MessergebnisMetrisch triggerMessung()  throws Exception {  
        String clientResponse = oneWireClient.GetResponseOfClient();
        if(clientResponse == null)
            throw new Exception("Client antwortet nicht");
        if(!clientResponse.contains("YES"))
            throw new Exception("Client antwortet. Aber ungültiges Messergebnis: \n" + clientResponse);
        
        double tempMessergebnis = getTemperaturVonResponse(clientResponse);
        return new MessergebnisMetrisch(tempMessergebnis / 1000, EEinheit.Celsius);
    }

    private double getTemperaturVonResponse(String clientResponse) throws Exception, NumberFormatException {
        int tempBegin = clientResponse.indexOf("t=");
        if(tempBegin == -1)
            throw new Exception("Antwort enthält keine Temperatur \n" + clientResponse);
        String temp = clientResponse.substring(tempBegin + 2);
        double tempMessergebnis = Double.valueOf(temp);
        return tempMessergebnis;
    }
    
    @Override
    protected MessergebnisMetrisch getFehlerMessung() {
        return new MessergebnisMetrisch(System.currentTimeMillis());
    }
    
    private IOneWireClient oneWireClient;   
    
}
