/* @author Kisréti Ákos
 * @date   2018.03.22.
 */

package com.garbagecollector.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "JOB_HISTORY")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "JobHistory.findAll", query = "SELECT j FROM JobHistory j")
  , @NamedQuery(name = "JobHistory.findByEmployeeId", query = "SELECT j FROM JobHistory j WHERE j.jobHistoryPK.employeeId = :employeeId")
  , @NamedQuery(name = "JobHistory.findByStartDate", query = "SELECT j FROM JobHistory j WHERE j.jobHistoryPK.startDate = :startDate")
  , @NamedQuery(name = "JobHistory.findByEndDate", query = "SELECT j FROM JobHistory j WHERE j.endDate = :endDate")})
public class JobHistory implements Serializable {

  private static final long serialVersionUID = 1L;
  @EmbeddedId
  protected JobHistoryPK jobHistoryPK;
  @Basic(optional = false)
  @Column(name = "END_DATE")
  @Temporal(TemporalType.DATE)
  private Date endDate;
  @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
  @ManyToOne
  private Department departmentId;
  @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID", insertable = false, updatable = false)
  @ManyToOne(optional = false)
  private Employee employees;
  @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
  @ManyToOne(optional = false)
  private Job jobId;

  public JobHistory() {
  }

  public JobHistory(JobHistoryPK jobHistoryPK) {
    this.jobHistoryPK = jobHistoryPK;
  }

  public JobHistory(JobHistoryPK jobHistoryPK, Date endDate) {
    this.jobHistoryPK = jobHistoryPK;
    this.endDate = endDate;
  }

  public JobHistory(int employeeId, Date startDate) {
    this.jobHistoryPK = new JobHistoryPK(employeeId, startDate);
  }

  public JobHistoryPK getJobHistoryPK() {
    return jobHistoryPK;
  }

  public void setJobHistoryPK(JobHistoryPK jobHistoryPK) {
    this.jobHistoryPK = jobHistoryPK;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Department getDepartmentId() {
    return departmentId;
  }

  public void setDepartmentId(Department departmentId) {
    this.departmentId = departmentId;
  }

  public Employee getEmployees() {
    return employees;
  }

  public void setEmployees(Employee employees) {
    this.employees = employees;
  }

  public Job getJobId() {
    return jobId;
  }

  public void setJobId(Job jobId) {
    this.jobId = jobId;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (jobHistoryPK != null ? jobHistoryPK.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof JobHistory)) {
      return false;
    }
    JobHistory other = (JobHistory) object;
    if ((this.jobHistoryPK == null && other.jobHistoryPK != null) || (this.jobHistoryPK != null && !this.jobHistoryPK.equals(other.jobHistoryPK))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "com.garbagecollector.entities.JobHistory[ jobHistoryPK=" + jobHistoryPK + " ]";
  }

}
