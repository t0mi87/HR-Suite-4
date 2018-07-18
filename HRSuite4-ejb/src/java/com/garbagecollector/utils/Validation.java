package com.garbagecollector.utils;



import com.garbagecollector.entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Validation {

    private EntityManagerFactory emf;

    public Validation(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * Ellenőrzi a felhasználó által beírt fizetés összegét és visszaad egy státuszt, hogy megfelelő-e a beírt összeg.
     * @param inputSalary A felhasználó által begépelt új fizetés értéke.
     * @param currentSalary A dolgozó aktuális fizetése.
     * @param minSalary A munkakörhöz tartozó fizetés alsó határa.
     * @param maxSalary A munkakörhöz tartozó fizetés felső határa.
     * @return A státuszkódot adja vissza, amely alapján vezérelhető a felhasználói üzenet.<br>
     *  0 - A beírt összeg megfelelő.<br>
     *  1 - A beírt összeg megegyezik az aktuális fizetéssel.<br>
     *  2 - A beírt összeg nem az alsó és felső határ közé esik.
     */
    public long validateSalary(long inputSalary, long currentSalary, long minSalary, long maxSalary) {

        long validMinSalary = calcMinSalary(currentSalary, minSalary);
        long validMaxSalary = calcMaxSalary(currentSalary, maxSalary);

        long statusCode = 0; // minden oké

        if (currentSalary == inputSalary)
            statusCode = 1; // a beírt összeg megegyezik az akutális fizetéssel

        else if (inputSalary < validMinSalary || inputSalary > validMaxSalary)
            statusCode = 2;

        return statusCode;
    }

    /**
     * Kiszámolja, hogy a jelenlegi fizetés -5%-a vagy a
     * munkakör alapján megadható minimum
     * fizetés lesz-e az új fizetés alsó határa
     * @param currentSalary A dolgozó aktuális fizetése.
     * @param minSalary A munkakörhöz tartozó fizetés alsó határa.
     * @return Az új fizetés alsó határa
     */
    public long calcMinSalary(long currentSalary, long minSalary) {

        long calcMinSalary = 0;
        double neg5PercentFromCurrentSalary = currentSalary * 0.95;

        if (neg5PercentFromCurrentSalary < minSalary)
            calcMinSalary = minSalary;
        else
            calcMinSalary = (long) neg5PercentFromCurrentSalary;

        return calcMinSalary;
    }

    /**
     * Kiszámolja, hogy a jelenlegi fizetés +5%-a vagy a
     * munkakör alapján megadható maximum
     * fizetés lesz-e az új fizetés felső határa
     * @param currentSalary A dolgozó aktuális fizetése.
     * @param maxSalary A munkakörhöz tartozó fizetés felső határa.
     * @return Az új fizetés felső határa
     */
    public long calcMaxSalary(long currentSalary, long maxSalary) {

        long calcMaxSalary = 0;
        double pos5PercentFromCurrentSalary = currentSalary * 1.05;

        if (pos5PercentFromCurrentSalary > maxSalary)
            calcMaxSalary = maxSalary;
        else
            calcMaxSalary = (long) pos5PercentFromCurrentSalary;

        return calcMaxSalary;

    }

    private boolean validateByRegex(String regexPattern, String inputString) {

        Pattern r = Pattern.compile(regexPattern);
        Matcher m;
        m = r.matcher(inputString);

        return m.matches();
    }

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

        return  validateByRegex(RegexPatterns.REGEX_COMMISSION, inputString);

    }

    public double calcMinSalaryByAvgSalary(double avgSalary) {

        double neg5PercentFromInputSalary = avgSalary * 0.95;

        return neg5PercentFromInputSalary;
    }


    public double calcMaxSalaryByAvgSalary(double avgSalary) {


        double pos5PercentFromInputSalary = avgSalary * 1.05;

        return pos5PercentFromInputSalary;
    }

    public double getAvgSalaryByDept(long deptId) {
        EntityManager em = emf.createEntityManager();
        List<Employee> q = em.createNamedQuery("EmployeeByDeptId").setParameter("deptId", deptId).getResultList();
        em.close();

        int sumSalary = 0;
        for (Employee employee : q) {
            sumSalary += employee.getSalary();
        }
        System.out.println(sumSalary);
        double avgSalary = sumSalary / q.size();
        System.out.println(avgSalary);
        return avgSalary;

    }

    public boolean isAuthenticationPassed(String username, String pass) {

        boolean isLoginValid = false;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Log.writeErrorToLogFile("Error while configuring xml parser", e);
        }
        Document document = null;
        try {
            document = db.parse(new FileInputStream(new File("files/users.xml")));
        } catch (SAXException e) {
            Log.writeErrorToLogFile("Error while parsing xml file", e);
        } catch (IOException e) {
            Log.writeErrorToLogFile("Error while reading xml file", e);
        }

        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();

        XPathExpression expr = null;
        Object result = null;
        try {
            expr = xpath.compile("//class//users//user//name[contains(.,'" + username + "')]");
            result = expr.evaluate(document, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            Log.writeErrorToLogFile("Error while parsing xml", e);
        }
        NodeList nodes = (NodeList) result;

        // user found
        // check password
        if (nodes.getLength() == 1) {
            Node parent = nodes.item(0).getParentNode();
            String xmlPassword = parent.getChildNodes().item(3).getTextContent();

            boolean matched = BCrypt.checkpw(pass, xmlPassword);
            if (matched) {
                isLoginValid = true;
                Log.writeToLogFile("Successful login. - user: " + username);
            }
            else {
                isLoginValid = false;
                Log.writeToLogFile("Invalid login. Invalid password. - user: " + username);
            }

        }
        // user not found
        else {
            Log.writeToLogFile("Invalid login. Invalid username. - user: " + username);
            isLoginValid = false;
        }

        return isLoginValid;

    }

    public String hashPassword(String pass) {

        String generatedSecuredPasswordHash = null;

        generatedSecuredPasswordHash = BCrypt.hashpw(pass, BCrypt.gensalt(8));

        return generatedSecuredPasswordHash;

    }

}
