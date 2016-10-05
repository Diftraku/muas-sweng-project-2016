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


public class Main extends Application {
	private Control control;
	private DoubleProperty value = new SimpleDoubleProperty();
	private TextField screen;
	private static final String[][] template = {
		      { "7", "8", "9", "/" },
		      { "4", "5", "6", "*" },
		      { "1", "2", "3", "-" },
		      { "0", "c", "=", "+" }
		};

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

	private VBox createLayout(TextField screen, TilePane buttons) {
	    final VBox layout = new VBox(20);
	    layout.setAlignment(Pos.CENTER);
	    layout.setStyle("-fx-background-color: darkgray; -fx-padding: 20; -fx-font-size: 20;");
	    layout.getChildren().setAll(screen, buttons);
	    screen.prefWidthProperty().bind(buttons.widthProperty());
	    return layout;
	}
	private TextField createScreen() {
	    screen = new TextField();
	    screen.setStyle("-fx-background-color: aquamarine;");
	    screen.setAlignment(Pos.CENTER_RIGHT);
	    screen.setEditable(false);
	    return screen;
	}
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
	private Button createButton(String s) {
	    Button button = new Button(s);
	    button.setStyle("-fx-base: beige;");
	    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	    button.setOnAction(myHandler);
	    return button;
	}
	final EventHandler<ActionEvent> myHandler = new EventHandler<ActionEvent>(){

        @Override
        public void handle(final ActionEvent e) {
        	Button button = (Button)e.getSource();
        	String value = button.getText();
        	if(value == "c") {
        		screen.clear();
        	}else {
        		if (value == "/" || value == "*" || value == "+" || value == "-") {
        			control.setMerkki(value);
        		}
        		if (value == "="){
        			control.laske();
        			screen.setText(Double.toString(control.getTulos()));
        		}
        		else{
        			control.setValue(value);
        		}
        		if(screen.getText() != null && !screen.getText().isEmpty()) {
        			screen.appendText(value);
        		}else {
        			screen.setText(value);
        		}
        	}
        }
	};

	public static void main(String[] args) {
		launch(args);
	}
}