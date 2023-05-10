        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



///test test 
package com.edu.project.service;
import com.edu.project.entities.user;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DBCnx.MyConnection;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.mail.*;
import java.util.Properties;
import java.util.Random;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rayen
 */
public class GeneralServices {
    private  Connection conn;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public GeneralServices() {
        conn = MyConnection.getInstance().getConx();
    }

  



//password encryption
    
  




// MOT DE PASSE OUBLIé  
   public String Token_Mdp_Oublie (String mail) // générer le token  
   {   Random rand = new Random();
       int token = rand.nextInt(99999);
     String req = "UPDATE user SET reset_token ='"+token+"'WHERE email ='"+mail+"'";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("token envoyé par mail");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return " <!doctype html>\n" +
"<html lang=\"en-US\">\n" +
"\n" +
"<head>\n" +
"    <meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
"    <title>Reset Password Email Template</title>\n" +
"    <meta name=\"description\" content=\"Reset Password Email Template.\">\n" +
"    <style type=\"text/css\">\n" +
"        a:hover {text-decoration: underline !important;}\n" +
"    </style>\n" +
"</head>\n" +
"\n" +
"<body marginheight=\"0\" topmargin=\"0\" marginwidth=\"0\" style=\"margin: 0px; background-color: #f2f3f8;\" leftmargin=\"0\">\n" +
"    <!--100% body table-->\n" +
"    <table cellspacing=\"0\" border=\"0\" cellpadding=\"0\" width=\"100%\" bgcolor=\"#f2f3f8\"\n" +
"        style=\"@import url(https://fonts.googleapis.com/css?family=Rubik:300,400,500,700|Open+Sans:300,400,600,700); font-family: 'Open Sans', sans-serif;\">\n" +
"        <tr>\n" +
"            <td>\n" +
"                <table style=\"background-color: #f2f3f8; max-width:670px;  margin:0 auto;\" width=\"100%\" border=\"0\"\n" +
"                    align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                           <h1> Verificaton code</h1>\n" +
"                          </a>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td>\n" +
"                            <table width=\"95%\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\"\n" +
"                                style=\"max-width:670px;background:#fff; border-radius:3px; text-align:center;-webkit-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);-moz-box-shadow:0 6px 18px 0 rgba(0,0,0,.06);box-shadow:0 6px 18px 0 rgba(0,0,0,.06);\">\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"padding:0 35px;\">\n" +
"                                        <h1 style=\"color:#1e1e2d; font-weight:500; margin:0;font-size:32px;font-family:'Rubik',sans-serif;\">You have\n" +
"                                            requested to reset your password</h1> <br><h1> "+token+" <br>\n " +
"                                        <span\n" +
"                                            style=\"display:inline-block; vertical-align:middle; margin:29px 0 26px; border-bottom:1px solid #cecece; width:100px;\"></span>\n" +
"                                        <p style=\"color:#455056; font-size:15px;line-height:24px; margin:0;\">\n" +
"                                            We cannot simply send you your old password. A unique Code to reset your\n" +
"                                            password has been generated for you. To reset your password, Copy the code.\n" +
"                                        </p>\n" +
"                                    </td>\n" +
"                                </tr>\n" +
"                                <tr>\n" +
"                                    <td style=\"height:40px;\">&nbsp;</td>\n" +
"                                </tr>\n" +
"                            </table>\n" +
"                        </td>\n" +
"                    <tr>\n" +
"                        <td style=\"height:20px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"text-align:center;\">\n" +
"                            <p style=\"font-size:14px; color:rgba(69, 80, 86, 0.7411764705882353); line-height:18px; margin:0 0 0;\">&copy; <strong>Verification Code</strong></p>\n" +
"                        </td>\n" +
"                    </tr>\n" +
"                    <tr>\n" +
"                        <td style=\"height:80px;\">&nbsp;</td>\n" +
"                    </tr>\n" +
"                </table>\n" +
"            </td>\n" +
"        </tr>\n" +
"    </table>\n" +
"    <!--/100% body table-->\n" +
"</body>\n" +
"\n" +
"</html> ";
    } 
   
   public  boolean VerifyToken(String mail,int token_user) // chercher le token
    {     int token ;
          String req="select reset_token from user WHERE email='"+mail+"'";   
          try {
              ste=conn.createStatement();
                rs= ste.executeQuery(req);
                rs.next();
            token= rs.getInt("reset_token");
            if(token==token_user)
                return true ;
          } catch (SQLException ex) {
              Logger.getLogger(GeneralServices.class.getName()).log(Level.SEVERE, null, ex);
          }
            return false;   
        }
         
           



// MAILING
           
    public static void sendMail(String recepient,String htmlCode) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String myAccountEmail = "nour.benrejeb@esprit.tn";
        //Your gmail password
        String password = "223AFT0730";
        

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient, htmlCode);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String htmlCode) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Verification Code");
            message.setContent(htmlCode,"text/html");
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(GeneralServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    
    
    
    
    

}
