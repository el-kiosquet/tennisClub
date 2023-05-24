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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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
 * @author porta
 */
public class UserPageController implements Initializable {

    @FXML
    private ImageView img;
    //@FXML
    //private Button Booked;
    //@FXML
    //private Button Profile;
    @FXML
    private Text nick;
    
    private Member member;
    @FXML
    private Button booked;
    @FXML
    private Button profile;
    @FXML
    private Button logOut;
    private Club club;
    @FXML
    private DatePicker calendar;
    
    private LocalDate today = LocalDate.now();
    private LocalDate selectedDay = today; //by default, selected day == today
    
    public int remain = 6;
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
    private Label label15;
    @FXML
    private Label label14;
    @FXML
    private Label label13;
    @FXML
    private Label label21;
    @FXML
    private GridPane schedule;
    @FXML
    private ImageView pista2img;
    @FXML
    private ImageView pista3img;
    @FXML
    private ImageView pista4img;
    @FXML
    private ImageView pista5img;
    @FXML
    private ImageView pista6img;
    @FXML
    private ImageView pista1img;
    @FXML
    private Button pista2;
    @FXML
    private Button pista3;
    @FXML
    private Button pista4;
    @FXML
    private Button pista5;
    @FXML
    private Button pista6;
    @FXML
    private Button pista1;
    
    private LocalTime localHour;
    
    //private Label[] labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){  
        String css = this.getClass().getResource("/Styles/style1.css").toExternalForm();
        refreshCourtImages();
        Label[]labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
        calendar.setValue(today);
        calendarInitializations();
        try {
            // TODO
            Club club = Club.getInstance();
            List<Booking> books = club.getForDayBookings(today);
            
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
        //pruebas();
        
    }

    public void initMem(Member mem){
        member=mem;
        nick.setText(member.getNickName());
    }

    @FXML
    private void proffileData(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new
        FXMLLoader(getClass().getResource("/view/ModifyPage.fxml"));
        Parent root = miCargador.load();
        
        ModifyPage modifypage = miCargador.getController();
        Member user = member;
        System.out.println(member.getName());
        modifypage.initMember(user);
                
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Esta es tu ventana de usuario");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(false);
                 //la ventana se muestra modal
        stage.showAndWait();
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException{
        FXMLLoader loader = new
        FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent root = loader.load();
       
        Scene scene = new Scene(root);
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Esta es tu ventana de usuario");
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(true);
                 //la ventana se muestra modal
        stage.show();
    }
    
    private void pruebas() throws ClubDAOException, IOException{
        club = Club.getInstance();
        
        //club.registerBooking(LocalDateTime.now(), LocalDate.MAX, LocalTime.NOON, true, court , member);
        LocalDate lc = LocalDate.now().plusDays(4);
        LocalDate ld = LocalDate.of(2025, Month.APRIL, 30);
        
        System.out.println(LocalDateTime.now().toString() + " , " + ld.toString());
        }

    @FXML
    private void booking(ActionEvent event) {
        try {
            pruebas();
            /*
            List<Court> list = club.getCourts();
            for(Court c : list){
            System.out.println(c.getName());
            }
        */      } catch (ClubDAOException ex) {
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        Object source = event.getSource();
        EventTarget target = event.getTarget();
        
        //System.out.println(GridPane.getRowIndex(source) +" and " + GridPane.getColumnIndex(source));
        
        if(target.getClass().equals(label19.getClass())){
            Label lb = (Label) target;
            GridPane.getRowIndex(lb);
            int a = GridPane.getRowIndex(lb);
            localHour = Utils.toHour(a + 8);
            refreshGrid();
            refreshCourtImages();
            event.consume();
        }
        else if(source.getClass().equals(label19.getClass())){
            Label lb = (Label) source;
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

    @FXML
    private void courtSelected(ActionEvent event) {
        Button b = (Button) event.getSource();
        char num = b.getId().charAt(b.getId().length() - 1);
        String pista = "Pista " + num;
        System.out.println(pista);
        bookCourt(pista);
    }
    
    
    private boolean bookCourt(String courtName){
        try{
            club = Club.getInstance();
            Court court = club.getCourt(courtName);
            //System.out.println("Today: " + today + "\n Made for DAY: " + selectedDay + 
            //        "\n From hour: " + localHour + "\n Court " + court.getName() + "\n member" + member);
            
            //all the information to book a court
            
            //if to check is court already booked
            if(!isBooked(court.getName(), localHour)){
                LocalDateTime now = LocalDateTime.now();
                club.registerBooking(now ,selectedDay, localHour, true, court, member);
                refreshCourtImages();
                refreshGrid();
            
            //alert for the user ro book the court
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Book Court");
                alert.setHeaderText("Confirm your booking");
                alert.setContentText("Here go the details of the reservation");
                alert.showAndWait();

            }
        }catch(Exception e){
        Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return false;
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
}
