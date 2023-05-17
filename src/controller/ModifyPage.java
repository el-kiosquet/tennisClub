/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void registry(ActionEvent event) {
        
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
        
        // no checkins for name
        // check only if the field is not empty
        if(textName.getText().length() == 0){
            
            isOk = false;
            System.out.println("put name");
        }
        
        
        // no checkins for surname
        // check only if the field is not empty
        if(textSurname.getText().length() == 0){
            isOk = false;
            System.out.println("put surname");
        }
        
        // no checkins for surname
        // check only if the field is not empty
        if(textPhone.getText().length() == 0){
            isOk = false;
            System.out.println("put telephone");
        }
            
        // password checkins:
        // if it has letters and numbers
        // length > 6
        Matcher matcher = Pattern.compile("[0-9]+[a-z]|[a-z]+[0-9]", Pattern.CASE_INSENSITIVE).matcher(textPassword.getText());
        if(textPassword.getText().length() <= 6){
            isOk = false;
            System.out.println("password to short");
        }
        else if(!matcher.find()){
            isOk = false;
            System.out.println("password must have letters and numbers");
        }
        
        
        //check repeated password
        if(!textRepeatedPassword.getText().equals(textPassword.getText())){
            System.out.println("passwords don't coincide");
        }
        
        // 16 numbers
        // only numbers
        matcher = Pattern.compile("[^0-9]").matcher(textCreditCard.getText());
        if(textCreditCard.getText().length() != 16){
            isOk = false;
            System.out.println("credit card must contain 16 numbers");
        }
        else if(matcher.find()){
            isOk = false;
            System.out.println("credit card must contain only numbers");
        }
        
        
        return isOk;
    }

    @FXML
    private void cancelAct(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
