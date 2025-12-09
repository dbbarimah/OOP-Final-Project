public class WeeklyReport implements Reportable {
    private int weekNumber;
    private double food;
    private double vehicle;
    private double water;
    private double waste;
    private double electricity;
    private double total;
    private boolean aboveBudget;
    private double difference;

    // Constructor including budget info
    public WeeklyReport(int weekNumber, double food, double vehicle, double water, double waste, double electricity, double total, boolean aboveBudget, double difference) {
        this.weekNumber = weekNumber;
        this.food = food;
        this.vehicle = vehicle;
        this.water = water;
        this.waste = waste;
        this.electricity = electricity;
        this.total = total;
        this.aboveBudget = aboveBudget;
        this.difference = difference;
    }

    // Constructor for backward compatibility (no budget info)
    public WeeklyReport(int weekNumber, double food, double vehicle, double water, double waste, double electricity, double total) {
        this(weekNumber, food, vehicle, water, waste, electricity, total, false, 0.0);
    }

    @Override
    public void printFinalReport() {
        System.out.println(
            "Report for Week: " + weekNumber + "\n" +
            "Food Emissions: " + String.format("%.2f", food) + "kg\n" +
            "Vehicle Emissions: " + String.format("%.2f", vehicle) + "kg\n" +
            "Water Emissions: " + String.format("%.2f", water) + "kg\n" +
            "Waste Emissions: " + String.format("%.2f", waste) + "kg\n" +
            "Electricity Emissions: " + String.format("%.2f", electricity) + "kg\n" +
            "Total Emissions: " + String.format("%.2f", total) + "kg"
        );

        if (aboveBudget) {
            System.out.println(" You were ABOVE budget this week by " + String.format("%.2f", difference) + "kg.");
        } else {
            System.out.println(" You were WITHIN your budget this week.");
        }
    }

    public String toFileString() {
        return weekNumber + "," + food + "," + vehicle + "," + water + "," + waste + "," + electricity + "," + total + "," + aboveBudget + "," + difference;
    }

    // Getters
    public int getWeekNumber() { 
        return weekNumber;
    }
    public double getTotal() {
        return total;
    }
    public boolean isAboveBudget() {
        return aboveBudget;
    }
    public double getDifference() {
        return difference;
    }
}
