/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.service;

import com.swcguild.flooringmastery.dao.PersistenceException;

/**
 *
 * @author zissah
 */
public class InvalidProductException extends Exception {

 public InvalidProductException(String message) throws PersistenceException {
        super(message);
    }
    public  InvalidProductException( String message, Throwable cause)throws PersistenceException {
        super(message, cause);
    }
    
}
