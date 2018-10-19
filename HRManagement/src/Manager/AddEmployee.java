package Manager;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class AddEmployee {
    private static Connection con=null;
    private ResultSet rs;
    private PreparedStatement ps,ps1;
    private Statement stmt;
    
    private ArrayList<String> designation_list=null;
    private ArrayList<String> level_list=null;
    private static String nextID;
    private static int nextID_int;
    
    public static Connection getDBConnection(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "System error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return con;
    }
    
    //get Designation from DB and give to GUI comboBox
    public ArrayList<String> getDesignation(){
        con=getDBConnection();
        designation_list=new ArrayList<>();
        try {
            stmt=con.createStatement();
            String sql="select des from designation_sal";
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                designation_list.add(rs.getString("des"));
            }
        } catch (SQLException ex) {
            System.out.print("SQL EXCEPTION");
        }
        return designation_list;
    }
    
    //get Levels from DB and give to GUI comboBox
    public ArrayList<String> getLevels(){
        con=getDBConnection();
        level_list=new ArrayList<>();
        try {
            stmt=con.createStatement();
            String sql="select level from levels";
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                level_list.add(rs.getString("level"));
            }
        } catch (SQLException ex) {
            System.out.print("SQL EXCEPTION");
        }
        return level_list;
    }
    
    public String getNextID(int choice_creation){
        String sql=null;
        int curr_id=0,next_id;
        String initial=null;
        
        if(choice_creation==0){//employee
            sql="select employee_eid from latest_id";
            initial="E";
        }
        else if(choice_creation==1){//manager
            sql="select manager_eid from latest_id";
            initial="M";
        }
        else if(choice_creation==2){//executive
            sql="select executive_eid from latest_id";
            initial="H";
        }
        
        con=getDBConnection();
        try{
            stmt=con.createStatement();
            rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                curr_id=rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.print("SQL EXCEPTION");
        }
        
        next_id=curr_id+1;
        nextID_int=next_id;
        nextID=initial+next_id;
        return nextID;
    }
    
    public int add_to_login(String empid,String pass,int choice_creation){
        String login_record=null;
        String latest_id=null;
        if(choice_creation==0){//employee
            //nextID_int is used to set new latest next ID
            login_record="insert into employee_login_details values (?,?)";
            latest_id="update latest_id set employee_eid=? where employee_eid=?";
        }
        else if(choice_creation==1){//manager
            login_record="insert into manager_login_details values (?,?)";
            latest_id="update latest_id set manager_eid=? where manager_eid=?";
        }
        else if(choice_creation==2){//executive
            login_record="insert into executive_login_details values (?,?)";
            latest_id="update latest_id set executive_eid=? where executive_eid=?";
        }
        
        try{
            con=getDBConnection();
            //insert to login records
            ps=con.prepareStatement(login_record);
            ps.setString(1, empid);
            ps.setString(2, pass);
            ps.executeUpdate();
            
            //update latest_id table
            ps1=con.prepareStatement(latest_id);
            ps1.setInt(1, nextID_int);
            ps1.setInt(2, nextID_int-1);
            ps1.executeUpdate();
            
        }catch(SQLException se){
            return 0;
        }
        return 1;
    }
    
    public int add_to_leaves(String empid){
        String leave_record="insert into leaves values(?,?,?,?)";
        
        try{
            con=getDBConnection();
            ps=con.prepareStatement(leave_record);
            ps.setString(1, empid);
            ps.setInt(2, 0);    //leaves taken
            ps.setInt(3, 14);    //paid leaves_rem
            ps.setInt(4, 0);    //unpaid leaves
            ps.executeUpdate();
            
        }catch(SQLException se){
            return 0;
        }
        
        return 1;
    }
    
    public int add_to_employees(String empid,String name,String email,long phno,String street,String city,String state,String country,String designation,int deptno,String mid,String pid,String level){
        
        GetSalary getsals=new GetSalary();
        double grossSal=getsals.calculateGrossSalary(designation, level);
        
        
        String emp_record="insert into employees values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        java.util.Date date=new java.util.Date();
        java.sql.Date curr_date=new java.sql.Date(date.getTime());
        
        try{
            con=getDBConnection();
            ps=con.prepareStatement(emp_record);
            ps.setString(1, empid);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setLong(4, phno);
            ps.setString(5, street);
            ps.setString(6, city);
            ps.setString(7, state);
            ps.setString(8, country);
            ps.setDate(9, curr_date);       //Date of joining
            ps.setString(10, designation);
            ps.setString(11, level);      //level
            ps.setDouble(12, grossSal);      //salary
            ps.setInt(13, deptno);
            ps.setString(14, mid);
            ps.setString(15, pid);
            ps.setString(16, null);       //project status
            
            ps.executeUpdate();
            
        }catch(SQLException se){
            return 0;
        }
        return 1;
    }
    
    public int add_to_salarySt(String empid,double des_sal,double gross_sal,String level){
        //eid,des,gross,taxed,net,net_annual
        double taxamt=0.0;
        String taxper_s=null;
        double taxper=0.0;
        double taxed_sal;
        double net_sal;
        double gross_annual_sal;
        
        if(level.equalsIgnoreCase("L1")){
            taxper=0.06;
            taxper_s="6%";
        }else if(level.equalsIgnoreCase("L2")){
            taxper=0.04;
            taxper_s="4%";
        }else if(level.equalsIgnoreCase("L3")){
            taxper=0.02;
            taxper_s="2%";
        }
        
        taxamt=gross_sal*taxper;
        taxed_sal=gross_sal-taxamt;
        //initially at time of creation, without any leaves
        net_sal=taxed_sal;
        gross_annual_sal=12*gross_sal;
        
        con=getDBConnection();
        String sql="insert into salary_structure values (?,?,?,?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setString(1, empid);
            ps.setDouble(2, des_sal);
            ps.setDouble(3, gross_sal);
            ps.setString(4, taxper_s);
            ps.setDouble(5, taxed_sal);
            ps.setDouble(6, net_sal);
            ps.setDouble(7, gross_annual_sal);
            ps.executeUpdate();
            
        }catch(SQLException ex){
            return 0;
        }
        
        
        return 1;
    }
    
    
    public int addEmployee(int choice_creation,String name,String email,long phno,String street,String city,String state,String country,String empid,String pass,String designation,int deptno,String mid,String pid,String level){
        
        con=getDBConnection();
        try {
            Savepoint s1=con.setSavepoint("before_insertion");
       
            int status_login=add_to_login(empid,pass,choice_creation);
            int status_leaves=add_to_leaves(empid);
            int status_employees=add_to_employees(empid, name, email, phno, street, city, state, country, designation, deptno, mid, pid,level);
            
            
            GetSalary getSal=new GetSalary();
            double des_sal=getSal.getDesignationSalary(designation);
            double gross_sal=getSal.calculateGrossSalary(designation, level);
            
            int status_salSt=add_to_salarySt(empid,des_sal,gross_sal,level);
            
            if(status_login==0 || status_employees==0 || status_leaves==0 || status_salSt==0){
                con.rollback(s1);
            }
            
            //autocommited here
        } catch (SQLException ex) {
            System.out.println("Error in addemp"+ex);
            return 0;
        }
        return 1;
    }
    
    
}
