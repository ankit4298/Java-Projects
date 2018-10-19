
package Emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeaveAppl {
    
    private int noOfDays;
    private String subj;
    private String msg;
    private String approval;
    //----------------------
    private int leaves_taken;
    private int paid_leaves;
    private int unpaid_leaves;

    public LeaveAppl(int noOfDays, String subj, String msg,String approval) {
        this.noOfDays = noOfDays;
        this.subj = subj;
        this.msg = msg;
        this.approval=approval;
    }

    public LeaveAppl() {
        super();
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getApproval() {
        return approval;
    }

    public int getNoOfDays() {
        return noOfDays;
    }

    public String getSubj() {
        return subj;
    }

    public String getMsg() {
        return msg;
    }

    public void setNoOfDays(int noOfDays) {
        this.noOfDays = noOfDays;
    }

    public void setSubj(String subj) {
        this.subj = subj;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    public int getLeaves_taken() {
        return leaves_taken;
    }

    public int getPaid_leaves() {
        return paid_leaves;
    }

    public int getUnpaid_leaves() {
        return unpaid_leaves;
    }

    public void setLeaves_taken(int leaves_taken) {
        this.leaves_taken = leaves_taken;
    }

    public void setPaid_leaves(int paid_leaves) {
        this.paid_leaves = paid_leaves;
    }

    public void setUnpaid_leaves(int unpaid_leaves) {
        this.unpaid_leaves = unpaid_leaves;
    }
    
    
    public void checkLeaves(String eid){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            Statement stmt=con.createStatement();
            PreparedStatement ps=null;
            
            String sql="select leaves_taken,paid_leaves_left,unpaid_leaves from leaves where eid like '"+eid+"'";
            ResultSet rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                this.leaves_taken=rs.getInt("leaves_taken");
                this.paid_leaves=rs.getInt("paid_leaves_left");
                this.unpaid_leaves=rs.getInt("unpaid_leaves");
            }	
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Something went wrong "+ex);
        }
    }
    
    
    public int applyLeave(String eid,String startDate,int noOfDays,String subj,String msg){
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	Date date = new Date();
        String curr_date=(String)dateFormat.format(date);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            PreparedStatement ps=null;
            
		String sql="insert into leave_applications values(?,?,?,?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setString(1, eid);
                ps.setInt(2, noOfDays);
                ps.setString(3, startDate);
                ps.setString(4, subj);
                ps.setString(5, msg);
                ps.setString(6, curr_date);
                ps.setString(7, "Pending");
                ps.executeUpdate();
                ps.close();
                //when hr approves leave, update leave table accordingly
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Something went wrong "+ex);
            return 0;
        }
       return 1;
    }
    
    
    
    public void checkLeaveStatus(String eid){
       try {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            Statement stmt=con.createStatement();
            String sql="select * from leave_applications where eid="+eid;
            ResultSet rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                this.noOfDays=rs.getInt("no_of_days");
                this.subj=rs.getString("leave_sub");
                this.msg=rs.getString("leave_content");
                this.approval=rs.getString("approval");
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("ERROR");
        }

    }
}
