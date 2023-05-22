/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.time.LocalDateTime;
import javafx.scene.layout.GridPane;

/**
 *
 * @author picos
 */
public class Utils {
    public static int columnCalc(GridPane grid, double x){
        int cellWidth = (int) grid.getWidth() / grid.getColumnCount();
        return (int)(x / cellWidth);
    }
    public static int rowCalc(GridPane grid, double y) {
        int cellHeight = (int) (grid.getHeight() / grid.getRowCount());
        return (int) (y / cellHeight);
    }
}
