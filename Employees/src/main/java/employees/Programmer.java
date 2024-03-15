package employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Programmer {

    private String firstName;
    private String lastName;
    private LocalDate DOB;

    private int linesOfCode = 0;
    private int yearsOfExp=0;
    private int IQ=0;

    private final String employeeRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern employeePat = Pattern.compile(employeeRegex);

   private final String programmerDetailsRegex = "\\w+\\=(?<lopcd>\\w+),\\w+\\=(?<yoe>\\w+),\\w+\\=(?<iq>\\w+)";
   private final Pattern programmerPat = Pattern.compile(programmerDetailsRegex);

   private final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
   DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");


    public Programmer(String employeeData) {
        Matcher employeeMat = employeePat.matcher(employeeData);
        if (employeeMat.find()){
            this.lastName = employeeMat.group("lastName");
            this.firstName = employeeMat.group("firstName");
            this.DOB  = LocalDate.from(dateTimeFormatter.parse(employeeMat.group("dob")));
            Matcher programmerMat = programmerPat.matcher(employeeMat.group("details"));
            if (programmerMat.find()){
                this.linesOfCode = Integer.parseInt(programmerMat.group("lopcd"));
                this.yearsOfExp = Integer.parseInt(programmerMat.group("yoe"));
                this.IQ = Integer.parseInt(programmerMat.group("iq"));
            }
        }
    }

    public int getSalary(){
        return 3000 + linesOfCode * yearsOfExp * IQ;
    }

    @Override
    public String toString() {
        return String.format("%s,%s : %s",lastName,firstName,moneyFormatter.format(getSalary()));
    }
}
