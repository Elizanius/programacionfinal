import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtNIF;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void login(ActionEvent event) {
        String nif = txtNIF.getText();
        String password = txtPassword.getText();

        if (authenticate(nif, password)) {
            showAlert(Alert.AlertType.INFORMATION, "Inicio de Sesión", "Inicio de sesión exitoso");
        } else {
            showAlert(Alert.AlertType.ERROR, "Inicio de Sesión", "NIF o contraseña incorrectos");
        }
    }

    private boolean authenticate(String nif, String password) {
        String url = "jdbc:mysql://localhost:3306/tu_base_de_datos";
        String user = "tu_usuario";
        String dbPassword = "tu_contraseña";
        String query = "SELECT * FROM Cliente WHERE nif = ? AND clave = ?";

        try (Connection con = DriverManager.getConnection(url, user, dbPassword);
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, nif);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
