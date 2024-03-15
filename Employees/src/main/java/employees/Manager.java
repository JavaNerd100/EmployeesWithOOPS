package employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {

    private String firstName;
    private String lastName;
    private LocalDate DOB;

    private int organisationSize = 0;
    private int directRepot = 0;


    private final String employeeRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern employeePat = Pattern.compile(employeeRegex);

    private final String managerDetailsRegex = "\\w+\\=(?<orgSize>\\w+),\\w+\\=(?<dr>\\w+)";
    private final Pattern managerPat = Pattern.compile(managerDetailsRegex);

    private final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");




    public Manager(String employeeData) {
        Matcher employeeMat = employeePat.matcher(employeeData);
        if (employeeMat.find()){
            this.lastName = employeeMat.group("lastName");
            this.firstName = employeeMat.group("firstName");
            this.DOB  = LocalDate.from(dateTimeFormatter.parse(employeeMat.group("dob")));
            Matcher managerMat = managerPat.matcher(employeeMat.group("details"));
            if (managerMat.find()){
                this.organisationSize = Integer.parseInt(managerMat.group("orgSize"));
                this.directRepot = Integer.parseInt(managerMat.group("dr"));
            }
        }

    }

    public int getSalary(){
        return 3500 + organisationSize * directRepot ;
    }

    @Override
    public String toString() {
        return String.format("%s , %s : %s",lastName,firstName,moneyFormatter.format(getSalary()));
    }


}
