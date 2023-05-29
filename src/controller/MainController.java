/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Models.CalendarCell;
import Models.Utils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Court;
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
    @FXML
    private DatePicker calendar;
    @FXML
    private GridPane schedule;
    @FXML
    private Label label9;
    @FXML
    private Label label12;
    @FXML
    private Label label11;
    @FXML
    private Label label10;
    @FXML
    private Label label20;
    @FXML
    private Label label19;
    @FXML
    private Label label18;
    @FXML
    private Label label17;
    @FXML
    private Label label16;
    @FXML
    private Label label14;
    @FXML
    private Label label13;
    @FXML
    private Label label21;
    @FXML
    private Label label15;
    @FXML
    private Button pista2;
    @FXML
    private ImageView pista2img;
    @FXML
    private Button pista3;
    @FXML
    private ImageView pista3img;
    @FXML
    private Button pista4;
    @FXML
    private ImageView pista4img;
    @FXML
    private Button pista5;
    @FXML
    private ImageView pista5img;
    @FXML
    private Button pista6;
    @FXML
    private ImageView pista6img;
    @FXML
    private Button pista1;
    @FXML
    private ImageView pista1img;
    
    //private Member member= null;
    //Label[]labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
    //Button []buttons = {pista1, pista2, pista3, pista4, pista5, pista6};
    //ImageView []images = {pista1img,pista2img,pista3img,pista4img,pista5img,pista6img};

   private LocalDate today = LocalDate.now();
   private LocalDate selectedDay = today; //by default, selected day == today
   private LocalTime localHour;
   private Club club;
   private int remain=6;
   private Label actual;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       calendar.setValue(today);
       calendar.setEditable(false);
       calendarInitializations();
       String css = this.getClass().getResource("/Styles/style1.css").toExternalForm();
       refreshCourtImages();
       refreshGrid();
        try{
        Club club = Club.getInstance();
        List<Member> list = club.getMembers();
        for(int i=0; i<list.size(); i++){
            System.out.println(list.get(i).getNickName() +" "+ list.get(i).getPassword());
        }
        

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
                
                 Scene scene = new Scene(root, login.getScene().getWidth(), login.getScene().getHeight());
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
        if( event.getCode().equals( KeyCode.ENTER) ){
            TextField t = (TextField) event.getSource();
            if(t.getId().equals("username")){
            password.requestFocus();
            }
            else autentication( new ActionEvent() );
        }
            
    }

    

 

    @FXML
    private void courtSelected(ActionEvent event) {
    }
    @FXML
    private void changeDay(ActionEvent event) {
        selectedDay = calendar.getValue();
        refreshGrid();
        refreshCourtImages();

    }
    
    private void calendarInitializations(){
        calendar.setDayCellFactory((picker) -> {return new CalendarCell();});
        calendar.showWeekNumbersProperty().setValue(false);
        
    }

    @FXML
    private void gridClicked(MouseEvent event) {        
        //System.out.println("-------");
        //System.out.println(event.getSource());
        //System.out.println(event.getTarget());
        //System.out.println("-------");
        if(actual != null){actual.setStyle("-fx-text-fill:blue");}
        Object source = event.getSource();
        EventTarget target = event.getTarget();
        
        //System.out.println(GridPane.getRowIndex(source) +" and " + GridPane.getColumnIndex(source));
        
        if(target.getClass().equals(label19.getClass())){
            Label lb = (Label) target;
            actual= lb;
            lb.setStyle("-fx-text-fill: purple");
            GridPane.getRowIndex(lb);
            int a = GridPane.getRowIndex(lb);
            localHour = Utils.toHour(a + 8);
            refreshGrid();
            refreshCourtImages();
            
            event.consume();
        }
        else if(source.getClass().equals(label19.getClass())){
            Label lb = (Label) source;
            actual= lb;
            lb.setStyle("-fx-text-fill: purple");
            int a = GridPane.getRowIndex(lb);
            localHour = Utils.toHour(a + 8);
            refreshGrid();
            refreshCourtImages();

            event.consume();
        }
        
        
        
        //event.consume();
    }
    
    private void showCourtsByHour(LocalTime hour){
        try {
            club = Club.getInstance();
            List<Booking> bookings = club.getForDayBookings(selectedDay);
            for(Booking b : bookings){
                if(b.getFromTime().equals(hour)){
                    Court c = b.getCourt();
                }
                        
            }
            
            
        } catch (Exception ex) {
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void refreshGrid(){
        try {
            club = Club.getInstance();
            List<Booking> books = club.getForDayBookings(selectedDay);
            Label[]labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
            
            
            for(int j = 0; j<labels.length;j++){
                remain=6;
                for(int i = 0; i<books.size();i++){
                    if(books.get(i).getFromTime().equals(LocalTime.of(9+j, 0))) {
                         remain--;
                    }
                }
                labels[j].setText("There are "+remain+" courts left");
            } 
            
            
        } catch (ClubDAOException ex) {
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        private void refreshCourtImages(){
        Button []buttons = {pista1, pista2, pista3, pista4, pista5, pista6};
        ImageView []images = {pista1img,pista2img,pista3img,pista4img,pista5img,pista6img};
        if(localHour == null){
            for(int i = 0; i < buttons.length; i++){
                buttons[i].setVisible(false);
                images[i].setVisible(false);
            }
        }
        else{
            for(int i = 0; i < buttons.length; i++){
                buttons[i].setVisible(true);
                images[i].setVisible(true);
            }
            for(int i = 1; i <= 6; i++){
                String court = "Pista " + i;
                if(isBooked(court, localHour)){
                    //   /img/
                    images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "RedCourt.png"));
                }
                else{
                    images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "GreenCourt.png"));
                }
            
            }
        }
    }
            private boolean isBooked(String court, LocalTime hour){
        try{
            club = Club.getInstance();
            List<Booking> bookings = club.getCourtBookings(court, selectedDay);
            for(Booking b : bookings){
                if(b.getFromTime().equals(hour)) return true;
            }
        }catch(Exception e){
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }
    
    
}
