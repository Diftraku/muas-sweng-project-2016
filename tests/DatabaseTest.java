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
    private static FormulaDAO dao = ConcreteFormulaDAO.getInstance();

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
    public void insertFormulaTest() {
        String id = "2";
        System.out.println("Insert formula test");
        Formula formula = new Formula();
        formula.setFormula("n!");
        formula.setId(id);
        dao.insertFormula(formula);
        Formula formula2 = dao.findFormula(id);
        if (formula2 == null) {
            assertTrue(false);
        }else {
            assertTrue(formula2.getFormula().equals("n!"));
        }
    }

    @Test
    public void findFormulaTest() {
        String id = "1";
        System.out.println("Find formula test");
        Formula formula = dao.findFormula(id);
        if (formula == null) {
            assertTrue(false);
        }else {
            assertTrue(formula.getId().equals(id));
        }
    }
    /*@Test
    public void deleteFormulaTest() {
        System.out.println("Delete formula test");
        /*String id = "2";
        System.out.println("Delete formula test");
        Formula formula = new Formula();
        formula.setFormula("n!");
        formula.setId(id);
        dao.insertFormula(formula);
        dao.deleteFormula("2");
        Formula formula2 = dao.findFormula("2");
        assertTrue(!formula2.getFormula().equals("n!"));
    }*/
}
