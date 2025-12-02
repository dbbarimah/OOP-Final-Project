public class WeeklyReport {
    // Member Variables For The WeeklyReport
    private int weekNumber;
    private double food;
    private double vehicle;
    private double water;
    private double waste;
    private double electricity;
    private double total;

    // Parametised Constructor For The WeekyReport
    public WeeklyReport(
        int weekNumber,
        double food,
        double vehicle,
        double water,
        double waste,
        double electricity,
        double total
    ){
        this.weekNumber = weekNumber;
        this.food = food;
        this.vehicle = vehicle;
        this.water = water;
        this.waste = waste;
        this.electricity = electricity;
        this.total = total;
    }

    //Essential Getters To Retrieve the Report For A Specified Week
    public int getWeekNumber(){
        return weekNumber;
    }
    public double getTotal(){
        return total;
    }

    //Method To Print Out The Final Report Of The User
    public void printFinalReport(){
        System.out.println("This is your report for Week " + weekNumber + "\n" +
        "Food Emissions: " + food + "\n" +
        "Vehicle Emissions: " + vehicle + "\n" +
        "Water Emissions: " + water + "\n" + 
        "Waste Emissions: " + waste + "\n" +
        "Electricity Emissions: " + electricity);
    }
}
