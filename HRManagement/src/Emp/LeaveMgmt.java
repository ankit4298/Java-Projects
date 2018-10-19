package Emp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LeaveMgmt {
    
    private String eid;
    private String ename;
    private String email;
    private long phno;
    private String street;
    private String city;
    private String state;
    private String country;

    public LeaveMgmt(String eid, String ename, String email, long phno, String street, String city, String state, String country) {
        this.eid = eid;
        this.ename = ename;
        this.email = email;
        this.phno = phno;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public LeaveMgmt() {
    }
    

    public void setEid(String eid) {
        this.eid = eid;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhno(long phno) {
        this.phno = phno;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEid() {
        return eid;
    }

    public String getEname() {
        return ename;
    }

    public String getEmail() {
        return email;
    }

    public long getPhno() {
        return phno;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }
    
    
    
    
    public void displayInfo(String eid){
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                Statement stmt=con.createStatement();
                String sql="select eid,ename,email,phno,street,city,state,country from employees where eid like '"+eid+"'";
                ResultSet rs=stmt.executeQuery(sql);


                while(rs.next()){
                     this.eid=rs.getString(1);
                     this.ename=rs.getString(2);
                     this.email=rs.getString(3);
                     this.phno=rs.getLong(4);
                     this.street=rs.getString(5);
                     this.city=rs.getString(6);
                     this.state=rs.getString(7);
                     this.country=rs.getString(8);
                }
                
             
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Something went wrong "+ex);
        }
    }
    
    public void UpdateInfo(int eid){
        
        
        
        
        
        try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
                PreparedStatement ps=null;
                
                
                
                
                
                
               
               
               
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Something went wrong "+ex);
        }
    }
    
    
    
}
