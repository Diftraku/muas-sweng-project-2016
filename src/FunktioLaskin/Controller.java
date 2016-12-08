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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

/*
 * Controller class
 */
public class Controller {
    private ConcreteFormulaDAO dao;
    private Laskin laskin;
    private Main main;
    private Laskujarjestys laske;
    ArrayList<String> listat;
    private String lasku = "";
    private int place = 0;
    private LuoLista luolista;
    private char aChar;
    private String Valitulos;
    private CharSequence charplace = "x";
    private String newString = "";
    private int StrChar = 0;
    private int lisaai= 0;
    private double vanha=0;
    private double Tulos=0;
    private int formulanum = 0;
    private int formulaA = 0;
    private int formulaB = 0;
    private int formulaC = 0;



    // JavaFX Elements
    @FXML
    private TextField screen;
    @FXML
    private ListView<String> history;
    @FXML
    private BorderPane root;


    /*
     * Constructor
     */
    public Controller() {
        laskin = new Laskin();
        main = new Main();
        laske = new Laskujarjestys();
        listat = new ArrayList<String>();
        luolista = new LuoLista();
    }


    @FXML
    public boolean screenIsEmpty() {
        if (screen.getText() != null && !screen.getText().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

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
     * starts operations when buttons have been clicked
     */
    @FXML
    public void numbersAndOperators(ActionEvent e) {
        Button btn = (Button) e.getSource();
        String value = btn.getText();

        /*
         * Button witch have more than one char, need to convert to one char value
         */
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
        printToScreen(value);
    }

    /*
    * equals method puts equals char forward and triggers laskelopputulos() -method witch calculates answer
    */
    @FXML
    public void equals(ActionEvent e) {
        String value = "=";
        this.sulut(value);
        this.laskelopputulos();
        String historyLine = screen.getText()+"="+Double.toString(this.getTulos());
        history.getItems().add(history.getItems().size(), historyLine);
        history.scrollTo(historyLine);
        screen.setText(Double.toString(this.getTulos()));
        //printToScreen(value);
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
            ResourceBundle bundle = ResourceBundle.getBundle("locale");
            scene.setRoot((Parent)FXMLLoader.load(getClass().getResource("/View.fxml"), bundle));
        }
        else if (Objects.equals(item.getText(), "France")|| Objects.equals(item.getText(), "Français") || Objects.equals(item.getText(), "Igifaransa")){
            ResourceBundle bundle = ResourceBundle.getBundle("locale_fr_FR");
            scene.setRoot((Parent)FXMLLoader.load(getClass().getResource("/View.fxml"), bundle));
        }
        else if (Objects.equals(item.getText(), "Rwanda")|| Objects.equals(item.getText(), "Rwanda") || Objects.equals(item.getText(), "Ikinyarwanda")){
            ResourceBundle bundle = ResourceBundle.getBundle("locale_rw_RW");
            scene.setRoot((Parent)FXMLLoader.load(getClass().getResource("/View.fxml"), bundle));
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
    @FXML
    public void putinFormula(String form) {
        formulaA = 0;
        formulaB = 0;
        formulaC = 0;
        for (int i = 0; i < form.length(); i++){
            if (Objects.equals(form.charAt(i), "c")){
                formulaC = 1;
            }
            else if (Objects.equals(form.charAt(i), "b")){
                formulaB = 1;
            }
            else if (Objects.equals(form.charAt(i), "a")){
                formulaA = 1;
            }
        }
        if (Objects.equals(formulaA, 1)){
            askLetter("a");
        }
        if (Objects.equals(formulaB, 1)){
            askLetter("b");
        }
        if (Objects.equals(formulaC, 1)){
            askLetter("c");
        }

    }
    @FXML
    public void askLetter(String x){
        screen.setText(x+" = ");
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
 *  removes last char from input
 */
    @FXML
    public void backspace(ActionEvent e) {
        String str = screen.getText();
        str = str.substring(0, str.length()-1);
        if(!screenIsEmpty() && screen.getText().length() > 1) {
            this.nollaa();
            this.convertTosetValue(str);
            screen.setText(str);
        }
        System.out.println("backspace: " + str);
        screen.undo();
        screen.undo();
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
}

