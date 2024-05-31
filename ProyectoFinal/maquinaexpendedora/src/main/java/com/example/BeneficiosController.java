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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class BeneficiosController {
    Cliente usuario = new Cliente(App.Usuario.getDinero_ingresado(), App.Usuario.getDinero_gastado(), App.Usuario.getNIF(), App.Usuario.getClave());
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private String productoseleccionado;

    static List<Producto> listaproductos = new ArrayList<>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label GananciasEspecificas;

    @FXML
    private Label GananciasTotales;

    @FXML
    private Button botonVolverAlMenuGanancias;

    @FXML
    private ComboBox<String> seleccionProducto;

    private static List<String> obtenerProductos() throws SQLException {
        List<String> listanombres = new ArrayList<>(); 
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

                listaproductos.add(new Producto(id, nombre, precio_compra,precio_venta, stock));
                listanombres.add(nombre);
            }
            
        }catch(Exception e){
            System.out.println("ERROR");
        }
        return listanombres;
    }


    @FXML
    void mostrarProductos(ActionEvent event) {
    
        productoseleccionado = seleccionProducto.getValue();

        for (int i = 0; i < listaproductos.size(); i++) {
            
            if (productoseleccionado = listaproductos.get(i).getNombre()) {
                
            }
        }
        try { 
            String query = "SELECT SUM(p.precio_venta) AS GananciasTotales " +
                           "FROM Ventas v " +
                           "JOIN Productos p ON v.idProducto = p.id" +
                           "INNER JOIN Productos where p.id = ?;";
            stmt = con.prepareStatement(query);
            stmt.setString(1, seleccionProducto.getValue());
            rs = stmt.executeQuery();

            if (rs.next()) {
                GananciasEspecificas.setText(rs.getString(1));
            }
            
        } catch (SQLException e) {
            System.out.println("Primer error");
            e.printStackTrace();
        }
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
    void initialize() {
        try {
               
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora", "root", "dbrootpass");
            String query = "SELECT SUM(p.precio_venta) AS GananciasTotales " +
                           "FROM Ventas v " +
                           "JOIN Productos p ON v.idProducto = p.id;";
            stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery();

            if (rs.next()) {
                GananciasTotales.setText(rs.getString("GananciasTotales"));
            }
            
        } catch (SQLException e) {
            System.out.println("Primer error");
            e.printStackTrace();
        }

        assert GananciasEspecificas != null : "fx:id=\"GananciasEspecificas\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert GananciasTotales != null : "fx:id=\"GananciasTotales\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert botonVolverAlMenuGanancias != null : "fx:id=\"botonVolverAlMenuGanancias\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert seleccionProducto != null : "fx:id=\"seleccionProducto\" was not injected: check your FXML file 'beneficios.fxml'.";


        
        try {
            List<String> nombres = obtenerProductos();
            ObservableList<String> productos2 = FXCollections.observableArrayList(nombres);
            seleccionProducto.setItems(productos2);

        } catch (SQLException e) {
        System.out.println("ERROR");        
        }
    }

}
