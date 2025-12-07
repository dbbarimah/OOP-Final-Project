public class BudgetCalculator {
    // Member Variables To Determine The Budget Of A User
    private int familySize;
    private double baseAllowancePerPerson;

    // Parametised Constructor For Determining Budget
    public BudgetCalculator(int familySize, double baseAllowancePerPerson){
        this.familySize = familySize;
        this.baseAllowancePerPerson = baseAllowancePerPerson;
    }

    // Method To Calculate The Weekly Budget
    public double calculateBudget(){
        return familySize * baseAllowancePerPerson;
    }
}