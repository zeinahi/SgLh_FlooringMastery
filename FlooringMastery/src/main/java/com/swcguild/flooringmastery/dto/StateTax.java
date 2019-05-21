/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author zissah
 */
public class StateTax {

    String stateName;
    BigDecimal stateTax;

    public StateTax(String state, BigDecimal stateTax) {
        this.stateName = state;
        this.stateTax = stateTax;
    }

    public String getState() {
        return stateName;
    }

    public void setStateName(String state) {
        this.stateName = state;
    }

    public BigDecimal getStateTax() {
        return stateTax;
    }

    public void setStateTax(BigDecimal stateTax) {
        this.stateTax = stateTax;
    }

}
