package com.example;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


public class MenuController {

    Cliente usuario = new Cliente(App.Usuario.getDinero_ingresado(), App.Usuario.getDinero_gastado(), App.Usuario.getNIF(), App.Usuario.getClave());
    private ResultSet rs;
    private PreparedStatement stmt;
    private Connection con;
    private Producto productoelegido = new Producto(null, null, null, null);
    private Producto selectedProducto = new Producto(null, null, null, null);

    @FXML
    private TextField dineroingresado;

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

        usuario.setDinero_ingresado(usuario.getDinero_ingresado()+Integer.parseInt(dineroingresado.getText()) );
        if (selectedProducto == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hay producto seleccionado");
            alert.setContentText("Por favor, selecciona un producto antes de comprar.");
            alert.showAndWait();
            return;
        }

        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText(productoelegido.getNombre());
        confirmAlert.setContentText("¿Estás seguro de realizar esta compra?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try (PreparedStatement stmt1 = con.prepareStatement("UPDATE Productos SET stock = stock - 1 WHERE id_producto = ?")) {
                stmt1.setInt(1, productoelegido.getId());
                int rowsAffected1 = stmt1.executeUpdate();

                if (rowsAffected1 > 0) {
                    try (PreparedStatement stmt2 = con.prepareStatement("UPDATE Cliente SET dinero_gastado = dinero_gastado + ? WHERE NIF = ?")) {
                        stmt2.setDouble(1, productoelegido.getPrecio_venta());
                        stmt2.setString(2, usuario.getNIF());
                        int rowsAffected2 = stmt2.executeUpdate();

                        if (rowsAffected2 > 0) {
                            Alert infoAlert = new Alert(AlertType.INFORMATION);
                            infoAlert.setTitle("Compra realizada");
                            infoAlert.setHeaderText(null);
                            infoAlert.setContentText("La compra se ha realizado con éxito.");
                            infoAlert.showAndWait();
                        } else {
                            throw new SQLException("Error al actualizar el dinero gastado del cliente.");
                        }
                    }
                } else {
                    throw new SQLException("Error al actualizar el stock del producto.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Error en la compra");
                errorAlert.setContentText("Ocurrió un error al realizar la compra. Inténtalo de nuevo.");
                errorAlert.showAndWait();
            }
        }
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
