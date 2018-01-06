/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sensoren.common.messergebnis;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author marian
 */
public enum EPraefix {
    peta,
    tera,
    giga,
    mega,
    kilo,
    hekto,
    deka,
    dezi,
    zenti,
    milli,
    mikro,
    nano,
    piko,
    fempto,
    atto;
      
    public int GetPotenez(EPraefix p)
    {
        switch(p)
        {
            case peta: return 15;
            case tera: return 12;
            case giga: return 9;
            case mega: return 6;
            case kilo: return 3;
            case hekto:return 2;
            case deka: return 1;
            case dezi: return -1;
            case zenti: return -2;
            case milli: return -3;
            case mikro: return -6;
            case nano: return -9;
            case piko: return -12;
            case fempto: return -15;
            case atto: return -18;            
        }
        throw new NotImplementedException();
    }
}
