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
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

public class StockActualController {

    private ResultSet rs;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonvolvermenu;

    @FXML
    private ComboBox<Producto> eleccionProducto;

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
        
        textID.setText(Integer.toString(eleccionProducto.getValue().getId()));
        textNombre.setText(eleccionProducto.getValue().getNombre());
        textPrecio_Compra.setText(Double.toString(eleccionProducto.getValue().getPrecio_compra()));
        textPrecio_Venta.setText(Double.toString(eleccionProducto.getValue().getPrecio_venta()));
        textStock.setText(Integer.toString(eleccionProducto.getValue().getStock()));
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
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM Productos",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
                rs = stmt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            
        assert botonvolvermenu != null : "fx:id=\"botonvolvermenu\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert eleccionProducto != null : "fx:id=\"eleccionProducto\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textGanancias != null : "fx:id=\"textGanancias\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textID != null : "fx:id=\"textID\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textNombre != null : "fx:id=\"textNombre\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textPrecio_Compra != null : "fx:id=\"textPrecio_Compra\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textPrecio_Venta != null : "fx:id=\"textPrecio_Venta\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textStock != null : "fx:id=\"textStock\" was not injected: check your FXML file 'stockactual.fxml'.";
        assert textVendidos != null : "fx:id=\"textVendidos\" was not injected: check your FXML file 'stockactual.fxml'.";

        List<Producto> listaproductos;
        try {
            listaproductos = obtenerProductos();
            ObservableList<Producto> productos2 = FXCollections.observableArrayList(listaproductos);
            eleccionProducto.setItems(productos2);

            eleccionProducto.setConverter(new StringConverter<Producto>() {
                @Override
                public String toString(Producto producto) {
                    return producto.getNombre(); // Mostrar solo el nombre de la persona
                }
            
                @Override
                public Producto fromString(String string) {
                    return null; 
                }
            });
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*use MaquinaExpendedora;
        Select * From Productos as P
        inner join Ventas on P.id = Ventas.idProducto
        order by Ventas.idVenta DESC*/
    }
}
