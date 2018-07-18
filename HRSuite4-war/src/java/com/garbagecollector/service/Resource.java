/* @author Kisréti Ákos
 * @date   2018.03.25.
 */

package com.garbagecollector.service;

import com.garbagecollector.dtos.EmployeeDTO;
import com.garbagecollector.ejbs.UserBean;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//@Stateless
@Path("resource")
@Stateless
public class Resource {
  
  @EJB
  private UserBean userBean;
  
  @GET
  public String valami() {
    return "22";
  }
  
  @GET
  @Path("/jobtitles")
  @Produces(MediaType.APPLICATION_JSON)
  public JsonArray getJsonEntity() {
    ArrayList<String> a = userBean.getJobTitlesList();
    JsonArrayBuilder builder = Json.createArrayBuilder();
    
    for (String string : a) {
      builder.add(string);
    }
    
    return builder.build();
    
  }
  
  @GET
  @Path("/empcount")
  public String getEmpCount(){
    return userBean.getEmployeeCount() + "";
  }
  
  @GET
  @Path("/avgsalary")
  public String getAvgSalary(){
    return (int)(userBean.getAllDeptsAvgSalary())+ "";
  }
  
  @GET
  @Path("/empcountbydepts")
  public String getEmpCountByDepts(){
    return (int)(userBean.getAvgEmpCountByDept())+ "";
  }
  
  @GET
  @Path("/emp")
  @Produces(MediaType.APPLICATION_JSON)
  public EmployeeDTO emp(){

    EmployeeDTO employeeDTO = userBean.getEmployeeById(110);
    System.out.println(employeeDTO);
    return employeeDTO;
  }
  
}

