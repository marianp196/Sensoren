/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common;

import abstractions.IMessergebnis;

import abstractions.ISensor;
import gpio.Interfaces.IControler;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public abstract class Sensor<TKonfiguration extends ISensorKonfiguration, TMessergebnis extends IMessergebnis>
        implements ISensor<TKonfiguration, TMessergebnis> {
    
    public Sensor(TKonfiguration konfiguration, IControler gpioControler) throws Exception
    {
        if(konfiguration == null)
            throw new NullPointerException("Argument Konfiguration");
        if(gpioControler == null)
            throw new NullPointerException("Argument gpioControler");
        
        this.konfiguration = konfiguration; 
        this.gpioControler = gpioControler;
        this.uid = getUid();
        
        this.checkKonfiguration();
    }
            
    public  TKonfiguration GetKonfiguartion()
    {
        return konfiguration;
    }
    
    @Override
    public boolean IsActive() {
        TMessergebnis messergebnis = GetMessergebnis();
        return messergebnis.HasWert();
    }
    
    public TMessergebnis GetMessergebnis() //ToDo Refactor..Nullprüfung bei triggerMessung()
    {
        try {
            if(konfiguration.GetResultLifeTimeMilliSeconds() != null && messergebnis != null)
            {
                long timeSinceLast = System.currentTimeMillis() - messergebnis.GetTimeStamp();
                if(timeSinceLast > konfiguration.GetResultLifeTimeMilliSeconds())
                    this.messergebnis = triggerMessung();
            }else
            { 
                this.messergebnis = triggerMessung();          
            }
        } catch (Exception ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);   
            return getFehlerMessung();
        }
        
       return messergebnis; 
    }
    
    public String GetUid()
    {
        return this.uid;
    }
     

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.konfiguration);
        hash = 89 * hash + Objects.hashCode(this.uid);
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
        final Sensor<?, ?> other = (Sensor<?, ?>) obj;
        if (!Objects.equals(this.uid, other.uid)) {
            return false;
        }
        if (!Objects.equals(this.konfiguration, other.konfiguration)) {
            return false;
        }
        return true;
    }    
    
    protected IControler getGpioControler() {
        return gpioControler;
    }
    
    protected abstract void checkKonfiguration() throws Exception;
    protected abstract TMessergebnis triggerMessung() throws Exception;
    protected abstract TMessergebnis getFehlerMessung();
    
    private String getUid()
    {
        UUID uid = UUID.randomUUID();
        return uid.toString();
    }   
    
    private IControler gpioControler;
   
    private TMessergebnis messergebnis;
    private TKonfiguration konfiguration;
    private String uid;
}
