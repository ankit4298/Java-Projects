package hrmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class UpdateValues {
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
    

    public int updateEmp(String eid, String email, String phno, String street, String city, String state, String country) {
        con=getDBConnection();
        String sql="update employees set email=?,phno=?,street=?,city=?,state=?,country=? where eid=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setLong(2, Long.parseLong(phno));
            ps.setString(3, street);
            ps.setString(4, city);
            ps.setString(5, state);
            ps.setString(6, country);
            ps.setString(7, eid);
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("SQL EXEC"+ex);
            
            return 0;
        }
        return 1;
    }
}
