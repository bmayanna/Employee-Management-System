package entity;

public class RequestLeave {
	private int id;
	private int emp_id;
	private int manager_id;
	private String details;
	private String response;
	private Boolean status;
	
public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getEmp_id() {
	return emp_id;
}
public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}
public int getManager_id() {
	return manager_id;
}
public void setManager_id(int manager_id) {
	this.manager_id = manager_id;
}
public String getDetails() {
	return details;
}
public void setDetails(String details) {
	this.details = details;
}

public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}


}
