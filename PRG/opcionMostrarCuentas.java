import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    // Método para obtener las cuentas asociadas a un usuario mediante su NIE
    public static List<Cuenta> obtenerCuentasPorNIE(String NIE) throws SQLException {
        List<Cuenta> cuentas = new ArrayList<>();
        String query = "SELECT * FROM Cuenta WHERE NIE = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, NIE);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNIE(rs.getString("NIE"));
                cuenta.setDineroDisponible(rs.getDouble("dinero_disponible"));
                cuentas.add(cuenta);
            }
        }

        return cuentas;
    }

    // Método para llenar una ChoiceBox con las cuentas obtenidas
    public static void llenarChoiceBoxConCuentas(String NIE, ChoiceBox<Cuenta> choiceBox) {
        try {
            List<Cuenta> cuentas = obtenerCuentasPorNIE(NIE);
            ObservableList<Cuenta> cuentasObservable = FXCollections.observableArrayList(cuentas);
            choiceBox.setItems(cuentasObservable);
        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al obtener las cuentas del usuario: " + e.getMessage());
        }
    }

    // Método para mostrar una alerta
    private static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
