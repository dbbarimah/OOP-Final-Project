public class ExpenseRecorder {
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

    // Methods To Calculate The Emissions Of Each Household Process
    // Calculation For Food
    public double foodToCO2() {
        double emissionFactor = 5.0;
        return foodEmission = foodInKg * emissionFactor;
    }

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

    // Calculation For Water Usage
    public double waterToCO2() {
        double emissionFactor = 0.0005;
        return waterEmission = waterUsage * emissionFactor;
    }

    // Calculation For Waste Generated
    public double wasteToCO2() {
        double emissionFactor = 0.25;
        return wasteEmission = wasteGenerated * emissionFactor;
    }

    // Calculation For Emssions from Electricity Usage
    public double electricityUsageToCO2() {
        double emissionFactor = 0.475;
        return electricityEmission = electricityUsage * emissionFactor;
    }

    // Calculation For The Total Emission
    public double calculateTotalEmissions() {
        totalEmissions = foodEmission + vehicleEmission + waterEmission + wasteEmission + electricityEmission;
        return totalEmissions;
    }

    // Getters For The Various Emissions
    public double getFoodEmission(){
        return foodEmission;
    }
    public double getVehicleEmission(){
        return vehicleEmission;
    }
    public double getWaterEmission(){
        return waterEmission;
    }
    public double getWasteEmission(){
        return wasteEmission;
    }
    public double getElectricityEmission(){
        return electricityEmission;
    }
    public double getTotalEmissions(){
        return totalEmissions;
    }
}

