package com.garbagecollector.dtos;

import com.garbagecollector.entities.Department;
import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.Job;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EmployeeDTO implements Serializable{

  private Integer employeeId;

  private String firstName;

  private String lastName;

  private String email;

  private String phoneNumber;

  private Date hireDate;
  
  private String hireDateFormatted;

  private int salary;

  private double commissionPct;

  private Department departmentId;

  private Employee managerId;

  private Job jobId;

  private Collection<Department> departmentsCollection;

  private Collection<Employee> employeesCollection;

  public EmployeeDTO() {
  }

    
  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getHireDate() {
    return hireDate;
  }

  public void setHireDate(Date hireDate) {
    this.hireDate = hireDate;
  }

  public String getHireDateFormatted() {
    return hireDateFormatted;
  }

  public void setHireDateFormatted(String hireDateFormatted) {
    this.hireDateFormatted = hireDateFormatted;
  }

  
  
  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public double getCommissionPct() {
    return commissionPct;
  }

  public void setCommissionPct(double commissionPct) {
    this.commissionPct = commissionPct;
  }

  public Department getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Department departmentId) {
    this.departmentId = departmentId;
  }

  public Collection<Employee> getEmployeesCollection() {
    return employeesCollection;
  }

  public void setEmployeesCollection(Collection<Employee> employeesCollection) {
    this.employeesCollection = employeesCollection;
  }

  public Employee getManagerId() {
    return managerId;
  }

  public void setManagerId(Employee managerId) {
    this.managerId = managerId;
  }

  public Job getJobId() {
    return jobId;
  }

  public void setJobId(Job jobId) {
    this.jobId = jobId;
  }

  public Collection<Department> getDepartmentsCollection() {
    return departmentsCollection;
  }

  public void setDepartmentsCollection(Collection<Department> departmentsCollection) {
    this.departmentsCollection = departmentsCollection;
  }

  @Override
  public String toString() {
    return "EmployeeDTO{" + "employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", hireDateFormatted=" + hireDateFormatted + ", salary=" + salary + ", commissionPct=" + commissionPct + ", departmentId=" + departmentId + ", managerId=" + managerId + ", jobId=" + jobId + ", departmentsCollection=" + departmentsCollection + ", employeesCollection=" + employeesCollection + '}';
  }

  
  
}
