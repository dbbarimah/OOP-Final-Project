import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CarbonEmissionsGUI extends JFrame {

    private UserProfile currentUser = null;
    private CarbonEmissionsSystem system = new CarbonEmissionsSystem();

    // Panels
    private JPanel loginPanel, mainPanel, reportPanel, summaryPanel;

    public CarbonEmissionsGUI() {
        setTitle("Carbon Emissions Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load reports
        try {
            system.loadFromFile("weeklyReports.csv");
        } catch (Exception e) {
            System.out.println("No reports found: " + e.getMessage());
        }

        initLoginPanel();
        setVisible(true);
    }

    private void initLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name: ");
        JTextField nameField = new JTextField();

        JLabel emailLabel = new JLabel("Email: ");
        JTextField emailField = new JTextField();

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();

        JLabel familyLabel = new JLabel("Household size: ");
        JTextField familyField = new JTextField();

        JButton createButton = new JButton("Create Profile");

        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(familyLabel);
        loginPanel.add(familyField);
        loginPanel.add(new JLabel());
        loginPanel.add(createButton);

        add(loginPanel);

        createButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            int familySize;

            try {
                familySize = Integer.parseInt(familyField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid household size.");
                return;
            }

            if (!isValidPassword(password)) {
                JOptionPane.showMessageDialog(this, "Password must be >= 8 chars and contain letters & numbers.");
                return;
            }

            currentUser = new UserProfile(name, email, password, familySize);
            JOptionPane.showMessageDialog(this, "Profile created! Your ID: " + currentUser.getUserID()
                    + "\nWeekly Budget: " + (familySize * 90.0) + "kg");

            remove(loginPanel);
            initMainPanel();
            revalidate();
            repaint();
        });
    }

    private boolean isValidPassword(String password) {
        boolean longEnough = password.length() >= 8;
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        return longEnough && hasLetter && hasNumber;
    }

    private void initMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton enterBtn = new JButton("Enter Weekly Emissions");
        JButton viewReportBtn = new JButton("View Weekly Report");
        JButton viewSummaryBtn = new JButton("View Summary of All Reports");
        JButton exitBtn = new JButton("Exit");

        mainPanel.add(enterBtn);
        mainPanel.add(viewReportBtn);
        mainPanel.add(viewSummaryBtn);
        mainPanel.add(exitBtn);

        add(mainPanel);

        enterBtn.addActionListener(e -> enterWeeklyEmissionsGUI());
        viewReportBtn.addActionListener(e -> viewWeeklyReportGUI());
        viewSummaryBtn.addActionListener(e -> viewAllReportsGUI());
        exitBtn.addActionListener(e -> {
            try {
                system.saveToFile("weeklyReports.csv");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving reports: " + ex.getMessage());
            }
            System.exit(0);
        });
    }

    private void enterWeeklyEmissionsGUI() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        JTextField weekField = new JTextField();
        JTextField foodField = new JTextField();
        JTextField vehicleField = new JTextField();
        JTextField fuelField = new JTextField();
        JTextField distanceField = new JTextField();
        JTextField waterField = new JTextField();
        JTextField wasteField = new JTextField();
        JTextField electricityField = new JTextField();

        panel.add(new JLabel("Week Number:"));
        panel.add(weekField);
        panel.add(new JLabel("Food (kg):"));
        panel.add(foodField);
        panel.add(new JLabel("Vehicle Type:"));
        panel.add(vehicleField);
        panel.add(new JLabel("Fuel Type:"));
        panel.add(fuelField);
        panel.add(new JLabel("Distance (km):"));
        panel.add(distanceField);
        panel.add(new JLabel("Water (L):"));
        panel.add(waterField);
        panel.add(new JLabel("Waste (kg):"));
        panel.add(wasteField);
        panel.add(new JLabel("Electricity (kWh):"));
        panel.add(electricityField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Weekly Emissions",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int weekNumber = Integer.parseInt(weekField.getText().trim());
                double food = Double.parseDouble(foodField.getText().trim());
                String vehicle = vehicleField.getText().trim();
                String fuel = fuelField.getText().trim();
                double distance = Double.parseDouble(distanceField.getText().trim());
                double water = Double.parseDouble(waterField.getText().trim());
                double waste = Double.parseDouble(wasteField.getText().trim());
                double electricity = Double.parseDouble(electricityField.getText().trim());

                ExpenseRecorder expense = new ExpenseRecorder(food, vehicle, fuel, distance, water, waste, electricity);
                expense.setWeekNumber(weekNumber);
                double totalEmissions = expense.calculateTotalEmissions();

                BudgetCalculator calc = new BudgetCalculator(currentUser.getFamilySize(), 90.0);
                double weeklyBudget = calc.calculateBudget();

                BudgetChecker checker = new BudgetChecker(false, 0);
                boolean withinBudget = checker.checkBudget(totalEmissions, weeklyBudget);
                double difference = withinBudget ? 0.0 : totalEmissions - weeklyBudget;

                String budgetMessage = withinBudget ? "You were WITHIN budget for this week!"
                        : "You were ABOVE budget for this week by " + String.format("%.2f", difference) + "kg.";

                expense.saveWeeklyReport(currentUser.getUserID());

                WeeklyReport report = new WeeklyReport(weekNumber,
                        expense.foodToCO2(),
                        expense.vehicleToCO2(),
                        expense.waterToCO2(),
                        expense.wasteToCO2(),
                        expense.electricityUsageToCO2(),
                        totalEmissions,
                        !withinBudget,
                        difference);

                system.addReport(report);

                JOptionPane.showMessageDialog(this, "Report saved!\n" + budgetMessage);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
            }
        }
    }

    private void viewWeeklyReportGUI() {
        String input = JOptionPane.showInputDialog(this, "Enter week number:");
        if (input != null) {
            try {
                int weekNumber = Integer.parseInt(input.trim());
                WeeklyReport report = system.getReport(weekNumber);
                if (report != null) {
                    JOptionPane.showMessageDialog(this, formatReport(report));
                } else {
                    JOptionPane.showMessageDialog(this, "No report found for Week " + weekNumber);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid week number.");
            }
        }
    }

    private void viewAllReportsGUI() {
        JOptionPane.showMessageDialog(this, system.getAllReports());
    }

    private String formatReport(WeeklyReport report) {
        return String.format(
                "Week: %d%nFood: %.2f kg%nVehicle: %.2f kg%nWater: %.2f kg%nWaste: %.2f kg%nElectricity: %.2f kg%nTotal: %.2f kg",
                report.getWeekNumber(),
                report.getFood(),
                report.getVehicle(),
                report.getWater(),
                report.getWaste(),
                report.getElectricity(),
                report.getTotal());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CarbonEmissionsGUI::new);
    }
}
