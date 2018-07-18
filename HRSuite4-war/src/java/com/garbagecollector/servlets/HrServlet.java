/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garbagecollector.servlets;

import com.garbagecollector.dtos.DepartmentDTO;
import com.garbagecollector.dtos.EmployeeDTO;
import com.garbagecollector.ejbs.LoginSessionBean;
import com.garbagecollector.ejbs.RestClientBean;
import com.garbagecollector.ejbs.UserBean;
import com.garbagecollector.ejbs.ValidationBean;
import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.gson.Gson;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author akisreti
 */
public class HrServlet extends HttpServlet {

  @EJB
  private UserBean userBean;

  @EJB
  private ValidationBean validationBean;

  @EJB
  private RestClientBean restClientBean;

  private LoginSessionBean loginSessionBean;

  private ArrayList<EmployeeDTO> employees;
  private int employeeCount;
  private int avgSalary;
  private int avgEmpsByDept;
  private String jobList;
  private String minSalaryList;
  private String maxSalaryList;
  private ArrayList<DepartmentDTO> departments;
  private String hireYears;
  private String hireCounts;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    //HttpSession session = request.getSession();
    HttpSession session = null;
    if (request.getSession(false) != null) {
      session = request.getSession();
    }
    loginSessionBean = (LoginSessionBean) session.getAttribute("sBean");
    if (loginSessionBean == null) {
      try {
        Context context = new InitialContext();
        loginSessionBean = (LoginSessionBean) context.lookup("java:global/HRSuite4/HRSuite4-ejb/LoginSessionBean!com.garbagecollector.ejbs.LoginSessionBean");
      }
      catch (NamingException ex) {
        Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
      }
      //loginSessionBean = new LoginSessionBean();
      session.setAttribute("sBean", loginSessionBean);

    }
    request.setAttribute("userBean", userBean);
    int displayPageParam = 1;
    if (request.getParameter("displayPage") != null) {
      displayPageParam = Integer.parseInt(request.getParameter("displayPage"));
    }
    int salaryChange = 0;
    if (request.getParameter("salaryChange") != null) {
      salaryChange = Integer.parseInt(request.getParameter("salaryChange"));
    }
    if (loginSessionBean != null && loginSessionBean.isLoggedIn()) {
      //instructorBean.setPage(1);
      switch (displayPageParam) {
        case 1:
          employeeCount = userBean.getEmployeeCount();
          avgSalary = restClientBean.getAvgSalary()/*(int) userBean.getAllDeptsAvgSalary()*/;
          avgEmpsByDept = restClientBean.getEmpCountByDepts();
          jobList = userBean.getJobTitles();
          minSalaryList = userBean.getMinSalaries();
          maxSalaryList = userBean.getMaxSalaries();
          hireYears = userBean.getHireDateYears();
          hireCounts = userBean.getHireDateCounts();
          request.setAttribute("employeeCount", employeeCount);
          request.setAttribute("avgSalary", avgSalary);
          request.setAttribute("avgEmpsByDept", avgEmpsByDept);
          request.setAttribute("jobList", jobList);
          request.setAttribute("minSalaryList", minSalaryList);
          request.setAttribute("maxSalaryList", maxSalaryList);
          request.setAttribute("hireYears", hireYears);
          request.setAttribute("hireCounts", hireCounts);
          request.setAttribute("displayPage", 1);
          request.setAttribute("salaryChange", salaryChange);
          break;
        case 2:
          employees = userBean.getAllEmployee();
          request.setAttribute("employees", employees);
          request.setAttribute("displayPage", 2);
          break;
        case 3:
          departments = userBean.getDepartments();
          employees = userBean.getAllEmployee();
          System.out.println(employees);
          String json = null;
          ArrayList<String> empName = new ArrayList<>();
          for (EmployeeDTO employee : employees) {
            //empName.add(employee.getFirstName() + " " + employee.getLastName() + " (" + employee.getEmployeeId() + ")");
            String deptName = null;
            if (null != employee.getDepartmentId()) {
              deptName = employee.getDepartmentId().getDepartmentName();
            }
            empName.add(employee.getFirstName() + " " + employee.getLastName() + ";" + employee.getEmployeeId() + ";" + deptName);
          }
          System.out.println(empName);
          json = new Gson().toJson(empName);
          request.setAttribute("departments", departments);
          request.setAttribute("employeesJson", json);
          request.setAttribute("displayPage", 3);
          break;
        case 4:
          request.setAttribute("displayPage", 4);
          break;
        case 10:
          loginSessionBean.reset(); // logout
          session.invalidate();
          response.sendRedirect(response.encodeRedirectURL("login"));
          break;
        default:
          request.setAttribute("displayPage", 1);
          break;
      }
      if (displayPageParam != 10) {
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
      }
    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    HttpSession session = null;
    if (request.getSession(false) != null) {
      session = request.getSession();
    }
    loginSessionBean = (LoginSessionBean) session.getAttribute("sBean");
    if (loginSessionBean.isLoggedIn() == false) {
      if (loginSessionBean == null) {
        try {
          Context context = new InitialContext();
          loginSessionBean = (LoginSessionBean) context.lookup("java:global/HRSuite4/HRSuite4-ejb/LoginSessionBean!com.garbagecollector.ejbs.LoginSessionBean");
        }
        catch (NamingException ex) {
          Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        session.setAttribute("sBean", loginSessionBean);

      }
      request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }
    else {
      int inputSalary = Integer.parseInt(request.getParameter("salary"));
      Integer empId = Integer.parseInt(request.getParameter("employee"));
      EmployeeDTO employeeDTO = userBean.getEmployeeById(empId);

      long valid = validationBean.validateSalary(inputSalary, employeeDTO.getSalary(),
              employeeDTO.getJobId().getMinSalary(), employeeDTO.getJobId().getMaxSalary());
      if (valid == 0) {
        userBean.updateEmployeeSalary(empId, inputSalary);
        response.sendRedirect("home?salaryChange=1");
      }
      else {
        departments = userBean.getDepartments();
        employees = userBean.getAllEmployee();
        System.out.println(employees);
        String json = null;
        ArrayList<String> empName = new ArrayList<>();
        for (EmployeeDTO employee : employees) {
          //empName.add(employee.getFirstName() + " " + employee.getLastName() + " (" + employee.getEmployeeId() + ")");
          String deptName = null;
          if (null != employee.getDepartmentId()) {
            deptName = employee.getDepartmentId().getDepartmentName();
          }
          empName.add(employee.getFirstName() + " " + employee.getLastName() + ";" + employee.getEmployeeId() + ";" + deptName);
        }
        System.out.println(empName);
        json = new Gson().toJson(empName);
        request.setAttribute("departments", departments);
        request.setAttribute("employeesJson", json);
        request.setAttribute("displayPage", 3);
        request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
        //response.sendRedirect(response.encodeRedirectURL(request.getRequestURL().toString()));
      }
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
