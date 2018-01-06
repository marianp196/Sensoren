/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonSensor;

import abstractions.ISensor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sensoren.common.messergebnis.MessergebnisMetrisch;
import util.ControlerMock;
import util.testsensor.TestSensor;
import util.testsensor.TestsensorKonfiguration;

/**
 *
 * @author marian
 */
public class SensorTest {
   
    @Test
    public void GetKonfiguration_ReturnsKonfiguration() throws Exception
    {
        TestsensorKonfiguration konfSet = new TestsensorKonfiguration(0, 0, 3l);
        ISensor<TestsensorKonfiguration, MessergebnisMetrisch> sut = createSut(3l);
        
        Assert.assertEquals(sut.GetKonfiguartion(), konfSet);
    }
    
    @Test
    public void GetMessergebnis_ReturnsNewMessergebnis_IfNoTimeSet() throws Exception
    {
        ISensor<TestsensorKonfiguration, MessergebnisMetrisch> sut = createSut(null);
        
        MessergebnisMetrisch m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 1.0,0);
        
        m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 2.0,0);
    }
    
    @Test
    public void GetMessergebnis_ReturnsOldMessergebnis_IfTimeSet() throws Exception
    {
        ISensor<TestsensorKonfiguration, MessergebnisMetrisch> sut = createSut(3l);
        
        MessergebnisMetrisch m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 1,0);
        
        Thread.sleep(2);
        
        m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 1,0);                
    }
    
     @Test
    public void GetMessergebnis_ReturnsNewMessergebnis_IfTimeSetAndEllapsed() throws Exception
    {
        ISensor<TestsensorKonfiguration, MessergebnisMetrisch> sut = createSut(3l);
        
        MessergebnisMetrisch m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 1,0);
        
        Thread.sleep(2);
        
        m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 1,0);
        
        Thread.sleep(2);
        
         m = sut.GetMessergebnis();
        Assert.assertEquals((double)m.GetWert(), 2,0);              
    }
    
   private ISensor<TestsensorKonfiguration, MessergebnisMetrisch> 
        createSut(Long time) throws Exception
   {
       TestsensorKonfiguration konf = new TestsensorKonfiguration(0, 0, time);
       return new TestSensor(konf, new ControlerMock());
   }
}
