package Emp;

import java.sql.*;

public class NewMonthUpdation {
    static Connection con=null;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    
    public Connection getDBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");  
        } catch (ClassNotFoundException ex) {
            System.out.println("Class exec");
        } catch (SQLException ex) {
            System.out.println("SQL exec");
        }
        return con;
    }
    
    public void updateSalSt(){
        con=getDBConnection();
        String sql="update salary_structure set net_sal=taxed_sal";
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            
        }catch(SQLException ex){
            System.out.println("SQL Exception");
        }
    }
    
    
}
