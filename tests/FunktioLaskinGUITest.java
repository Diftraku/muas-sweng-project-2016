import FunktioLaskin.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.util.ResourceBundle;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

/**
 * FunktioLaskin
 *
 * @author diftraku
 * @package FunktioLaskin
 * @copyright Copyright (c) 2016, Diftraku
 * @license https://opensource.org/licenses/MIT The MIT License (MIT)
 */

public class FunktioLaskinGUITest extends ApplicationTest {
    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("locale");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View.fxml"), bundle);
        loader.setController(Controller.getInstance());
        Parent root = loader.load();
        stage.setTitle("Calculator");
        stage.setScene(new Scene(root, 780, 600));
        Controller.getInstance().setBundle(bundle);
        stage.show();
    }

    @Test
    public void one_plus_two_equals_three() {
        clickOn("#one");
        clickOn("#plus");
        clickOn("#two");
        clickOn("#equals");
        verifyThat("#screen", hasText("3.0"));
    }

    @Test
    public void backspace() {
        clickOn("#one");
        clickOn("#plus");
        clickOn("#two");
        clickOn("#backspace");
        clickOn("#backspace");
        verifyThat("#screen", hasText("1"));
    }
}
