import employees.Analyst;
import employees.CEO;
import employees.Manager;
import employees.Programmer;

import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        int totalSalaries = getTotalSalaries();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        System.out.printf("The total payout for employees %s%n",numberFormat.format(totalSalaries));

    }

    private static int getTotalSalaries() {
        String employeeData = """
                Flinstone, Fred, 1/1/1900,Programmer,{lopcd=2000,yoe=10,iq=140}
                Flinstone2, Fred, 1/1/1900,Programmer,{lopcd=1300,yoe=14,iq=100}
                Flinstone3, Fred, 1/1/1900,Programmer,{lopcd=2300,yoe=8,iq=105}
                Flinstone4, Fred, 1/1/1900,Programmer,{lopcd=1630,yoe=3,iq=115}
                Flinstone5, Fred, 1/1/1900,Programmer,{lopcd=5,yoe=10,iq=100}
                Rubble, Barney, 2/2/1905,Manager,{orgSize=300,dr=10}
                Rubble2, Barney, 2/2/1905,Manager,{orgSize=100,dr=4}
                Rubble3, Barney, 2/2/1905,Manager,{orgSize=200,dr=2}
                Rubble4, Barney, 2/2/1905,Manager,{orgSize=500,dr=8}
                Rubble5, Barney, 2/2/1905,Manager,{orgSize=175,dr=12}
                Flinstone, Wilma, 3/3/1910,Analyst,{projectCount=3}
                Flinstone2, Wilma, 3/3/1910,Analyst,{projectCount=4}
                Flinstone3, Wilma, 3/3/1910,Analyst,{projectCount=5}
                Flinstone4, Wilma, 3/3/1910,Analyst,{projectCount=6}
                Flinstone5, Wilma, 3/3/1910,Analyst,{projectCount=9}
                Rubble, Betty, 4/4/1915,CEO,{avgStockPrice=300}
                """;

        String peopleRegex = "(?<lastName>\\w+),\\s*(?<firstName>\\w+),\\s*(?<dob>\\d{1,2}/\\d{1,2}/\\d{4}),\\s*(?<role>\\w+)(?:,\\s*\\{(?<details>.*)\\})?\\n";
        Pattern peoplePat = Pattern.compile(peopleRegex);
        Matcher peopleMat = peoplePat.matcher(employeeData);

        String CEORegex = "\\w+\\=(?<avgStockPrice>\\w+)";
        Pattern CEOPat = Pattern.compile(CEORegex);


        int totalSalaries = 0;
        while (peopleMat.find()) {
            totalSalaries += switch (peopleMat.group("role")){
                case "Programmer" -> {
                    Programmer programmer = new Programmer(peopleMat.group());
                    System.out.println(programmer.toString());
                    yield programmer.getSalary();

                }
                case "Manager" -> {
                    Manager manager = new Manager(peopleMat.group());
                    System.out.println(manager.toString());
                    yield manager.getSalary();

                }
                case "Analyst" -> {
                    Analyst analyst = new Analyst(peopleMat.group());
                    System.out.println(analyst.toString());
                    yield analyst.getSalary();

                }
                case "CEO" ->{
                    CEO ceo = new CEO(peopleMat.group());
                    System.out.println(ceo.toString());
                    yield ceo.getSalary();
                }
               default -> 0;
            };
        }
        return totalSalaries;
    }
}