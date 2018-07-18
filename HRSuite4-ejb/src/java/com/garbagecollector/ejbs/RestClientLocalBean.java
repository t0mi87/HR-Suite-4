/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.garbagecollector.ejbs;

import javax.ejb.Local;

/**
 *
 * @author akisreti
 */
@Local
public interface RestClientLocalBean {
 
  int getAvgSalary();
  
  int getEmpCountByDepts();
  
}
