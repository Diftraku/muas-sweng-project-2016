package FunktioLaskin;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Main class for starting the javafx application
 */
public class Main extends Application {
    private Control control;
    private VBox view;
    private DoubleProperty value = new SimpleDoubleProperty();

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
    	Parent root = FXMLLoader.load(getClass().getResource("View.fxml"));
		//FXMLLoader loader = new FXMLLoader(Main.class.getResource("View.fxml"));
		//loader.setController(new MainController("FunktioLaskin.Control"));
		//Parent root2 = loader.load();
		stage.setTitle("Calculator");
		stage.setScene(new Scene(root, 800, 700));	
		stage.show();

        /*
        OLD MAIN!!!!!
        control = Control.getInstance();
        view = new FunktioLaskinView();
        stage.setTitle("Calc");
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(new Scene(view));
        stage.show();
         */
    }
}