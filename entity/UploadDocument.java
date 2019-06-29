package entity;

import java.sql.Blob;

public class UploadDocument {
private int id;
private int emp_id;
private String document_name;
private Blob doc;
private String type;
private int dir_id;
private Boolean status;

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
public String getDocument_name() {
	return document_name;
}
public void setDocument_name(String document_name) {
	this.document_name = document_name;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getDir_id() {
	return dir_id;
}
public void setDir_id(int dir_id) {
	this.dir_id = dir_id;
}
public Blob getDoc() {
	return doc;
}
public void setDoc(Blob doc) {
	this.doc = doc;
}
public Boolean getStatus() {
	return status;
}
public void setStatus(Boolean status) {
	this.status = status;
}


}
