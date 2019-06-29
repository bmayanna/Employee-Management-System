package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Directory;
import entity.EmpEntity;
import entity.Profile;
import entity.RequestLeave;
import entity.Salary;
import entity.UploadDocument;
import utility.DBConnection;

public class EmpRegisterDao {

	
	public static EmpEntity register(String f_name, String l_name,String email,String address,String phone, String username, String password) throws SQLException {
		EmpEntity regd = new EmpEntity();
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee");
		ResultSet rs = stmt.executeQuery();
		int n = 1;
		while(rs.next()){
			n++;
		}
		stmt.close();
		PreparedStatement stmt2 = DBConnection.createConnection().prepareStatement("INSERT INTO employee(id,f_name,l_name,email,address,phone,username,password,manager_id,status,level,manager_status) values(?,?,?,?,?,?,?,?,?,?,?,?)");
		stmt2.setInt(1, n);
		stmt2.setString(2, f_name);
		stmt2.setString(3, l_name);
		stmt2.setString(4, email);
		stmt2.setString(5, address);
		stmt2.setString(6, phone);
		stmt2.setString(7, username);
		stmt2.setString(8, password);
		stmt2.setInt(9, (Integer)0);
		stmt2.setBoolean(10, false);
		stmt2.setInt(11, 1);
		stmt2.setBoolean(12, false );
		stmt2.executeUpdate();
		stmt2.close();
		regd.setEmp_id(n);
		regd.setFunctionResponse(true);
		return regd;
	}
	public static EmpEntity validate(String username, String password) throws SQLException {
		EmpEntity emp = new EmpEntity();
	    PreparedStatement stmt = DBConnection.createConnection().prepareStatement("select * from employee where username = ? and password = ?");
		stmt.setString(1,username);
	    stmt.setString(2,password);
	    ResultSet rd = stmt.executeQuery();
	    while(rd.next()){
	    	emp.setId(rd.getInt(1));
	    	emp.setF_name(rd.getString(2));
	    	emp.setL_name(rd.getString(3));
	    	emp.setEmail(rd.getString(4));
	    	emp.setAddress(rd.getString(5));
	    	emp.setPhone(rd.getString(6));
	    	emp.setUsername(rd.getString(7));
	    	emp.setManager_id(rd.getInt(9));
	    	emp.setLevel(rd.getInt(11));
	    	emp.setStatus(true);
	    	stmt.close();
	    	return emp;
	    }
	    stmt.close();
	    emp.setStatus(true);
	    return emp;
	}
	
		
	public static EmpEntity getData(int id) throws SQLException{
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee where id = ?");
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		EmpEntity regdata = new EmpEntity();
		while(rs.next()){
			regdata.setId(rs.getInt(1));
			regdata.setF_name(rs.getString(2));
			regdata.setL_name(rs.getString(3));
			regdata.setAddress(rs.getString(4));
			regdata.setEmail(rs.getString(5));
			regdata.setPhone(rs.getString(6));
			regdata.setUsername(rs.getString(7));
			regdata.setManager_id(rs.getInt(9));
			regdata.setLevel(rs.getInt(11));
			stmt.close();
			return regdata;
		}
		return regdata;
		
	}

	public static Boolean updateData(int id, String address, String phone, String email) throws SQLException {
		// TODO Auto-generated method stub
		PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("update employee set address = ?,phone = ?,email = ? where id = ?");
		stmt.setString(1, address);
		stmt.setString(2, phone);
		stmt.setString(3, email);
		stmt.setInt(4, id);
		stmt.executeUpdate();
		stmt.close();
		return true; 
	}

	public static Boolean saveLeave(int id, String details, int mid) throws SQLException {
		PreparedStatement preparedStatement = DBConnection.createConnection().prepareStatement("insert into reqleaves (emp_id, manager_id, details, response) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS); //Making use of prepared statements here to insert bunch of data
		 preparedStatement.setInt(1,id);
		 preparedStatement.setInt(2,mid);
		 preparedStatement.setString(3, details);
		 preparedStatement.setString(4, "Not Available");//Active or inactive status
		 preparedStatement.executeUpdate();
		 return true;
	}

		public static List<RequestLeave> RequestLeaveList(int managerId) throws SQLException{
			List<RequestLeave> list=new ArrayList<RequestLeave>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from reqleaves where manager_id = ?");// and response = ?
			stmt.setInt(1, managerId);
			//stmt.setString(2, "Not Available");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				RequestLeave feed = new RequestLeave();
				feed.setId(rs.getInt(1));
				feed.setEmp_id(rs.getInt(2));
				feed.setManager_id(rs.getInt(3));
				feed.setDetails(rs.getString(4));
				feed.setResponse(rs.getString(5));
				list.add(feed);
			}
			stmt.close();
			return list;
		}
		
