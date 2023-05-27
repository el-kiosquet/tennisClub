/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class YourBookingscontr implements Initializable {

    @FXML
    private ListView<Booking> listView;
    
    private ObservableList observable;
    private Member member;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setCellFactory(c-> new BookingListCell());
        
    } 
    void initMember(Member user){
        member=user;
        Club club;
        try {
            club = Club.getInstance();
            List<Booking> myBookings = club.getUserBookings(member.getNickName());
            observable = FXCollections.observableList(myBookings);
            listView.setItems(observable);
        } catch (ClubDAOException ex) {
            Logger.getLogger(YourBookingscontr.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(YourBookingscontr.class.getName()).log(Level.SEVERE, null, ex);
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
            setText("Reservada la pista x el día " +t.getMadeForDay()  );
        }
    }
    
   
    
}
