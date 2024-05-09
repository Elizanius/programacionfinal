import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.Alert;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    // Método para sacar dinero de una cuenta en la base de datos
    public static boolean sacarDineroDeCuenta(double cantidad, Cuenta cuenta) throws SQLException {
        // Verificar si hay suficiente dinero disponible en la cuenta
        if (cuenta.getDineroDisponible() >= cantidad) {
            cuenta.setDineroDisponible(cuenta.getDineroDisponible() - cantidad);

            // Actualizar el dinero disponible en la cuenta
            String query = "UPDATE Cuenta SET dinero_disponible = ? WHERE NIE = ?";
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setDouble(1, cuenta.getDineroDisponible());
                stmt.setString(2, cuenta.getNIE());
                stmt.executeUpdate();
                return true; // Indica que el retiro fue exitoso
            }
        } else {
            // Mostrar una alerta indicando que no hay suficiente dinero disponible
            mostrarAlerta("Fondos insuficientes", "No hay suficiente dinero disponible en la cuenta.");
            return false; // Indica que el retiro no fue exitoso
        }
    }

    // Método para mostrar una alerta
    private static void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
