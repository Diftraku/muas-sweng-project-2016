package FunktioLaskin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mako on 9.11.2016.
 */
/*
 * DAO formula design pattern
 */
public class ConcreteFormulaDAO implements FormulaDAO {

    private List<Formula> formulaList;
    private Formula formula;
    private Connection conn;
    private PreparedStatement statement;
    private static ConcreteFormulaDAO dao = new ConcreteFormulaDAO();

    private ConcreteFormulaDAO() {
        //dao.connectDatabase();
        conn = connectDatabase();
        //formula = new Formula();
    }

    public static ConcreteFormulaDAO getInstance() {
        return dao;
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
            /*if(!isConnected()) {
                conn = connectDatabase();
            }*/
            statement = conn.prepareStatement(query);
            statement.setString(1, id);
            ResultSet result = statement.executeQuery();
            String resultStr = result.getString("formula");
            String id2 = result.getString("id");
            formula.setFormula(resultStr);
            formula.setId(id2);
            statement.close();
            return formula;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
    /*
     * Finds all formulas in database
     */
    public List<Formula> findAllFormulas() {
        String query = ("SELECT * FROM formulas");
        formulaList = new ArrayList<Formula>();
        try {
            /*if(!isConnected()) {
                conn = connectDatabase();
            }*/
            statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while(result.next()) {
                formula = new Formula();
                formula.setFormula(result.getString("formula"));
                formula.setId(result.getString("id"));
                formulaList.add(formula);
                System.out.println("Formulas: " +formula.getId() + formula.getFormula());

            }
            statement.close();
            return formulaList;
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
            /*if(!isConnected()) {
                conn = connectDatabase();
            }*/
            statement = conn.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
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
            /*if(!isConnected()) {
                conn = connectDatabase();
            }*/
            statement = conn.prepareStatement(query);
            statement.setString(1, formula.getId());
            statement.setString(2, formula.getFormula());
            statement.executeUpdate();
            statement.close();
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
