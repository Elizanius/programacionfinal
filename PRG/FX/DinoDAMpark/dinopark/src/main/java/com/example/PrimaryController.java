package com.example;

import com.example.App.Atraccion;
import com.example.App.Zona;
import com.example.App.Dinosaurio;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PrimaryController {

    private ResultSet rs;

    @FXML
    private TextField capacidad;

    @FXML
    private ChoiceBox<?> dinoatraccion;

    @FXML
    private ImageView imagen;

    @FXML
    private TextField nombre;

    @FXML
    private ChoiceBox<?> zonaatraccion;

    @FXML
    private ChoiceBox<?> alimentacion;

    @FXML
    private TableView<?> tabla;

    @FXML
    private ChoiceBox<?> tamaño;

    @FXML
    private ChoiceBox<?> tipo;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView mapa;

    @FXML
    private Button mapaeste;

    @FXML
    private Button mapanorte;

    @FXML
    private Button mapaoeste;

    @FXML
    private Button mapasur;

    @FXML
    private Button botoncrear;

    @FXML
    private Button botonlistardinos;

    @FXML
    private Button botonlistarzonas;

    @FXML
    private ImageView rex;

    @FXML
    void listardinos() throws IOException {
        App.setRoot("dinopark_listardinos");
        alimentacion.getItems();
    }

    @FXML
    void listarzona() throws IOException {
        App.setRoot("dinopark_mapa");
    }

    @FXML
    void crearatraccion(ActionEvent event) throws IOException {
        App.setRoot("dinopark_crearatraccion");
    }

    @FXML
    void salir(ActionEvent event) throws SQLException {
        rs.close();
    }

    @FXML
    void capacidadatraccion(ActionEvent event) {

    }

    @FXML
    void listareste(ActionEvent event) {

    }

    @FXML
    void listarnorte(ActionEvent event) {

    }

    @FXML
    void listaroeste(ActionEvent event) {
        
    }

    @FXML
    void listarsur(ActionEvent event) {

    }

    @FXML
    void initialize() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/JurassicPark","root", "dbrootpass" );
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM JurassicPark",
        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery();
        assert botoncrear != null : "fx:id=\"botoncrear\" was not injected: check your FXML file 'dinopark_1rmenu.fxml'.";
        assert botonlistardinos != null : "fx:id=\"botonlistardinos\" was not injected: check your FXML file 'dinopark_1rmenu.fxml'.";
        assert botonlistarzonas != null : "fx:id=\"botonlistarzonas\" was not injected: check your FXML file 'dinopark_1rmenu.fxml'.";
        assert rex != null : "fx:id=\"rex\" was not injected: check your FXML file 'dinopark_1rmenu.fxml'.";
        assert capacidad != null : "fx:id=\"capacidad\" was not injected: check your FXML file 'dinopark_crearatraccion.fxml'.";
        assert dinoatraccion != null : "fx:id=\"dinoatraccion\" was not injected: check your FXML file 'dinopark_crearatraccion.fxml'.";
        assert imagen != null : "fx:id=\"imagen\" was not injected: check your FXML file 'dinopark_crearatraccion.fxml'.";
        assert nombre != null : "fx:id=\"nombre\" was not injected: check your FXML file 'dinopark_crearatraccion.fxml'.";
        assert zonaatraccion != null : "fx:id=\"zonaatraccion\" was not injected: check your FXML file 'dinopark_crearatraccion.fxml'.";
        assert mapa != null : "fx:id=\"mapa\" was not injected: check your FXML file 'dinopark_mapa.fxml'.";
        assert mapaeste != null : "fx:id=\"mapaeste\" was not injected: check your FXML file 'dinopark_mapa.fxml'.";
        assert mapanorte != null : "fx:id=\"mapanorte\" was not injected: check your FXML file 'dinopark_mapa.fxml'.";
        assert mapaoeste != null : "fx:id=\"mapaoeste\" was not injected: check your FXML file 'dinopark_mapa.fxml'.";
        assert mapasur != null : "fx:id=\"mapasur\" was not injected: check your FXML file 'dinopark_mapa.fxml'.";        
        assert alimentacion != null : "fx:id=\"alimentacion\" was not injected: check your FXML file 'dinopark_listardinos.fxml'.";
        assert tabla != null : "fx:id=\"tabla\" was not injected: check your FXML file 'dinopark_listardinos.fxml'.";
        assert tamaño != null : "fx:id=\"tamaño\" was not injected: check your FXML file 'dinopark_listardinos.fxml'.";
        assert tipo != null : "fx:id=\"tipo\" was not injected: check your FXML file 'dinopark_listardinos.fxml'.";

    }

}

