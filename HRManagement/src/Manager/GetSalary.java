package Manager;

import java.sql.*;
import javax.swing.JOptionPane;

public class GetSalary {
    
    private static Connection con=null;
    private ResultSet rs;
    private PreparedStatement ps,ps1;
    private Statement stmt;
    
    public static Connection getDBConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "System error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return con;
    }
    
    
    public double getDesignationSalary(String designation){
        double salary=0;
        
        con=getDBConnection();
        String sql="select sal from designation_sal where des like '"+designation+"'";
        
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                salary=rs.getDouble("sal");
            }       
        }catch(SQLException ex){
            return 0;
        }   
        return salary;   
    }
    
    
    public double getLevelSal(String level){
        double salary=0;
        
        con=getDBConnection();
        String sql="select sal from levels where level like '"+level+"'";
        
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                salary=rs.getDouble("sal");
            }       
        }catch(SQLException ex){
            return 0;
        }   
        return salary;   
    }
    
    
    public double calculateGrossSalary(String designation,String level){
        double desSal=getDesignationSalary(designation);
        double lvlSal=getLevelSal(level);
        
        double totalSal=desSal+lvlSal;
        
        return totalSal;
    }
    
}
