package FunktioLaskin;

import java.sql.*;

/**
 * Created by mako on 9.11.2016.
 */
public class ConcreteFormulaDAO implements FormulaDAO {

    private Formula formula;

    public ConcreteFormulaDAO() {
        formula = new Formula();
    }

    private Connection conn;
    @Override
    public Formula findFormula() {
        String sql = "SELECT * FROM formulas WHERE id=1";
        try {
            conn = connectDatabase();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            String resultStr = result.getString("formula");
            formula.setFormula(resultStr);
            System.out.println(resultStr);
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
            System.out.println("Connection established");
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
