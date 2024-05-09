import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class Controller implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            List<String> opciones = DatabaseHelper.obtenerOpciones();
            choiceBox.getItems().addAll(opciones);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
