
package com.garbagecollector.dtos;

import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.JobHistory;
import java.util.Collection;


public class JobDTO {
    
    
    private String jobId;
 
  private String jobTitle;
 
  private Integer minSalary;

  private Integer maxSalary;
  
  private Collection<Employee> employeesCollection;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
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
  
  private Collection<JobHistory> jobHistoryCollection;
}
