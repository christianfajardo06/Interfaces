package aplicacioncomponentes;

import barraprogreso.BarraProgreso;
import checkbutton.CheckButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import chrisboton.Chrisboton;
import radialbuttoncustom.RadialButtonCustom;

import java.net.URL;
import java.util.ResourceBundle;

public class AplicacionComponentesController implements Initializable {

    // CheckButtons
    @FXML private CheckButton checkNotificaciones;
    @FXML private CheckButton checkModoOscuro;
    @FXML private CheckButton checkSonido;

    // Radial buttons
    @FXML private RadialButtonCustom radioTemaAzul;
    @FXML private RadialButtonCustom radioTemaVerde;
    @FXML private RadialButtonCustom radioTemaRojo;

    // Barra de progreso
    @FXML private BarraProgreso barraProgreso;

    // Labels
    @FXML private Label lblConfiguracion;
    @FXML private Label lblEstado;

    // Botones
    @FXML private Chrisboton btnChris;       // Guardar
    @FXML private Chrisboton btnRestaurar;   // Restaurar

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Inicializar checkbuttons y radios
        checkNotificaciones.setSelected(false);
        checkModoOscuro.setSelected(false);
        checkSonido.setSelected(false);

        radioTemaAzul.setSelected(false);
        radioTemaVerde.setSelected(false);
        radioTemaRojo.setSelected(false);

        barraProgreso.setProgress(0);

        lblEstado.setText("Configuración sin guardar");
        lblEstado.setStyle("-fx-text-fill: #7f8c8d;");

        // Inicializar botones
        btnChris.setText("Guardar");
        btnChris.setStyle("-fx-background-color: #28a745; -fx-text-fill: white;");

        btnRestaurar.setText("Restaurar");
        btnRestaurar.setVisible(false);
        btnRestaurar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");

        // Hover azul oscuro para Restaurar
        btnRestaurar.setOnMouseEntered(e -> btnRestaurar.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white;"));
        btnRestaurar.setOnMouseExited(e -> btnRestaurar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"));

        configurarEventos();
        actualizarTextoConfiguracion();
    }

    // Eventos
    private void configurarEventos() {

        // Guardar configuración
        btnChris.setOnAction(e -> {
            guardarConfiguracion();

            // Mostrar botón azul Restaurar y forzar estilo
            btnRestaurar.setVisible(true);
            btnRestaurar.setText("Restaurar");
            btnRestaurar.setStyle(
                "-fx-background-color: #007bff;" +   // Azul
                "-fx-text-fill: white;" +
                "-fx-border-color: #0056b3;" +
                "-fx-border-width: 1;" +
                "-fx-background-radius: 5;" +
                "-fx-border-radius: 5;"
            );

            // Hover azul oscuro
            btnRestaurar.setOnMouseEntered(ev -> 
                btnRestaurar.setStyle("-fx-background-color: #0056b3; -fx-text-fill: white; -fx-border-color: #0056b3; -fx-border-width: 1; -fx-background-radius: 5; -fx-border-radius: 5;")
            );
            btnRestaurar.setOnMouseExited(ev -> 
                btnRestaurar.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-border-color: #0056b3; -fx-border-width: 1; -fx-background-radius: 5; -fx-border-radius: 5;")
            );
        });

        // Restaurar configuración
        btnRestaurar.setOnAction(e -> {
            restaurarConfiguracion();
            // Ocultar Restaurar
            btnRestaurar.setVisible(false);
        });

        // Listeners de los check y radio
        checkNotificaciones.setOnAction(e -> actualizarTextoConfiguracion());
        checkModoOscuro.setOnAction(e -> actualizarTextoConfiguracion());
        checkSonido.setOnAction(e -> actualizarTextoConfiguracion());

        radioTemaAzul.setOnAction(e -> actualizarTextoConfiguracion());
        radioTemaVerde.setOnAction(e -> actualizarTextoConfiguracion());
        radioTemaRojo.setOnAction(e -> actualizarTextoConfiguracion());
    }

    // Guardar 
    private void guardarConfiguracion() {

        barraProgreso.setProgress(0);
        lblEstado.setText("Guardando configuración...");
        lblEstado.setStyle("-fx-text-fill: #2980b9;"); // Azul mientras guarda

        new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    Thread.sleep(200);
                    final double progreso = i / 10.0;

                    javafx.application.Platform.runLater(() ->
                        barraProgreso.setProgress(progreso)
                    );
                }

                javafx.application.Platform.runLater(() -> {
                    lblEstado.setText("Configuración guardada correctamente");
                    lblEstado.setStyle("-fx-text-fill: #27ae60;"); // Verde al finalizar
                });

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    // Restaurar
    private void restaurarConfiguracion() {

        // Todo desmarcado
        checkNotificaciones.setSelected(false);
        checkModoOscuro.setSelected(false);
        checkSonido.setSelected(false);

        radioTemaAzul.setSelected(false);
        radioTemaVerde.setSelected(false);
        radioTemaRojo.setSelected(false);

        // Reiniciar progreso
        barraProgreso.setProgress(0);

        // Texto de estado
        lblEstado.setText("Configuración restaurada");
        lblEstado.setStyle("-fx-text-fill: black;");

        actualizarTextoConfiguracion();
    }

    // Texto estado           
    private void actualizarTextoConfiguracion() {

        StringBuilder estado = new StringBuilder("Preferencias activas:\n");

        estado.append("- Notificaciones: ")
              .append(checkNotificaciones.isSelected() ? "Sí" : "No").append("\n");

        estado.append("- Modo Oscuro: ")
              .append(checkModoOscuro.isSelected() ? "Sí" : "No").append("\n");

        estado.append("- Sonidos: ")
              .append(checkSonido.isSelected() ? "Sí" : "No").append("\n");

        estado.append("- Tema: ");

        if (radioTemaAzul.isSelected()) {
            estado.append("Azul Océano");
        } else if (radioTemaVerde.isSelected()) {
            estado.append("Verde Bosque");
        } else if (radioTemaRojo.isSelected()) {
            estado.append("Rojo Atardecer");
        } else {
            estado.append("Ninguno");
        }

        lblConfiguracion.setText(estado.toString());
    }
}
