/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author picos
 */
public class MainController implements Initializable {

    @FXML
    private Button register;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
        FXMLLoader(getClass().getResource("/view/Register.fxml"));
        Parent root = miCargador.load();
        
        Scene scene = new Scene(root,500,300);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrar un nuevo usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        //la ventana se muestra modal
        stage.showAndWait();
    }
    
}
