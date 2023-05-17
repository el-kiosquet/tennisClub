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
import javafx.scene.image.Image;
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
        
        // if all correct, create member
        if(validUser()){
            try{
                club = Club.getInstance();
                club.registerMember(name, surname, telephone, nickName, password, creditCard, scv, img);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }
    }
    
    
    private boolean validUser(){
        isOk = true;
        
        // no checkins for name
        // check only if the field is not empty
        if(name.length() == 0){
            
            isOk = false;
            System.out.println("put name");
        }
        
        
        // no checkins for surname
        // check only if the field is not empty
        if(surname.length() == 0){
            isOk = false;
            System.out.println("put surname");
        }
        
        // no checkins for surname
        // check only if the field is not empty
        if(telephone.length() == 0){
            isOk = false;
            System.out.println("put telephone");
        }
       
        
        //find whitespaces in nickName
        Matcher matcher = Pattern.compile("[ ]").matcher(nickName);
        //if white space find:
        if(matcher.find()){
            isOk = false;
            System.out.println("nickName can't have spaces");
        }
        //if field is empty
        else if(nickName.length() == 0){ 
            isOk = false;
            System.out.println("put nickName");
        }
        
        
        // password checkins:
        // if it has letters and numbers
        // length > 6
        matcher = Pattern.compile("[0-9]+[a-z]|[a-z]+[0-9]", Pattern.CASE_INSENSITIVE).matcher(password);
        if(password.length() <= 6){
            isOk = false;
            System.out.println("password to short");
        }
        else if(!matcher.find()){
            isOk = false;
            System.out.println("password must have letters and numbers");
        }
        
        
        //check repeated password
        if(!textRepeatedPassword.getText().equals(password)){
            System.out.println("passwords don't coincide");
        }
        
        // 16 numbers
        // only numbers
        matcher = Pattern.compile("[^0-9]").matcher(creditCard);
        if(creditCard.length() != 16){
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
