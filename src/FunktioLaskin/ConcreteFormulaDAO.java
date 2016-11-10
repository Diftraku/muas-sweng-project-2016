package FunktioLaskin;

import java.sql.*;

/**
 * Created by mako on 9.11.2016.
 */
public class ConcreteFormulaDAO implements FormulaDAO {

    private Formula formula;
    private Connection conn;

    public ConcreteFormulaDAO() {
        formula = new Formula();
    }

    @Override
    public Formula findFormula(int index) {
        String sql = new String("SELECT * FROM formulas WHERE id="+index);
        try {
            conn = connectDatabase();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            String resultStr = result.getString("formula");
            int id = result.getInt("id");
            formula.setFormula(resultStr);
            formula.setId(id);
            return formula;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    @Override
    public boolean insertFormula() {
        return false;
    }
    public Connection connectDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:test.db");
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
