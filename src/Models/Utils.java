/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import javafx.scene.layout.GridPane;

/**
 *
 * @author picos
 */
public class Utils {
    
    
    public static LocalTime toHour(int hour){
        return LocalTime.of(hour, 0);
    }
}
