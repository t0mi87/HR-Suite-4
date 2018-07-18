/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "JOBS")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "Jobs.findAll", query = "SELECT j FROM Job j")
  , @NamedQuery(name = "Jobs.findByJobId", query = "SELECT j FROM Job j WHERE j.jobId = :jobId")
  , @NamedQuery(name = "Jobs.findByJobTitle", query = "SELECT j FROM Job j WHERE j.jobTitle = :jobTitle")
  , @NamedQuery(name = "Jobs.findByMinSalary", query = "SELECT j FROM Job j WHERE j.minSalary = :minSalary")
  , @NamedQuery(name = "Jobs.findByMaxSalary", query = "SELECT j FROM Job j WHERE j.maxSalary = :maxSalary")})
public class Job implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @Basic(optional = false)
  @Column(name = "JOB_ID")
  private String jobId;
  @Basic(optional = false)
  @Column(name = "JOB_TITLE")
  private String jobTitle;
  @Column(name = "MIN_SALARY")
  private Integer minSalary;
  @Column(name = "MAX_SALARY")
  private Integer maxSalary;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
  private Collection<Employee> employeesCollection;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
  private Collection<JobHistory> jobHistoryCollection;

  public Job() {
  }

  public Job(String jobId) {
    this.jobId = jobId;
  }

  public Job(String jobId, String jobTitle) {
    this.jobId = jobId;
    this.jobTitle = jobTitle;
  }

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

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (jobId != null ? jobId.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof Job)) {
      return false;
    }
    Job other = (Job) object;
    if ((this.jobId == null && other.jobId != null) || (this.jobId != null && !this.jobId.equals(other.jobId))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.Jobs[ jobId=" + jobId + " ]";
  }

}
