/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

/**
 *
 * @author zissah
 */
public interface AuditDao {

    void writeAuditEntry(String entry) throws PersistenceException;

}
