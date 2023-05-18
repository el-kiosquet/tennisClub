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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Club;
import model.ClubDAOException;
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
    private int scv;
    private Image img;
    private Member member;
    
    Club club;
    @FXML
    private Label errorName;
    @FXML
    private Label errorSurname;
    @FXML
    private Label errorPhone;
    @FXML
    private ImageView avatarView;
    @FXML
    private ChoiceBox<String> avatarSelect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
        try{
            scv = Integer.parseInt(textSCV.getText());
        }catch(NumberFormatException e){
            String string = textSCV.getText();
            if(string.length() != 0){errorSCV.setText("scv must contain only 3 numbers");
            System.out.println("scv must contain only 3 numbers"); 
            }
            
        }
        
        img = null;
        System.out.println(validUser());
        // if all correct, create member
        
        if(validUser()){
            try{
                //club = Club.getInstance();
                //club.registerMember(name, surname, telephone, nickName, password, creditCard, scv, img);
                Stage stage = (Stage) cancelButton.getScene().getWindow();
                stage.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
        
    }
    
    
    private boolean validUser(){
        isOk = true;
        Validations.resetErrorLabels();
        if(!Validations.noEmpty(name)){
            isOk = false;
            errorName.setText("put Name");
        }else{errorName.setText("");}
        
        if(!Validations.noEmpty(surname)){
            isOk = false;
            errorSurname.setText("put Surname");
        }else{errorSurname.setText("");}
        
        if(!Validations.noEmpty(telephone)){
            isOk = false;
            errorPhone.setText("Put telephone");
        }else{errorPhone.setText("");}
        
        isOk &= Validations.validateNickName(nickName);
        isOk &= Validations.validatePassword(password);
        isOk &= Validations.equalPasswords(password, textRepeatedPassword.getText());
        isOk &= Validations.validateCreditCard(creditCard);
        isOk &= Validations.validateScv(textSCV.getText());

        
        errorNickname.setText(Validations.errorNickname);
        errorPassword.setText(Validations.errorPassword);
        errorRepeatPassword.setText(Validations.errorRepeatedPassword);
        errorCreditCard.setText(Validations.errorCreditCard);
        errorSCV.setText(Validations.errorScv);
        
        //check if user exists
       
        return isOk;
        
    }

    @FXML
    private void cancelAct(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    
    
    
    
}
