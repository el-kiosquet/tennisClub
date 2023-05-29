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
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
    private Button logOut;
    private Club club;
    @FXML
    private DatePicker calendar;
    
    private LocalDateTime today = LocalDateTime.now();
    private LocalDate selectedDay = today.toLocalDate(); //by default, selected day == today
    
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
    @FXML
    private Button booked;
    @FXML
    private Button profile;
    @FXML
    private Label pista1label;
    @FXML
    private Label pista3label;
    @FXML
    private Label pista2label;
    @FXML
    private Label pista4label;
    @FXML
    private Label pista5label;
    @FXML
    private Label pista6label;
    @FXML
    private Label pista1name;
    @FXML
    private Label pista3name;
    @FXML
    private Label pista2name;
    @FXML
    private Label pista4name;
    @FXML
    private Label pista5name;
    @FXML
    private Label pista6name;
    
    //Label [] namePistas = {pista1name, pista2name, pista3name, pista4name, pista5name, pista6name};       
    //Button []buttons = {pista1, pista2, pista3, pista4, pista5, pista6};
    //ImageView []images = {pista1img,pista2img,pista3img,pista4img,pista5img,pista6img};
    //Label[] nickPistas = {pista1label,pista2label, pista3label, pista4label, pista5label, pista6label};
    @FXML
    private ImageView pista1userImg;
    @FXML
    private ImageView pista3userImg;
    @FXML
    private ImageView pista2userImg;
    @FXML
    private ImageView pista4userImg;
    @FXML
    private ImageView pista5userImg;
    @FXML
    private ImageView pista6userImg;
    
    private Label actual;
    
    //private Label[] labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){  
        String css = this.getClass().getResource("/Styles/style1.css").toExternalForm();
        refreshCourtImages();
        showPistasLabels(false);
        Label []labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
        calendar.setValue(today.toLocalDate());
        calendarInitializations();
        try {
            // TODO
            club = Club.getInstance();
            List<Booking> books = club.getForDayBookings(today.toLocalDate());
            
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
        img.setImage(member.getImage());
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
        stage.setTitle("Modify your data");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(false);
                 //la ventana se muestra modal
        stage.showAndWait();
        img.setImage(member.getImage()); //Refresh avatar in case changes where made
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException{
        FXMLLoader loader = new
        FXMLLoader(getClass().getResource("/view/Main.fxml"));
        Parent root = loader.load();
       
        Scene scene = new Scene(root, logOut.getScene().getWidth(), logOut.getScene().getHeight());
        Stage stage = (Stage) logOut.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Tennis Club");
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
        showPistasLabels(true);
        if(actual != null){actual.setStyle("-fx-text-fill:blue");}
            //System.out.println("-------");
        //System.out.println(event.getSource());
        //System.out.println(event.getTarget());
        //System.out.println("-------");
        
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
            actual=lb;
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

    @FXML
    private void courtSelected(ActionEvent event) {
        Button b = (Button) event.getSource();
        char num = b.getId().charAt(b.getId().length() - 1);
        String court = "Pista " + num;
        Booking booking = Utils.isAvailable(court, localHour, selectedDay);
        if(booking != null){
            if(booking.getMember().equals(member)){
                booking.getPaid();
                String paid = booking.getPaid() ? "yes" : "no";
                // cancel and more info about court
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("booking info");
                alert.setContentText("you have booked court " + num + 
                        "\n  paid: " + paid + 
                        "\n hour and day: " + booking.getMadeForDay().toString() 
                        + " : " + booking.getFromTime().toString()); 
                
                ButtonType cancel = new ButtonType("Cancel Booking");
                alert.getButtonTypes().add(cancel);
                Optional <ButtonType> op = alert.showAndWait();
                
                if(op.get().equals(cancel)){
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("");
                    //cancel booking
                    if(Utils.cancelBooking(booking, today)){ 
                    //cancelBooking() = true if booking cancelled
                        alert.setContentText("court cancelled");
                    }
                    else{
                        alert.setContentText("alert cannot be cancelled within 24 hours anticipation");
                    }
                    alert.show();
                    refreshGrid();
                    refreshCourtImages();
                }
            }
            else{
                // alert message, this court is already booked
            }
        }
        else bookCourt(court);
    }
    
    
    private boolean bookCourt(String courtName){
        try{
            club = Club.getInstance();
            Court court = club.getCourt(courtName);
            boolean repetido = false;
            boolean consecutivo = false;
            List<Booking> myBookings = club.getUserBookings(member.getNickName());
            List<LocalTime> consecutivas = new ArrayList();
            
            for(int i=0; i<myBookings.size();i++){
                Booking check = myBookings.get(i);
                if(selectedDay.compareTo(check.getMadeForDay())==0 && localHour.compareTo(check.getFromTime())==0){
                    repetido = true;
                    System.out.println("repe");
                }
                if(check.getCourt().equals(court)  &&  selectedDay.compareTo(check.getMadeForDay())==0){
                    consecutivas.add(check.getFromTime()); ////lista con todas las horas en las que tiene esta pista seleccionada
                }
            }
            boolean prev = false;
            boolean next = false;
            for(int i=0;i<consecutivas.size();i++){
                if(consecutivas.get(i).equals(localHour.plusHours(1))){
                    next=true;
                }else if(consecutivas.get(i).equals(localHour.minusHours(1))){
                    prev = true;
                }
            } 
            
            if(consecutivas.size()>=2){
                if(prev && next){
                    consecutivo=true;
                }else if(prev){
                    for(int i=0;i<consecutivas.size();i++){
                        if(consecutivas.get(i).equals(localHour.minusHours(2))){
                            consecutivo=true;
                        }
                    }
                }else if(next){
                    for(int i=0;i<consecutivas.size();i++){
                        if(consecutivas.get(i).equals(localHour.plusHours(2))){
                            consecutivo=true;
                        }
                    }
                }
            }
            //System.out.println("Today: " + today + "\n Made for DAY: " + selectedDay + 
            //        "\n From hour: " + localHour + "\n Court " + court.getName() + "\n member" + member);
            
            //if to check is court already booked
            if(isBooked(court.getName(), localHour) != null)
                return false;
            if(selectedDay.compareTo(today.toLocalDate())<0 && localHour.compareTo(today.toLocalTime())<0){         /// if para comprobar que no estás guardando en el pasado
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Exception dialog");
                alert.setHeaderText("You can't book this court");
                alert.setContentText("You are trying to book a court in the past, it isn't possible");
                alert.showAndWait();
            }else if(repetido){           ///if para saber si no has reservado otra pista a esa misma hora
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Exception dialog");
                alert.setHeaderText("You can't book this court");
                alert.setContentText("You have already booked a court a this hour, you can't book two courts at the same time");
                alert.showAndWait();
            }else if(consecutivo){         ///if para saber si has reservado más de dos horas consecutivas
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Exception dialog");
                alert.setHeaderText("You can't book this court");
                alert.setContentText("You have already booked this court two consecutive hours, you can't book the same court more than two consecutive hours");
                alert.showAndWait();
            }else{
            //alert for the user ro book the court
            boolean paid = false;
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Book Court");
            alert.setHeaderText("Confirm your booking");
            StringBuilder content = new StringBuilder();
            content.append("You selected " + courtName + " on "
                    + selectedDay + " at " + localHour + "\n\n");
                if ( member.checkHasCreditInfo() ) {
                    content.append("The booking will be automaticaly paid");
                    paid = true;
                } else {
                    content.append("ATENTION:\nNo credit card information found on "
                        + "your acount. \nYou will need to pay when you arrive "
                        + "at the club");
                    paid = false;
                }
                content.append("\n\n"+"Do you want to book this court?");
                alert.setContentText(content.toString());
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    //all the information to book a court
                    LocalDateTime now = LocalDateTime.now();
                    club.registerBooking(now ,selectedDay, localHour, paid, court, member);
                    refreshCourtImages();
                    refreshGrid();
                    Alert bookedCorrectly = new Alert(AlertType.INFORMATION);
                    bookedCorrectly.setHeaderText("");
                    bookedCorrectly.setTitle("Done!");
                    bookedCorrectly.setContentText("Booked court succesfully");
                    bookedCorrectly.show();
                }
            }

            }catch(Exception e){
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, e);
            }
        
            return true;
        
    }
    
    private Member isBooked(String court, LocalTime hour){
        try{
            club = Club.getInstance();
            List<Booking> bookings = club.getCourtBookings(court, selectedDay);
            for(Booking b : bookings){
                if(b.getFromTime().equals(hour)) return b.getMember();
            }
        }catch(Exception e){
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    
    private void refreshCourtImages(){
        Label[] nickPistas = {pista1label,pista2label, pista3label, pista4label, pista5label, pista6label};
        Button []buttons = {pista1, pista2, pista3, pista4, pista5, pista6};
        ImageView []images = {pista1img,pista2img,pista3img,pista4img,pista5img,pista6img};
        ImageView []imgUserPistas = {pista1userImg, pista2userImg, pista3userImg, pista4userImg, pista5userImg, pista6userImg};
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
                Member m = isBooked(court, localHour);
                if( m != null ){
                    if(m == member){
                        images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "BlueCourt.png"));
                        nickPistas[i - 1].setVisible(true);
                        nickPistas[i - 1].setText("yours");
                        imgUserPistas[i - 1].setVisible(true);
                        imgUserPistas[i - 1].setImage(m.getImage());
                    }
                    else{
                        images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "RedCourt.png")); 
                        nickPistas[i - 1].setVisible(true);
                        nickPistas[i - 1].setText(m.getNickName());
                        imgUserPistas[i - 1].setVisible(true);
                        imgUserPistas[i - 1].setImage(m.getImage());
                    }
                    
                }
                else{
                    images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "GreenCourt.png"));
                    nickPistas[i - 1].setVisible(false);
                    imgUserPistas[i - 1].setVisible(false);
                }
            
            }
        }
    }

    @FXML
    private void showMyBookings(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/view/YourBookings.fxml"));
        Parent root = miCargador.load();
        
        YourBookingsController modifypage = miCargador.getController();
        System.out.println(member.getName());
        modifypage.initMember(member);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Your bookings");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.resizableProperty().set(false);
                 //la ventana se muestra modal
        stage.showAndWait();
        refreshCourtImages();
        
    }
    
    private void showPistasLabels(boolean show){
        Label [] namePistas = {pista1name, pista2name, pista3name, pista4name, pista5name, pista6name}; 
        Label[] nickPistas = {pista1label,pista2label, pista3label, pista4label, pista5label, pista6label};
        ImageView []imgPistas = {pista1img, pista2img, pista3img, pista4img, pista5img, pista6img};
        try{
            Club club = Club.getInstance();
            List<Court> courts = club.getCourts();
            for(int i = 0; i < courts.size(); i++){
                imgPistas[i].setVisible(show);
                namePistas[i].setText(courts.get(i).getName());
                namePistas[i].setVisible(show);
                nickPistas[i].setVisible(show);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    // return null is the court is available, else return booking
    /*
    private Booking isAvailable(String court){
        try{
            club = Club.getInstance();
            List<Booking> list = club.getCourtBookings(court, selectedDay);
            for(Booking booking : list){
                if(booking.getFromTime().equals(localHour)) {
                    return booking;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    private boolean cancelBooking(Booking booking){
        LocalDate date = booking.getMadeForDay();
        LocalDateTime book = date.atTime(booking.getFromTime());
        if(book.compareTo(today.plusHours(24)) > 0){
            try{
            club = Club.getInstance();
            club.removeBooking(booking);
            return true;
            }catch(Exception e){System.err.println(e.getMessage());}               
        }
        return false;
    }
*/

}
