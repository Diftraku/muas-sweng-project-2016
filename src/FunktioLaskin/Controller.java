package FunktioLaskin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/*
 * Controller class
 */
public class Controller {
    private ConcreteFormulaDAO dao;
    private Laskin laskin;
    private Main main;
    private CalcOrder laske;
    ArrayList<String> listat;
    private String lasku = "";
    private int place = 0;
    private CreateList luolista;
    private char aChar;
    private String Valitulos;
    private CharSequence charplace = "x";
    private String newString = "";
    private int StrChar = 0;
    private int lisaai= 0;
    private double vanha=0;
    private double Tulos=0;
    private int isformula = 0;
    private String formulanum = "";
    private int formulaA = 0;
    private int formulaB = 0;
    private int formulaC = 0;
    private String formula = "";
    private String formula2 = "";
    private List<Formula> formulaList;
    private int first = 0;
    private String a = "";
    private String b = "";
    private String c = "";
    private static Controller controller = new Controller();
    private ResourceBundle bundle;


    // JavaFX Elements
    @FXML
    private TextField screen;
    @FXML
    private ListView<String> history;
    @FXML
    private ListView<String> formulalistview;
    @FXML
    private BorderPane root;


    /*
     * Constructor
     */
    private Controller() {
        laskin = new Laskin();
        main = new Main();
        laske = new CalcOrder();
        listat = new ArrayList<String>();
        luolista = new CreateList();
        dao = ConcreteFormulaDAO.getInstance();
    }
    /*
     * Singleton method for getting the controller instance
     */
    public static Controller getInstance() {
        return controller;
    }

