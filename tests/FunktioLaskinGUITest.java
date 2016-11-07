import FunktioLaskin.Control;
import FunktioLaskin.FunktioLaskinView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
        Control control = Control.getInstance();
        FunktioLaskinView view = new FunktioLaskinView();
        stage.setTitle("Calc");
        stage.initStyle(StageStyle.UNIFIED);
        stage.setResizable(false);
        stage.setScene(new Scene(view));
        stage.show();
    }

    @Test
    public void one_plus_two_equals_three() {
        clickOn("#number_one");
        clickOn("#add");
        clickOn("#number_two");
        clickOn("#equals");
        verifyThat("#result", hasText("3.0"));
    }
}
