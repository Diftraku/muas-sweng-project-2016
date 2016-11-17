package FunktioLaskin;

import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Objects;

/**
 * FunktioLaskin
 *
 * @author diftraku
 * @package FunktioLaskin
 * @copyright Copyright (c) 2016, Diftraku
 * @license https://opensource.org/licenses/MIT The MIT License (MIT)
 */

public class FunktioLaskinView extends VBox {
    public TextField screen;
    public TilePane buttons;
    private FormulaDAO dao = new ConcreteFormulaDAO();
    private Formula formula = null;

    private static final String[][] template = {
            {"^", "N", ".", "(", ")"},
            {"7", "8", "9", "/", "sin"},
            {"4", "5", "6", "*", "cos"},
            {"1", "2", "3", "-", "tan"},
            {"0", "c", "=", "+", "Pii"}
    };

    final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>() {
        /*
         * (non-Javadoc)
         * @see javafx.event.EventHandler#handle(javafx.event.Event)
         */
        @Override
        public void handle(final ActionEvent e) {
            Button button = (Button) e.getSource();
            String value = button.getText();
            Control control = Control.getInstance();
            if (Objects.equals(value, "c")) {
                screen.clear();
                control.nollaa();
            } else {
                if (Objects.equals(value, "/") || Objects.equals(value, "*") || Objects.equals(value, "+") || Objects.equals(value, "-") || Objects.equals(value, "^")) {
                    control.setValue(value);
                } else if (Objects.equals(value, "N") || Objects.equals(value, "sin") || Objects.equals(value, "tan") || Objects.equals(value, "cos")) {
                    control.setValue(value);
                } else if (Objects.equals(value, "Pii")) {
                    //control.setPii();
                    formula = dao.findFormula("1");
                    screen.setText(formula.getFormula());
                } else if (Objects.equals(value, "=")) {
                    control.sulut(value);
                    control.laskelopputulos();
                    screen.setText(Double.toString(control.getTulos()));
                    //control.nollaa();
                } else if (Objects.equals(value, "0") || Objects.equals(value, "1") || Objects.equals(value, "2") || Objects.equals(value, "3") || Objects.equals(value, "4") || Objects.equals(value, "5") || Objects.equals(value, "6") || Objects.equals(value, "7") || Objects.equals(value, "8") || Objects.equals(value, "9") || Objects.equals(value, ".")) {
                    control.setValue(value);
                } else if (Objects.equals(value, "(") || Objects.equals(value, ")")) {
                    control.sulut(value);

                }
                if (screen.getText() != null && !screen.getText().isEmpty()) {
                    if (!Objects.equals(value, "=")) {
                        screen.appendText(value);
                    }
                } else {
                    if (!Objects.equals(value, "=")) {
                        screen.setText(value);
                    }
                }
            }
        }
    };

    /**
     * FunktioLaskinView
     *
     * Instantiates the JavaFX window
     */
    public FunktioLaskinView() {
        // Initialize screen and buttons
        screen = createScreen();
        buttons = createButtons();

        // Set defaults
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: darkgray; -fx-padding: 20; -fx-font-size: 20;");
        screen.prefWidthProperty().bind(buttons.widthProperty());

        // Bind elements to the window
        this.getChildren().setAll(screen, buttons);
    }

    /*
 * Creates the screen
 * @return TextField Textfield is the screen where numbers go
 */
    private TextField createScreen() {
        TextField screen = new TextField();
        screen.setId("result");
        screen.setStyle("-fx-background-color: aquamarine;");
        screen.setAlignment(Pos.CENTER_RIGHT);
        screen.setEditable(false);
        return screen;
    }

    /*
     * Creates all the buttons from the template
     * @return TilePane Pane of the buttons created
     */
    private TilePane createButtons() {
        TilePane buttons = new TilePane();
        buttons.setVgap(7);
        buttons.setHgap(7);
        buttons.setPrefColumns(template[0].length);
        for (String[] r : template) {
            for (String s : r) {
                buttons.getChildren().add(createButton(s));
            }
        }
        return buttons;
    }

    /*
     * @param String s Label of the button to create
     * @return Button JavaFX Button object
     */
    private Button createButton(String s) {
        Button button = new Button(s);
        button.setId(stringifyChar(s));
        button.setStyle("-fx-base: beige;");
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        button.setOnAction(myHandler);
        return button;
    }

    private String stringifyChar(String s) {
        // A terrible hack
        HashMap<String,String> stringify = new HashMap<>();
        stringify.put("^", "exponent");
        stringify.put("N", "capital_n");
        stringify.put(".", "decimal_separator");
        stringify.put("(", "parenthesis_start");
        stringify.put(")", "parenthesis_end");
        stringify.put("7", "number_seven");
        stringify.put("8", "number_eight");
        stringify.put("9", "number_nine");
        stringify.put("/", "divide");
        stringify.put("sin", "sin");
        stringify.put("4", "number_four");
        stringify.put("5", "number_five");
        stringify.put("6", "number_six");
        stringify.put("*", "multiply");
        stringify.put("cos", "cos");
        stringify.put("1", "number_one");
        stringify.put("2", "number_two");
        stringify.put("3", "number_three");
        stringify.put("-", "subtract");
        stringify.put("tan", "tan");
        stringify.put("0", "number_zero");
        stringify.put("c", "clear");
        stringify.put("=", "equals");
        stringify.put("+", "add");
        stringify.put("pi", "number_pi");
        return stringify.get(s);
    }
}
