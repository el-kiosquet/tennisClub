/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package view;

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Member;

/**
 * FXML Controller class
 *
 * @author porta
 */
public class UserPageController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Button Booked;
    @FXML
    private Button Profile;
    @FXML
    private Text nick;
    
    public Member member;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initMem(Member mem){
        member=mem;
        nick.setText(member.getNickName());
    }

    @FXML
    private void proffileData(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
        FXMLLoader(getClass().getResource("/view/ModifyPage.fxml"));
        Parent root = miCargador.load();
                
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Esta es tu ventana de usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(false);
                 //la ventana se muestra modal
        stage.showAndWait();
    }
    
}
