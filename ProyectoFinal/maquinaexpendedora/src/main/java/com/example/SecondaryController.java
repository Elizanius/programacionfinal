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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SecondaryController {
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonvolvermenu;

    @FXML
    private ComboBox<String> eleccionProducto;

    @FXML
    private TextField textGanancias;

    @FXML
    private TextField textID;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textPrecio_Compra;

    @FXML
    private TextField textPrecio_Venta;

    @FXML
    private TextField textStock;

    @FXML
    private TextField textVendidos;

    @FXML
    private Label GananciasEspecificas;

    @FXML
    private Label GananciasTotales;

    @FXML
    private Button botonVolverAlMenuGanancias;

    @FXML
    private ComboBox<String> seleccionProducto;

    


    private static List<Producto> obtenerProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM Productos";
        try { Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass");
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                Integer stock = rs.getInt("stock");

                productos.add(new Producto(id, nombre, precio_compra,precio_venta, stock));

            }
        
        }catch(Exception e){
            System.out.println("ERROR");
        }
        return productos;
    }

    @FXML
    void mostrarProductos(ActionEvent event) throws SQLException {
        List<Producto> productos = obtenerProductos();
        List<String> nomproductos = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            nomproductos.add(productos.get(i).getNombre());
        }
        ObservableList<String> cuentasObservable = FXCollections.observableArrayList(nomproductos);
        eleccionProducto.setItems(cuentasObservable);
        seleccionProducto.setItems(cuentasObservable);
        
    }

    @FXML
    void volvermenugestion(ActionEvent event) {
        try {
            App.setRoot("menugestion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private Button botonGestionProductos;

    @FXML
    private Button botonMostrarBeneficios;

    @FXML
    private Button botonMásVendidos;

    @FXML
    private Button botonvolver;

    @FXML
    void CambiarEscenaGestiónProductos(ActionEvent event) {
        try {
            App.setRoot("stockactual");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CambiarScenaBeneficios(ActionEvent event) {
        try {
            App.setRoot("beneficios");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void CambiarScenaProductosMásVendidos(ActionEvent event) {
        try {
            App.setRoot("topventas");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void volvermenu(ActionEvent event) {

        try {
            App.setRoot("menuexpendedora");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        
        assert botonGestionProductos != null : "fx:id=\"botonGestionProductos\" was not injected: check your FXML file 'menugestion.fxml'.";
        assert botonMostrarBeneficios != null : "fx:id=\"botonMostrarBeneficios\" was not injected: check your FXML file 'menugestion.fxml'.";
        assert botonMásVendidos != null : "fx:id=\"botonMásVendidos\" was not injected: check your FXML file 'menugestion.fxml'.";

    }

}