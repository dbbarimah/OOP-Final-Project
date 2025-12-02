import java.util.HashMap;

public class ReportGenerator{
    private HashMap<Integer, WeeklyReport> allReports = new HashMap<>();

    public void addReport(WeeklyReport report){
        allReports.put(report.getWeekNumber(), report);
    }

    public void generateReport(int weekNumber){
        if (allReports.containsKey(weekNumber)){
            report.get(weekNumber).printFinalReport();
        } else {
            System.out.println("No report found for Week: " + weekNumber);
        }
    }
}
