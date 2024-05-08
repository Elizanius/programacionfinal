package com.example;

import java.io.IOException;
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
import javafx.scene.control.ChoiceBox;

public class SecondaryController {

    ResultSet rs;
    Connection con;
    PreparedStatement stmt;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("accesoprimario");
    }
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cerrarboton;

    @FXML
    private ChoiceBox<?> cuentas;

    @FXML
    private Button pagarboton;

    @FXML
    private Button sacarboton;

    @FXML
    void cerrarsesion(ActionEvent event) throws IOException {
        switchToPrimary();
    }

    @FXML
    void pagarfactura(ActionEvent event) {
        
    }

    @FXML
    void sacardinero(ActionEvent event) {

    }

    @FXML
    void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/CajeroNOVA","root", "dbrootpass" );
            stmt = con.prepareStatement("SELECT * FROM Cliente",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert cerrarboton != null : "fx:id=\"cerrarboton\" was not injected: check your FXML file 'accesosecundario.fxml'.";
        assert cuentas != null : "fx:id=\"cuentas\" was not injected: check your FXML file 'accesosecundario.fxml'.";
        assert pagarboton != null : "fx:id=\"pagarboton\" was not injected: check your FXML file 'accesosecundario.fxml'.";
        assert sacarboton != null : "fx:id=\"sacarboton\" was not injected: check your FXML file 'accesosecundario.fxml'.";

    }

}