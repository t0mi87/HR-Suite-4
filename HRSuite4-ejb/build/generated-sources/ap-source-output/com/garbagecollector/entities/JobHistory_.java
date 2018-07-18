package com.garbagecollector.entities;

import com.garbagecollector.entities.Department;
import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.Job;
import com.garbagecollector.entities.JobHistoryPK;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(JobHistory.class)
public class JobHistory_ { 

    public static volatile SingularAttribute<JobHistory, Job> jobId;
    public static volatile SingularAttribute<JobHistory, JobHistoryPK> jobHistoryPK;
    public static volatile SingularAttribute<JobHistory, Date> endDate;
    public static volatile SingularAttribute<JobHistory, Department> departmentId;
    public static volatile SingularAttribute<JobHistory, Employee> employees;

}