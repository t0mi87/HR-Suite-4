
package com.garbagecollector.ejbs;

import javax.ejb.Local;


@Local
public interface ValidationBeanLocal {
   long validateSalary(long inputSalary, long currentSalary, long minSalary, long maxSalary);

    long calcMinSalary(long currentSalary, long minSalary);

    long calcMaxSalary(long currentSalary, long maxSalary);

    boolean validateLastName(String inputString);

    boolean validateFirstName(String inputString);

    boolean validatePhoneNumber(String inputString);

    boolean validateCommission(String inputString);

    double calcMinSalaryByAvgSalary(double avgSalary);

    double calcMaxSalaryByAvgSalary(double avgSalary);

  
  
}
