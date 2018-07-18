/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.dtos;

import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.JobHistory;
import com.garbagecollector.entities.Location;
import java.util.Collection;

public class DepartmentDTO {

  private Short departmentId;
  private String departmentName;
  private Collection<Employee> employeesCollection;
  private Collection<JobHistory> jobHistoryCollection;
  private Employee managerId;
  private Location locationId;

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

  public Collection<Employee> getEmployeesCollection() {
    return employeesCollection;
  }

  public void setEmployeesCollection(Collection<Employee> employeesCollection) {
    this.employeesCollection = employeesCollection;
  }

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
  
  
  
}
