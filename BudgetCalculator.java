public class BudgetCalculator {
    // Member Variables To Determine The Budget Of A User
    private int familySize;
    private double baseAllowancePerPerson;
    private double budget;

    // Parametised Constructor For Determining Budget
    public BudgetCalculator(int familySize, double baseAllowancePerPerson, double budget){
        this.familySize = familySize;
        this.baseAllowancePerPerson = baseAllowancePerPerson;
        this.budget = budget;
    }

    // Method To Calculate The Weekly Budget
    public double calculateBudget(){
        budget = familySize * baseAllowancePerPerson;
        return budget;
    }

    // Getter For The Weekly Budget
    public double getWeeklyBudget(){
        return budget;
    }
}