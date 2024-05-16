package com.example;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SecondaryController {

    @FXML
    private TableColumn<?, ?> Id_Tabla_TopVentas;

    @FXML
    private TableColumn<?, ?> Nombre_Tabla_TopVentas;

    @FXML
    private TableColumn<?, ?> Stock_Tabla_TopVentas;

    @FXML
    private TableView<?> TablaTopVentas;

    @FXML
    private TableColumn<?, ?> Ventas_Tabla_TopVentas;

    @FXML
    private Button botonVolverMenuVentas;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonvolvermenu;

    @FXML
    private ComboBox<?> eleccionProducto;

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
    private ComboBox<?> seleccionProducto;

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