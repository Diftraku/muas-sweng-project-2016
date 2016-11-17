package FunktioLaskin;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

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
    public void start(Stage stage) {
        control = Control.getInstance();
        view = new FunktioLaskinView();
        stage.setTitle("Calc");
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(new Scene(view));
        stage.show();
    }
}