    /*
     * Checks if the screen is empty. Used by a lot of methods before setting text to the screen.
     * @return Returns true if is empty.
     */
    @FXML
    public boolean screenIsEmpty() {
        if (screen.getText() != null && !screen.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    /*
     * Opens the formula selection screen
     */
    @FXML
    public void FormulaOpen(ActionEvent event){
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/FormulaView.fxml"), bundle);
    	   Scene newScene;
           try {
               newScene = new Scene((Parent)loader.load());
           } catch (Exception ex) {
               ex.printStackTrace();
               // TODO: handle error
               return;
           }
           //stage.setScene(new Scene(root, 780, 600));
           Stage inputStage = new Stage();
          // inputStage.initOwner(primaryStage);
           inputStage.setScene(newScene);
           inputStage.show();
           //loadFormulas(newScene.getRoot());
    }

    /*
     * Prints the value of a button to the screen. If screen is not empty value will be appended at the end.
     * @param value Value that is going to be printed on the screen.
     */
    @FXML
    public void printToScreen(String value) {
        if (screenIsEmpty()) {
            if (!Objects.equals(value, "=")) {
                screen.setText(value);
            }
        } else {
            if (!Objects.equals(value, "=")) {
                screen.appendText(value);
            }
        }
    }
    /*
     * Returns string on the screen for testing purposes
     * @return Text on screen as String
     */
    @FXML
    public String getScreen() {
        return screen.getText();
    }

    /*
     * starts operations when buttons have been clicked
     */
    @FXML
    public void numbersAndOperators(ActionEvent e) {
        Button btn = (Button) e.getSource();
        String value = btn.getText();

        /*
         * Button witch have more than one char, need to convert to one char value
         */
        if (Objects.equals(isformula, 1)){
            numFormula(value);
        }
        else {
            if (Objects.equals(value, "sqrt")) {
                this.setValue("q");
            } else if (Objects.equals(value, "^")) {
                this.setValue("^");
            } else if (Objects.equals(value, "PI")) {
                this.setValue("P");
            } else if (Objects.equals(value, "sin")) {
                this.setValue("s");
            } else if (Objects.equals(value, "cos")) {
                this.setValue("c");
            } else if (Objects.equals(value, "tan")) {
                this.setValue("t");
            } else {
            /*
         * One char buttons are already cool
         */
                this.setValue(value);
            }
        }
        printToScreen(value);
    }

    /*
    * equals method puts equals char forward and triggers laskelopputulos() -method witch calculates answer
    */
    @FXML
    public void equals(ActionEvent e) {
        if (Objects.equals(isformula, 1)){
            numFormula("=");
        }
        else {
            String value = "=";
            this.sulut(value);
            this.laskelopputulos();
            String historyLine = screen.getText() + "=" + Double.toString(this.getTulos());
            history.getItems().add(history.getItems().size(), historyLine);
            history.scrollTo(historyLine);
            screen.setText(Double.toString(this.getTulos()));
            //printToScreen(value);
        }
    }

    /*
   * Clears screen
   */
    @FXML
    public void clear(ActionEvent e) {
        screen.clear();
        this.nollaa();
    }
    /*
     * Sets new locale
     */
    @FXML
    public void setLocale(ActionEvent e) throws IOException {
        Scene scene = root.getScene();
        MenuItem item = (MenuItem)e.getSource();
        System.out.println(item.getText());
        if (Objects.equals(item.getText(), "English")|| Objects.equals(item.getText(), "Anglais") || Objects.equals(item.getText(), "Icyongereza")){
            bundle = ResourceBundle.getBundle("locale");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"), bundle);
            loader.setController(Controller.getInstance());
            scene.setRoot((Parent)loader.load());
        }
        else if (Objects.equals(item.getText(), "France")|| Objects.equals(item.getText(), "Français") || Objects.equals(item.getText(), "Igifaransa")){
            bundle = ResourceBundle.getBundle("locale_fr_FR");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"), bundle);
            loader.setController(Controller.getInstance());
            scene.setRoot((Parent)loader.load());
        }
        else if (Objects.equals(item.getText(), "Rwanda")|| Objects.equals(item.getText(), "Rwanda") || Objects.equals(item.getText(), "Ikinyarwanda")){
            bundle = ResourceBundle.getBundle("locale_rw_RW");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"), bundle);
            loader.setController(Controller.getInstance());
            scene.setRoot((Parent)loader.load());
        }

    }
    /*
    @FXML
    public void setFormula(ActionEvent e) throws IOException {
        Formula formula = dao.findFormula("1");
        screen.setText(formula.getFormula());
    }
    */
    @FXML
    public void convertTosetValue(String form) {
        String str;
        for (int i = 0; i < form.length(); i++){
            str = Character.toString(form.charAt(i));
            if(str.equals("(") || str.equals(")")) {
                sulut(str);
            }else {
                setValue(str);
            }
        }
    }
    public void setIsformula(String form){
        for (int i = 0; i < form.length(); i++){
            if (Objects.equals(form.charAt(i), 'c')){
                formulaC = 1;
            }
            else if (Objects.equals(form.charAt(i), 'b')){
                formulaB = 1;
            }
            else if (Objects.equals(form.charAt(i), 'a')){
                formulaA = 1;
            }
        }
    }
    @FXML
    public void putinFormula(String form) {
        System.out.println(form);
        formula = form;
        if (Objects.equals(formulaA, 1)){
            System.out.println("a");
            askLetter("a");
        }
        else if (Objects.equals(formulaB, 1)){
            askLetter("b");
        }
        else if (Objects.equals(formulaC, 1)){
            askLetter("c");
        }
        else{
            String historyLine2 = getScreen();
            history.getItems().add(history.getItems().size(), historyLine2);
            history.scrollTo(historyLine2);
            isformula = 0;
            numFormula("=");
        }

    }
    @FXML
    public void askLetter(String x){
        isformula = 1;
        String historyLine2 = getScreen();
        history.getItems().add(history.getItems().size(), historyLine2);
        history.scrollTo(historyLine2);
        screen.setText(x+" = ");

    }
    public void numFormula(String num){
        first = 0;
        if (Objects.equals(num, "=")){
            System.out.print(formulaA+", " +formulaB+", " +formulaC);
            if (Objects.equals(formulaA, 1)){
                a = formulanum;
                formulanum = "";
                formulaA = 0;
                doFormulaA();
                putinFormula(formula);

            }
            else if (Objects.equals(formulaB, 1)){
                b = formulanum;
                formulanum = "";
                formulaB = 0;
                doFormulaB();
                putinFormula(formula);
            }
            else if (Objects.equals(formulaC, 1)){
                c = formulanum;
                formulanum = "";
                formulaC = 0;
                doFormulaC();
                putinFormula(formula);
            }
            else {
                formula2 = "";
                first = 0;
                screen.setText(formula);
                convertTosetValue(formula);
            }
        }
        else {
            formulanum += num;
        }

    }

    public void doFormulaA() {
        first = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (Objects.equals(formula.charAt(i), 'a')) {
                if (Objects.equals(first, 0)) {
                    formula2 = a;
                    first = 1;
                } else {
                    formula2 += a;
                }
            } else {
                formula2 += formula.charAt(i);
                first = 1;
            }
        }
        formula = formula2;
        formula2 = "";
    }
    public void doFormulaB() {
        first = 0;
        for (int i = 0; i < formula.length(); i++) {
            if (Objects.equals(formula.charAt(i), 'b')) {
                if (Objects.equals(first, 0)) {
                    formula2 = b;
                    first = 1;
                } else {
                    formula2 += b;
                }
            } else {
                formula2 += formula.charAt(i);
                first = 1;
            }
        }
        formula = formula2;
        formula2 = "";
    }
    public void doFormulaC(){
        first = 0;
            for (int i = 0; i < formula.length(); i++) {
                if (Objects.equals(formula.charAt(i), 'c')) {
                    if (Objects.equals(first, 0)){
                        formula2 = c;
                        first = 1;
                    }
                    else {
                        formula2 += c;
                    }
                } else {
                    formula2 += formula.charAt(i);
                    first = 1;
                }
            }
        formula = formula2;
        formula2 = "";
    }

    /*
     * Clears everything, both screen and calculator
     */
    @FXML
    public void clearEverything(ActionEvent e) {
        screen.clear();
        this.nollaa();
        history.getItems().clear();
    }
    /*
     * brings old formula from history
     */
    @FXML
    public void fetchHistory(MouseEvent e) {
        String selectedLine = history.getSelectionModel().getSelectedItem();
        if (selectedLine != null) {
            String formula = selectedLine.substring(0, selectedLine.indexOf("="));
            this.nollaa();
            this.convertTosetValue(formula);
            screen.setText(formula);
        }

    }
    /*
 *
 */
    @FXML
    public void handleScreenInput(KeyEvent keyEvent) {
        System.out.println(keyEvent.getCode());
        if (keyEvent.getCode() == KeyCode.ENTER) {
            this.setValue(screen.getText());
            this.equals(new ActionEvent());
            this.nollaa();
        }
        else {
            //String value = screen.getText();
            //this.setValue(value);
            //printToScreen(value);
        }
    }
    /*
     * puts bracket to its place
     */
    @FXML
    public void brackets(ActionEvent e) {
        Button btn = (Button) e.getSource();
        String value = btn.getText();
        this.sulut(value);
        printToScreen(value);
    }

    /*
     * removes last char from input
     */
    @FXML
    public void backspace(ActionEvent e) {
        String str = screen.getText();
        str = str.substring(0, str.length()-1);
        if(!screenIsEmpty() && screen.getText().length() >= 1) {
            this.nollaa();
            this.convertTosetValue(str);
            screen.setText(str);
        }
        System.out.println("backspace: " + str);
    }

    // Ye Olde code

    /*
     * clears the calculator
     */
    public void nollaa() {
        vanha = Tulos;
        laskin.nollaa();
        laske.nollaa();
        Tulos = 0;
        listat.clear();
        Valitulos = "";
        lasku = "";
        place = 0;
        luolista.nollaa();
         formulanum = "";
         formulaA = 0;
         formulaB = 0;
         formulaC = 0;
         formula = "";
         formula2 = "";
         isformula = 0;
         first = 0;
         a = "";
         b = "";
         c = "";
        //edellinen = "tyhja";
        //arvot.clear();
        //merkit.clear();
        //miinus = 0;
        //luku1 = 0;
        //luku = null;
        System.out.println(listat+" nollattu?");
    }
    /*
	 * clears only chosen classes between calculations
	 */
    public void nollaaValilasku(){
    	laske.nollaa();
    	laskin.nollaa();
    	newString = "";
    }
    /*
 *  clears calculator, but saves latest answer
 */
    public void nollaaJatkalaskua() {
        vanha = Tulos;
        laskin.nollaa();
        laske.nollaa();
        listat.clear();
        Valitulos = "";
        lasku = "";
        place = 0;
        luolista.nollaa();
        setValue(Double.toString(Tulos));
    }

    /*
     * Counts the calculation in the right order
     *
     */
    public void laskelopputulos() {
    	System.out.println("this" +listat);
    	for (int i = (listat.size()-1); i >= 0; i--){
    		/*
    		 * checks if there are multiple strings in array.
    		 * if so calculates last one first and so on
    		 * saves result each time
    		 */

    		if (!Objects.equals(i,  0)){
    			luolista.luolista(listat.get(i));
    			System.out.println("laskee tuloksen" + luolista.getArvotList() + ", " + luolista.getMerkitList());
    			Tulos = laske.laske(luolista.getArvotList(), luolista.getMerkitList());
    			nollaaValilasku();
    			Valitulos = Double.toString(Tulos);
    			lasku = listat.get(i-1);
    		/*
    		 * replace x in previous string with result of calculations inside prackets
    		 */
    			StrChar = 0;
    		for (int k=0; k < lasku.length(); k++){
    			aChar = ((CharSequence) lasku).charAt(k);
    			if (Objects.equals(StrChar, 0)){
    				if (Objects.equals(aChar, 'x')){
        				newString += Valitulos;
        				StrChar = 1;
        			}
        			else {
        				newString += Character.toString(aChar);
        			}
    			}
    			else if (Objects.equals(StrChar, 1)){
    				if (Objects.equals(aChar, 'x')){
    				}
    				newString += Character.toString(aChar);
    			}

    		}
    			listat.set((i-1), newString);

    		}
    		/*
    		 * Calculates final string and saves result
    		 */
    		else if (Objects.equals(i, 0)){
    			luolista.luolista(listat.get(i));
        		System.out.println(listat.get(i)+ " 0 laskee tuloksen" + luolista.getArvotList() + ", " + luolista.getMerkitList());
                Tulos = laske.laske(luolista.getArvotList(), luolista.getMerkitList());
                nollaaValilasku();
    		}
    	}
    	nollaaJatkalaskua();
    	}
    /*
	 * sets value to string "lasku#
	 */
    public void setValue(String value) {
        lasku += value;
    }
    /*
         * sets answer to string "lasku#
         */
    @FXML
    public void setAnswer() {
        this.printToScreen("Ans");
        lasku += vanha;
    }
    /*
	 * if prackets where used makes new string to array
	 */
    public void sulut(String sulku) {
        if (Objects.equals(sulku, "(")) {
            lasku += sulku;
        	listat.add(place, lasku);
        	System.out.println(lasku);
        	place++;
        	lasku = "";
        }
        /*
    	 * adds new string to array and goes back to previous string in array
    	 */
        else if (Objects.equals(sulku, ")")) {
        	lasku += sulku;
        	listat.add(place, lasku);
        	System.out.println(lasku);
        	place--;
        	lasku = listat.get(place);
        	listat.remove(place);
        	lasku += "x";
        }
        /*
    	 * adds new string to array
    	 */
        else if (Objects.equals(sulku, "=")) {
        	lasku += sulku;
        	listat.add(place, lasku);
        	System.out.println(lasku);
        }

    }

    /*
     * returns the result
     * @return Tulos Tulos is the result of the calculation
     */
    public double getTulos() {
        return Tulos;
    }
    public void setBundle(ResourceBundle b) {
        this.bundle = b;
    }
}

