package com.sehinc.common.service.spring;

import com.sehinc.erosioncontrol.db.project.EcProject;

import javax.mail.MessagingException;
import java.util.List;

public interface EmailService
{
    void sendEmail(List<String> recepients, String message, String subject, EcProject project)
        throws MessagingException;

    void sendEmailExceptionHandled(List<String> recepients, String message, String subject, EcProject project);
}
