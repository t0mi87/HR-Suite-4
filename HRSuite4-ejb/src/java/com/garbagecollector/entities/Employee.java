/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "EMPLOYEES")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Employees.findAll", query = "SELECT e FROM Employee e")
  , @NamedQuery(name = "EmailCount", query = "SELECT e FROM Employee e WHERE e.email LIKE :email")
  , @NamedQuery(name = "Employees.findByEmployeeId", query = "SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
  , @NamedQuery(name = "Employees.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName")
  , @NamedQuery(name = "Employees.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
  , @NamedQuery(name = "Employees.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
  , @NamedQuery(name = "Employees.findByPhoneNumber", query = "SELECT e FROM Employee e WHERE e.phoneNumber = :phoneNumber")
  , @NamedQuery(name = "Employees.findByHireDate", query = "SELECT e FROM Employee e WHERE e.hireDate = :hireDate")
  , @NamedQuery(name = "Employees.findBySalary", query = "SELECT e FROM Employee e WHERE e.salary = :salary")
  , @NamedQuery(name = "Employees.findByCommissionPct", query = "SELECT e FROM Employee e WHERE e.commissionPct = :commissionPct")
  , @NamedQuery(name = "SumByHireDate", query = "SELECT EXTRACT(year FROM e.hireDate), COUNT(e.hireDate) "
          + "FROM Employee e GROUP BY EXTRACT(year FROM e.hireDate)\n" +
                            "ORDER BY EXTRACT(year FROM e.hireDate)")})
public class Employee implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "EMPLOYEE_ID")
  private Integer employeeId;
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Basic(optional = false)
  @Column(name = "LAST_NAME")
  private String lastName;
  @Basic(optional = false)
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PHONE_NUMBER")
  private String phoneNumber;
  @Basic(optional = false)
  @Column(name = "HIRE_DATE")
  @Temporal(TemporalType.DATE)
  private Date hireDate;
  // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
  @Column(name = "SALARY")
  private int salary;
  @Column(name = "COMMISSION_PCT")
  private double commissionPct;
  @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
  @ManyToOne
  private Department departmentId;
  @OneToMany(mappedBy = "managerId")
  private Collection<Employee> employeesCollection;
  @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID")
  @ManyToOne
  private Employee managerId;
  @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
  @ManyToOne(optional = false)
  private Job jobId;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "employees")
  private Collection<JobHistory> jobHistoryCollection;
  @OneToMany(mappedBy = "managerId")
  private Collection<Department> departmentsCollection;

  public Employee() {
  }

  public Employee(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Employee(Integer employeeId, String lastName, String email, Date hireDate) {
    this.employeeId = employeeId;
    this.lastName = lastName;
    this.email = email;
    this.hireDate = hireDate;
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

  @XmlTransient
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

  @XmlTransient
  public Collection<JobHistory> getJobHistoryCollection() {
    return jobHistoryCollection;
  }

  public void setJobHistoryCollection(Collection<JobHistory> jobHistoryCollection) {
    this.jobHistoryCollection = jobHistoryCollection;
  }

  @XmlTransient
  public Collection<Department> getDepartmentsCollection() {
    return departmentsCollection;
  }

  public void setDepartmentsCollection(Collection<Department> departmentsCollection) {
    this.departmentsCollection = departmentsCollection;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (employeeId != null ? employeeId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Employee)) {
      return false;
    }
    Employee other = (Employee) object;
    if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Employees[ employeeId=" + employeeId + " ]";
  }

}
