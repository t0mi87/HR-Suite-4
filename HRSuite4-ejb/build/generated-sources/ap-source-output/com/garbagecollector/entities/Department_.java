package com.garbagecollector.entities;

import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.JobHistory;
import com.garbagecollector.entities.Location;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(Department.class)
public class Department_ { 

    public static volatile SingularAttribute<Department, String> departmentName;
    public static volatile CollectionAttribute<Department, Employee> employeesCollection;
    public static volatile CollectionAttribute<Department, JobHistory> jobHistoryCollection;
    public static volatile SingularAttribute<Department, Location> locationId;
    public static volatile SingularAttribute<Department, Short> departmentId;
    public static volatile SingularAttribute<Department, Employee> managerId;

}