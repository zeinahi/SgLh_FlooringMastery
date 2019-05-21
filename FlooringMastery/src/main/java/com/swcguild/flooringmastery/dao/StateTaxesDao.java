/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import com.swcguild.flooringmastery.dto.StateTax;
import java.util.List;

/**
 *
 * @author zissah
 */
public interface StateTaxesDao {
    
    
    List <StateTax> getStateTaxes();
    StateTax getState(String stateAbv);
   
}
