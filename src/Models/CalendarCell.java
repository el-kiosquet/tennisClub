/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDate;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

/**
 *
 * @author picos
 */
public class CalendarCell extends DateCell{
    
    public CalendarCell(){
        super();
    }    

   
    @Override
    public void updateItem(LocalDate date, boolean empty){
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();
        setDisable(empty || date.compareTo(today) < 0);
    }
}   
