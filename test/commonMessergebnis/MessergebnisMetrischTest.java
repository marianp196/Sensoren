/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commonMessergebnis;

import org.junit.Assert;
import org.junit.Test;
import sensoren.common.messergebnis.EEinheit;
import sensoren.common.messergebnis.EPraefix;
import sensoren.common.messergebnis.MessergebnisMetrisch;

/**
 *
 * @author marian
 */
public class MessergebnisMetrischTest {
   
    @Test
    public void HasValue_ShouldBeFalse_IfNoValueSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(System.currentTimeMillis());
        Assert.assertFalse(sut.HasWert());
    }
    
    @Test
    public void HasValue_ShouldBeTrue_IfValueSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(0.0, EEinheit.Meter);
        Assert.assertTrue(sut.HasWert());
    }
    
    @Test
    public void GetEinheit_ShouldReturnEinheit_IfEinheitSet() throws Exception
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(0.0, EEinheit.Meter);
        Assert.assertEquals(EEinheit.Meter, sut.GetEinheit());
    }
    
    @Test
    public void GetEinheit_ShouldThrowException_IfNoValiueSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(0);        
        
        try {
            sut.GetEinheit();
            Assert.assertFalse(true);
        } catch (Exception ex) {}
    }
    
    @Test
    public void GetWert_ShouldReturnValue_IfValueSet() throws Exception
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(1.1, EEinheit.Meter);
        Assert.assertEquals(1.1, sut.GetWert(),0);
    }
    
    @Test
    public void GetWert_ShouldThrowException_IfNoValiueSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(0);        
        
        try {
            sut.GetWert();
            Assert.assertFalse(true);
        } catch (Exception ex) {}
    }
    
    @Test
    public void GetDateTime_ShouldReturnNow_IfNoTimeSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(1.1,EEinheit.Meter);        
        Assert.assertEquals(System.currentTimeMillis(), sut.GetTimeStamp());
       
    }
    
    @Test
    public void GetDateTime_ShouldReturnTime_IfTimeSet()
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(1.1,EEinheit.Meter,111);        
        Assert.assertEquals(111, sut.GetTimeStamp());       
    }
      
    @Test
    public void GetValuePraefix_ShouldReturnRightValue_IfPotenzHigher0() throws Exception
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(1000.0,EEinheit.Meter,111);
        Assert.assertEquals(1, sut.GetPraefixValue(EPraefix.kilo),0.00001);
    }
    
    @Test
    public void GetValuePraefix_ShouldReturnRightValue_IfPotenzLower0() throws Exception
    {
        MessergebnisMetrisch sut = new MessergebnisMetrisch(0.001,EEinheit.Meter,111);
        Assert.assertEquals(1, sut.GetPraefixValue(EPraefix.milli),0.00001);
    }
   
}
