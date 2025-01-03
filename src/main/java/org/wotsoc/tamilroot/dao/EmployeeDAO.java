package org.wotsoc.tamilroot.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.wotsoc.tamilroot.model.Employee;
import org.wotsoc.tamilroot.model.EmployeeForm;
  
@Repository
public class EmployeeDAO {
  
    private static final Map<Long, Employee> empMap = new HashMap<Long, Employee>();
  
    static {
        initEmps();
    }
  
    private static void initEmps() {
        Employee emp1 = new Employee(1L, "E01", "Smith", "Clerk","Working");
        Employee emp2 = new Employee(2L, "E02", "Allen", "Salesman","Better");
        Employee emp3 = new Employee(3L, "E03", "Jones", "Manager","Good");
  
        empMap.put(emp1.getEmpId(), emp1);
        empMap.put(emp2.getEmpId(), emp2);
        empMap.put(emp3.getEmpId(), emp3);
    }
  
    public Long getMaxEmpId() {
        Set<Long> keys = empMap.keySet();
        Long max = 0L;
        for (Long key : keys) {
            if (key > max) {
                max = key;
            }
        }
        return max;
    }
  
    public Employee getEmployee(Long empId) {
        return empMap.get(empId);
    }
  
    public Employee addEmployee(EmployeeForm empForm) {
        Long empId= this.getMaxEmpId()+ 1;
        empForm.setEmpId(empId);
        Employee newEmp = new Employee(empForm);  
         
        empMap.put(newEmp.getEmpId(), newEmp);
        return newEmp;
    }
  
    public Employee updateEmployee(EmployeeForm empForm) {
        Employee emp = this.getEmployee(empForm.getEmpId());
        if(emp!= null)  {
            emp.setEmpNo(empForm.getEmpNo());
            emp.setEmpName(empForm.getEmpName());
            emp.setPosition(empForm.getPosition());
            emp.setComments(empForm.getComments());
        }  
        return emp;
    }
  
    public void deleteEmployee(Long empId) {
        empMap.remove(empId);
    }
  
    public List<Employee> getAllEmployees() {
    	empMap.put(4l,new Employee(4L, "E01", "Rajamani", "Clerk","Rajamani"));
        Collection<Employee> c = empMap.values();
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(c);
        return list;
    }
  
}