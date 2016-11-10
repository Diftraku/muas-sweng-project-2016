package FunktioLaskin;

import java.sql.Connection;

/**
 * Created by mako on 9.11.2016.
 */
public interface FormulaDAO {
    Formula findFormula(int index);
    boolean insertFormula();
    Connection connectDatabase();
}
