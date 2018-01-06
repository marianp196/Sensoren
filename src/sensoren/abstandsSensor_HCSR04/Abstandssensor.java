/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.abstandsSensor_HCSR04;

import gpio.Enums.EModus;
import gpio.Interfaces.IControler;

/**
 * Der Abstandssensor läuft auf 5v Betriebsspannung
 * Trigger auf 3.3V komplett ausreichend
 * @author marian
 */
public class Abstandssensor {
    private IControler controler;
    private int triggerPin;
    private int echoPin;
    
    public Abstandssensor(IControler controler, int triggerpin, int echoPin) throws Exception {
        if(controler == null)
            throw new NullPointerException("controler");
            
        this.controler = controler;
        this.triggerPin = triggerpin;
        this.echoPin = echoPin;
        
        registerPins();        
    }
    
    /**
     * 
     * @return
     * @throws Exception.. wenn zu lange unterwegs
     */
    public long getSignalZeitNano() throws Exception
    {        
        trigger();
        waitForSignal(50);
        long anfang = System.nanoTime();
        signalUnterwegs(anfang, 1000);
        long ende = System.nanoTime();
        return ende - anfang;
    }
    
    public int getMMeter() throws Exception
    {
        long msSchallgeschwindigkeit = 330;
        long zeitNano = getSignalZeitNano();
        double zeitSek = zeitNano / Math.pow(10, 9);
        double wegMeter = msSchallgeschwindigkeit * zeitSek;
        double wegMMeter = wegMeter * 1000;
        return (int)(wegMMeter / 2);        
    }   

    private void registerPins() throws Exception{
        controler.register(echoPin, EModus.In);
        controler.register(triggerPin, EModus.Out);
        controler.setLow(triggerPin);
    }

    private void trigger() throws Exception {
        controler.setHigh(triggerPin);
        controler.setLow(triggerPin);
    }
    
    @Deprecated
    private void test() throws Exception
    {
        System.out.println("Vorher State:  ");
        for(int i =0; i< 100; i++)
            System.out.println(controler.getState(echoPin));
        trigger();
        System.out.println("Nachher State:  ");
        for(int i =0; i< 300; i++)
            System.out.println(controler.getState(echoPin));
    } 

    private void waitForSignal(int timneOutPeriod ) throws Exception {
       int z =0;
       boolean state = controler.getState(echoPin);
       while(z<timneOutPeriod && !state)
       {
           state = controler.getState(echoPin);
           z++;
       }
    }

    private void signalUnterwegs(long anfang, long timeout) throws Exception {
        boolean state = true;
        while (state)
        {
            state = controler.getState(echoPin);
            if(isTimedOut(anfang, timeout))
                throw new Exception("Signal zu lange unterwegs");
        }
    }
    
    private boolean isTimedOut(long anfang, long timeOut)
    {
        long now = System.nanoTime();
        return (now - anfang) < timeOut;
    }
    
}
