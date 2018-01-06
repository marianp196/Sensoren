/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.konfiguartion;

import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public class TriggerEchoKonfiguration implements ISensorKonfiguration{

    public TriggerEchoKonfiguration( int trigger, int echo, long time) {
        this.time = time;
        this.trigger = trigger;
        this.echo = echo;
    }

    public long getTime() {
        return time;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.trigger;
        hash = 83 * hash + this.echo;
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
        final TriggerEchoKonfiguration other = (TriggerEchoKonfiguration) obj;
        if (this.trigger != other.trigger) {
            return false;
        }
        if (this.echo != other.echo) {
            return false;
        }
        return true;
    }

    public int getTrigger() {
        return trigger;
    }
   
    
    @Override
    public String GetName() {
        return null;
    }

    @Override
    public Long GetResultLifeTimeMilliSeconds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private long time;
    private int trigger;
    private int echo;
    
}
