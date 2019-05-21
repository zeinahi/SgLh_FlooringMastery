/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmastery.advice;

import com.swcguild.flooringmastery.dao.AuditDao;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author zissah
 */
public class LoggingAdvice {

    private AuditDao auditDao;

    public LoggingAdvice(AuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg; //add to audit entry
        }
        try {
            auditDao.writeAuditEntry(auditEntry);// write to file
        } catch (Exception e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
