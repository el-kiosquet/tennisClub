/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Models.Validations;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ImageView avatarView;
    @FXML
    private ChoiceBox<String> avatarSelect;
    
    private String[] avatarUrl = new String[14]; //Contains the URLs of the images
    int selectedAvatar = 0;
    Image userAvatar = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        avatarSelect.setOnAction(this::avatarChange);
                
        // Load the avatarSelector options
        avatarSelect.getItems().add("Default");
        for ( int i = 1; i < 6; i++ ) {
            avatarSelect.getItems().add("Man " + i);
        }
        for ( int i = 1; i < 7; i++ ) {
            avatarSelect.getItems().add("Woman " + i);
        }
        avatarUrl[0] = "default";
        for(int i = 1; i < 6; i++) {
            avatarUrl[i] = "men" + (i);
        }
        for(int i = 6; i < 14; i++) {
            avatarUrl[i] = "woman" + (i-5);
        }
        for ( int i = 0; i < avatarUrl.length; i++ ) {
            avatarUrl[i] = File.separator+"img"+File.separator+avatarUrl[i]+".PNG";
        }
       
    }    

    @FXML
    private void registry(ActionEvent event) {
        boolean isValid = isValid();
        System.out.println(isValid);
        if (isValid) {
            member.setImage( userAvatar );
            member.setName(textName.getText());
            member.setSurname(textSurname.getText());
            member.setPassword(textPassword.getText());
            member.setTelephone(textPhone.getText());
            member.setCreditCard(textCreditCard.getText());
            if(textSCV.getText().equals("")){
                member.setSvc(0);
            }else{
                member.setSvc(Integer.parseInt(textSCV.getText()));
            }
            Alert changedCorrectly = new Alert(Alert.AlertType.INFORMATION);
           changedCorrectly.setHeaderText("Modification confirmation");
           changedCorrectly.setTitle("Done!");
           changedCorrectly.setContentText("Your data has been modified");
           changedCorrectly.show();
           Stage st = (Stage) modifyButton.getScene().getWindow();
           st.close();
            
        }
        
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
        if(member.getSvc()==0){  
            textSCV.setText("");
        }else{
            textSCV.setText(""+member.getSvc());

        }
        
        avatarView.setImage( member.getImage() );
        userAvatar = member.getImage();

        
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
    
    private void avatarChange(ActionEvent event) {
        selectedAvatar = avatarSelect.getSelectionModel().getSelectedIndex();
        try {
            avatarView.setImage(
                    new Image( avatarUrl[selectedAvatar]) );
            userAvatar = new Image ( avatarUrl[selectedAvatar] );
            
        } catch (Exception e) {e.printStackTrace();}
        
    }
}
