/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Models.Validations;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
    @FXML
    private Label errorName;
    @FXML
    private Label errorSurname;
    @FXML
    private Label errorPhone;
    @FXML
    private Label errorNickname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void registry(ActionEvent event) {
        System.out.println(isValid());
        
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
        
    }
    
    private boolean isValid(){
        boolean isOk = true;
        Validations.resetErrorLabels();
        if(!Validations.noEmpty(textName.getText())){
            isOk = false;
            errorName.setText("put Name");
            System.out.println("ok");
        }
        if(!Validations.noEmpty(textSurname.getText())){
            isOk = false;
            errorSurname.setText("put Surname");
        }
        if(!Validations.noEmpty(textPhone.getText())){
            isOk = false;
            errorPhone.setText("Put telephone");
        }
        
        isOk &= Validations.validatePassword(textPassword.getText());
        isOk &= Validations.equalPasswords(textPassword.getText(), textRepeatedPassword.getText());
        isOk &= Validations.validateCreditCard(textCreditCard.getText(), textSCV.getText());
        //isOk &= Validations.validateScv(textSCV.getText());

        if(!isOk){
            errorPassword.setText(Validations.errorPassword);
            errorRepeatPassword.setText(Validations.errorRepeatedPassword);
            errorCreditCard.setText(Validations.errorCreditCard);
            errorSCV.setText(Validations.errorScv);
        }
        return isOk;
    }

    @FXML
    private void cancelAct(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
