package FunktioLaskin;

import java.sql.*;

/**
 * Created by mako on 9.11.2016.
 */
/*
 * DAO formula design pattern
 */
public class ConcreteFormulaDAO implements FormulaDAO {

    private Formula formula;
    private Connection conn;

    public ConcreteFormulaDAO() {
        //formula = new Formula();
    }

    /*
     * Finds a formula in database
     * @param id Formula id in the database used for the search parameter
     * @return Formula object retrieved from the database
     */
    @Override
    public Formula findFormula(String id) {
        String query = ("SELECT * FROM formulas WHERE id= ? ");
        Formula formula = new Formula();
        try {
            if(!isConnected()) {
                conn = connectDatabase();
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            String resultStr = result.getString("formula");
            String id2 = result.getString("id");
            formula.setFormula(resultStr);
            formula.setId(id2);
            return formula;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    /*
     * Deletes a formula from the database
     * @param id id Formula id in the database used for the search parameter
     * @return Returns true if successful deletion
     */
    public boolean deleteFormula(String id) {
        String query = "DELETE FROM formulas WHERE id= ?";
        try {
            if(!isConnected()) {
                conn = connectDatabase();
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    /*
     * Insert a formula into the database
     * @param formula Formula object that will be inserted into the database
     * @return Returns true if successful
     */
    @Override
    public boolean insertFormula(Formula formula) {
        String query = "INSERT INTO formulas (id, formula) VALUES (?, ?)";
        try {
            if(!isConnected()) {
                conn = connectDatabase();
            }
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, formula.getId());
            statement.setString(2, formula.getFormula());
            statement.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /*
     * Connects to the database
     * @return Connection to the database
     */
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
    /*
     * Checks if database connection is already established
     * @return true if is connected
     */
    public boolean isConnected() {
        if(conn == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
