package com.garbagecollector.ejbs;

import com.garbagecollector.utils.BCrypt;
import java.util.List;
import com.garbagecollector.dtos.DepartmentDTO;
import com.garbagecollector.dtos.EmployeeDTO;
import com.garbagecollector.dtos.JobDTO;
import com.garbagecollector.entities.Department;
import com.garbagecollector.entities.Employee;
import com.garbagecollector.entities.Job;
import com.garbagecollector.entities.User;
import com.google.gson.Gson;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author akisreti
 */
@Stateless
@LocalBean
//@Remote(UserBeanLocal.class)
public class UserBean implements UserBeanLocal {

  @PersistenceContext(name = "HRSuite4-ejbPU")
  private EntityManager em;

  public UserBean() {

  }

  @Override
  public boolean register(String username, String pass) {
    User user = new User();
    user.setUsername(username);
    user.setPass(username);
    em.persist(user);
    if (user.getId() != 0) {
      return true;

    }
    else {
      return false;
    }
  }

  @Override
  public ArrayList<DepartmentDTO> getDepartments() {
    ArrayList<Department> departments = new ArrayList<>();
    ArrayList<DepartmentDTO> departmentsDTO = new ArrayList<>();
    departments.addAll(em.createNamedQuery("Departments.findAll").getResultList());

    for (Department department : departments) {
      if (department.getEmployeesCollection().size() > 0) { // ha van employee a dept-ben

        DepartmentDTO deptDTO = new DepartmentDTO();
        deptDTO.setDepartmentId(department.getDepartmentId());
        deptDTO.setDepartmentName(department.getDepartmentName());
        deptDTO.setEmployeesCollection(department.getEmployeesCollection());

        departmentsDTO.add(deptDTO);
      }
    }

    return departmentsDTO;
  }

  @Override
  public EmployeeDTO getEmployeeById(long id) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    Employee employee = (Employee) em.createNamedQuery("Employees.findByEmployeeId").setParameter("employeeId", id).getSingleResult();
    
