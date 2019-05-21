/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.StateTax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StateTaxesDaoFileImpl implements StateTaxesDao {

    Map<String, StateTax> allStateTax = new HashMap<>();
    
    public static final String STATETAX_FILE = "Taxes.txt";
    public static final String DELIMITER = ",";
    
    @Override
    public List<StateTax> getStateTaxes() {
         loadStates();
        return new ArrayList<>(allStateTax.values());
    }
    
    @Override
    public StateTax getState(String stateAbv) {
        return allStateTax.get(stateAbv);
        
    }
    
    private void loadStates() {
        Scanner sc;
        
        try {
            sc = new Scanner(new BufferedReader(new FileReader(STATETAX_FILE)));
            String currentLine;
            String[] currentTokens;
            while (sc.hasNextLine()) {
                currentLine = sc.nextLine();
                currentTokens = currentLine.split(DELIMITER);
                
                StateTax state = new StateTax(currentTokens[0], new BigDecimal(currentTokens[1]));
                
                allStateTax.put(state.getState(), state);
            }
            sc.close();
            
        } catch (FileNotFoundException ex) {
            System.out.println("Was not able to load State tax info in memory");
        }
        
    }
    

            
         
  



 
    
}
