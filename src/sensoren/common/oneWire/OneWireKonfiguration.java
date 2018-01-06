/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.oneWire;

import java.util.Objects;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public class OneWireKonfiguration implements ISensorKonfiguration
{

    
    public static OneWireKonfiguration GetStandardRaspiKonfig(String adresse)
    {
        return new OneWireKonfiguration(adresse, "/sys/bus/w1/devices", null, 4);
    }
    
    public static OneWireKonfiguration GetStandardRaspiKonfig(String adresse, long time)
    {
        return new OneWireKonfiguration(adresse, "/sys/bus/w1/devices", time, 4);
    }
    
    public OneWireKonfiguration(String adresse, String devicesPath, Long resultLifeTimeMilliSeconds, int onewirePin) {
        this.adresse = adresse;
        this.devicesPath = devicesPath;
        this.resultLifeTimeMilliSeconds = resultLifeTimeMilliSeconds;
        this.onewirePin = onewirePin;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.adresse);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OneWireKonfiguration other = (OneWireKonfiguration) obj;
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        return true;
    }
    
    public String getAdresse() {
        return adresse;
    }

    public String getDevicesPath() {
        return devicesPath;
    }

    public int getOnewirePin() {
        return onewirePin;
    }

    @Override
    public String GetName() {
        return adresse;
    }

    @Override
    public Long GetResultLifeTimeMilliSeconds() {
        return resultLifeTimeMilliSeconds;
    }
    
    @Override
    public String toString() {
        return "1W-Konfig: Chip-ID: " + this.adresse + " Pin: " + this.getOnewirePin() + "\n" +
               "DevicesPath: " + this.getDevicesPath() + " ResultLifetime: " + String.valueOf(this.GetResultLifeTimeMilliSeconds());
    }
    
    private String adresse;
    private String devicesPath;
    private Long resultLifeTimeMilliSeconds;
    private int onewirePin;     
   
    
}
