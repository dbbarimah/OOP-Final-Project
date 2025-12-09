import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main{

    private static final double DEFAULT_ALLOWANCE_RATE = 90.0;
    private static UserProfile currentUser = null;
    private static CarbonEmissionsSystem system = new CarbonEmissionsSystem();
    private static Scanner input = new Scanner(System.in);


    public static void main(String[] args){

        try{
            system.loadFromFile("weeklyReports.csv");
            System.out.println("Reports loaded successfully.");

        } catch (IOException e){
            System.out.println("No reports found to load: " + e.getMessage());
        }

        boolean operational = true;

        while(operational){
            System.out.println();
            System.out.println("Carbon Emissions Tracker");
            System.out.println("----------------------------");

            if (currentUser == null){
                System.out.println("1. Create New User Profile");
                System.out.println("2. Exit");
            } else{
                System.out.println("Logged in as " + currentUser.getName() + "!");
                System.out.println("1. Enter Weekly Emissions");
                System.out.println("2. View Weekly Report");
                System.out.println("3. View Summary of Reports");
                System.out.println("4. Exit");
            }

            int choice = -1;
            boolean validInput = false;
            while (!validInput) {
                try{
                    System.out.println("Choose an input: ");
                    choice = Integer.parseInt(input.nextLine().trim());
                    validInput = true;

                } catch (NumberFormatException e){
                    System.out.println("Invalid Input. Enter a number.");
                    System.out.println();
                }
            }

            if (currentUser == null){
                switch (choice) {
                    case 1:
                        System.out.println();
                        currentUser = createProfile();
                        break;
                    case 2:
                        operational = exitSystem();
                        break;
                
                    default:
                        System.out.println("Invalid Input. Try Again.");
                }
            } else {
                switch (choice) {
                    case 1:
                        enterWeeklyEmissions();
                        break;
                    case 2:
                        viewWeeklyReport();
                        break;
                    case 3:
                        viewAllReports();
                        break;
                    case 4:
                        operational = exitSystem();
                        break;
                    default:
                        System.out.println("Invalid Input. Try Again.");
                        break;
                }
            }
        }
        input.close();
    }

    public static UserProfile createProfile(){
        System.out.println("Profile Creator");
        System.out.println("----------------------------");
        System.out.println("Enter your name: ");
        String name = input.nextLine();

        System.out.println("Enter your email: ");
        String email = input.nextLine();

        String password;
        while (true){
            System.out.println("Enter your password (min 8 characters, must include letters and numbers): ");
            password = input.nextLine();

            boolean longEnough = password.length() >= 8;
            boolean hasLetter = password.matches(".*[A-Za-z].*");
            boolean hasNumber = password.matches(".*[0-9].*");

            if (longEnough && hasLetter && hasNumber){
                break;
            } else {
                System.out.println("Invalid Password. Try Again.");
                System.out.println();
            }
        }

        int familySize = getIntInput("Enter the size of your household: ");

        UserProfile user = new UserProfile(name, email, password, familySize);
        System.out.println("Profile Created Successfully!");
        System.out.println("Your ID is: " + user.getUserID());

        BudgetCalculator calc = new BudgetCalculator(user.getFamilySize(), DEFAULT_ALLOWANCE_RATE);
        double weeklyBudget = calc.calculateBudget();
        System.out.println("Your Weekly Carbon Budget is: " + String.format("%.2f", weeklyBudget) + " kg.");

        return user;
    }

    public static void enterWeeklyEmissions() {
    System.out.println("Enter Weekly Emissions");
    System.out.println("----------------------------");

    int weekNumber = getIntInput("Enter the Current Week Number: ");
    double foodInKg = getDoubleInput("Enter the Food Consumed (in kg): ");
    String vehicleType = getStringInput("Enter the Type of Vehicle Used (Car/Bus/Train): ");
    String fuelType = getStringInput("Enter the Type of Fuel Used (Petrol/Diesel/Electric): ");
    double vehicleDistance = getDoubleInput("Enter the Distance Travelled (in km): ");
    double waterUsage = getDoubleInput("Enter the Water Usage (in litres): ");
    double wasteGenerated = getDoubleInput("Enter the Waste Generated (in kg): ");
    double electricityUsage = getDoubleInput("Enter the Electricity Used (in kWh): ");

    ExpenseRecorder expense = new ExpenseRecorder(foodInKg, vehicleType, fuelType, vehicleDistance, waterUsage, wasteGenerated, electricityUsage);
    expense.setWeekNumber(weekNumber);
    double totalEmissions = expense.calculateTotalEmissions();

    BudgetCalculator calc = new BudgetCalculator(currentUser.getFamilySize(), DEFAULT_ALLOWANCE_RATE);
    double weeklyBudget = calc.calculateBudget();

    BudgetChecker checker = new BudgetChecker(false, 0);
    boolean withinBudget = checker.checkBudget(totalEmissions, weeklyBudget);
    double difference = withinBudget ? 0.0 : totalEmissions - weeklyBudget;

    if (withinBudget) {
        System.out.println("You were WITHIN budget for this week!");
    } else {
        System.out.println("You were ABOVE budget for this week by " + String.format("%.2f", difference) + "kg.");
    }

    // Save weekly report
    expense.saveWeeklyReport(currentUser.getUserID());

    // Create report object including budget info
    WeeklyReport report = new WeeklyReport(
        weekNumber,
        expense.foodToCO2(),
        expense.vehicleToCO2(),
        expense.waterToCO2(),
        expense.wasteToCO2(),
        expense.electricityUsageToCO2(),
        totalEmissions,
        !withinBudget,  
        difference);

    system.addReport(report);
    report.printFinalReport();
}


    private static void viewWeeklyReport(){
        int weekNumber = getIntInput("Enter week number to view Report: ");
        WeeklyReport report = system.getReport(weekNumber);

        if (report != null){
            report.printFinalReport();
        } else {
            System.out.println("No report found for Week:" + weekNumber);
        }
    }

    private static void viewAllReports(){
        System.out.println("Summary of Report");
        System.out.println(system.getAllReports());

    }

    private static boolean exitSystem(){
        System.out.println("Exit System");
        try{
            system.saveToFile("weeklyReports.csv");
            System.out.println("All reports saved successfully");

        } catch (Exception e){
            System.out.println("Error saving reports: " + e.getMessage());
        }
        return false;
    }

    private static int getIntInput(String prompt){
        while (true){
            System.out.println(prompt);
            try{
                int value = Integer.parseInt(input.nextLine());
                return value;
            } catch (NumberFormatException e){
                System.out.println("Invalid input. Enter a whole number.");
            }
        }
    }

    private static double getDoubleInput(String prompt){
        while (true){
            System.out.println(prompt);
            try{
                double value = Double.parseDouble(input.nextLine());
                return value;
            } catch (NumberFormatException e){
                System.out.println("Invalid Input. Enter a number (decimal).");
            }
        }
    }

    private static String getStringInput(String prompt){
        System.out.println(prompt);
        return input.nextLine();
    }
}
