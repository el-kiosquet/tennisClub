/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private ImageView imageDisplay;
    @FXML
    private Pagination imageSelector;

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
        
        //restore errorLabel values
        errorName.setText("");
        errorSurname.setText("");
        errorPhone.setText("");
        errorNickname.setText("");
        errorPassword.setText("");
        errorRepeatPassword.setText("");
        errorSCV.setText("");
        
        
        //get values
        name = textName.getText();
        surname = textSurname.getText();
        telephone = textPhone.getText();
        nickName = textNickname.getText();
        password = textPassword.getText();
        creditCard = textCreditCard.getText();
        img = null;
        
        try{
                club = Club.getInstance();
                
            }catch(IOException | ClubDAOException e){
                System.out.println(e.getMessage());
            }
        
        // if all correct, create member
        if(validUser()){
            try{
                Member newMember = club.registerMember(name, surname, telephone, nickName, password, creditCard, scv, img);
            }catch(ClubDAOException e){System.err.println(e.getMessage());}
            
            
            
            
        }
    }
    
    
    private boolean validUser(){
        isOk = true;
        
        // no checkins for name
        // check only if the field is not empty
        if(name.length() == 0){
            isOk = false;
            errorName.setText("put name");
        }
        
        // no checkins for surname
        // check only if the field is not empty
        if(surname.length() == 0){
            isOk = false;
            errorSurname.setText("put surname");
        }
        
        // no checkins for telephone number
        // check only if the field is not empty
        if(telephone.length() == 0){
            isOk = false;
            errorPhone.setText("put telephone");
        }
       
        //find whitespaces in nickName
        Matcher matcher = Pattern.compile("[ ]").matcher(nickName);
        //if white space find:
        if(matcher.find()){
            isOk = false;
            errorNickname.setText("nickName can't have spaces");
        }
        //if field is empty
        else if(nickName.length() == 0){ 
            isOk = false;
            errorNickname.setText("put nickName");
        }
        else{
            List<Member> listOfMembers = club.getMembers();
            for(int i = 0; i < listOfMembers.size(); i++){
                if(listOfMembers.get(i).getNickName().equals(nickName)){
                    errorNickname.setText("member already exists");
                    isOk = false;
                }
        }
        }
        
        
        // password checkins:
        // if it has letters and numbers
        // length > 6
        matcher = Pattern.compile("[0-9]+[a-z]|[a-z]+[0-9]", Pattern.CASE_INSENSITIVE).matcher(password);
        if(password.length() <= 6){
            isOk = false;
            errorPassword.setText("password to short");
        }
        else if(!matcher.find()){
            isOk = false;
            errorPassword.setText("password must have letters and numbers");
        }
        
        
        //check repeated password
        if(!textRepeatedPassword.getText().equals(password)){
            errorRepeatPassword.setText("passwords don't coincide");
        }
        
        // 16 numbers
        // only numbers
        matcher = Pattern.compile("[^0-9]").matcher(creditCard);
        if(creditCard.length() != 16){
            isOk = false;
            errorCreditCard.setText("credit card must contain 16 numbers");
        }
        else if(matcher.find()){
            isOk = false;
            errorCreditCard.setText("credit card must contain only numbers");
        }
        
        //check SCV
        try{
            scv = Integer.parseInt(textSCV.getText());
        }catch(NumberFormatException e){
            if(textSCV.getText().length() != 0){
                errorSCV.setText("scv must contain only 3 numbers");
            } 
        }
        if(textSCV.getText().length() != 3 && textSCV.getText().length() != 0){errorSCV.setText("scv must contain only 3 numbers");}
        
        
        return isOk;
    }

    
    
    
    
    
    
}
