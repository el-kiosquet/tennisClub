/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author picos
 */
public class Validations {
    
    public static String errorName, errorSurname, errorPhone, errorCreditCard, errorNickname, errorScv, errorPassword, errorRepeatedPassword;
    
    
    public static boolean noEmpty(String str){
        return str.length() != 0;
    }
    
    public static boolean validatePassword(String passw){
        Matcher matcher = Pattern.compile("[0-9]+[a-z]|[a-z]+[0-9]", Pattern.CASE_INSENSITIVE).matcher(passw);
        if(passw.length() <= 6){
            errorPassword = "password to short";
            return false;
            
        }
        else if(!matcher.find()){
            errorPassword = "password must have letters and numbers";
            return false;
        }
        
        return true;
    }
    
    public static boolean equalPasswords(String pas1, String pas2){
        if(pas1.equals(pas2)){
            return true;
        }
        errorRepeatedPassword = "passwords don't coincide";
        return false;
    }
    
    public static boolean validateNickName(String nickName){
        Matcher matcher = Pattern.compile("[ ]").matcher(nickName);
        //if white space find:
        if(matcher.find()){
            errorNickname = "nickName can't have spaces";
            return false;
        }
        //if field is empty
        else if(nickName.length() == 0){ 
            errorNickname="put nickName";
            return false;
        }
        return true;
    }
    
    public static boolean validateCreditCard(String creditCard){
        Matcher matcher =  Pattern.compile("[^0-9]").matcher(creditCard);
        if(creditCard.length() != 16){
            errorCreditCard  = "credit card must contain 16 numbers";
            return false;
        }
        else if(matcher.find()){
            errorCreditCard = "credit card must contain only numbers";
            return false;
        }
        return true;
    }
    
    public static boolean validateScv(String scvTxt){
        try{
            Integer.parseInt(scvTxt);
        }catch(NumberFormatException e){
           
            if(scvTxt.length() != 0){
                errorScv = "scv must contain only 3 numbers";
                return false;
            }
            else return true;
            
        }
        if(scvTxt.length() != 0 || scvTxt.length() != 3){
            errorScv = "scv must contain only 3 numbers";
            return false;
        }
        return true;
    }
    
    public static void resetErrorLabels(){
        errorName = ""; errorSurname=""; errorPhone="";
        errorPassword=""; errorRepeatedPassword="";
        errorCreditCard=""; errorScv="";
        errorNickname="";
    }
    
}

