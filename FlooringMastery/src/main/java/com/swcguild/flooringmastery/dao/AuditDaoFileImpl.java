/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AuditDaoFileImpl implements AuditDao {

    private final static String FILENAME = "logger.txt";

    @Override
    public void writeAuditEntry(String entry) {
        try {
            PrintWriter out = new PrintWriter(new FileWriter("logger.txt", true));
            out.println(entry);
            out.flush();
            out.close();
        } catch (IOException ex) {
        }
    }

}