		public static List<EmpEntity> inactiveuser(int id) throws SQLException {
			List<EmpEntity> Data = new ArrayList<EmpEntity>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee where status = ?");
			stmt.setBoolean(1, false);
			ResultSet rs = stmt.executeQuery();
			 while (rs.next()) {
				 EmpEntity info = new EmpEntity();
				 info.setId(rs.getInt(1));
				 info.setF_name(rs.getString(2));
				 info.setL_name(rs.getString(3));
				 info.setEmail(rs.getString(5));
				 info.setManager_id(rs.getInt(9));
				 info.setLevel(rs.getInt(11));
				 Data.add(info);
			 }
			return Data;
		}
		
		public static List<EmpEntity> manager(int id) throws SQLException {
			List<EmpEntity> Data1 = new ArrayList<EmpEntity>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee where level = ? and manager_status = ?");
			stmt.setInt(1, 2);
			stmt.setBoolean(2, true);
			ResultSet rs = stmt.executeQuery();
			 while (rs.next()) {
				 EmpEntity info = new EmpEntity();
				 info.setId(rs.getInt(1));
				 info.setF_name(rs.getString(2));
				 info.setL_name(rs.getString(3));
				 info.setEmail(rs.getString(4));
				 //info.setMid(rs.getInt(9));
				 info.setLevel(rs.getInt(11));
				 Data1.add(info);
			 }
			return Data1;
		}
		
		public static List<EmpEntity> activeuser(int id) throws SQLException {
			List<EmpEntity> Data = new ArrayList<EmpEntity>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee where status = ?");
			stmt.setBoolean(1, true);
			ResultSet rs = stmt.executeQuery();
			 while (rs.next()) {
				 EmpEntity info = new EmpEntity();
				 info.setId(rs.getInt(1));
				 info.setF_name(rs.getString(2));
				 info.setL_name(rs.getString(3));
				 info.setEmail(rs.getString(5));
				 info.setManager_id(rs.getInt(9));
				 info.setLevel(rs.getInt(11));
				 Data.add(info);
			 }
			return Data;
		}
		
