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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Club;
import model.Member;


/**
 * FXML Controller class
 *
 * @author picos
 */
public class MainController implements Initializable {

    @FXML
    private Button register;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private ImageView img;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
        Club club = Club.getInstance();
        
        

        }catch(Exception e){
            System.out.println("err" + e);
    }
    }    

    @FXML
    private void click(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
        FXMLLoader(getClass().getResource("/view/Register.fxml"));
        Parent root = miCargador.load();
        
        Scene scene = new Scene(root,400,500);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrar un nuevo usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(false);
        //la ventana se muestra modal
        stage.showAndWait();
    }

    @FXML
    private void autentication(ActionEvent event) {
        String name = username.getText();
        String pass = password.getText();
        try{
        Club club = Club.getInstance();
        if(club.existsLogin(name)){
            
                if(club.getMemberByCredentials(name, pass)== null){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Log in Failure");
                alert.setHeaderText("Information error");
                alert.setContentText("Incorrect password. Change the information and try again");
                alert.showAndWait();
            }else{
                FXMLLoader miCargador = new
                FXMLLoader(getClass().getResource("/view/UserPage.fxml"));
                Parent root = miCargador.load();
             
                UserPageController userpage = miCargador.getController();
                Member member = club.getMemberByCredentials(name, pass);
                System.out.println(member.getName());
                userpage.initMem(member);
                
                 Scene scene = new Scene(root);
                 Stage stage = (Stage) login.getScene().getWindow();   //new Stage();
                 stage.setScene(scene);
                 stage.setTitle("Main Overview Page");
                 //stage.initModality(Modality.APPLICATION_MODAL);
                 stage.resizableProperty().set(true);
                 //la ventana se muestra modal
                 stage.show();
            }
        }else{
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Log in Failure");
                alert.setHeaderText("Information error");
                alert.setContentText("Incorrect username. Change the information and try again");
                alert.showAndWait();
        }
        }catch(Exception e){
            System.err.println("Error" + e);
        }
        
        
        
    }

    @FXML
    /** When you press the enter key in the password field
     * you log in
     */
    private void checkForEnter(KeyEvent event) {
        if( event.getCode().equals( KeyCode.ENTER) )
            autentication( new ActionEvent() );
    }
    
}
