package com.example;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.example.Clases.Cuenta;
import com.example.Clases.Factura;
import com.example.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;

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
    private ComboBox<Cuenta> showcuentas;

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
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Sacar dinero");
        td.getGraphic().applyCss();
        td.showAndWait();
        
    }

    @FXML
    void mostrarcuentas(ActionEvent event) throws SQLException{
    
        List<Cuenta> cuentas = obtenerCuentasPorNIE();
        ObservableList<Cuenta> cuentasObservable = FXCollections.observableArrayList(cuentas);
        showcuentas.setItems(cuentasObservable);
        
    }

    private static List<Cuenta> obtenerCuentasPorNIE() throws SQLException {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT * FROM Cuenta WHERE NIE = ?";
        String NIE = App.cliente1.getNIF();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/CajeroNOVA","root", "dbrootpass");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, NIE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNIF(rs.getString("NIF"));
                cuenta.setSaldo(rs.getDouble("saldo"));
                cuentas.add(cuenta);
            }
        }

        return cuentas;
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
        assert showcuentas != null : "fx:id=\"cuentas\" was not injected: check your FXML file 'accesosecundario.fxml'.";
        assert pagarboton != null : "fx:id=\"pagarboton\" was not injected: check your FXML file 'accesosecundario.fxml'.";
        assert sacarboton != null : "fx:id=\"sacarboton\" was not injected: check your FXML file 'accesosecundario.fxml'.";

    }

}