package Emp;

import Mail.MailApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Login() {
        super();
    }
    
    
    
    public int loginEmp(String uid_f,String pass_f){
        int flag=0;
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select * from employee_login_details where eid like '"+uid_f+"'";
                ResultSet rs=stmt.executeQuery(sql);
                this.username=uid_f;
                String dbpass=null;
                
                while(rs.next()){
//                     this.username=rs.getString("eid");
                     this.password=rs.getString("pass");
                     dbpass=this.password;
                }
                if(dbpass.equals(pass_f)){
                    flag= 1;
                }
                
                
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch(NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
        if(flag==1){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public int loginManager(String uid_f,String pass_f){
        int flag=0;
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select * from manager_login_details where eid like '"+uid_f+"'";
                ResultSet rs=stmt.executeQuery(sql);
                this.username=uid_f;
                String dbpass=null;
                
                while(rs.next()){
                     this.password=rs.getString("pass");
                     dbpass=this.password;
                }
                if(dbpass.equals(pass_f)){
                    flag= 1;
                }
                
                
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch(NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
        if(flag==1){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    public int loginExecutive(String uid_f,String pass_f){
        int flag=0;
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select * from executive_login_details where eid like '"+uid_f+"'";
                ResultSet rs=stmt.executeQuery(sql);
                this.username=uid_f;
                String dbpass=null;
                
                while(rs.next()){
//                     this.username=rs.getString("eid");
                     this.password=rs.getString("pass");
                     dbpass=this.password;
                }
                if(dbpass.equals(pass_f)){
                    flag= 1;
                }
                
                
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return 0;
        } catch(NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
        
        if(flag==1){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    
    public void ForgotPassEmp(String uid_f){
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select * from employee_login_details where eid like '"+uid_f+"'";
                ResultSet rs=stmt.executeQuery(sql);
                this.username=uid_f;
                
                String dbpass=null;
                while(rs.next()){
                     this.password=rs.getString("pass");
                     dbpass=this.password;
                }
                //Send Email
                if(dbpass==null){
                    throw new NullPointerException();
                }
                //send Email
                String email=getEmail(uid_f);
                MailApp mailapp=new MailApp(email, uid_f, dbpass);
                JOptionPane.showMessageDialog(null, "Password sent to your email id", "Password sent", JOptionPane.PLAIN_MESSAGE);
                
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        } catch(NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Incorrect Username", "Retrive Failed", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void ForgotPassExecutive(String uid_f){
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select * from executive_login_details where eid like '"+uid_f+"'";
                ResultSet rs=stmt.executeQuery(sql);
                this.username=uid_f;
                String dbpass=null;
                
                while(rs.next()){
                    this.password=rs.getString("pass");
                    dbpass=this.password;
                }
                if(dbpass==null){
                    throw new NullPointerException();
                }
                //send Email
                String email=getEmail(uid_f);
                MailApp mailapp=new MailApp(email, uid_f, dbpass);
                JOptionPane.showMessageDialog(null, "Password sent to your email id", "Password sent", JOptionPane.PLAIN_MESSAGE);
                
        } catch (ClassNotFoundException | SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Incorrect Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ne){
            JOptionPane.showMessageDialog(null, "Incorrect Username", "Retrive Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static String getEmail(String eid){
        String email=null;
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select email from employees where eid like '"+eid+"'";
                ResultSet rs=stmt.executeQuery(sql);
                
                
                
                while(rs.next()){
                    email=rs.getString("email");
                }
                if(email==null){
                    throw new NullPointerException();
                }
                //send Email
                
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error retriving email");
        } catch(NullPointerException np){
            System.out.println("Error retriving email - null ptr");
        }
        
        return email;
        
    }
    
    
}
