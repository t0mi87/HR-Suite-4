package com.garbagecollector.entities;

import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.JobHistory;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(Job.class)
public class Job_ { 

    public static volatile CollectionAttribute<Job, Employee> employeesCollection;
    public static volatile SingularAttribute<Job, String> jobId;
    public static volatile SingularAttribute<Job, Integer> maxSalary;
    public static volatile CollectionAttribute<Job, JobHistory> jobHistoryCollection;
    public static volatile SingularAttribute<Job, String> jobTitle;
    public static volatile SingularAttribute<Job, Integer> minSalary;

}