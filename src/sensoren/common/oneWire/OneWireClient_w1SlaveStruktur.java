/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.oneWire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author marian
 */
public class OneWireClient_w1SlaveStruktur implements IOneWireClient
{

    public OneWireClient_w1SlaveStruktur(OneWireKonfiguration konfig) throws Exception {
        SetKonfiguration(konfig);
    }
    
    @Override
    public void SetKonfiguration(OneWireKonfiguration konfig) throws Exception {
        if(konfig == null)
            throw new NullPointerException("konfig");
        this.konfig = konfig;  
       initAndCheckKonfig();
    }
   
    @Override
    public String GetResponseOfClient() throws Exception {
       FileReader fr = new FileReader(deviceDatei);
       BufferedReader br = new BufferedReader(fr);
       
       String line = br.readLine();
       String result = "";
       
       while(line != null)
       {
           result += line + "\n";
           line = br.readLine();
       }
       
       return result;
       
    }
    
    private void initAndCheckKonfig() throws Exception 
    {
        initDevicesPath();                
        initDeviceDatei();
    }

    private void initDeviceDatei() throws Exception {
        if(konfig.getAdresse() == null || konfig.getAdresse().isEmpty())
            throw new Exception("Adresse nicht gesetzt");
        
        File device = getDeviceDatei(konfig.getAdresse(), devicesOrdner.listFiles());
        
        if(device == null)
            throw new Exception("device nicht gefunden");
        if(!device.canExecute())
            throw new Exception("Keine Rechte DeviceDatei zu lesen");
        
        deviceDatei =  getDeviceDatei("w1_slave", device.listFiles());
        
        if(device == null)
            throw new Exception("w1_slave nicht gefunden");
        if(!device.canExecute())
            throw new Exception("Keine Rechte w1_slave zu lesen");
    }

    private File getDeviceDatei(String adresse, File[] devices) {        
        for(File device : devices)
        {
            if(device.getName().equals(adresse))
            {
                return device;
            }
        }
        return null;
    }

    private void initDevicesPath() throws Exception {
        if(konfig.getDevicesPath() == null || konfig.getDevicesPath().equals(""))
            throw new Exception("devices Path Null or empty");
        devicesOrdner = new File(konfig.getDevicesPath());
        System.out.println(konfig.getDevicesPath());
        if(!devicesOrdner.exists())
            throw new Exception("devicesPath nicht vorhanden");
        if(devicesOrdner.isFile() || !devicesOrdner.canExecute())
            throw new Exception("devicePath ist kein Verzeichnis oder nicht ausführbar");
    }
    
    private OneWireKonfiguration konfig;    
    private File devicesOrdner;
    private File deviceDatei;
}
