package ni.edu.uam.practicahilosjavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("procesador-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 520);

        stage.setTitle("NicaData Solutions - Monitor Concurrente de Procesos");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        ProcesadorController controller = fxmlLoader.getController();
        stage.setOnCloseRequest(event -> controller.detenerAlCerrar());
    }
}