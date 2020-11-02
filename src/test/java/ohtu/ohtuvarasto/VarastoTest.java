package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto3;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto3 = new Varasto(10, 2);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusEiOleNegatiivinen() {
        Varasto varasto2 = new Varasto(-1);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(11);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void voiTayttaa() {
        varasto.lisaaVarastoon(10);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }
    
    @Test
    public void tilavuuttaIsompaaEiVoiOttaa() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(-1);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }
  
    @Test
    public void ottamallaEiVoiMennaNegatiiviseksi() {
        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktori2LuoTyhjanVaraston() {
        assertEquals(10, varasto3.getTilavuus(), vertailuTarkkuus);
        assertEquals(2, varasto3.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastonTilavuusEiOleNegatiivinen2() {
        Varasto varasto2 = new Varasto(-1, -1);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test 
    public void saldoEiOleIsompiKuinTilavuus() {
        Varasto varasto2 = new Varasto(10, 11);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void oikeaTulostus() {
        assertEquals("saldo = 2.0, vielä tilaa 8.0", varasto3.toString());
    }
}
