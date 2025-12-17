package aplicacioncomponentes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Aplicación de Configuración de Perfil
 * Utiliza componentes personalizados: BarraProgreso, CheckButton, RadialButtonCustom y Joseboton
 * @author Alumno
 */
public class AplicacionComponentes extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AplicacionComponentes.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Configurador de Perfil de Usuario");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}