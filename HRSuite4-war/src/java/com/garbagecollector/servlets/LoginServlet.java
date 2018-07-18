package com.garbagecollector.servlets;

import com.garbagecollector.ejbs.LoginSessionBean;
import com.garbagecollector.ejbs.UserBean;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author akisreti
 */
public class LoginServlet extends HttpServlet {

  @EJB
  private LoginSessionBean loginSessionBean;

  @EJB
  private UserBean userBean;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    HttpSession session = null;
    if (request.getSession(false) != null) {
      session = request.getSession();
    }
    
    if (session != null)
      loginSessionBean = (LoginSessionBean) session.getAttribute("sBean");
    //userBean = (UserBean) session.getAttribute("userBean");
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

    //request.setAttribute("successReg", new Boolean(false));
    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {

    //HttpSession session = request.getSession();
    HttpSession session = null;
    if (request.getSession(false) != null) {
      session = request.getSession();
    }

    loginSessionBean = (LoginSessionBean) session.getAttribute("sBean");

    String userName = request.getParameter("username");
    String password = request.getParameter("password");
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
    loginSessionBean.setUserName(userName);

    /*System.out.println(userName);
    System.out.println(password);
    userBean.register(userName, password);
    System.out.println(userBean.find(1));*/
    if (userBean.isAuthenticationPassed(userName, password)) {
      loginSessionBean.setLoggedIn(true);
      loginSessionBean.setRole(userBean.getRoleByName(userName).getRoleId().getId());
      loginSessionBean.setRoleName(userBean.getRoleByName(userName).getRoleId().getName());
      response.sendRedirect("home");
    }
    else {
      loginSessionBean.setLoggedIn(false);
      response.sendRedirect(response.encodeRedirectURL("login"));
    }
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }

}
