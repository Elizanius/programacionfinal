package com.example;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    ResultSet rs;
    Connection con;
    PreparedStatement stmt;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("accesosecundario");
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField User;

    @FXML
    private Button acceso;

    @FXML
    private PasswordField pass;

    String NIE = User.getText();
    String contrasenya = pass.getText();

    @FXML
    void iniciar(ActionEvent event) throws IOException, InvocationTargetException {
        
        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente WHERE NIF = ? AND clave = ?");
            stmt.setString(1, NIE);
            stmt.setString(2, contrasenya);
            rs = stmt.executeQuery();
            if (rs.next()) {
                switchToSecondary();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error de login");
                alert.setHeaderText("Credenciales incorrectas");
                alert.setContentText("Por favor, verifica tu nombre de usuario y contraseña");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    void logpass(ActionEvent event) {
        
    }

    @FXML
    void loguser(ActionEvent event) {

    }

    @FXML
    void initialize(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/CajeroNOVA","root", "dbrootpass" );
            stmt = con.prepareStatement("SELECT * FROM Cliente",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert acceso != null : "fx:id=\"acceso\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'accesoprimario.fxml'.";

    }

}
