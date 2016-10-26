package FunktioLaskin;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

import javafx.stage.StageStyle;

/*
 * Main class for starting the javafx application
 */
public class Main extends Application {
	private Control control;
	private DoubleProperty value = new SimpleDoubleProperty();
	private TextField screen;
	private static final String[][] template = {
			  { "^", "N", ".", "(", ")"},
		      { "7", "8", "9", "/", "sin"},
		      { "4", "5", "6", "*", "cos" },
		      { "1", "2", "3", "-", "tan" },
		      { "0", "c", "=", "+", "Pii" }
		};
/*
 * (non-Javadoc)
 * @see javafx.application.Application#start(javafx.stage.Stage)
 */
	@Override
	public void start(Stage stage) {
		control = new Control();
		final TextField screen  = createScreen();
	    final TilePane  buttons = createButtons();

	    stage.setTitle("Calc");
	    stage.initStyle(StageStyle.UTILITY);
	    stage.setResizable(false);
	    stage.setScene(new Scene(createLayout(screen, buttons)));
	    stage.show();
	}
/*
 * Creates the calculator layout
 * @param screen the textfield where the numbers go
 * @param buttons Buttons TilePane where all the buttons are
 * @return Vbox which has the screen and buttons in it
 */
	private VBox createLayout(TextField screen, TilePane buttons) {
	    final VBox layout = new VBox(20);
	    layout.setAlignment(Pos.CENTER);
	    layout.setStyle("-fx-background-color: darkgray; -fx-padding: 20; -fx-font-size: 20;");
	    layout.getChildren().setAll(screen, buttons);
	    screen.prefWidthProperty().bind(buttons.widthProperty());
	    return layout;
	}
	/*
	 * Creates the screen
	 * @return TextField Textfield is the screen where numbers go
	 */
	private TextField createScreen() {
	    screen = new TextField();
	    screen.setStyle("-fx-background-color: aquamarine;");
	    screen.setAlignment(Pos.CENTER_RIGHT);
	    screen.setEditable(false);
	    return screen;
	}
	/*
	 * Creates all the buttons from the template
	 * @return TilePane Pane which has all the buttons in it
	 */
	private TilePane createButtons() {
	    TilePane buttons = new TilePane();
	    buttons.setVgap(7);
	    buttons.setHgap(7);
	    buttons.setPrefColumns(template[0].length);
	    for (String[] r: template) {
	      for (String s: r) {
	        buttons.getChildren().add(createButton(s));
	      }
	    }
	    return buttons;
	}
	/*
	 * @param s Is the name of the button which comes from the template
	 * @return Button returns JavaFX Button object
	 */
	private Button createButton(String s) {
	    Button button = new Button(s);
	    button.setStyle("-fx-base: beige;");
	    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    button.setOnAction(myHandler);
	    return button;
	}

	final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){
		/*
		 * (non-Javadoc)
		 * @see javafx.event.EventHandler#handle(javafx.event.Event)
		 */
        @Override
        public void handle(final ActionEvent e) {
        	Button button = (Button)e.getSource();
        	String value = button.getText();
        	if(value == "c") {
        		screen.clear();
        		control.nollaa();
        	}
        	else {
        		if (value == "/" || value == "*" || value == "+" || value == "-" || value == "^") {
        			control.setMerkki(value);
        		}
        		else if (value == "="){
        			control.setMerkki(value);
        			control.laske();
        			screen.setText(Double.toString(control.getTulos()));
        			//control.nollaa();
        		}
        		else if (value == "0" || value == "1" || value == "2" || value == "3" || value == "4" || value == "5" || value == "6" || value == "7" || value == "8" || value == "9" || value == "."){
        			control.setLuku(value);
        		}
        		else if (value == "(" || value == ")" ){
        			control.sulut(value);

        		}
        		if(screen.getText() != null && !screen.getText().isEmpty()) {
        			if (value != "="){
        				screen.appendText(value);
        			}
        		}else {
        			if (value != "="){
        			screen.setText(value);
        			}
        		}
        	}
        }
	};
/*
 * Main function
 */
	public static void main(String[] args) {
		launch(args);
	}
}