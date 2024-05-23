package com.example;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
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
    try {
        selectedProducto = Mostrar_Productos.getSelectionModel().getSelectedItem();
        if (selectedProducto == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No hay producto seleccionado");
            alert.setContentText("Por favor, selecciona un producto antes de comprar.");
            alert.showAndWait();
            return;
        }
        
        String dineroIngresadoText = dineroingresado.getText();
        double dineroIngresadoExistente = usuario.getDinero_ingresado();
        if (dineroIngresadoText == null || dineroIngresadoText.trim().isEmpty()) {
            if (dineroIngresadoExistente < selectedProducto.getPrecio_venta()) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Dinero insuficiente");
                errorAlert.setContentText("No hay suficiente dinero para comprar este producto.");
                errorAlert.showAndWait();
                return;
            }
        } else {
            double dineroIngresado;
            try {
                dineroIngresado = Double.parseDouble(dineroIngresadoText);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Cantidad de dinero inválida");
                alert.setContentText("Por favor, ingresa una cantidad válida de dinero.");
                alert.showAndWait();
                return;
            }
            if (dineroIngresado + dineroIngresadoExistente < selectedProducto.getPrecio_venta()) {
                Alert errorAlert = new Alert(AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText("Dinero insuficiente");
                errorAlert.setContentText("El dinero ingresado es insuficiente para comprar este producto.");
                errorAlert.showAndWait();
                return;
            }
        }

        // Guardar el dinero ingresado en la tabla Cliente
        try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Cliente SET dinero_ingresado = ? WHERE NIF = ?");
            stmt.setDouble(1, usuario.getDinero_ingresado());
            stmt.setString(2, usuario.getNIF());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected <= 0) {
                throw new SQLException("Error al actualizar el dinero ingresado del cliente.");
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error SQL");
            alert.setContentText("Ocurrió un error al actualizar el dinero ingresado del cliente: " + ex.getMessage());
            alert.showAndWait();
            return;
        }
        

        double diferencia = (dineroIngresadoText == null || dineroIngresadoText.trim().isEmpty()) ? 
        dineroIngresadoExistente - selectedProducto.getPrecio_venta() : 
        Double.parseDouble(dineroIngresadoText) - selectedProducto.getPrecio_venta();
        usuario.setDinero_gastado(usuario.getDinero_gastado() + selectedProducto.getPrecio_venta());
        if (diferencia > 0) {
            usuario.setDinero_ingresado(diferencia);
        } else {
            usuario.setDinero_ingresado(usuario.getDinero_ingresado());
        }

        // Actualizar el stock del producto
        selectedProducto.setStock(selectedProducto.getStock() - 1);
        PreparedStatement stmt1 = con.prepareStatement("UPDATE Productos SET stock = ? WHERE id = ?");
        stmt1.setInt(1, selectedProducto.getStock());
        stmt1.setInt(2, selectedProducto.getId());
        int rowsAffected1 = stmt1.executeUpdate();

        if (rowsAffected1 <= 0) {
            throw new SQLException("Error al actualizar el stock del producto.");
        }

        // Confirmar la compra
        Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirmación");
        confirmAlert.setHeaderText(selectedProducto.getNombre());
        confirmAlert.setContentText("¿Estás seguro de realizar esta compra?");
        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Alert infoAlert2 = new Alert(AlertType.INFORMATION);
            infoAlert2.setTitle("Gracias");
            infoAlert2.setContentText("¡Gracias por tu compra!");
            infoAlert2.showAndWait();
        }
        
        ObservableList<Producto> listaProductos = Mostrar_Productos.getItems();
        for (Producto producto : listaProductos) {
            if (producto.getId() == selectedProducto.getId()) {
                producto.setStock(selectedProducto.getStock());
                break;
            }
        }
        

        // Refrescar la TableView para que se muestren los cambios
        Mostrar_Productos.refresh();

        PreparedStatement stmt = con.prepareStatement("UPDATE Cliente SET dinero_gastado = ? WHERE NIF = ?");
        stmt.setDouble(1, usuario.getDinero_gastado());
        stmt.setString(2, usuario.getNIF());

    } catch (Exception e) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error inesperado");
        alert.setContentText("Ocurrió un error inesperado: " + e.getMessage());
        alert.showAndWait();
    }
}
    
    

    @FXML
    void enseñar(ActionEvent event) {

    }

    public static ObservableList<Producto> getProductos() {
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        String query = "SELECT * FROM Productos";

        try {
             Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double precio_compra = rs.getDouble("precio_compra");
                double precio_venta = rs.getDouble("precio_venta");
                Integer stock = rs.getInt("stock");

                productos.add(new Producto(id, nombre, stock, precio_venta));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return productos;
    }

    @FXML
    void initialize() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/MaquinaExpendedora","root", "dbrootpass" );
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
