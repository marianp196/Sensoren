/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.messergebnis;

import abstractions.IMessergebnis;

/**
 *
 * @author marian
 */
public abstract class Messergebnis<TWert> implements IMessergebnis<TWert>
{
    /**
     * Für Fehlmessung
     * @param erstellung 
     */
    public Messergebnis(long erstellung)
    {
        hasWert = false;
        setErstellung(erstellung);
    }
    
    /**
     * Für messerfolg mit Zeitangabe
     * @param wert
     * @param einheit 
     */
    public Messergebnis(TWert wert, EEinheit einheit)
    {
        commonKonstruktor(wert, einheit, -1);
    }
    
    /**
     * Für Messerfolg. erstellungszeit wird auf Now gesetzt
     * @param wert
     * @param einheit
     * @param erstellung 
     */
    public Messergebnis(TWert wert, EEinheit einheit, long erstellung)
    {
        commonKonstruktor(wert, einheit, erstellung);
    }
    
    private void commonKonstruktor(TWert wert, EEinheit einheit, long erstellung)
    {
        if(einheit == null)
            throw new NullPointerException("einheit");
        if(wert == null)
            throw new NullPointerException("wert");
        
        this.hasWert = true;
        this.wert = wert;
        this.einheit = einheit;
        
        setErstellung(erstellung);
    }

    private void setErstellung(long erstellung1) {
        if (erstellung1 == -1) {
            this.erstellung = System.currentTimeMillis();
        } else {
            this.erstellung = erstellung1; 
        }
    }
    
    @Override
    public long GetTimeStamp() {
        return erstellung;
    }
   
    @Override
    public boolean HasWert() {
        return hasWert;
    }
   
    @Override
    public TWert GetWert() throws Exception {
        if(!HasWert())
            throw new Exception("Wert nicht gesetzt");
        return wert;
    }
    
    @Override
    public EEinheit GetEinheit() throws Exception {
        if(!HasWert())
            throw new Exception("Wert nicht gesetzt");
        return einheit;
    }
    
    TWert wert;
    EEinheit einheit;
    long erstellung;
    boolean hasWert;    


}
