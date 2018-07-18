/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garbagecollector.ejbs;

import com.garbagecollector.dtos.DepartmentDTO;
import com.garbagecollector.dtos.EmployeeDTO;
import com.garbagecollector.dtos.JobDTO;
import com.garbagecollector.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author akisreti
 */
@Local
public interface UserBeanLocal {
  
  ArrayList<EmployeeDTO> getEmpsByDeptName(String deptName);
  
  String getJobTitles();
  
  ArrayList<String> getJobTitlesList();
  
  String getMinSalaries();
  
  String getMaxSalaries();
          
  boolean register(String username, String pass);
  
  ArrayList<DepartmentDTO> getDepartments();
  
  EmployeeDTO getEmployeeById(long id);
  
  JobDTO getJobByEmployee(String jobId);
  
  boolean updateEmployeeSalary(int id, int newSalary);
  
  ArrayList<JobDTO> getJobs();
  
  long addEmployee(String firstName, String lastName, String email,
                     String phoneNumber, String jobId, int salary,
                     double commission, long managerId, long deptId);
  
  ArrayList<EmployeeDTO> getAllEmployee();
  
  DepartmentDTO getDeptById(long id);
  
  double getAvgSalaryByDept(long deptId);
  
   public String createEmail(String firstName, String lastName);
  
  //Statisztikai lekérdezések
  public int getEmployeeCount();
  
  
  public double getAllDeptsAvgSalary();
  
  public double getAvgEmpCountByDept();
  
  public double secondMostLucrativeDept();
  
  public ArrayList<Double> getAllDeptAvgs();
  
  User getRoleByName(String username);
  
  String find(int id);
  
  boolean isAuthenticationPassed(String username, String pass);
  
  
}
