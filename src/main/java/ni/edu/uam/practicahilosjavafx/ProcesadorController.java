package ni.edu.uam.practicahilosjavafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class ProcesadorController {

    @FXML private Label lblEstadoClientes;
    @FXML private Label lblPorcentajeClientes;
    @FXML private ProgressBar barraClientes;
    @FXML private Button btnIniciarClientes;
    @FXML private Button btnDetenerClientes;
    @FXML private Button btnReiniciarClientes;

    @FXML private Label lblEstadoProductos;
    @FXML private Label lblPorcentajeProductos;
    @FXML private ProgressBar barraProductos;
    @FXML private Button btnIniciarProductos;
    @FXML private Button btnDetenerProductos;
    @FXML private Button btnReiniciarProductos;

    private Thread hiloClientes;
    private Thread hiloProductos;

    @FXML
    private void iniciarProcesoClientes() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            return;
        }

        barraClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Procesando clientes...");

        btnIniciarClientes.setDisable(true);
        btnDetenerClientes.setDisable(false);
        btnReiniciarClientes.setDisable(true);

        Runnable tareaClientes = () -> {
            try {
                for (int i = 1; i <= 100; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(100); // 100ms por registro

                    final int avance = i;
                    Platform.runLater(() -> {
                        barraClientes.setProgress(avance / 100.0);
                        lblPorcentajeClientes.setText(avance + "%");
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Proceso de clientes finalizado");
                    habilitarBotonesClientes();
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Platform.runLater(() -> {
                    lblEstadoClientes.setText("Proceso de clientes detenido");
                    habilitarBotonesClientes();
                });
            }
        };

        hiloClientes = new Thread(tareaClientes);
        hiloClientes.setName("Hilo-Clientes");
        hiloClientes.setDaemon(true);
        hiloClientes.start();
    }

    @FXML
    private void detenerProcesoClientes() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            btnDetenerClientes.setDisable(true);
            lblEstadoClientes.setText("Solicitando detención...");
            hiloClientes.interrupt();
        }
    }

    @FXML
    private void reiniciarProcesoClientes() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            return;
        }
        barraClientes.setProgress(0);
        lblPorcentajeClientes.setText("0%");
        lblEstadoClientes.setText("Esperando inicio");
        habilitarBotonesClientes();
    }

    private void habilitarBotonesClientes() {
        btnIniciarClientes.setDisable(false);
        btnDetenerClientes.setDisable(true);
        btnReiniciarClientes.setDisable(false);
    }

    @FXML
    private void iniciarProcesoProductos() {
        if (hiloProductos != null && hiloProductos.isAlive()) {
            return;
        }

        barraProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Procesando productos...");

        btnIniciarProductos.setDisable(true);
        btnDetenerProductos.setDisable(false);
        btnReiniciarProductos.setDisable(true);

        Runnable tareaProductos = () -> {
            try {
                for (int i = 1; i <= 50; i++) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    Thread.sleep(150); // 150ms por registro

                    final int avance = i;
                    Platform.runLater(() -> {
                        barraProductos.setProgress(avance / 50.0);
                        lblPorcentajeProductos.setText((avance * 2) + "%");
                    });
                }

                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Proceso de productos finalizado");
                    habilitarBotonesProductos();
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Platform.runLater(() -> {
                    lblEstadoProductos.setText("Proceso de productos detenido");
                    habilitarBotonesProductos();
                });
            }
        };

        hiloProductos = new Thread(tareaProductos);
        hiloProductos.setName("Hilo-Productos");
        hiloProductos.setDaemon(true);
        hiloProductos.start();
    }

    @FXML
    private void detenerProcesoProductos() {
        if (hiloProductos != null && hiloProductos.isAlive()) {
            btnDetenerProductos.setDisable(true);
            lblEstadoProductos.setText("Solicitando detención...");
            hiloProductos.interrupt();
        }
    }

    @FXML
    private void reiniciarProcesoProductos() {
        if (hiloProductos != null && hiloProductos.isAlive()) {
            return;
        }
        barraProductos.setProgress(0);
        lblPorcentajeProductos.setText("0%");
        lblEstadoProductos.setText("Esperando inicio");
        habilitarBotonesProductos();
    }

    private void habilitarBotonesProductos() {
        btnIniciarProductos.setDisable(false);
        btnDetenerProductos.setDisable(true);
        btnReiniciarProductos.setDisable(false);
    }

    public void detenerAlCerrar() {
        if (hiloClientes != null && hiloClientes.isAlive()) {
            hiloClientes.interrupt();
        }
        if (hiloProductos != null && hiloProductos.isAlive()) {
            hiloProductos.interrupt();
        }
    }
}