package employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyst {
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private int projectCount = 0;
    private final String employeeRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern employeePat = Pattern.compile(employeeRegex);

    private final String analystDetailsRegex = "\\w+\\=(?<projectCount>\\w+)";
    private final Pattern analystPat = Pattern.compile(analystDetailsRegex);

    private final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");


    public Analyst(String employeeData) {
        Matcher employeeMat = employeePat.matcher(employeeData);
        if (employeeMat.find()){
            this.lastName = employeeMat.group("lastName");
            this.firstName = employeeMat.group("firstName");
            this.DOB  = LocalDate.from(dateTimeFormatter.parse(employeeMat.group("dob")));
            Matcher analystMat = analystPat.matcher(employeeMat.group("details"));
            if (analystMat.find()){
                this.projectCount = Integer.parseInt(analystMat.group("projectCount"));
            }
        }

    }

    public int getSalary(){
        return 2500 +  projectCount * 2;
    }

    @Override
    public String toString() {
        return String.format("%s , %s : %s",lastName,firstName,moneyFormatter.format(getSalary()));
    }



}
