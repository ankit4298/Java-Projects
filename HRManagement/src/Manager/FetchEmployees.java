package Manager;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class FetchEmployees {
    
    private static Connection con=null;
    private ResultSet rs;
    private PreparedStatement ps,ps1;
    private Statement stmt;
    
    String name,email,street,city,state,country,eid,designation,mid,pid;
    long phno;
    double salary;
    int deptno;
    Date doj;
    String dateOfJoining;
    
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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

    public String getEid() {
        return eid;
    }

    public String getDesignation() {
        return designation;
    }

    public long getPhno() {
        return phno;
    }

    public double getSalary() {
        return salary;
    }

    public int getDeptno() {
        return deptno;
    }

    public String getMid() {
        return mid;
    }

    public String getPid() {
        return pid;
    }

    public Date getDoj() {
        return doj;
    }

    public String getDateOfJoining() {
        return dateOfJoining;
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
    
    public void getEmployees(String eid){
        String sql="select * from employees where eid like '"+eid+"'";
        con=getDBConnection();
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                this.name=rs.getString("ename");
                this.email=rs.getString("email");
                this.phno=rs.getLong("phno");
                this.street=rs.getString("street");
                this.city=rs.getString("city");
                this.state=rs.getString("state");
                this.country=rs.getString("country");
                this.doj=rs.getDate("doj");
                this.designation=rs.getString("designation");
                this.salary=rs.getDouble("salary");
                this.deptno=rs.getInt("deptno");
                this.mid=rs.getString("manager_id");
                this.pid=rs.getString("pid");
                
            }
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
            this.dateOfJoining = dateFormat.format(doj);  
            
            
        }catch(SQLException ex){
            System.out.println("SQL EXEC"+ex);
        }
    }
    
    
    public int update(String empid,String name,String email,long phno,String street,String city,String state,String country,int deptno,String mid,String pid){
        con=getDBConnection();
        String sql="update employees set ename=?,email=?,phno=?,street=?,city=?,state=?,country=?,deptno=?,manager_id=?,pid=? where eid=?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setLong(3, phno);
            ps.setString(4, street);
            ps.setString(5, city);
            ps.setString(6, state);
            ps.setString(7, country);
            ps.setInt(8, deptno);
            ps.setString(9, mid);
            ps.setString(10, pid);
            ps.setString(11, empid);
            ps.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("SQL EXEC"+ex);
            
            return 0;
        }
        return 1;
    }
    
    
    
    
    
    
}
