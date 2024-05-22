package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


public class MenuController {

    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private Producto productoelegido = new Producto(null, null, null, null);
    private Producto selectedProducto = new Producto(null, null, null, null);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button MenuGestion;

    @FXML
    private TableView<Producto> Mostrar_Productos;

    @FXML
    private Button botoncomprar;

    @FXML
    private Button cerrar;

    @FXML
    private TableColumn<Producto, Integer> id_producto_tabla;
    
    @FXML
    private ImageView imgMaquina;

    @FXML
    private TableColumn<Producto, String> nombre_producto_tabla;

    @FXML
    private TableColumn<Producto, Double> precioCompra_producto_tabla;

    @FXML
    private TableColumn<Producto, Integer> stock_producto_maquina;

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
            App.setRoot("accesoprimario");
        } catch (IOException e) {
            System.out.println("Ha habido un error cerrando la sesión");
            e.printStackTrace();
        }
    }

    @FXML
    void comprar(ActionEvent event) {

        Mostrar_Productos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectedProducto = newValue;
                productoelegido.setNombre(selectedProducto.getNombre()); 
                productoelegido.setId(selectedProducto.getId());
                productoelegido.setStock(selectedProducto.getStock());
                productoelegido.setPrecio_venta(selectedProducto.getPrecio_venta());
                
                try {
                    stmt = con.prepareStatement("UPDATE Productos\n" + "SET stock = stock-1\n" + "WHERE id_producto = ?;\n" + "");
                    stmt.setString(1, String.valueOf(productoelegido.getId()));
                    rs = stmt.executeQuery();

                    stmt = con.prepareStatement("UPDATE Cliente\n" + "SET dinero_gastado = dinero_gastado + ?\n" + "WHERE NIF = ?;\n" + "");
                    stmt.setString(1, String.valueOf(productoelegido.getPrecio_venta()));
                    stmt.setString(2, String.valueOf(productoelegido.getPrecio_venta()));
                    rs = stmt.executeQuery();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });


        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("¿Estás seguro de realizar esta compra?");
        confirmAlert.showAndWait();
    }

    @FXML
    void enseñar(ActionEvent event) {

    }

    public static ObservableList<Producto> getProductos() {
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        String query = "SELECT * FROM Productos";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                Integer stock = rs.getInt("stock");

                productos.add(new Producto(id, nombre, stock, precio_venta));
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
            java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM Productos",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
                rs = stmt.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        Mostrar_Productos.setItems(MenuController.getProductos());
        id_producto_tabla.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre_producto_tabla.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        precioCompra_producto_tabla.setCellValueFactory(new PropertyValueFactory<>("precio_venta"));
        stock_producto_maquina.setCellValueFactory(new PropertyValueFactory<>("stock"));

        Mostrar_Productos.setEditable(false);
        nombre_producto_tabla.setEditable(false);
        id_producto_tabla.setEditable(false);
        precioCompra_producto_tabla.setEditable(false);
        stock_producto_maquina.setEditable(false);
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
