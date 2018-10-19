package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DisapproveLeave {

    public int disapproveStatus(String eid_lt, String ts_lt) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "");
            String sql="update leave_applications set approval='Disapprove' where eid=? and timestamp=? ";
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1, eid_lt);
            ps.setString(2, ts_lt);
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
    
}
