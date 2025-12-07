import java.io.*;

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

    public WeeklyReport(String filename){
        try (BufferedReader reader = new BufferedReader(new Filename(filename))){
            reader.readLine();
            this.food = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.vehicle = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.water = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.waste = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.electricity = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.total = Double.parseDouble(reader.readLine().split(": ")[1]);
            this.weekNumber = Integer.parseInt(filename.replaceAll("\\D+",""));
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    //Necessary Getters
    public int getWeekNumber(){
        return weekNumber;
    }
    public double getTotal(){
        return total;
    }

    //Method To Print Report
    public void printFinalReport(){
        System.out.println("This is your report for Week: " + weekNumber + "\n" +
                "Food Emissions: " + food + "\n" +
                "Vehicle Emissions: " + vehicle + "\n" +
                "Water Emissions: " + water + "\n" +
                "Waste Emissions: " + waste + "\n" + 
                "Electricity Emissions: " + electricity + "\n" +
                "Total Emissions: " + total);
    }
}