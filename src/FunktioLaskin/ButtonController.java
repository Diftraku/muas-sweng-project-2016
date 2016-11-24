package FunktioLaskin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.Objects;

public class ButtonController {

	private Control control;
	@FXML
	private TextArea screen;

	public ButtonController() {
		control = Control.getInstance();
	}
	@FXML
	public boolean screenIsEmpty() {
		if (screen.getText() != null && !screen.getText().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	@FXML
	public void printToScreen(String value) {
		if (screenIsEmpty()) {
			if (!Objects.equals(value, "=")) {
				screen.setText(value);
			}
		}else {
			if (!Objects.equals(value, "=")) {
				screen.appendText(value);
			}
		}
	}
	@FXML public void numbersAndOperators(ActionEvent e) {
		Button btn = (Button)e.getSource();
		String value = btn.getText();

		if(Objects.equals(value, "sqrt")) {
			control.setValue("N");
		}
		else if (Objects.equals(value, "PI")) {
			control.setValue("PII");
		}
		control.setValue(value);
		printToScreen(value);
	}
	@FXML
	public void equals(ActionEvent e) {
		Button btn = (Button) e.getSource();
		String value = btn.getText();
		control.sulut(value);
		control.laskelopputulos();
		screen.setText(Double.toString(control.getTulos()));
		printToScreen(value);
	}
	@FXML
	public void clear(ActionEvent e) {
		screen.clear();
		control.nollaa();
	}
	@FXML
	public void brackets(ActionEvent e) {
		Button btn = (Button)e.getSource();
		String value = btn.getText();
		control.sulut(value);
		printToScreen(value);
	}
	@FXML
	public void backspace(ActionEvent e) {

	}

}
