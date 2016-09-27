import static org.junit.Assert.*;

import org.junit.*;

import FunktioLaskin.Laskin;

/**
 * muas-sweng-project-2016
 *
 * @author diftraku
 * @package muas-sweng-project-2016
 * @license https://opensource.org/licenses/MIT The MIT License (MIT)
 */

public class LaskinTest {
	private static Laskin laskin = new Laskin();
    @Before
    public void setUp() throws Exception {

    	System.out.println("Laskin nollataan aina ennen testiä" + laskin.annaTulos());
    	laskin.nollaa();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void nollaa() throws Exception {

    	System.out.println("Laskintesti nollaa");
    	laskin.nollaa();
    	assertTrue(laskin.annaTulos() == 0);
    }

    @Test
    public void lisaa() throws Exception {

    	System.out.println("Laskintesti lisaa");
    	laskin.lisaa(5);
    	assertEquals(5, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(0.5);
    	assertEquals(0.5, laskin.annaTulos(), 0);
    }

    @Test
    public void vahenna() throws Exception {

    	System.out.println("Laskintesti vahenna");
    	laskin.vahenna(5);
    	assertEquals(-5, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.vahenna(0.5);
    	assertEquals(-0.5, laskin.annaTulos(), 0);
    }

    @Test
    public void kerro() throws Exception {

    	System.out.println("Laskintesti kerro");
    	laskin.kerro(5);
    	assertEquals(0, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(5);
    	laskin.kerro(-5);
    	assertEquals(-25, laskin.annaTulos(), 0);
    	laskin.kerro(0.5);
    	assertEquals(-12.5, laskin.annaTulos(), 0);
    }

    @Test
    public void jaa() throws Exception {

    	System.out.println("Laskintesti jaa");
    	laskin.jaa(5);
    	assertEquals(0, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(5);
    	laskin.jaa(-5);
    	assertEquals(-1, laskin.annaTulos(), 0);
    	laskin.jaa(0.5);
    	assertEquals(-2, laskin.annaTulos(), 0);
    }

    @Test
    public void nelio() throws Exception {

    	System.out.println("Laskintesti neliö");
    	laskin.nelio(5);
    	assertEquals(25, laskin.annaTulos(), 0);
    	laskin.nelio(-5);
    	assertEquals(25, laskin.annaTulos(), 0);
    	laskin.nelio(0.5);
    	assertEquals(0.25, laskin.annaTulos(), 0);
    }

    @Test
    public void neliojuuri() throws Exception {
    	System.out.println("Laskintesti neliöjuuri");
    	laskin.neliojuuri(5);
    	assertEquals(2.24, laskin.annaTulos(), 0.1);
    	laskin.neliojuuri(-5);
    	assertEquals(0.0, laskin.annaTulos(), 0.1);
    	laskin.neliojuuri(0.5);
    	assertEquals(0.71, laskin.annaTulos(), 0.1);
    }

    @Test
    public void edellinen() throws Exception {
    	laskin.lisaa(5);
    	laskin.edellinen();
    	assertEquals(0, laskin.annaTulos(), 0);
    	laskin.lisaa(5);
    	laskin.lisaa(5);
    	laskin.edellinen();
    	assertEquals(5, laskin.annaTulos(), 0);
    }
    @Test
    public void potenssi() throws Exception {
    	System.out.println("Laskintesti potenssi");
    	laskin.lisaa(5);
    	laskin.potenssi(0);
    	assertEquals(1, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(3.5);
    	laskin.potenssi(3);
    	assertEquals(42.875, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(5);
    	laskin.potenssi(-2);
    	assertEquals(0.04, laskin.annaTulos(), 0);
    	laskin.nollaa();
    	laskin.lisaa(-5);
    	laskin.potenssi(3);
    	assertEquals(-125, laskin.annaTulos(), 0);
    	laskin.nollaa();
    }

    @Test
    public void sin() throws Exception {
    	laskin.sin(15);
    	assertEquals(0.26, laskin.annaTulos(), 0.1);
    	laskin.sin(-15);
    	assertEquals(-0.26, laskin.annaTulos(), 0.1);
    }

    @Test
    public void cos() throws Exception {
    	laskin.cos(15);
    	assertEquals(0.96, laskin.annaTulos(), 0.1);
    	laskin.cos(-15);
    	assertEquals(0.96, laskin.annaTulos(), 0.1);
    }

    @Test
    public void tan() throws Exception {
    	laskin.tan(15);
    	assertEquals(0.27, laskin.annaTulos(), 0.1);
    	laskin.tan(-15);
    	assertEquals(-0.27, laskin.annaTulos(), 0.1);
    }
/*
    @BeforeClass
    public void virtaON() throws Exception {

    }

    @AfterClass
    public void virtaOFF() throws Exception {

    }
*/
}
