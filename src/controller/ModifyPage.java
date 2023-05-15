/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author porta
 */
public class ModifyPage implements Initializable {

    @FXML
    private TextField textName;
    @FXML
    private TextField textSurname;
    @FXML
    private TextField textPhone;
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
    private Button cancelButton;
    @FXML
    private Label nick;
    
    private Member member;
    @FXML
    private Button modifyButton;

    private Club club;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    
    public void initMember(Member user){
        member=user;
        nick.setText(member.getNickName());
        textName.setText(member.getName());
        textSurname.setText(member.getSurname());
        textPassword.setText(member.getPassword());
        textRepeatedPassword.setText(member.getPassword());
        textPhone.setText(member.getTelephone());
        textCreditCard.setText(member.getCreditCard());
        textSCV.setText(""+member.getSvc());
        
        try {
            club = Club.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(ModifyPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modifyChanges(ActionEvent event) {
        
        
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
}
