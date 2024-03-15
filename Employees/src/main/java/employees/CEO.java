package employees;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CEO {

    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private int avgStockPrice =0;

    private final String employeeRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
    private final Pattern employeePat = Pattern.compile(employeeRegex);

    String CEORegex = "\\w+\\=(?<avgStockPrice>\\w+)";
    Pattern CEOPat = Pattern.compile(CEORegex);

    private final NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

    public CEO(String employeeData) {
        Matcher employeeMat = employeePat.matcher(employeeData);
        if (employeeMat.find()){
            this.lastName = employeeMat.group("lastName");
            this.firstName = employeeMat.group("firstName");
            this.DOB  = LocalDate.from(dateTimeFormatter.parse(employeeMat.group("dob")));
            Matcher CEOMat = CEOPat.matcher(employeeMat.group("details"));
            if (CEOMat.find()){
                this.avgStockPrice = Integer.parseInt(CEOMat.group("avgStockPrice"));
            }
        }

    }

    public int getSalary(){
        return 5000 *  avgStockPrice;
    }

    @Override
    public String toString() {
        return String.format("%s , %s : %s",lastName,firstName,moneyFormatter.format(getSalary()));
    }


}
