public class BudgetCalculator {
    // Member Variables To Determine The Budget Of A User
    private int familySize;
    private double allowanceRate;

    // Parametised Constructor For Determining Budget
    public BudgetCalculator(int familySize, double allowanceRate){
        this.familySize = familySize;
        this.allowanceRate = allowanceRate;
    }

    // Method To Calculate The Weekly Budget
    public double calculateBudget(){
        return familySize * allowanceRate;
    }
}
