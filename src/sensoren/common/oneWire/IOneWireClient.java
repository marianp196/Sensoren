/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.oneWire;

/**
 *
 * @author marian
 */
public interface IOneWireClient {
    void SetKonfiguration(OneWireKonfiguration konfig) throws Exception;    
        
    String GetResponseOfClient() throws Exception;    
}
