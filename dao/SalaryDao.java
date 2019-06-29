package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Salary;
import utility.DBConnection;

public class SalaryDao {

	public static Salary updatesalary(int emp_id)throws SQLException {
		Salary salary=new Salary();
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from salary");
		ResultSet rs = stmt.executeQuery();
		int n = 1;
		while(rs.next()){
			n++;
		}
		stmt.close();
		PreparedStatement stmt2 = DBConnection.createConnection().prepareStatement("INSERT INTO salary(id,emp_id,salary,bonus,total) values(?,?,?,?,?)");
		stmt2.setInt(1, n);
		stmt2.setInt(2,emp_id);
		stmt2.setDouble(3, (double)6000);
		stmt2.setDouble(4,(double)0);
		stmt2.setDouble(5,(double)0);
		stmt2.executeUpdate();
		stmt2.close();
		salary.setEmp_id(emp_id);
		return salary;// TODO Auto-generated method stub
	}

}
