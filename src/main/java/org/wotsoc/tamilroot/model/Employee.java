package org.wotsoc.tamilroot.model;

public class Employee {
	 
    private Long empId;
    private String empNo;
    private String empName;
    private String position;
    private String comments;
 
    public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Employee() {
 
    }
 
    public Employee(EmployeeForm empForm) {
        this.empId = empForm.getEmpId();
        this.empNo = empForm.getEmpNo();
        this.empName = empForm.getEmpName();
        this.position = empForm.getPosition();
        this.comments = empForm.getComments();
    }
 
    public Employee(Long empId, String empNo, String empName, String position,String comments) {
        this.empId = empId;
        this.empNo = empNo;
        this.empName = empName;
        this.position = position;
        this.comments = comments;
    }
 
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
 
}