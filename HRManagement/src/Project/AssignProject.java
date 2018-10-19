package Project;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class AssignProject {
    static Connection con=null;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public static Connection getDBConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "System error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return con;
    }
    
    
    public int insertIntoProject(String pid,String pname,String mid,String deadline){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	java.util.Date date = new java.util.Date();
        String curr_date=(String)dateFormat.format(date);
        
        String sql="insert into projects values (?,?,?,?,?)";
        try{
            con=getDBConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setString(2, pname);
            ps.setString(3, mid);
            ps.setString(4, curr_date);
            ps.setString(5, deadline);
            ps.executeUpdate();
            
        }catch(SQLException ex){
            return 0;
        }
        return 1;
    }
    
    //pleader is mid
    public int updateEmployees_projects(String pid,String mid,String e1,String e2,String e3,String e4){
        String sql="update employees set status_proj=? ,pid=?,manager_id=? where eid in(?,?,?,?)";
        
        try{
            con=getDBConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, "BUSY");
            ps.setString(2, pid);
            ps.setString(3, mid);
            ps.setString(4, e1);
            ps.setString(5, e2);
            ps.setString(6, e3);
            ps.setString(7, e4);
            ps.executeUpdate();
            
            
        }catch(SQLException ex){
            return 0;
        }
        return 1;
    }
    
}
