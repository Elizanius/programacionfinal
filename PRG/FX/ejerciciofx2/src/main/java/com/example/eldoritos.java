package com.example;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;

public class eldoritos {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonvalidar;

    @FXML
    private PasswordField contraseña;

    @FXML
    private AnchorPane fondo;

    @FXML
    private ImageView imagen;

    @FXML
    private Label txtcontraseña;

    @FXML
    private TextField txtuser;

    @FXML
    private Label user;

    
    @FXML
    void intropass(InputEvent event) {
        
    }

    @FXML
    void introusuario(InputEvent event) {
        
    }

    @FXML
    void validar(ActionEvent event) {
        
        String contraseñatxt = contraseña.getText();
        String usertxt = txtuser.getText();

        if (usertxt.equals("Izan") && contraseñatxt.equals("123")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("ACCESO CONCEDIDO");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Formato incorrecto");
            alert.showAndWait();
        }
        
    }

    @FXML
    void initialize() {
        assert botonvalidar != null : "fx:id=\"botonvalidar\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert contraseña != null : "fx:id=\"contraseña\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert fondo != null : "fx:id=\"fondo\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert imagen != null : "fx:id=\"imagen\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert txtcontraseña != null : "fx:id=\"txtcontraseña\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert txtuser != null : "fx:id=\"txtuser\" was not injected: check your FXML file 'eldoritos.fxml'.";
        assert user != null : "fx:id=\"user\" was not injected: check your FXML file 'eldoritos.fxml'.";

    }

}
