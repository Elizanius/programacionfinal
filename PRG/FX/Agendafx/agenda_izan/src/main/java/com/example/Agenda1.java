package com.example;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Agenda1 {

    private ResultSet rs;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label apell;

    @FXML
    private TextField apelltxt;

    @FXML
    private Button atras;

    @FXML
    private Label cargo;

    @FXML
    private TextField cargotxt;

    @FXML
    private Button fin;

    @FXML
    private Label id;

    @FXML
    private TextField idtxt;

    @FXML
    private Button ini;

    @FXML
    private Label nacimiento;

    @FXML
    private TextField nacimientotxt;

    @FXML
    private Label nom;

    @FXML
    private TextField nomtxt;

    @FXML
    private Button siguiente;

    @FXML
    private Label telf;

    @FXML
    private TextField telftxt;

    @FXML
    private Label titulo;

    @FXML
    void AtrasUno(ActionEvent event) throws SQLException {
        try {
            
            if (rs.isFirst()) {
                atras.setDisable(true);
            }else{
                rs.previous();
                atras.setDisable(false);
                siguiente.setDisable(false);
                idtxt.setText(Integer.toString(rs.getInt("IdEmpleado")));
                nomtxt.setText(rs.getString("Nombre"));
                apelltxt.setText(rs.getString("Apellidos"));
                telftxt.setText(Integer.toString(rs.getInt("Teléfono")));
                nacimientotxt.setText(rs.getString("Fecha_Nacimiento"));
                cargotxt.setText(rs.getString("Cargo")); 
            }
            
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        }
}

    @FXML
    void AvanzaUno(ActionEvent event) throws SQLException {
            
        try {
            
            if (rs.isLast()) {
                siguiente.setDisable(true);
            }else{
                rs.next();
                atras.setDisable(false);
                siguiente.setDisable(false);
                idtxt.setText(Integer.toString(rs.getInt("IdEmpleado")));
                nomtxt.setText(rs.getString("Nombre"));
                apelltxt.setText(rs.getString("Apellidos"));
                telftxt.setText(Integer.toString(rs.getInt("Teléfono")));
                nacimientotxt.setText(rs.getString("Fecha_Nacimiento"));
                cargotxt.setText(rs.getString("Cargo")); 
            }
                
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    @FXML
    void AvanzarFinal(ActionEvent event) throws SQLException {
            try {
                rs.last();
                atras.setDisable(false);
                siguiente.setDisable(true);
                idtxt.setText(Integer.toString(rs.getInt("IdEmpleado")));
                nomtxt.setText(rs.getString("Nombre"));
                apelltxt.setText(rs.getString("Apellidos"));
                telftxt.setText(Integer.toString(rs.getInt("Teléfono")));
                nacimientotxt.setText(rs.getString("Fecha_Nacimiento"));
                cargotxt.setText(rs.getString("Cargo"));
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             }
    }
    

    @FXML
    void VolverInicio(ActionEvent event) throws SQLException {
            
        try {
                rs.first();
                siguiente.setDisable(false);
                atras.setDisable(true);
                idtxt.setText(Integer.toString(rs.getInt("IdEmpleado")));
                nomtxt.setText(rs.getString("Nombre"));
                apelltxt.setText(rs.getString("Apellidos"));
                telftxt.setText(Integer.toString(rs.getInt("Teléfono")));
                nacimientotxt.setText(rs.getString("Fecha_Nacimiento"));
                cargotxt.setText(rs.getString("Cargo"));
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
             }
    
    }

    @FXML
    void initialize() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:33006/empleados","root", "dbrootpass" );
        try {
            
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM empleados",
            ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeQuery();
            rs = stmt.executeQuery();
            rs.first();
            idtxt.setText(Integer.toString(rs.getInt("IdEmpleado")));
            nomtxt.setText(rs.getString("Nombre"));
            apelltxt.setText(rs.getString("Apellidos"));
            telftxt.setText(Integer.toString(rs.getInt("Teléfono")));
            nacimientotxt.setText(rs.getString("Fecha_Nacimiento"));
            cargotxt.setText(rs.getString("Cargo"));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assert apell != null : "fx:id=\"apell\" was not injected: check your FXML file 'agenda.fxml'.";
        assert apelltxt != null : "fx:id=\"apelltxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert atras != null : "fx:id=\"atras\" was not injected: check your FXML file 'agenda.fxml'.";
        assert cargo != null : "fx:id=\"cargo\" was not injected: check your FXML file 'agenda.fxml'.";
        assert cargotxt != null : "fx:id=\"cargotxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert fin != null : "fx:id=\"fin\" was not injected: check your FXML file 'agenda.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'agenda.fxml'.";
        assert idtxt != null : "fx:id=\"idtxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert ini != null : "fx:id=\"ini\" was not injected: check your FXML file 'agenda.fxml'.";
        assert nacimiento != null : "fx:id=\"nacimiento\" was not injected: check your FXML file 'agenda.fxml'.";
        assert nacimientotxt != null : "fx:id=\"nacimientotxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert nom != null : "fx:id=\"nom\" was not injected: check your FXML file 'agenda.fxml'.";
        assert nomtxt != null : "fx:id=\"nomtxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert siguiente != null : "fx:id=\"siguiente\" was not injected: check your FXML file 'agenda.fxml'.";
        assert telf != null : "fx:id=\"telf\" was not injected: check your FXML file 'agenda.fxml'.";
        assert telftxt != null : "fx:id=\"telftxt\" was not injected: check your FXML file 'agenda.fxml'.";
        assert titulo != null : "fx:id=\"titulo\" was not injected: check your FXML file 'agenda.fxml'.";

    }

}

