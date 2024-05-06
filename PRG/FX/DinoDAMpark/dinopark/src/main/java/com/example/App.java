package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("dinopark_1rmenu"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static class Dinosaurio {
    
        private int id_dino;
        private String nombre;
        private String tamanyo;
        private String alimentacion;
        private String tipo;

    }

    public static class Atraccion{

        private int id_atraccion;
        private int id_zona;
        private int id_dino;
        private String nombre;
        private int capacidad;
        private int edad_minima;

    }

    public static class Zona{

        private int id_zona;
        private String ubicacion;
        private String nombre;
    }

}