package com.example;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TopVentasController {

    Cliente usuario = new Cliente(App.Usuario.getDinero_ingresado(), App.Usuario.getDinero_gastado(), App.Usuario.getNIF(), App.Usuario.getClave());
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;

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
        String query = "SELECT p.*, COALESCE(v.total_ventas, 0) AS total_ventas\n" + //
                        "FROM Productos p\n" + //
                        "LEFT JOIN (\n" + //
                        "    SELECT idProducto, COUNT(*) AS total_ventas\n" + //
                        "    FROM Ventas\n" + //
                        "    GROUP BY idProducto\n" + //
                        ") v ON p.id = v.idProducto\n" + //
                        "ORDER BY COALESCE(v.total_ventas, 0) DESC;\n" + //
                        "";

        try {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora", "root", "dbrootpass");  

        query = "SELECT p.*, COALESCE(v.total_ventas, 0) AS total_ventas " +
                "FROM Productos p " +
                "LEFT JOIN (SELECT idProducto, COUNT(*) AS total_ventas FROM Ventas GROUP BY idProducto) v " +
                "ON p.id = v.idProducto " +
                "ORDER BY COALESCE(v.total_ventas, 0) DESC";   

        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
          Producto producto = new Producto();
          producto.setId(rs.getInt("id"));
          producto.setNombre(rs.getString("nombre"));
          producto.setStock(rs.getInt("stock"));
          producto.setVentas(rs.getInt("total_ventas")); 
          productos.add(producto);
        }
        rs.close();
        stmt.close();
        con.close();

           } catch (SQLException e) {
              System.out.println("Error al obtener los productos y sus ventas asociadas");
              e.printStackTrace();
           }

        return productos;
    }
    @FXML
    void initialize() {
        
        ObservableList<Producto> productos = FXCollections.observableArrayList();

   try {
        
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora", "root", "dbrootpass");  

        String query = "SELECT p.*, COALESCE(v.total_ventas, 0) AS total_ventas " +
                     "FROM Productos p " +
                     "LEFT JOIN (SELECT idProducto, COUNT(*) AS total_ventas FROM Ventas GROUP BY idProducto) v " +
                     "ON p.id = v.idProducto " +
                     "ORDER BY COALESCE(v.total_ventas, 0) DESC";   
        
        PreparedStatement stmt = con.prepareStatement(query);
        
   } catch (SQLException e) {
      System.out.println("Error al obtener los productos y sus ventas asociadas");
      e.printStackTrace();
   }


    TablaTopVentas.setItems(MenuController.getProductos());
    Id_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("id"));
    Nombre_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    Stock_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("stock"));
    Ventas_Tabla_TopVentas.setCellValueFactory(new PropertyValueFactory<>("total_ventas"));

    TablaTopVentas.setEditable(false);
    Nombre_Tabla_TopVentas.setEditable(false);
    Id_Tabla_TopVentas.setEditable(false);
    Ventas_Tabla_TopVentas.setEditable(false);
    Stock_Tabla_TopVentas.setEditable(false);
            
        

    }
}
