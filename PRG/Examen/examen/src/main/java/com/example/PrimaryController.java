package com.example;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.example.Clases.Cliente;
import com.example.App;
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

    

    @FXML
    void iniciar(ActionEvent event) {
    
         try {
            stmt = con.prepareStatement("SELECT * FROM Cliente WHERE NIF = ? AND clave = ?");
            stmt.setString(1, User.getText());
            stmt.setString(2, pass.getText());
            rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(rs.getString(3), rs.getString(4), rs.getString(2), rs.getString(1),rs.getString(5));
                App.cliente1.setClave(cliente.getClave());
                App.cliente1.setNombre(cliente.getNombre());
                App.cliente1.setApellidos(cliente.getApellidos());
                App.cliente1.setMovil(cliente.getMovil());
                App.cliente1.setNIF(cliente.getNIF());
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
        } catch (IOException e) {
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
    void initialize() {
        assert User != null : "fx:id=\"User\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert acceso != null : "fx:id=\"acceso\" was not injected: check your FXML file 'accesoprimario.fxml'.";
        assert pass != null : "fx:id=\"pass\" was not injected: check your FXML file 'accesoprimario.fxml'.";

        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/CajeroNOVA","root", "dbrootpass" );
            stmt = con.prepareStatement("SELECT * FROM Cliente",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}