import java.util.HashMap;

public class ReportGenerator{
    private HashMap<Integer, WeeklyReport> allReports = new HashMap<>();

    public void addReport(WeeklyReport report){
        report.put(Report.getWeekNumber(), report);
    }

    public void generateReport(int weekNumber){
        
    }
}
