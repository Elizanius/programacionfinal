import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Posibilidades {
    
    private void insertServicioCliente(int idServicio, int codTrabajador, int numHabitacion, double importe) {
        String sql = "INSERT INTO servicio_cliente(id_servicio, cod_trabajador, num_habitacion, importe) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, idServicio);
            pstmt.setInt(2, codTrabajador);
            pstmt.setInt(3, numHabitacion);
            pstmt.setDouble(4, importe);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Reserva de Servicio");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label servicioLabel = new Label("Servicio:");
        GridPane.setConstraints(servicioLabel, 0, 0);
        ComboBox<Integer> servicioBox = new ComboBox<>();
        servicioBox.getItems().addAll(1, 2, 3, 4, 5, 6); // Estos valores deberían ser dinámicos.
        GridPane.setConstraints(servicioBox, 1, 0);

        Label trabajadorLabel = new Label("Trabajador:");
        GridPane.setConstraints(trabajadorLabel, 0, 1);
        ComboBox<Integer> trabajadorBox = new ComboBox<>();
        trabajadorBox.getItems().addAll(123, 321, 111, 101, 666, 911); // Estos valores deberían ser dinámicos.
        GridPane.setConstraints(trabajadorBox, 1, 1);

        Label habitacionLabel = new Label("Habitación:");
        GridPane.setConstraints(habitacionLabel, 0, 2);
        TextField habitacionField = new TextField();
        GridPane.setConstraints(habitacionField, 1, 2);

        Label importeLabel = new Label("Importe:");
        GridPane.setConstraints(importeLabel, 0, 3);
        TextField importeField = new TextField();
        GridPane.setConstraints(importeField, 1, 3);

        Button reservarButton = new Button("Reservar");
        GridPane.setConstraints(reservarButton, 1, 4);
        reservarButton.setOnAction(e -> {
            int idServicio = servicioBox.getValue();
            int codTrabajador = trabajadorBox.getValue();
            int numHabitacion = Integer.parseInt(habitacionField.getText());
            double importe = Double.parseDouble(importeField.getText());

            insertServicioCliente(idServicio, codTrabajador, numHabitacion, importe);
        });

        grid.getChildren().addAll(servicioLabel, servicioBox, trabajadorLabel, trabajadorBox, habitacionLabel, habitacionField, importeLabel, importeField, reservarButton);
    }
}
