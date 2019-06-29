package entity;

public class Directory {
private int id;
private String DirectoryName;
private int manager_id;
private int access_id;
private String accessible_id;
private Boolean status;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDirectoryName() {
	return DirectoryName;
}
public void setDirectoryName(String directoryName) {
	DirectoryName = directoryName;
}
public int getManager_id() {
	return manager_id;
}
public void setManager_id(int manager_id) {
	this.manager_id = manager_id;
}
public int getAccess_id() {
	return access_id;
}
public void setAccess_id(int access_id) {
	this.access_id = access_id;
}
public String getAccessible_id() {
	return accessible_id;
}
public void setAccessible_id(String accessible_id) {
	this.accessible_id = accessible_id;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}

}
