/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garbagecollector.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 *
 * @author akisreti
 */
@Stateless
@LocalBean
public class RestClientBean implements RestClientLocalBean {

  @Override
  public int getAvgSalary() {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/HRSuite4-war/service/resource/avgsalary");
    String response = webTarget.request().get(String.class);
    return Integer.parseInt(response);
  }

  @Override
  public int getEmpCountByDepts() {
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/HRSuite4-war/service/resource/empcountbydepts");
    String response = webTarget.request().get(String.class);
    return Integer.parseInt(response);
  }

}
