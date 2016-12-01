import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

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
        Parent root = FXMLLoader.load(getClass().getResource("/View.fxml"));
        stage.setTitle("Calculator");
        stage.setScene(new Scene(root, 800, 700));
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
}
