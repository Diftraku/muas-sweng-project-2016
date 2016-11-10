import FunktioLaskin.ConcreteFormulaDAO;
import FunktioLaskin.Formula;
import FunktioLaskin.FormulaDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertTrue;

/**
 * Created by mako on 10.11.2016.
 */
public class DatabaseTest {
    private static FormulaDAO dao = new ConcreteFormulaDAO();

    @Before
    public void setUp() {

    }
    @After
    public void tearDown() {

    }

    @Test
    public void connectTest() {
        System.out.println("Connect to database test");
        Connection conn = dao.connectDatabase();
        assertTrue(conn != null);
    }
    @Test
    public void findFormulaTest() {
        System.out.println("Find formula test");
        Formula formula = dao.findFormula(1);
        assertTrue(formula.getId() == 1);
    }
}
