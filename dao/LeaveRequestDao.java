package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.LeaveEntity;
import entity.RequestLeave;
import utility.DBConnection;

public class LeaveRequestDao {

	public static RequestLeave resleave(String res, int a, int b,int c) throws SQLException {

		int resp=Integer.parseInt(res);
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("UPDATE reqleaves SET response=? where id = ? and manager_id = ? and emp_id= ? ");	
		if(resp==2){
			stmt.setString(1, "Accept");
		}
		else{
			stmt.setString(1, "Decline");
		}
		stmt.setInt(2, c);
		stmt.setInt(3, a);
		stmt.setInt(4, b);
		stmt.executeUpdate();
		RequestLeave roi = new RequestLeave();
		roi.setStatus(true);
		return roi;
	}

	public static LeaveEntity leave(int emp_id) throws SQLException {
		LeaveEntity roi = new LeaveEntity();
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from `leave`");
		ResultSet rs = stmt.executeQuery();
		int n = 1;
		while(rs.next()){
			n++;
		}
		stmt.close();
		PreparedStatement stmt2 = DBConnection.createConnection().prepareStatement("INSERT INTO `leave`(id,lesvenum,emp_id) values(?,?,?)");
		stmt2.setInt(1, n);
		stmt2.setInt(2,(int)4);
		stmt2.setInt(3, emp_id);
		stmt2.executeUpdate();
		stmt2.close();
		roi.setEmp_id(emp_id);
		return roi;
	}
}

	
	


