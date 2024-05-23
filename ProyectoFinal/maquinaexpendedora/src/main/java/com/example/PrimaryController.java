package com.example;

import java.io.IOException;
import com.example.*;
import java.sql.*;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class PrimaryController {

    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;

    @FXML
    private TextField User;

    @FXML
    private Button acceso;

    @FXML
    private Label nombrePrincipal;
    
    @FXML
    private PasswordField pass;

    @FXML
    void iniciar(ActionEvent event) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" )) {
            try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente WHERE NIF = ? AND clave = ?")) {
                stmt.setString(1, User.getText());
                stmt.setString(2, pass.getText());
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        try {
                            Cliente usuario = new Cliente(Double.parseDouble(rs.getString(1)),Double.parseDouble(rs.getString(2)),rs.getString(3),rs.getString(4));
                            App.Usuario.setNIF(usuario.getNIF());
                            App.Usuario.setDinero_ingresado(usuario.getDinero_ingresado());
                            App.Usuario.setDinero_gastado(usuario.getDinero_gastado());
                            App.Usuario.setClave(usuario.getClave());
                            App.setRoot("menuexpendedora");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (User.getText() == null || User.getText().trim().isEmpty()) {
                            
                            Alert errorAlert = new Alert(AlertType.ERROR);
                            errorAlert.setTitle("ERROR");
                            errorAlert.setHeaderText("Error de creación");
                            errorAlert.setContentText("No se puede crear una cuenta con estas credenciales.");
                            errorAlert.showAndWait();
                        
                        }
                        if (pass.getText() == null || pass.getText().trim().isEmpty()) {
                            
                            Alert errorAlert = new Alert(AlertType.ERROR);
                            errorAlert.setTitle("ERROR");
                            errorAlert.setHeaderText("Error de creación");
                            errorAlert.setContentText("No se puede crear una cuenta con estas credenciales.");
                            errorAlert.showAndWait();
                        
                        }
                        if (User.getText() != null && !User.getText().trim().isEmpty() && pass.getText() != null  && !pass.getText().trim().isEmpty()){
                            Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                            confirmAlert.setTitle("Error de login");
                            confirmAlert.setHeaderText("No hay ninguna cuenta con estas credenciales");
                            confirmAlert.setContentText("¿Quieres crear una cuenta con el siguiente NIF?");
                            Optional<ButtonType> result = confirmAlert.showAndWait();

                            if (result.isPresent() && result.get() == ButtonType.OK) {
                                try (PreparedStatement insertStmt = con.prepareStatement("INSERT INTO Cliente (NIF, dinero_gastado, dinero_ingresado, clave) VALUES (?, 0.0, 0.0, ?)")) {
                                    insertStmt.setString(1, User.getText());
                                    insertStmt.setString(2, pass.getText());
                                    int rowsAffected = insertStmt.executeUpdate();

                                    if (rowsAffected > 0) {
                                        Alert infoAlert = new Alert(AlertType.INFORMATION);
                                        infoAlert.setTitle("Creado!");
                                        infoAlert.setHeaderText(null);
                                        infoAlert.setContentText("Se ha creado correctamente el usuario.");
                                        infoAlert.showAndWait();
                                    }
                                }
                            }   
                        }
                        
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void initialize() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM Cliente",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert acceso != null : "fx:id=\"acceso\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert nombrePrincipal != null : "fx:id=\"nombrePrincipal\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'accesoprimario.fxml'.";
    }

}