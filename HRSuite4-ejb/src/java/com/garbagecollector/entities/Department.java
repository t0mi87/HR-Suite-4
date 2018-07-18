/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "DEPARTMENTS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Departments.findAll", query = "SELECT d FROM Department d ORDER BY d.departmentName")
  , @NamedQuery(name = "Departments.findByDepartmentId", query = "SELECT d FROM Department d WHERE d.departmentId = :departmentId")
  , @NamedQuery(name = "Departments.findByDepartmentName", query = "SELECT d FROM Department d WHERE d.departmentName = :departmentName")
})
public class Department implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "DEPARTMENT_ID")
  private Short departmentId;
  @Basic(optional = false)
  @Column(name = "DEPARTMENT_NAME")
  private String departmentName;
  @OneToMany(mappedBy = "departmentId")
  private Collection<Employee> employeesCollection;
  @OneToMany(mappedBy = "departmentId")
  private Collection<JobHistory> jobHistoryCollection;
  @JoinColumn(name = "MANAGER_ID", referencedColumnName = "EMPLOYEE_ID")
  @ManyToOne
  private Employee managerId;
  @JoinColumn(name = "LOCATION_ID", referencedColumnName = "LOCATION_ID")
  @ManyToOne
  private Location locationId;

  public Department() {
  }

  public Department(Short departmentId) {
    this.departmentId = departmentId;
  }

  public Department(Short departmentId, String departmentName) {
    this.departmentId = departmentId;
    this.departmentName = departmentName;
  }

  public Short getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Short departmentId) {
    this.departmentId = departmentId;
  }

  public String getDepartmentName() {
    return departmentName;
  }

  public void setDepartmentName(String departmentName) {
    this.departmentName = departmentName;
  }

  @XmlTransient
  public Collection<Employee> getEmployeesCollection() {
    return employeesCollection;
  }

  public void setEmployeesCollection(Collection<Employee> employeesCollection) {
    this.employeesCollection = employeesCollection;
  }

  @XmlTransient
  public Collection<JobHistory> getJobHistoryCollection() {
    return jobHistoryCollection;
  }

  public void setJobHistoryCollection(Collection<JobHistory> jobHistoryCollection) {
    this.jobHistoryCollection = jobHistoryCollection;
  }

  public Employee getManagerId() {
    return managerId;
  }

  public void setManagerId(Employee managerId) {
    this.managerId = managerId;
  }

  public Location getLocationId() {
    return locationId;
  }

  public void setLocationId(Location locationId) {
    this.locationId = locationId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (departmentId != null ? departmentId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Department)) {
      return false;
    }
    Department other = (Department) object;
    if ((this.departmentId == null && other.departmentId != null) || (this.departmentId != null && !this.departmentId.equals(other.departmentId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Departments[ departmentId=" + departmentId + " ]";
  }

}
