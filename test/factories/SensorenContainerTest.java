/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factories;

import abstractions.ISensor;
import gpio.ControlerFactory;
import gpio.Interfaces.IControler;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import util.ControlerMock;
import util.testSensor2.ITestSensor2;
import util.testSensor2.TestSensor2;
import util.testSensor2.TestSensor2Konfiguration;
import util.testsensor.ITestSensor;
import util.testsensor.TestSensor;
import util.testsensor.TestsensorKonfiguration;

/**
 *
 * @author marian
 */
public class SensorenContainerTest {
    
    @Before
    public void initTestData() throws Exception
    {
        
        testsensorKonfiguration1 = new TestsensorKonfiguration(0,1,3l);
        testSensor1 = new TestSensor(testsensorKonfiguration1, gpioControler);
        
        testsensorKonfiguration1_1 = new TestsensorKonfiguration(3,8,3l);
        testSensor1_1 = new TestSensor(testsensorKonfiguration1_1, gpioControler);
        
        testSensor2Konfiguration = new TestSensor2Konfiguration("hi", 0);
        testSensor2 = new TestSensor2(testSensor2Konfiguration, gpioControler);
    }
    
    @Test    
    public void GetSensorByUID_ShouldReturnItByUid_IfExists() throws Exception
    {
        ISensorContainer sut = createSut();
        addSenoren(sut);
        
        ISensor sensorReturned = sut.GetSensorByUID(testSensor1.GetUid());
        Assert.assertTrue(sensorReturned == testSensor1);    
        
        sensorReturned = sut.GetSensorByUID(testSensor2.GetUid());
        Assert.assertTrue(sensorReturned == testSensor2);
    }
    
    @Test
    public void GetSensorByUID_ShouldReturnNull_IfDoesntExists() throws Exception
    {
        ISensorContainer sut = createSut();
        addSenoren(sut);
        
        ISensor sensorReturned = sut.GetSensorByUID("Uid Doesnt exits");
        Assert.assertTrue(sensorReturned == null);    
    }
    
    @Test
    public void AddSensor_ShouldThrowException_IfSensorDoesAllreadyExist() throws Exception
    {
        ISensorContainer sut = createSut();
        addSenoren(sut);
        try {
            sut.<ITestSensor>AddSensor(ITestSensor.class,testSensor1);
            assertTrue(false);
        } catch (Exception e) {            
        }
        
        try {
            sut.<ITestSensor>AddSensor(ITestSensor.class,testSensor1_1);
            assertTrue(false);
        } catch (Exception e) {            
        }
        
        try {
            sut.<ITestSensor2>AddSensor(ITestSensor2.class,testSensor2);
            assertTrue(false);
        } catch (Exception e) {            
        }
        
    }
    
    @Test
    public void GetSensor_ShouldReturnItByConfiguartion() throws Exception
    {
        ISensorContainer sut = createSut();
        TestsensorKonfiguration konf = new TestsensorKonfiguration(0, 1,3l);
        ITestSensor testSensor = new TestSensor(konf, gpioControler);
        
        sut.AddSensor(ITestSensor.class,testSensor);
        
        ITestSensor returnedSensor = sut.<ITestSensor>GetSensor(ITestSensor.class,konf);
        Assert.assertEquals(testSensor, returnedSensor);
        Assert.assertTrue(returnedSensor instanceof  ITestSensor);
    }
    
    @Test
    public void GetSensor_ShouldReturnSensor_IfCanDifferenceBetweenInterfaces() throws Exception
    {
        ISensorContainer sut = createSut();
        addSenoren(sut);
        
        ITestSensor testSensorReturned = sut.<ITestSensor>GetSensor(ITestSensor.class,testsensorKonfiguration1);
        Assert.assertEquals(testSensor1, testSensorReturned);
        Assert.assertTrue(testSensorReturned instanceof ITestSensor);
        
        ITestSensor2 testSensorReturned2 = sut.<ITestSensor2>GetSensor(ITestSensor2.class,testSensor2Konfiguration);
        Assert.assertEquals(testSensor2, testSensorReturned2);
        Assert.assertTrue(testSensorReturned2 instanceof ITestSensor2);        
    }
    
    @Test
    public void GetSensor_ShouldReturnNull_IfThereIsNoSensorWithInterface() throws Exception
    {
        ISensorContainer sut = createSut();
        sut.AddSensor(ITestSensor.class,testSensor1);
        
        ITestSensor2 returnedValue = sut.<ITestSensor2>GetSensor(ITestSensor2.class,testsensorKonfiguration1_1);
        Assert.assertNull(returnedValue);        
    }
    
     @Test
    public void GetSensor_ShouldReturnNull_IfThereIsNoSensorWithKonfiguration() throws Exception
    {
        ISensorContainer sut = createSut();
        sut.AddSensor(ITestSensor.class,testSensor1);
        
        ITestSensor returnedValue = sut.<ITestSensor>GetSensor(ITestSensor.class,testSensor2Konfiguration);
        Assert.assertNull(returnedValue);
    }
    
    @Test
    public void GetSensorenByInterface_ShouldReturnAllObjectsWithInterface_IfExists() throws Exception
    {
        ISensorContainer sut = createSut();
        addSenoren(sut);
        
        ArrayList<ITestSensor> returned = sut.<ITestSensor>getSensorenByInterface(ITestSensor.class);
        assertEquals(returned.size(), 2);
        assertTrue(returned.contains(testSensor1));
        assertTrue(returned.contains(testSensor1_1));
        
    }
     
    @Test
    public void GetSensorenByInterface_ShouldReturnEmptyList_IfdoesntExist() throws Exception
    {
        ISensorContainer sut = createSut();
                
        ArrayList<ITestSensor> returned = sut.<ITestSensor>getSensorenByInterface(ITestSensor.class);
        assertEquals(returned.size(), 0);        
        
    }
    private ISensorContainer createSut()
    {
        return new SensorContainer();
    }
    
    private void addSenoren(ISensorContainer sensorVerwalter) throws Exception
    {        
       sensorVerwalter.AddSensor(ITestSensor.class,testSensor1);
       sensorVerwalter.AddSensor(ITestSensor.class,testSensor1_1);
       sensorVerwalter.AddSensor(ITestSensor2.class,testSensor2);
       
    }
    
    private IControler gpioControler = new ControlerMock();

    private TestSensor2Konfiguration testSensor2Konfiguration;
    private ITestSensor2 testSensor2;
    
    private TestsensorKonfiguration testsensorKonfiguration1;
    private ITestSensor  testSensor1; 
    
    private TestsensorKonfiguration testsensorKonfiguration1_1;
    private ITestSensor  testSensor1_1; 
}
