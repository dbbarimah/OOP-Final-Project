public class BudgetChecker {
    //Member Variables To Check If The User Is Within Budget
    private boolean isWithinBudget;
    private double difference;

    //Parametised Constructor To Check If The User Is Within Budget
    public BudgetChecker(boolean isWithinBudget, double difference){
        this.isWithinBudget = isWithinBudget;
        this.difference = difference;
    }

    //Method To Check If The User Is Within Budget Or Not
    public boolean checkBudget(double totalEmissions, double budget){
        System.out.println("Total Emissions: " + totalEmissions + "kg");
        System.out.println("Your budget for the week is: " + budget + "kg");

        if (totalEmissions <= budget){
            isWithinBudget = true;
            System.out.println("Your are within your budget!");
        } else {
            isWithinBudget = false;
            difference = totalEmissions - budget;
            System.out.println("You exceeded your budget by " + difference + "kg.");
        }
        return isWithinBudget;
    }
}
