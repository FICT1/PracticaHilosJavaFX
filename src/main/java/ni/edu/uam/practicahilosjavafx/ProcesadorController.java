package ni.edu.uam.practicahilosjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProcesadorController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
