/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

/**
 *
 * @author zissah
 */
public class UserIOConsoleImpl implements UserIO {

     private static Scanner sc = new Scanner(System.in);// instatiated to be used by this class
     
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    @Override
    public double readDouble(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public int readInt(String prompt) {
        print(prompt);   
        String userInput = sc.nextLine();
        int result = 0;
        try {
            
          Integer.parseInt(userInput);
        } catch (NumberFormatException ex){
            System.out.println("Invalid input. Please enter a number"); 
            return readInt (prompt);
        }       
       return result; 
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        print(prompt);   
        String userInput = sc.nextLine();
        int result = 0;   
        try {
            result = Integer.parseInt(userInput);
       if (result >= min && result <= max) {
           return result;
//           System.out.println("Please choose between" + min + " and " + max);
//           result = readInt(prompt, min, max);
       }else{
           return readInt(prompt, min, max);
       }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input. Please enter a number");
        }
         return result;

    }

    @Override
    public long readLong(String prompt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String readString(String prompt) {
//        Scanner sc = new Scanner(System.in);
        print(prompt);
        return sc.nextLine();
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                System.out.println(prompt);
                String userInput = sc.nextLine();
                BigDecimal bd = new BigDecimal(userInput).setScale(2, RoundingMode.HALF_UP);//2 deci places, rounds up
                return bd;
            } catch (NumberFormatException e) {
                System.out.println("Please enter proper value");
            }
        }
    }

    @Override
    public BigDecimal readBigDecimal(BigDecimal area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

//    @Override
//    public LocalDate readLocalDate() {
//        String m = readString("Please enter the Month(MM)");
//        String d = readString("Please enter the Day(DD)");
//        String y = readString("Please enter the Year(YYYY)");
//        String LocalDate =(m+d+y);
////      return LocalDate.parse(check, DateTimeFormatter.("MM/DD/YYYY"));
//        return null;
//    }

 
 
