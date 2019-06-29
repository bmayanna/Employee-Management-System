package entity;

public class LeaveEntity {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLeavesnum() {
		return leavesnum;
	}
	public void setLeavesnum(int leavesnum) {
		this.leavesnum = leavesnum;
	}
	
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	private int leavesnum;
	private int emp_id;
	

}
