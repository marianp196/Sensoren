/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractions;

import sensoren.common.messergebnis.EEinheit;

/**
 *
 * @author marian
 */

public interface IMessergebnis<TWert> 
{
    long GetTimeStamp();
    EEinheit GetEinheit() throws Exception;    
    boolean HasWert();
    TWert GetWert() throws Exception;
}
