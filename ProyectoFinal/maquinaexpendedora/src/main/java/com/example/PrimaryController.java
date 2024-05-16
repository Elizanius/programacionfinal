package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

public class PrimaryController {

    private ResultSet rs;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MenuGestion;

    @FXML
    private TableView<?> Mostrar_Productos;

    @FXML
    private Button botoncomprar;

    @FXML
    private Button cerrar;

    @FXML
    private TableColumn<?, ?> id_producto_tabla;

    @FXML
    private ImageView imgMaquina;

    @FXML
    private TableColumn<?, ?> nombre_producto_tabla;

    @FXML
    private TableColumn<?, ?> precioCompra_producto_tabla;

    @FXML
    private TableColumn<?, ?> stock_producto_maquina;

    

    @FXML
    void CambiarGestion(ActionEvent event) {
        try {
            App.setRoot("menugestion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CerrarApp(ActionEvent event) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void comprar(ActionEvent event) {

    }

    @FXML
    void enseñar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        /*try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM Productos",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
                rs = stmt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        */
        assert MenuGestion != null : "fx:id=\"MenuGestion\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert Mostrar_Productos != null : "fx:id=\"Mostrar_Productos\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert botoncomprar != null : "fx:id=\"botoncomprar\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert cerrar != null : "fx:id=\"cerrar\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert id_producto_tabla != null : "fx:id=\"id_producto_tabla\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert imgMaquina != null : "fx:id=\"imgMaquina\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert nombre_producto_tabla != null : "fx:id=\"nombre_producto_tabla\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert precioCompra_producto_tabla != null : "fx:id=\"precioCompra_producto_tabla\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
        assert stock_producto_maquina != null : "fx:id=\"stock_producto_maquina\" was not injected: check your FXML file 'menuexpendedora.fxml'.";
    }

}