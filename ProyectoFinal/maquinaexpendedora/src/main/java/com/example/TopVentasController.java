package com.example;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TopVentasController {

    private ResultSet rs;
    @FXML
    private TableColumn<Producto, Integer> Id_Tabla_TopVentas;

    @FXML
    private TableColumn<Producto, String> Nombre_Tabla_TopVentas;

    @FXML
    private TableColumn<Producto, Integer> Stock_Tabla_TopVentas;

    @FXML
    private TableView<Producto> TablaTopVentas;

    @FXML
    private TableColumn<Producto, Integer> Ventas_Tabla_TopVentas;

    @FXML
    private Button botonVolverMenuVentas;

    @FXML
    void volvermenugestion(ActionEvent event) {
        try {
            App.setRoot("menugestion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<Producto> getProductos() {
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        String query = "SELECT * FROM Productos ORDER BY stock ASC";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                Integer stock = rs.getInt("stock");

                productos.add(new Producto(id, nombre, precio_compra, precio_venta, stock));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }
    @FXML
    void initialize() {
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM Productos ORDER BY stock DESC",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
                rs = stmt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        TablaTopVentas.setItems(MenuController.getProductos());
        Id_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("id"));
        Nombre_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        Ventas_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        Stock_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("stock"));

        TablaTopVentas.setEditable(false);
        Nombre_Tabla_TopVentas.setEditable(false);
        Id_Tabla_TopVentas.setEditable(false);
        Ventas_Tabla_TopVentas.setEditable(false);
        Stock_Tabla_TopVentas.setEditable(false);

    }
}
