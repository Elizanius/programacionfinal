import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    // Método para modificar una factura en la base de datos y actualizar el dinero disponible en la cuenta
    public static void modificarFacturaYDineroDisponible(Factura factura, Cuenta cuenta) throws SQLException {
        // Restar el coste de la factura al dinero disponible en la cuenta
        cuenta.setDineroDisponible(cuenta.getDineroDisponible() - factura.getCoste());

        // Actualizar la factura en la base de datos
        String facturaQuery = "UPDATE Factura SET coste = ? WHERE NIE = ?";
        String cuentaQuery = "UPDATE Cuenta SET dinero_disponible = ? WHERE NIE = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement facturaStmt = conn.prepareStatement(facturaQuery);
             PreparedStatement cuentaStmt = conn.prepareStatement(cuentaQuery)) {
            conn.setAutoCommit(false);

            // Modificar la factura
            facturaStmt.setDouble(1, factura.getCoste());
            facturaStmt.setString(2, factura.getNIE());
            facturaStmt.executeUpdate();

            // Modificar el dinero disponible en la cuenta
            cuentaStmt.setDouble(1, cuenta.getDineroDisponible());
            cuentaStmt.setString(2, cuenta.getNIE());
            cuentaStmt.executeUpdate();

            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // En caso de error, hacer rollback para deshacer los cambios
            conn.rollback();
        }
    }
}
