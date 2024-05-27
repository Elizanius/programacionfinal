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
import javafx.util.StringConverter;

public class BeneficiosController {
    private PreparedStatement stmt;
    private ResultSet rs;

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
            System.out.println("Productos cargados");
        
        }catch(Exception e){
            System.out.println("ERROR");
        }
        return listanombres;
    }


    @FXML
    void mostrarProductos(ActionEvent event) {

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
            stmt = con.prepareStatement("SELECT * FROM Productos",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            rs = stmt.executeQuery();
            System.out.println("Cargando beneficios");
        } catch (SQLException e) {
            System.out.println("Primer error");
        }

        assert GananciasEspecificas != null : "fx:id=\"GananciasEspecificas\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert GananciasTotales != null : "fx:id=\"GananciasTotales\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert botonVolverAlMenuGanancias != null : "fx:id=\"botonVolverAlMenuGanancias\" was not injected: check your FXML file 'beneficios.fxml'.";
        assert seleccionProducto != null : "fx:id=\"seleccionProducto\" was not injected: check your FXML file 'beneficios.fxml'.";


        
        try {
            System.out.println("Obteniendo productos");
            List<String> nombres = obtenerProductos();
            ObservableList<String> productos2 = FXCollections.observableArrayList(nombres);
            seleccionProducto.setItems(productos2);

            /*seleccionProducto.setConverter(new StringConverter<Producto>() {
                @Override
                public String toString(Producto producto) {
                    return producto.getNombre(); // Mostrar solo el nombre del Producto
                }
            
                @Override
                public Producto fromString(String string) {
                    return null; 
                }
            });*/    
        } catch (SQLException e) {
        System.out.println("ERROR");        
        }
    }

}
