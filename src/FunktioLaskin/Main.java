package FunktioLaskin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

/*
 * Main class for starting the javafx application
 */
public class Main extends Application {
    /*
     * Main function
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("locale");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"), bundle);
        loader.setController(Controller.getInstance());
        Parent root = loader.load();
		stage.setTitle("Calculator");
		stage.setScene(new Scene(root, 780, 600));
		Controller.getInstance().setBundle(bundle);
		stage.show();
    }
}
