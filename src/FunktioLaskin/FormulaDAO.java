package FunktioLaskin;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by mako on 9.11.2016.
 */
/*
 * DAO design pattern interface
 */
public interface FormulaDAO {
    Formula findFormula(String index);
    boolean insertFormula(Formula formula);
    boolean deleteFormula(String id);
    Connection connectDatabase();
    boolean isConnected();
}
