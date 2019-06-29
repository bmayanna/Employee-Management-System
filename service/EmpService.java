package service;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.EmpRegisterDao;
import entity.Directory;
import entity.EmpEntity;
import entity.Profile;
import entity.Salary;
import entity.UploadDocument;

public class EmpService {
	public static List<EmpEntity> inactiveuser(String id) throws SQLException {
		
		int result = Integer.parseInt(id);
		List<EmpEntity> info = new ArrayList<EmpEntity>();
		info= EmpRegisterDao.inactiveuser(result);
		return info;
	}
	
	public static List<EmpEntity> managerlist(String id) throws SQLException {
		int result = Integer.parseInt(id);
		List<EmpEntity> Minfo = new ArrayList<EmpEntity>();
		Minfo= EmpRegisterDao.manager(result);
		return Minfo;
	}
	
	public static List<EmpEntity> activeuser(int id) throws SQLException {
		List<EmpEntity> info = new ArrayList<EmpEntity>();
		info= EmpRegisterDao.activeuser(id);
		for (EmpEntity empEntity : info) {
			Profile profile = EmpRegisterDao.getProfile(empEntity.getId());
			empEntity.setDesignation(profile.getDesignation());
			empEntity.setDivision(profile.getDivision());
		}
		return info;
	}
	
	public static EmpEntity saveUser(int result, int result1, int result2, String designation, String division) throws SQLException {
		EmpEntity regdata = new EmpEntity();
		regdata= EmpRegisterDao.saveUser(result,result1,result2);
		if(regdata.getStatus()){
			EmpEntity nothing = EmpRegisterDao.updateOrganization(regdata.getEmp_id(),regdata.getManager_id(),designation,division);
		}
		return regdata;
	}

	public static Salary updateSalary(String id) throws SQLException {
		// TODO Auto-generated method stub
		Salary sal = EmpRegisterDao.getsalary(id);
		double total = Double.parseDouble(sal.getSalary())+Double.parseDouble(sal.getBonus());
		Salary updated = EmpRegisterDao.updateSalary(id,total);
		return updated;
	}

	public static List<Salary> getbonus(int id, List<EmpEntity> user) throws SQLException {
		List<Salary> bonuss = new ArrayList<Salary>();
		int count = 0;
		for (EmpEntity empEntity : user) {
			Salary bonus = EmpRegisterDao.getallbonus(empEntity.getId());
			bonuss.add(count, bonus);
			count++;
		}
		return bonuss;
	}

	public static Salary updateBonus(String salaryid, String empid, String bonus) throws NumberFormatException, SQLException {
		Salary updated = EmpRegisterDao.updateSalary(salaryid,empid,bonus);
		return updated;
	}

	public static Directory saveDirectory(int id,String dir, String access) throws SQLException {
		Directory send = EmpRegisterDao.saveDirectory(id,dir,access);
		return send;
	}

	public static List<Directory> getDir(int mid) throws SQLException {
		// TODO Auto-generated method stub
		List<Directory> dir = EmpRegisterDao.getDir(mid);
		return dir;
	}

	public static UploadDocument updateFile(int dirid, int mid, String docname, InputStream is, String mime) throws SQLException {
		UploadDocument doc = EmpRegisterDao.updateFile(dirid,mid,docname,is,mime);
		return doc;
	}

	public static List<UploadDocument> getDocuments(int mid) throws SQLException {
		List<UploadDocument> dir = EmpRegisterDao.getDocuments(mid);
		return dir;
	}

	public static UploadDocument getDoc(int file) throws SQLException {
		UploadDocument doc = EmpRegisterDao.getDoc(file);
		return doc;
	}

//	public static UploadDocument updateFil(int dirid, int eid, String docname, InputStream is, String mime) {
//		UploadDocument docum = EmpRegisterDao.updateFil(dirid,eid,docname,is,mime);
//		return docum;
//}

}
