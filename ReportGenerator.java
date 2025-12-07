import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class ReportGenerator{
    private TreeMap<Integer, String> allReports = new TreeMap<>();

    public void addReport(Int weekNumber, String filename){
        allReports.put(weekNumber, filename);
    }

    public void generateReport(int weekNumber){
        if (allReports.containsKey(weekNumber)){
            report.get(weekNumber).printFinalReport();
            String filename = allReports.get(weekNumber);
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
                String line;
                while((line = reader.readLine() != null)){
                    System.out.println(line);
                }
            } catch (IOException e){
                System.out.println("Error reading report: " + e.getMessage());
            }
        } else {
            System.out.println("No report found for week: " + weekNumber);
        }
    }
}
