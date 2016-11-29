import FunktioLaskin.Control;
import FunktioLaskin.Laskin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by mako on 11/29/16.
 */
public class ControlTest {
    private static Control controller = Control.getInstance();
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void nollaaTest() {
        controller.setValue("1");
        controller.setValue("+");
        controller.setValue("2");
        controller.sulut("=");
        controller.laskelopputulos();
        controller.nollaa();
        assertTrue(controller.getTulos() == 0.0);
    }

}
