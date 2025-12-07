import java.io.*;

public class ExpenseRecorder implements Expense{
    // Member Variables to Calculate The Expenses
    private double foodInKg;
    private String vehicleType;
    private String fuelType;
    private double vehicleDistance;
    private double waterUsage;
    private double wasteGenerated;
    private double electricityUsage;

    private double vehicleFactor;
    private double fuelFactor;

    private double foodEmission;
    private double vehicleEmission;
    private double waterEmission;
    private double wasteEmission;
    private double electricityEmission;
    private double totalEmissions;
    private int weekNumber;

    // Parametised Constructor For The Expenses Calculation
    public ExpenseRecorder(
            double foodInKg,
            String vehicleType,
            String fuelType,
            double vehicleDistance,
            double waterUsage,
            double wasteGenerated,
            double electricityUsage) {

        this.foodInKg = foodInKg;
        this.vehicleType = vehicleType;
        this.fuelType = fuelType;
        this.vehicleDistance = vehicleDistance;
        this.waterUsage = waterUsage;
        this.wasteGenerated = wasteGenerated;
        this.electricityUsage = electricityUsage;
    }

    @Override
    // Methods To Calculate The Emissions Of Each Household Process
    // Calculation For Food
    public double foodToCO2() {
        foodEmission = foodInKg * 5.0;
        return foodEmission;
    }

    @Override
    // Calculation For Different Types Of Vehicles And Their Corresponding Fuel Type
    public double vehicleToCO2() {
       // Emission Based On Type Of Vehicle
        switch (vehicleType.toLowerCase()) {
            case "car":
                vehicleFactor = 0.21;
                break;
            case "bus":
                vehicleFactor = 0.105;
                break;
            case "train":
                vehicleFactor = 0.041;
                break;
            default:
                vehicleFactor = 0;
        }

        // Emission Based On Type Of Fuel
        switch (fuelType.toLowerCase()) {
            case "petrol":
                fuelFactor = 1.0;
                break;
            case "diesel":
                fuelFactor = 1.15;
                break;
            case "electric":
                fuelFactor = 0.20;
                break;
            default:
                fuelFactor = 1.0;
        }

        if (vehicleType.equalsIgnoreCase("train") && fuelType.equalsIgnoreCase("petrol")){
            System.out.println("Trains do not run on petrol.");
            vehicleEmission = 0;

        } else {
            vehicleEmission = vehicleDistance * vehicleFactor * fuelFactor;
        }
        
        return vehicleEmission;
    }

    @Override
    // Calculation For Water Usage
    public double waterToCO2() {
        waterEmission = waterUsage * 0.0005;
        return wasteEmission;
    }

    @Override
    // Calculation For Waste Generated
    public double wasteToCO2() {
        wasteEmission = wasteGenerated * 0.25;
        return wasteEmission;
    }

    @Override
    // Calculation For Emssions from Electricity Usage
    public double electricityUsageToCO2() {
        electricityEmission = electricityUsage * 0.475;
        return electricityEmission;
    }

    @Override
    // Calculation For The Total Emission
    public double calculateTotalEmissions() {
        totalEmissions = foodEmission + vehicleEmission + waterEmission + wasteEmission + electricityEmission;
        return totalEmissions;
    }

    //Necessary Getter
    public double getTotalEmissions(){
        return totalEmissions;
    }

    public void saveWeeklyReport(int WeekNumber, String userID) {
        try{
            File reportsFolder = new File("reports");
            if (!reportsFolder.exists()){
                reportsFolder.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(reportsFolder + "/" + userID + "_week" + weekNumber + ".txt"));

            writer.write("Week: " + weekNumber + "Report\n");
            writer.write("Food: " + foodEmission + "\n");
            writer.write("Vehicle: " + vehicleEmission + "\n");
            writer.write("Water: " + waterEmission + "\n");
            writer.write("Waste: " + wasteEmission + "\n");
            writer.write("Electricity: " + electricityEmission + "\n");
            writer.write("Total: " + totalEmissions + "\n");

            writer.close();
            System.out.println("Report Saved Succesfully!");
            
        } catch (IOException e){
            System.out.println("Error saving report: " + e.getMessage());
        }
        
    }
}
