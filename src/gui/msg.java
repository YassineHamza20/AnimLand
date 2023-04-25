/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.MessagingException;

/**
 *
 * @author Ahmed
 */
public class msg {
    // Replace with your Twilio Account SID and Auth Token
    public static final String ACCOUNT_SID = "ACb8376ec3e140592ae50cac70e0ecf973";
    public static final String AUTH_TOKEN = "f51bd091db74d753b145553e57472d70";
    public static final String TWILIO_NUMBER = "+16074246351";

    public static void sendSMS(String recepient) throws MessagingException{

   
Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String messageText = "  Reclamation bien ajoutee !!!!!!!!!!!!  " + formatter.format(currentDate);
    Message message = Message.creator(new PhoneNumber(recepient),
            new PhoneNumber(TWILIO_NUMBER),
            messageText).create();
}
 
    
}