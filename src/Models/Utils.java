/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import controller.UserPageController;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import model.Booking;
import model.Club;
import model.ClubDAOException;
import model.Member;

/**
 *
 * @author picos
 */
public class Utils {
    
    
    public static LocalTime toHour(int hour){
        return LocalTime.of(hour, 0);
    }
    
    public static Member isBooked(String court, LocalTime hour, LocalDate selectedDay){
        try{
            Club club = Club.getInstance();
            List<Booking> bookings = club.getCourtBookings(court, selectedDay);
            for(Booking b : bookings){
                if(b.getFromTime().equals(hour)) return b.getMember();
            }
        }catch(Exception e){
            Logger.getLogger(UserPageController.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public static void refreshCourtImages(Button []buttons, ImageView []images, LocalDate selectedDay, Member member, LocalTime localHour){
        //[]buttons = {pista1, pista2, pista3, pista4, pista5, pista6};
        //ImageView []images = {pista1img,pista2img,pista3img,pista4img,pista5img,pista6img};
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
                Member m = isBooked(court, localHour, selectedDay);
                if( m != null ){
                    if(m == member) images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "BlueCourt.png"));
                    else{
                        images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "RedCourt.png"));   
                        // set label with member
                    }
                    
                }
                else{
                    images[i - 1].setImage(new Image(File.separator + "img" + File.separator + "GreenCourt.png"));
                }
            
            }
        }
    }
    
    public static void refreshGrid(LocalDate selectedDay, Label []labels){
        try {
            Club club = Club.getInstance();
            List<Booking> books = club.getForDayBookings(selectedDay);
            //Label[]labels = {label9,label10,label11,label12,label13,label14,label15,label16,label17,label18,label19,label20,label21};
            
            for(int j = 0; j<labels.length;j++){
                int remain=6;
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
}
