/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import gpio.ControlerFactory;
import gpio.Interfaces.IControler;

/**
 *
 * @author marian
 */
public class SensorProviderFactory {
    public static ISensorProvider createInstance()
    {
        if(providerInstance== null)
            providerInstance = buildNew();        
        return providerInstance;
    }   

    private static ISensorProvider buildNew() {
        IControler con = ControlerFactory.getInstance();
        ISensorContainer container = new SensorContainer();
        return new SensorProvider(container, con);
    }
    private static ISensorProvider providerInstance;
}
