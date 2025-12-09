import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class CarbonEmissionsSystem {

    private TreeMap<Integer, WeeklyReport> reports = new TreeMap<>();

    //Adds a report to the system
    public void addReport(WeeklyReport report){
        reports.put(report.getWeekNumber(), report);
        
    }

    public WeeklyReport getReport(int week){
        return reports.get(week);
    }

    public String getAllReports(){
        if (reports.isEmpty()){
            return "No reports available.";
        }

        StringBuilder string = new StringBuilder();
        for (WeeklyReport values: reports.values()){
            string.append("Week ").append(values.getWeekNumber())
            .append(" - Total: ").append(values.getTotal()).append("kg\n");
        }
        return string.toString();
    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (WeeklyReport report : reports.values()) {
            writer.write(report.toFileString());
            writer.newLine();
        }
        writer.close();
    }


    public void loadFromFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int weekNumber = Integer.parseInt(parts[0]);
            double food = Double.parseDouble(parts[1]);
            double vehicle = Double.parseDouble(parts[2]);
            double water = Double.parseDouble(parts[3]);
            double waste = Double.parseDouble(parts[4]);
            double electricity = Double.parseDouble(parts[5]);
            double total = Double.parseDouble(parts[6]);

            boolean aboveBudget = parts.length > 7 && Boolean.parseBoolean(parts[7]);
            double difference = parts.length > 8 ? Double.parseDouble(parts[8]) : 0.0;

            WeeklyReport report = new WeeklyReport(weekNumber, food, vehicle, water, waste, electricity, total, aboveBudget, difference);
            reports.put(weekNumber, report);
        }
        reader.close();
    }
}
