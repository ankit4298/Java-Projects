package hrmanagement;

import java.sql.*;
import javax.swing.JOptionPane;

public class FetchValues {
    private static Connection con=null;
    private ResultSet rs;
    private PreparedStatement ps,ps1;
    private Statement stmt;
    
    private String email;
    private long phno;
    private String street,city,state,country;

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
    
    
    
    
    public static Connection getDBConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "System error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return con;
    }
    
    public void getOldValues(String eid){
        con=getDBConnection();
        try{
            stmt=con.createStatement();
            String sql="select email,phno,street,city,state,country from employees where eid like '"+eid+"'";
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                this.email=rs.getString("email");
                this.phno=rs.getLong("phno");
                this.street=rs.getString("street");
                this.city=rs.getString("city");
                this.state=rs.getString("state");
                this.country=rs.getString("country");
            }
            
        }catch(SQLException ex){
            System.out.println("SQL EXEC"+ex);
        }
    }
    
}