		public static EmpEntity saveUser(int result, int result1, int result2) throws SQLException {
			PreparedStatement stmt2 = DBConnection.createConnection().prepareStatement("UPDATE employee SET manager_id = ?,level = ?,status = ?,manager_status = ? WHERE id = ?");
			stmt2.setInt(1, result1);
	 		stmt2.setInt(2, result2);
			stmt2.setBoolean(3, true);
			if(result2 == 2){
				stmt2.setBoolean(4, true);
			}else{
				stmt2.setBoolean(4, false);
			}
			stmt2.setInt(5, result);
			stmt2.executeUpdate();
			EmpEntity regdata = new EmpEntity();
			regdata.setStatus(true);
			regdata.setEmp_id(result);
			regdata.setManager_id(result1);
			return regdata;
		
		}
		public static EmpEntity updateOrganization(int emp_id, int manager_id, String designation, String division) throws SQLException {
			PreparedStatement preparedStatement = DBConnection.createConnection().prepareStatement("insert into profile (emp_id, role, divisionname, supervisor) values (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS); //Making use of prepared statements here to insert bunch of data
			 preparedStatement.setInt(1,emp_id);
			 preparedStatement.setString(2, designation);
			 preparedStatement.setString(3, division);
			 preparedStatement.setString(4, Integer.toString(manager_id));
			 preparedStatement.executeUpdate();
			 EmpEntity regdata = new EmpEntity();
			 regdata.setStatus(true);
			 return regdata;
		}
		public static Profile getProfile(int id) throws SQLException {
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from profile where emp_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Profile regdata = new Profile();
			while(rs.next()){
				regdata.setId(rs.getInt(1));
				regdata.setEmpid(rs.getInt(2));
				regdata.setDesignation(rs.getString(3));
				regdata.setDivision(rs.getString(4));
				regdata.setManid(rs.getString(5));
				stmt.close();
				return regdata;
			}
			return regdata;
		}
		public static Salary getsalary(String id) throws SQLException {
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from salary where emp_id = ?");
			stmt.setInt(1, Integer.parseInt(id));
			ResultSet rs = stmt.executeQuery();
			Salary regdata = new Salary();
			while(rs.next()){
				regdata.setId(rs.getInt(1));
				regdata.setEmp_id(rs.getInt(2));
				regdata.setSalary(rs.getString(3));
				regdata.setBonus(rs.getString(4));
				regdata.setTotal(rs.getString(5));
				stmt.close();
				return regdata;
			}
			return regdata;
		}
		public static Salary updateSalary(String id, double total) throws NumberFormatException, SQLException {
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("update salary set total = ? where emp_id = ?");
			stmt.setString(1, Double.toString(total));
			stmt.setInt(2, Integer.parseInt(id));
			stmt.executeUpdate();
			stmt.close();
			Salary sal = new Salary();
			sal.setBonus("SUCCESS");
			return sal; 
		}
		
		public static Salary getallbonus(int id) throws SQLException {
			//List<Salary> bonus = new ArrayList<Salary>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from salary where emp_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			Salary regdata = new Salary();
			while(rs.next()){
				regdata.setId(rs.getInt(1));
				regdata.setEmp_id(rs.getInt(2));
				regdata.setSalary(rs.getString(3));
				regdata.setBonus(rs.getString(4));
				regdata.setTotal(rs.getString(5));
			}
			stmt.close();
			return regdata;
		}
		
		public static List<EmpEntity> getSubUsers(int id) throws SQLException {
			List<EmpEntity> Data = new ArrayList<EmpEntity>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from employee where manager_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			 while (rs.next()) {
				 EmpEntity info = new EmpEntity();
				 info.setId(rs.getInt(1));
				 info.setF_name(rs.getString(2));
				 info.setL_name(rs.getString(3));
				 info.setEmail(rs.getString(5));
				 info.setManager_id(rs.getInt(9));
				 info.setLevel(rs.getInt(11));
				 Data.add(info);
			 }
			 stmt.close();
			return Data;
		}
		public static Salary updateSalary(String salaryid, String empid, String bonus) throws NumberFormatException, SQLException {
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("update salary set bonus = ? where emp_id = ? and id = ?");
			stmt.setString(1, bonus);
			stmt.setInt(2, Integer.parseInt(empid));
			stmt.setInt(3, Integer.parseInt(salaryid));
			stmt.executeUpdate();
			stmt.close();
			Salary sal = new Salary();
			sal.setBonus("SUCCESS");
			return sal; 
		}
		public static Directory saveDirectory(int id, String dir, String access) throws SQLException {
			
			PreparedStatement stmt = DBConnection.createConnection().prepareStatement("insert into directory (directoryname,manager_id,access_id ) values (?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS); //Making use of prepared statements here to insert bunch of data
			stmt.setString(1,dir);
			stmt.setInt(2,id);
			int access1=Integer.parseInt(access);
			stmt.setInt(3,access1);
			stmt.executeUpdate();
			stmt.close();
			
			Directory direct=new Directory();
			direct.setStatus(true);
			return direct;
			
		}
		
		public static List<Directory> getDir(int mid) throws SQLException {
			List<Directory> Data = new ArrayList<Directory>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from directory where manager_id = ?");
			stmt.setInt(1, mid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				 Directory info = new Directory();
				 info.setId(rs.getInt(1));
				 info.setDirectoryName(rs.getString(2));
				 info.setManager_id(rs.getInt(3));
				 info.setAccess_id(rs.getInt(4));
				 Data.add(info);
			 }
			return Data;
		}
		
		public static UploadDocument updateFile(int dirid, int mid, String docname, InputStream is, String mime) throws SQLException {
			PreparedStatement stmt = DBConnection.createConnection().prepareStatement("insert into document (emp_id,document_name,doc,type,dir_id) values (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS); //Making use of prepared statements here to insert bunch of data
			stmt.setInt(1,mid);
			stmt.setString(2,docname);
			stmt.setBlob(3, is);
			stmt.setString(4,mime);
			stmt.setInt(5,dirid);
			stmt.executeUpdate();
			stmt.close();
			UploadDocument direct=new UploadDocument();
			direct.setStatus(true);
			return direct;
		}
		
		public static List<UploadDocument> getDocuments(int mid) throws SQLException {
			List<UploadDocument> Data = new ArrayList<UploadDocument>();
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from document where emp_id = ?");
			stmt.setInt(1, mid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				 UploadDocument info = new UploadDocument();
				 info.setId(rs.getInt(1));
				 info.setEmp_id(rs.getInt(2));//(rs.getString(2));
				 info.setDocument_name(rs.getString(3));
				 info.setDoc(rs.getBlob(4));
				 info.setType(rs.getString(5));
				 info.setDir_id(rs.getInt(6));
				 Data.add(info);
			 }
			return Data;
		}
		public static UploadDocument getDoc(int file) throws SQLException {
			PreparedStatement stmt  = DBConnection.createConnection().prepareStatement("select * from document where id = ?");
			stmt.setInt(1, file);
			ResultSet rs = stmt.executeQuery();
			UploadDocument info = new UploadDocument();
			while (rs.next()) {
				 info.setId(rs.getInt(1));
				 info.setEmp_id(rs.getInt(2));//(rs.getString(2));
				 info.setDocument_name(rs.getString(3));
				 info.setDoc(rs.getBlob(4));
				 info.setType(rs.getString(5));
				 info.setDir_id(rs.getInt(6));
			 }
			return info;
		}
		
	}



