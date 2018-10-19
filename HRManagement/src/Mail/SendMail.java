/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ANKIT
 */
public class SendMail {
    public static void send(String to, String sub,String msg, final String user, final String pass) 
    {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");	
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getDefaultInstance(props,new Authenticator() 
        {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(user, pass);
            }
        });

        try 
        {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html; charset=utf-8");

            Transport.send(message);
            
            System.out.println("Email Sended");
            
        } catch (MessagingException e) 
        {
            System.out.println("Fail to send Email");
            
//            throw new RuntimeException(e);
            JOptionPane.showMessageDialog(null, "Network connectivity error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