    employeeDTO.setCommissionPct(employee.getCommissionPct());
    employeeDTO.setDepartmentId(employee.getDepartmentId());
    employeeDTO.setEmployeeId(employee.getEmployeeId());
    employeeDTO.setEmail(employee.getEmail());
    employeeDTO.setSalary(employee.getSalary());
    employeeDTO.setFirstName(employee.getFirstName());
    employeeDTO.setLastName(employee.getLastName());
    employeeDTO.setPhoneNumber(employee.getPhoneNumber());
    employeeDTO.setHireDate(employee.getHireDate());
    employeeDTO.setManagerId(employee.getManagerId());
    employeeDTO.setEmployeeId(employee.getEmployeeId());
    employeeDTO.setJobId(employee.getJobId());
    //Job job = (Job) em.createNamedQuery("Jobs.findByJobId").setParameter("jobId", employee.getJobId()).getSingleResult();
    //employeeDTO.setJobId(job);
    return employeeDTO;
  }

  @Override
  public JobDTO getJobByEmployee(String jobId) {
    Job job = (Job) em.find(Job.class, jobId);

    JobDTO jobDTO = new JobDTO();

    jobDTO.setEmployeesCollection(job.getEmployeesCollection());
    jobDTO.setJobHistoryCollection(job.getJobHistoryCollection());
    jobDTO.setJobId(job.getJobId());
    jobDTO.setJobTitle(job.getJobTitle());
    jobDTO.setMaxSalary(job.getMaxSalary());
    jobDTO.setMinSalary(job.getMinSalary());

    return jobDTO;
  }

  @Override
  public boolean updateEmployeeSalary(int id, int newSalary) {

    try {

      Employee employee;
      employee = em.find(Employee.class, id);
      employee.setSalary(newSalary);
      em.persist(employee);
      return true;
    }
    catch (Exception e) {
      return false;
    }

  }

  @Override
  public ArrayList<JobDTO> getJobs() {
    ArrayList<JobDTO> jobsDTO = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();
    jobs.addAll(em.createNamedQuery("Jobs.findAll").getResultList());
    for (Job job : jobs) {
      JobDTO jobDTO = new JobDTO();
      jobDTO.setEmployeesCollection(job.getEmployeesCollection());
      jobDTO.setJobHistoryCollection(job.getJobHistoryCollection());
      jobDTO.setJobId(job.getJobId());
      jobDTO.setJobTitle(job.getJobTitle());
      jobDTO.setMaxSalary(job.getMaxSalary());
      jobDTO.setMinSalary(job.getMinSalary());
      jobsDTO.add(jobDTO);
    }
    return jobsDTO;
  }

  @Override
  public String getJobTitles() {
    ArrayList<String> jobTitles = new ArrayList<>();
    ArrayList<JobDTO> jobs = new ArrayList<>();
    //jobs.addAll(em.createNamedQuery("Jobs.findAll").getResultList());
    jobs.addAll(getJobs());
    for (JobDTO job : jobs) {
      jobTitles.add(job.getJobTitle());
    }
    String json = new Gson().toJson(jobTitles);
    return json;
  }

  @Override
  public ArrayList<String> getJobTitlesList() {
    ArrayList<String> jobTitles = new ArrayList<>();
    ArrayList<JobDTO> jobs = new ArrayList<>();
    //jobs.addAll(em.createNamedQuery("Jobs.findAll").getResultList());
    jobs.addAll(getJobs());
    for (JobDTO job : jobs) {
      jobTitles.add(job.getJobTitle());
    }
    //String json = new Gson().toJson(jobTitles);
    return jobTitles;
  }

  @Override
  public String getMinSalaries() {
    ArrayList<Integer> jobTitles = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();
    jobs.addAll(em.createNamedQuery("Jobs.findAll").getResultList());
    for (Job job : jobs) {
      jobTitles.add(job.getMinSalary());
    }
    String json = new Gson().toJson(jobTitles);
    return json;
  }

  @Override
  public String getMaxSalaries() {
    ArrayList<Integer> jobTitles = new ArrayList<>();
    ArrayList<Job> jobs = new ArrayList<>();
    jobs.addAll(em.createNamedQuery("Jobs.findAll").getResultList());
    for (Job job : jobs) {
      jobTitles.add(job.getMaxSalary());
    }
    String json = new Gson().toJson(jobTitles);
    return json;
  }

  public String getHireDateYears() {
    ArrayList<String> hireDates = new ArrayList<>();
    //ArrayList<Object> hires = new ArrayList<>();
    List hires = (em.createNativeQuery("SELECT EXTRACT(year FROM HIRE_DATE) years, COUNT(HIRE_DATE) hires FROM EMPLOYEES\n"
            + "GROUP BY EXTRACT(year FROM HIRE_DATE)\n"
            + "ORDER BY EXTRACT(year FROM HIRE_DATE)").getResultList());

    for (Object hireObject : hires) {
      Object[] row = (Object[]) hireObject;
      for (int i = 0; i < row.length; i += 2) {
        hireDates.add(row[i].toString());
      }
    }

    String json = new Gson().toJson(hireDates);
    return json;
  }

  public String getHireDateCounts() {
    ArrayList<String> hireDates = new ArrayList<>();
    //ArrayList<Object> hires = new ArrayList<>();
    List hires = (em.createNativeQuery("SELECT EXTRACT(year FROM HIRE_DATE) years, COUNT(HIRE_DATE) hires FROM EMPLOYEES\n"
            + "GROUP BY EXTRACT(year FROM HIRE_DATE)\n"
            + "ORDER BY EXTRACT(year FROM HIRE_DATE)").getResultList());

    for (Object hireObject : hires) {
      Object[] row = (Object[]) hireObject;
      for (int i = 1; i < row.length; i += 2) {
        hireDates.add(row[i].toString());
      }
    }

    String json = new Gson().toJson(hireDates);
    return json;
  }

  @Override
  public long addEmployee(String firstName, String lastName, String email, String phoneNumber, String jobId, int salary, double commission, long managerId, long deptId) {
    long millis = System.currentTimeMillis();
    Date date = new Date(millis);
    Job job = (Job) em.createNamedQuery("Jobs.findByJobId").setParameter("jobId", jobId).getSingleResult();

    Employee employee = new Employee();
    Employee managerEmployee = new Employee();
    managerEmployee = em.find(Employee.class, managerId);
    Department department = new Department();
    department = em.find(Department.class, deptId);
    employee.setFirstName(firstName);
    employee.setLastName(lastName);
    employee.setEmail(email);
    employee.setPhoneNumber(phoneNumber);
    employee.setJobId(job);
    employee.setSalary(salary);
    employee.setCommissionPct(commission);
    employee.setManagerId(managerEmployee);
    employee.setDepartmentId(department);
    employee.setHireDate(date);

    em.persist(employee);
    return employee.getEmployeeId();
  }

  @Override
  public ArrayList<EmployeeDTO> getAllEmployee() {
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<EmployeeDTO> employeesDTO = new ArrayList<>();
    employees.addAll(em.createNamedQuery("Employees.findAll").getResultList());

    for (Employee employee : employees) {

      EmployeeDTO employeeDTO = new EmployeeDTO();

      employeeDTO.setCommissionPct(employee.getCommissionPct());
      employeeDTO.setDepartmentId(employee.getDepartmentId());
      employeeDTO.setEmployeeId(employee.getEmployeeId());
      employeeDTO.setEmail(employee.getEmail());
      employeeDTO.setSalary(employee.getSalary());
      employeeDTO.setFirstName(employee.getFirstName());
      employeeDTO.setLastName(employee.getLastName());
      employeeDTO.setPhoneNumber(employee.getPhoneNumber());
      employeeDTO.setHireDate(employee.getHireDate());
      employeeDTO.setManagerId(employee.getManagerId());
      employeeDTO.setEmployeeId(employee.getEmployeeId());

      String formattedDate = new SimpleDateFormat("yyyy.MM.dd.").format(employee.getHireDate());
      employeeDTO.setHireDateFormatted(formattedDate);
      //Job job = new Job();
      Job job = (Job) em.createNamedQuery("Jobs.findByJobId").setParameter("jobId", employee.getJobId().getJobId()).getSingleResult();
      employeeDTO.setJobId(job);

      employeesDTO.add(employeeDTO);
    }
    return employeesDTO;
  }

  @Override
  public DepartmentDTO getDeptById(long id) {
    Department department = new Department();
    department = (Department) em.createNamedQuery("Departments.findByDepartmentId").setParameter("departmentId", id).getSingleResult();
    DepartmentDTO deptDTO = new DepartmentDTO();
    deptDTO.setDepartmentId(department.getDepartmentId());
    deptDTO.setDepartmentName(department.getDepartmentName());
    deptDTO.setEmployeesCollection(department.getEmployeesCollection());
    deptDTO.setLocationId(department.getLocationId());
    deptDTO.setManagerId(department.getManagerId());
    deptDTO.setJobHistoryCollection(department.getJobHistoryCollection());

    return deptDTO;
  }

  @Override
  public double getAvgSalaryByDept(long deptId) {

    Collection<Employee> employees = getDeptById(deptId).getEmployeesCollection();

    int sumSalary = 0;
    for (Employee employee : employees) {
      sumSalary += employee.getSalary();
    }
    double avgSalary = sumSalary / employees.size();

    return avgSalary;
  }

  @Override
  public String createEmail(String firstName, String lastName) {
    String generatedEmail = (firstName.substring(0, 1) + lastName.substring(0)).toUpperCase();

    int emailListSize = em.createNamedQuery("EmailCount").setParameter("email", generatedEmail + "%").getResultList().size();

    System.out.println(emailListSize);
    if (emailListSize > 0) {
      generatedEmail = generatedEmail + emailListSize;
    }
    return generatedEmail;
  }

  //statisztikak metodusai
  @Override
  public int getEmployeeCount() {
    return getAllEmployee().size();
  }

  @Override
  public double getAllDeptsAvgSalary() {

    ArrayList<EmployeeDTO> employees = getAllEmployee();

    int sum = 0;
    for (EmployeeDTO employee : employees) {
      sum += employee.getSalary();
    }
    double allDeptsAvgSalary = sum / employees.size() * 1.0;

    return allDeptsAvgSalary;
  }

  @Override
  public double getAvgEmpCountByDept() {
    int empCount = getEmployeeCount();
    int deptCount = getDepartments().size();
    return empCount / deptCount;
  }

  @Override
  public double secondMostLucrativeDept() {
    ArrayList<DepartmentDTO> departments = getDepartments();
    ArrayList<Double> avgSalaries = new ArrayList<>();

    for (DepartmentDTO department : departments) {
      avgSalaries.add(getAvgSalaryByDept(department.getDepartmentId()));

    }
    Collections.sort(avgSalaries);
    return avgSalaries.get(avgSalaries.size() - 1);

  }

  @Override
  public ArrayList<Double> getAllDeptAvgs() {
    ArrayList<DepartmentDTO> departments = getDepartments();
    ArrayList<Double> avgSalaries = new ArrayList<>();

    for (DepartmentDTO department : departments) {
      avgSalaries.add(getAvgSalaryByDept(department.getDepartmentId()));

    }
    Collections.sort(avgSalaries);
    return avgSalaries;
  }

  @Override
  public User getRoleByName(String username) {
    User user = (User) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
    return user;
  }

  @Override
  public String find(int id) {
    User user = em.find(User.class, id);
    return user.getUsername();
  }

  @Override
  public boolean isAuthenticationPassed(String username, String pass) {

    boolean isLoginValid = false;
    User user = null;

    List<User> users = em.createNamedQuery("Users.findByUsername").setParameter("username", username).getResultList();

    if (users.size() == 0) {
      isLoginValid = false;
    }
    else {
      user = (User) em.createNamedQuery("Users.findByUsername").setParameter("username", username).getSingleResult();
      boolean matched = BCrypt.checkpw(pass, user.getPass());
      if (matched) {
        isLoginValid = true;
      }
      else {
        isLoginValid = false;
      }
    }

    return isLoginValid;

  }

  @Override
  public ArrayList<EmployeeDTO> getEmpsByDeptName(String deptName) {
    Department department = (Department) em.createNamedQuery("Departments.findByDepartmentName").setParameter("departmentName", deptName).getSingleResult();
    ArrayList<Employee> employees = (ArrayList<Employee>) department.getEmployeesCollection();
    ArrayList<EmployeeDTO> employeeDTOList = new ArrayList<>();
    for (Employee employee : employees) {
      EmployeeDTO employeeDTO = new EmployeeDTO();
      employeeDTO.setFirstName(employee.getFirstName());
      employeeDTO.setLastName(employee.getLastName());
      employeeDTO.setEmployeeId(employee.getEmployeeId());
      employeeDTOList.add(employeeDTO);
    }
    return employeeDTOList;
  }

}
