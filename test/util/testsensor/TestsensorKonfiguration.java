/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.testsensor;

import abstractions.ISensorKonfiguration;

/**
 *
 * @author marian
 */
public class TestsensorKonfiguration implements ISensorKonfiguration {
    public int k1;
    public int k2;

    public TestsensorKonfiguration(int k1, int k2, Long time) {
        this.k1 = k1;
        this.k2 = k2;
        this.time = time;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.k1;
        hash = 53 * hash + this.k2;
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
        final TestsensorKonfiguration other = (TestsensorKonfiguration) obj;
        if (this.k1 != other.k1) {
            return false;
        }
        if (this.k2 != other.k2) {
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
        return time;
    }
    
    private Long time;
}
