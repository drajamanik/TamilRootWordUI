package org.wotsoc.tamilroot.model;

public class EmployeeForm {
    
    private Long empId;
    private String empNo;
    private String empName;
    private String position;
    private String comments;
 
    public Long getEmpId() {
        return empId;
    }
 
    public void setEmpId(Long empId) {
        this.empId = empId;
    }
 
    public String getEmpNo() {
        return empNo;
    }
 
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
 
    public String getEmpName() {
        return empName;
    }
 
    public void setEmpName(String empName) {
        this.empName = empName;
    }
 
    public String getPosition() {
        return position;
    }
 
    public void setPosition(String position) {
        this.position = position;
    }

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}