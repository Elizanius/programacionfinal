package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class PrimaryController {

    @FXML
    private Label etiqueta1;

    @FXML
    private void switchToSecondary() throws IOException {
        etiqueta1.setTextFill(Color.ORANGE);
        //App.setRoot("secondary");
    }
}
