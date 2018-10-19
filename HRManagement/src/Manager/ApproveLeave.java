package Manager;

import java.sql.*;

public class ApproveLeave {
    //leaves table
    private int leaves_taken;
    private int paid_leaves_left;
    private int unpaid_leaves;

    //leaves applications table
    private int no_of_days;
    private String start_date;


    public int approveStatus(String eid,String ts){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            String sql="update leave_applications set approval='Approved' where eid=? and timestamp=? ";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, eid);
            ps.setString(2, ts);
            ps.executeUpdate();

        } catch (ClassNotFoundException ex) {
            System.out.println("Error in getting Class");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION"+ex);
            return 0;
        }

        return 1;
    }


    public int getLeaves(String eid){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            String sql="select * from leaves where eid like '"+eid+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            String empid=null;

            while(rs.next()){
                empid=rs.getString("eid");
                this.leaves_taken=rs.getInt("leaves_taken");
                this.paid_leaves_left=rs.getInt("paid_leaves_left");
                this.unpaid_leaves=rs.getInt("unpaid_leaves");
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error in getting Class");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION"+ex);
            return 0;
        }

        return 1;
    }

    public int getLeavesApplications(String eid){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            String sql="select eid,no_of_days,start_date from leave_applications where eid like '"+eid+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);

            String empid;

            while(rs.next()){
                empid=rs.getString("eid");
                this.no_of_days=rs.getInt("no_of_days");
                this.start_date=rs.getString("start_date");
            }


        }catch (ClassNotFoundException ex) {
            System.out.println("Error in getting Class");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL EXCEPTION"+ex);
            return 0;
        }
        return 1;
    }

    public int updateLeaveApproved(String eid,int noOfDays,int month){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            
            String updation="";
            
            String sql="insert into leaves_approved values(?,?,?)";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, eid);
            ps.setInt(2, noOfDays);
            ps.setInt(3, month);
            ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class exec");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL exec"+ex);
            return 0;
        }
        return 1;
    }
    
    public int updateLeaves(String eid,int leavesTaken,int paidLeavesLeft,int unpaidLeaves){
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            
            
            String sql="update leaves set leaves_taken=? , paid_leaves_left=? , unpaid_leaves=? where eid=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, leavesTaken);
            ps.setInt(2, paidLeavesLeft);
            ps.setInt(3, unpaidLeaves);
            ps.setString(4, eid);
            ps.executeUpdate();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Class exec");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL exec"+ex);
            return 0;
        }
        return 1;
    }
    
    public int calculations(String eid){
        //applications table values
        int noOfDays=this.no_of_days;
        String startDate=this.start_date;
        
        //leaves table
        int leavesTaken=this.leaves_taken;
        int paidLeavesLeft=this.paid_leaves_left;
        int unpaidLeaves=this.unpaid_leaves;
        
        System.out.println(leavesTaken+" "+paidLeavesLeft+" "+unpaidLeaves);
        
        if(paidLeavesLeft==0){
            unpaidLeaves+=noOfDays;
            leavesTaken+=noOfDays;
        }
        else if(paidLeavesLeft!=0){
            for(int i=noOfDays;i!=0;i--){
                if(paidLeavesLeft!=0)
                    paidLeavesLeft-=1;
                else if(paidLeavesLeft==0){
                    unpaidLeaves+=1;
                }
            }
            leavesTaken+=noOfDays;
        }
        
        System.out.println(leavesTaken+" "+paidLeavesLeft+" "+unpaidLeaves);
        
        
        int month=Integer.parseInt(startDate.substring(5, 7));  //to get month
        
//        System.out.println("eid "+eid);
//        System.out.println("No of days "+noOfDays);
//        System.out.println("Month= "+month);
        
        
        //updating to leaves table
        int status1=updateLeaves(eid, leavesTaken, paidLeavesLeft, unpaidLeaves);
        //inserting to leaves_approved table
        int status2=updateLeaveApproved(eid,noOfDays,month);
        //updating salStructure table for changing net sal
        int status3=1;
        if(unpaidLeaves!=0)
            status3=updateNetSal(eid,unpaidLeaves);
        
        
        
        if(status1==0 && status2==0 && status3==0)
            return 0;
        else
            return 1;
    }

    private int updateNetSal(String eid, int noOfDays) {
        
        double des_sal=getDesignationSal_SalSt(eid);
        double per_day_cut=des_sal/30;
        double cutAmt=per_day_cut*noOfDays;
        
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            
            String sql="update salary_structure set net_sal=net_sal-? where eid=?";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setDouble(1, cutAmt);
            ps.setString(2, eid);
            ps.executeUpdate();
            
            
        }catch (ClassNotFoundException ex) {
            System.out.println("Class exec");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL exec"+ex);
            return 0;
        }
        
        
        return 1;
    }
    
    public double getDesignationSal_SalSt(String eid){
        double des_sal=0.0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            
            String sql="select designation_sal from salary_structure where eid like '"+eid+"'";
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            
            while(rs.next()){
                des_sal=rs.getDouble("designation_sal");
            }
            
        }catch (ClassNotFoundException ex) {
            System.out.println("Class exec");
            return 0;
        } catch (SQLException ex) {
            System.out.println("SQL exec"+ex);
            return 0;
        }
        
        return des_sal;
    }
    
}
