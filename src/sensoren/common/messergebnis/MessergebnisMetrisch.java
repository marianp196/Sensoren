/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.messergebnis;

/**
 *
 * @author marian
 */
public class MessergebnisMetrisch extends Messergebnis<Double> {
    
    /**
     * Für Messerfolg. erstellungszeit wird auf Now gesetzt
     * @param wert
     * @param einheit
     * @param erstellung 
     */
    public MessergebnisMetrisch(Double wert, EEinheit einheit) {
        super(wert, einheit);
    }
    
     /**
     * Für messerfolg mit Zeitangabe
     * @param wert
     * @param einheit 
     */
    public MessergebnisMetrisch(Double wert, EEinheit einheit, long erstellung) {
        super(wert, einheit, erstellung);
    }     
    /**
     * Für Fehlmessung
     * @param erstellung 
     */
    public MessergebnisMetrisch(long erstellung) {
        super(erstellung);
    }   
    
    public Double GetPraefixValue(EPraefix praefix) throws Exception
    {
        Double baseValue = super.GetWert();
        int potenz = praefix.GetPotenez(praefix);
        potenz *= -1;
        Double result = baseValue * Math.pow(10, potenz);
        return result;
    }
        
}
 
    