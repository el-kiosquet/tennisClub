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
    
    public static boolean cancelBooking(Booking booking, LocalDateTime today){
        LocalDate date = booking.getMadeForDay();
        LocalDateTime book = date.atTime(booking.getFromTime());
        if(book.compareTo(today.plusHours(24)) > 0){
            try{
            Club club = Club.getInstance();
            club.removeBooking(booking);
            return true;
            }catch(Exception e){System.err.println(e.getMessage());}               
        }
        return false;
    }
    
    public static Booking isAvailable(String court, LocalTime localHour, LocalDate selectedDay){
        try{
            Club club = Club.getInstance();
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
}
