package FunktioLaskin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;


/**
 * Created by mako on 12/13/16.
 */
public class ControllerFormulaView {

    private Controller contr;
    private ConcreteFormulaDAO dao;
    private List<Formula> formulaList;

    @FXML
    private ListView<String> formulalistview;

    public ControllerFormulaView() {
        dao = ConcreteFormulaDAO.getInstance();
    }

    /*
     * This will be executed when window is loaded
     */
    public void initialize() {
        loadFormulas();
        contr = Controller.getInstance();
    }

    /*
     * Loads all the formulas from the database to formula selection screen
     * @param root Root node of the selection screen. Used to find the right ListView from the FXML
     */
    @FXML
    public void loadFormulas() {
        //Toisen asteen yhtälö (-b+N(b^2 - 4ac)):(2a)
        formulaList = dao.findAllFormulas();
        //formulalistview = root.lookup("#formulalistview");
        for(int i=0; i<formulaList.size(); i++) {
            System.out.println(formulaList.get(i).getFormula());
            formulalistview.getItems().add(formulalistview.getItems().size(), formulaList.get(i).getFormula());
        }
    }
    /*
     * Closes the formula selection window and passes the selected the formula to the main window/calculator
     */
    @FXML
    public void closeFormulaView(ActionEvent e) {
        String selectedLine = formulalistview.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            Button closeButton = (Button) e.getSource();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
            contr.printToScreen(selectedLine);
            contr.putinFormula(selectedLine);
        }
    }
}
