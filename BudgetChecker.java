public class BudgetChecker {
    //Member Variables To Check If The User Is Within Budget
    private boolean isWithinBudget;
    private double difference;

    //Parametised Constructor To Check If The User Is Within Budget
    public BudgetChecker(boolean isWithinBudget, double difference){
        this.isWithinBudget = isWithinBudget;
        this.difference = difference;
    }

    //Method To Check If The User Is Within Budget
    public boolean withinBudget(double totalEmissions, double budget){
        System.out.println("Your total emissions for the week were " + totalEmissions + "kg.");
        System.out.println("Your budget for the week was " + budget + "kg.");

        if (totalEmissions < budget){
            isWithinBudget = true;
            System.out.println("You were withing budget for this week.");

        } else {
            isWithinBudget = false;
            difference = totalEmissions - budget;
            System.out.println("You were NOT within your budget for this week.");
            System.out.println("You exceeded our budget for this week by " + difference + "kg.");
        }
        
        return isWithinBudget;
    }

    //Getter To Check If The User Is Within Budget
    public boolean getIsWithinBudget(){
        return isWithinBudget;
    }
}public class BudgetChecker {
    //Member Variables To Check If The User Is Within Budget
    private boolean isWithinBudget;
    private double difference;

    //Parametised Constructor To Check If The User Is Within Budget
    public BudgetChecker(boolean isWithinBudget, double difference){
        this.isWithinBudget = isWithinBudget;
        this.difference = difference;
    }

    //Method To Check If The User Is Within Budget
    public boolean withinBudget(double totalEmissions, double budget){
        System.out.println("Your total emissions for the week were " + totalEmissions + "kg.");
        System.out.println("Your budget for the week was " + budget + "kg.");

        if (totalEmissions < budget){
            isWithinBudget = true;
            System.out.println("You were withing budget for this week.");

        } else {
            isWithinBudget = false;
            difference = totalEmissions - budget;
            System.out.println("You were NOT within your budget for this week.");
            System.out.println("You exceeded our budget for this week by " + difference + "kg.");
        }
        
        return isWithinBudget;
    }

    //Getter To Check If The User Is Within Budget
    public boolean getIsWithinBudget(){
        return isWithinBudget;
    }
}
