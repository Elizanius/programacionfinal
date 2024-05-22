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
        try {
            stmt = con.prepareStatement("SELECT * FROM Cliente WHERE NIF = ? AND clave = ?");
            stmt.setString(1, User.getText());
            stmt.setString(2, pass.getText());
            rs = stmt.executeQuery();
            if (rs.next()) {
                try {
                    Cliente usuario = new Cliente(Double.parseDouble(rs.getString(1)) , Double.parseDouble(rs.getString(2)), rs.getString(3), rs.getString(4));
                    App.Usuario.setNIF(usuario.getNIF());
                    App.Usuario.setDinero_ingresado(usuario.getDinero_ingresado());
                    App.Usuario.setDinero_gastado(usuario.getDinero_gastado());
                    App.Usuario.setClave(usuario.getClave());
                    App.setRoot("menuexpendedora");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
                confirmAlert.setTitle("Error de login");
                confirmAlert.setHeaderText(null);
                confirmAlert.setContentText("¿Quieres crear una cuenta con el siguiente NIF?");
                Optional<ButtonType> result = confirmAlert.showAndWait();
    
                
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    stmt = con.prepareStatement("INSERT INTO Cliente (NIF, dinero_gastado, dinero_ingresado, clave) VALUES (?, 0.0, 0.0, ?)");
                    stmt.setString(1, User.getText());
                    stmt.setString(2, pass.getText());
                    int rowsAffected = stmt.executeUpdate();
    
                    if (rowsAffected > 0) {
                        Alert infoAlert = new Alert(AlertType.INFORMATION);
                        infoAlert.setTitle("Creado!");
                        infoAlert.setHeaderText(null);
                        infoAlert.setContentText("Se ha creado correctamente el usuario.");
                        infoAlert.showAndWait();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos en finally para asegurarse de que siempre se cierren
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        
    }

}