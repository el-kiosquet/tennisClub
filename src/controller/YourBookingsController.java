/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import Models.Utils;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 * FXML Controller class
 *
 * @author porta
 */
public class YourBookingsController implements Initializable {

    @FXML
    private ListView<Booking> listView;
    
    private ObservableList observable;
    private Member member;
    private LocalDateTime today = LocalDateTime.now();
    private LocalDate selectedDay;
    @FXML
    private Button cancelBooking;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(c-> new BookingListCell());
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                cancelBooking.setDisable(false);
            } else {
                cancelBooking.setDisable(true);
            }
        });
    } 
    void initMember(Member user){
        member=user;
        Club club;
        try {
            club = Club.getInstance();
            List<Booking> myBookings = club.getUserBookings(member.getNickName());
            System.out.println(myBookings.size());
            List<Booking> newBookings = new ArrayList();
            
            
            //if(book.compareTo(today.plusHours(24)) > 0){
            
            for(int i = 0; i<myBookings.size();i++){
                LocalDate date = myBookings.get(i).getMadeForDay();
                LocalDateTime book = date.atTime(myBookings.get(i).getFromTime());
                
                if(book.compareTo(today) > 0){
                        newBookings.add(myBookings.get(i));
                }
            }
            List<Booking> showList= new ArrayList();
            
            for(int i = 0; i<10 && !newBookings.isEmpty(); i++){
                Booking soon = newBookings.get(0);
                for(int j = 0; j<newBookings.size(); j++){
                    if(soon.getMadeForDay().compareTo(newBookings.get(j).getMadeForDay())>0){
                        if(soon.getFromTime().compareTo(newBookings.get(j).getFromTime())>0){
                        soon = newBookings.get(j);
                        
                        }
                    }
                }
                System.out.println("Tamaño: "+newBookings.size());
                showList.add(soon);
                int index = newBookings.indexOf(soon);
                newBookings.remove(index);
            }
            observable = FXCollections.observableList(showList);
            listView.setItems(observable);
        } catch (ClubDAOException ex) {
            Logger.getLogger(YourBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YourBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /** checks if the cancellation process can be performed (24h anticipation)
     *  if true: cancel book, return true
     *  otherwise: return false
     */
    boolean cancel(Booking booking) {
        return Utils.cancelBooking(booking, today);
    }

    @FXML
    private void deleteBooking(ActionEvent event) {
        Booking booking = listView.getFocusModel().getFocusedItem();
        /*
        if ( booking.getBookingDate().minus(24, ChronoUnit.HOURS).compareTo(ChronoLocalDateTime.from(LocalDateTime.now())) < 0 ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Unable to delete booking");
            alert.setHeaderText("You cant delete this booking");
            alert.setContentText("Your booking date if very close (less than 24 h)"
                    + " so you cant cancell it. \nSorry for the inconvenience");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete booking");
            alert.setHeaderText("Confirm you want to delete your booking");
            alert.setContentText("Booked " + booking.getCourt() + " for " + 
                    booking.getMadeForDay() + " at " + booking.getFromTime());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean done = false;
                try{
                    done = Club.getInstance().removeBooking( booking );
                } catch (Exception e) { }
                /* WIP
                if (done){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Unbooked succesfully");
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Unexpected Error.\nCouldnt Unbook");

                }
            }
            */
        
        
            // ALERTA
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete booking");
            alert.setHeaderText("Confirm you want to delete your booking");
            alert.setContentText("Booked " + booking.getCourt().getName().replace("Pista","Court") + " for " + 
                    booking.getMadeForDay() + " at " + booking.getFromTime());
            Optional<ButtonType> result = alert.showAndWait();
            //------------------------------------------------------------------
            
            
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if(Utils.cancelBooking(booking, today)){
                    // ALERTA - Unbooked succesfully
                    alert = new Alert(Alert.AlertType.INFORMATION, "Unbooked succesfully");
                    alert.show();
                    listView.getItems().remove(booking);
                    listView.refresh();
                }else{
                    //Alerta - error 24h
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Unable to delete booking");
                    alert.setHeaderText("You cant delete this booking");
                    alert.setContentText("Your booking date if very close (less than 24 h)"
                            + " so you cant cancell it. \nSorry for the inconvenience");
                    alert.showAndWait();
                }
            }
            
        }
        
    }
    


class BookingListCell extends ListCell<Booking>{

    @Override
    protected void updateItem(Booking t, boolean bln) {
        super.updateItem(t, bln); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        if(t==null || bln){
            setText(null);
        }else{
            setText("Booked  "+ t.getCourt().getName().replace("Pista","Court") +" for " +t.getMadeForDay() + " at " + t.getFromTime() 
                    + (t.getPaid() ? " paid" : " pending payment") );
        }
    }
    
   
    
}
