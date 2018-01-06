/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.testSensor2;

import java.util.Objects;
import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public class TestSensor2Konfiguration implements ISensorKonfiguration{
   

    public TestSensor2Konfiguration(String Adresse, int k2) {
        this.Adresse = Adresse;
        this.k2 = k2;
    }
    public String Adresse;
    public int k2;
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.Adresse);
        hash = 71 * hash + this.k2;
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
        final TestSensor2Konfiguration other = (TestSensor2Konfiguration) obj;
        if (this.k2 != other.k2) {
            return false;
        }
        if (!Objects.equals(this.Adresse, other.Adresse)) {
            return false;
        }
        return true;
    }

    @Override
    public String GetName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long GetResultLifeTimeMilliSeconds() {
        return null;
    }
}
