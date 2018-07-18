package com.garbagecollector.ejbs;

import com.garbagecollector.utils.RegexPatterns;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

@Stateless
@LocalBean
public class ValidationBean implements ValidationBeanLocal, RegexPatterns {

  @Override
  public long validateSalary(long inputSalary, long currentSalary, long minSalary, long maxSalary) {
    long validMinSalary = calcMinSalary(currentSalary, minSalary);
    long validMaxSalary = calcMaxSalary(currentSalary, maxSalary);
    long statusCode = 0; // minden oké
    if (currentSalary == inputSalary) {
      statusCode = 1; // a beírt összeg megegyezik az akutális fizetéssel
    }
    else if (inputSalary < validMinSalary || inputSalary > validMaxSalary) {
      statusCode = 2; // beírt összeg kisebb vagy nagyobb
    }

    return statusCode;
  }

  @Override
  public long calcMinSalary(long currentSalary, long minSalary) {
    long calcMinSalary = 0;
    double neg5PercentFromCurrentSalary = currentSalary * 0.95;
    if (neg5PercentFromCurrentSalary < minSalary) {
      calcMinSalary = minSalary;
    }
    else {
      calcMinSalary = (long) neg5PercentFromCurrentSalary;
    }

    return calcMinSalary;
  }

  @Override
  public long calcMaxSalary(long currentSalary, long maxSalary) {
    long calcMaxSalary = 0;
    double pos5PercentFromCurrentSalary = currentSalary * 1.05;
    if (pos5PercentFromCurrentSalary > maxSalary) {
      calcMaxSalary = maxSalary;
    }
    else {
      calcMaxSalary = (long) pos5PercentFromCurrentSalary;
    }

    return calcMaxSalary;
  }

  private boolean validateByRegex(String regexPattern, String inputString) {

    Pattern r = Pattern.compile(regexPattern);
    Matcher m;
    m = r.matcher(inputString);

    return m.matches();
  }

  @Override
  public boolean validateLastName(String inputString) {

    boolean result = validateByRegex(RegexPatterns.REGEX_LAST_NAME, inputString);

    return result;
  }

  public boolean validateFirstName(String inputString) {

    boolean result = validateByRegex(RegexPatterns.REGEX_FIRST_NAME, inputString);

    return result;
  }

  public boolean validatePhoneNumber(String inputString) {

    boolean result = validateByRegex(RegexPatterns.REGEX_PHONE_NUMBER, inputString);

    return result;
  }

  public boolean validateCommission(String inputString) {

    return validateByRegex(RegexPatterns.REGEX_COMMISSION, inputString);

  }

  public double calcMinSalaryByAvgSalary(double avgSalary) {

    double neg5PercentFromInputSalary = avgSalary * 0.95;

    return neg5PercentFromInputSalary;
  }

  public double calcMaxSalaryByAvgSalary(double avgSalary) {

    double pos5PercentFromInputSalary = avgSalary * 1.05;

    return pos5PercentFromInputSalary;
  }

}
