package Mail;

import java.util.Date;


/**
 *
 * @author ANKIT
 */
public class MailApp {

    private static String senderUID;
    private static String senderPass;
            
    public MailApp(String email,String uid,String dbpass) {
        
        Date date=new Date();
        
        String to = email;
        String subject = "Change Password";
        String msg="<html><body><h2>Request for password Retrival for User <b><i><font color=\"blue\">"+uid+"</font></i></b> at <font color=\"red\">"+date
                +"</font><br>Password: <b><font color=\"blue\">"+dbpass+"</font></b></h2></body></html>";
        
        String message =  msg;
        
        senderUID = "id@gmail.com";
        senderPass = "password";
        
        SendMail.send(to,subject, message, senderUID, senderPass);
        
    }

    
}
