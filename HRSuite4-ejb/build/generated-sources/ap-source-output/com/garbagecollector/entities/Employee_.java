package com.garbagecollector.entities;

import com.garbagecollector.entities.Department;
import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.Job;
import com.garbagecollector.entities.JobHistory;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-02T11:34:06")
@StaticMetamodel(Employee.class)
public class Employee_ { 

    public static volatile SingularAttribute<Employee, String> lastName;
    public static volatile SingularAttribute<Employee, Date> hireDate;
    public static volatile CollectionAttribute<Employee, JobHistory> jobHistoryCollection;
    public static volatile SingularAttribute<Employee, Department> departmentId;
    public static volatile SingularAttribute<Employee, Integer> employeeId;
    public static volatile SingularAttribute<Employee, Employee> managerId;
    public static volatile SingularAttribute<Employee, Integer> salary;
    public static volatile SingularAttribute<Employee, Double> commissionPct;
    public static volatile CollectionAttribute<Employee, Employee> employeesCollection;
    public static volatile SingularAttribute<Employee, String> firstName;
    public static volatile SingularAttribute<Employee, Job> jobId;
    public static volatile SingularAttribute<Employee, String> phoneNumber;
    public static volatile CollectionAttribute<Employee, Department> departmentsCollection;
    public static volatile SingularAttribute<Employee, String> email;

}