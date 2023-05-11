/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import model.Member;

/**
 * FXML Controller class
 *
 * @author picos
 */
public class RegisterController implements Initializable {

    private static void registryMember() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @FXML
    private TextField textName;
    @FXML
    private TextField textSurname;
    @FXML
    private TextField textPhone;
    @FXML
    private TextField textNickname;
    @FXML
    private Label errorNickname;
    @FXML
    private TextField textPassword;
    @FXML
    private Label errorPassword;
    @FXML
    private TextField textRepeatedPassword;
    @FXML
    private Label errorRepeatPassword;
    @FXML
    private TextField textCreditCard;
    @FXML
    private Label errorCreditCard;
    @FXML
    private TextField textSCV;
    @FXML
    private Label errorSCV;
    @FXML
    private Button registryButton;
    @FXML
    private Button cancelButton;
    
    
    //variable to check wether the values have to be passed or not
    private boolean isOk = false;
    private String name, surname, telephone, nickName, password, creditCard;
    private int svc;
    private Image img;
    private Member member;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registryButton.setOnAction(this::registry);
    }

    //public class that returns member to pass after pressing register
    public void getMember(){
        
    }

    @FXML
    private void registry(ActionEvent event) {
        
        //get values
        name = textName.getText();
        surname = textSurname.getText();
        telephone = textPhone.getText();
        nickName = textNickname.getText();
        password = textPassword.getText();
        creditCard = textCreditCard.getText();
        svc = Integer.parseInt(textSCV.getText());
        img = null;
        
        //test values
        //TO COMPLETE
        
        //create member
        //TO COMPLETE
        //member = new Member(name, surname, telephone,nickName, password, creditCard, svc, img); <--- Opcion más lógica, pero han decidido que el constructor sea privado
        //RegisterController.registryMember(); <-- en teoria deberia ser con este método
        
        
    }
    
    
    private static boolean check(){
        return false;
    }
    
    
    
    
    
}
