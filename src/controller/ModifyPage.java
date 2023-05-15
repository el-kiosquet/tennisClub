/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    @FXML
    private Label errorName;
    @FXML
    private Label errorSurname;
    @FXML
    private Label errorPhone;
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
        
        modifyButton.setOnAction(this::modifyChanges);
        
    }

    @FXML
    private void modifyChanges(ActionEvent event) {
        //restore errorLabel values
        errorName.setText("");
        errorSurname.setText("");
        errorPhone.setText("");
        errorNickname.setText("");
        errorPassword.setText("");
        errorRepeatPassword.setText("");
        errorSCV.setText("");

        
        if(validUser()){
            member.setName(textName.getText());
            member.setSurname(textSurname.getText());
            member.setTelephone(textPhone.getText());
            member.setPassword(textPassword.getText());
            member.setSvc(Integer.parseInt(textSCV.getText()));
            member.setCreditCard(textCreditCard.getText());   
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
    }
    
    private boolean validUser(){
        boolean isOk = true;
        
        // no checkins for name
        // check only if the field is not empty
        if(textName.getText().length() == 0){
            isOk = false;
            errorName.setText("put name");
        }
        
        // no checkins for surname
        // check only if the field is not empty
        if(textSurname.getText().length() == 0){
            isOk = false;
            errorSurname.setText("put surname");
        }
        
        // no checkins for telephone number
        // check only if the field is not empty
        if(textPhone.getText().length() == 0){
            isOk = false;
            errorPhone.setText("put telephone");
        }  
        
        
        
        // password checkins:
        // if it has letters and numbers
        // length > 6
        Matcher matcher = Pattern.compile("[0-9]+[a-z]|[a-z]+[0-9]", Pattern.CASE_INSENSITIVE).matcher(textPassword.getText());
        if(textPassword.getText().length() <= 6){
            isOk = false;
            errorPassword.setText("password to short");
        }
        else if(!matcher.find()){
            isOk = false;
            errorPassword.setText("password must have letters and numbers");
        }
        
        
        //check repeated password
        if(!textRepeatedPassword.getText().equals(textPassword.getText())){
            errorRepeatPassword.setText("passwords don't coincide");
        }
        
        // 16 numbers
        // only numbers
        matcher = Pattern.compile("[^0-9]").matcher(textCreditCard.getText());
        if(textCreditCard.getText().length() != 16){
            isOk = false;
            errorCreditCard.setText("credit card must contain 16 numbers");
        }
        else if(matcher.find()){
            isOk = false;
            errorCreditCard.setText("credit card must contain only numbers");
        }
        
        //check SCV
        try{
            int scv = Integer.parseInt(textSCV.getText());
        }catch(NumberFormatException e){
            if(textSCV.getText().length() != 0){
                errorSCV.setText("scv must contain only 3 numbers");
            } 
        }
        if(textSCV.getText().length() != 3 && textSCV.getText().length() != 0){errorSCV.setText("scv must contain only 3 numbers");}
        
        
        return isOk;
    }
}
