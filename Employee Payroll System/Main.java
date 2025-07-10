
import java.util.ArrayList;

// ==============================
// 🔹 Abstract Base Class: Employee
// ==============================

abstract class Employee {
    private String name;
    private int id;

    // 🔸 Constructor
    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // 🔸 Getter methods (Encapsulation)
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // 🔸 Abstract method: must be defined in child classes
    public abstract double calculateSalary();

    // 🔸 Display employee info
    @Override
    public String toString() {
        return "Employee [Name = " + name + ", ID = " + id + ", Salary = ₹" + calculateSalary() + "]";
    }
}

// ==============================
// 🔹 Full-Time Employee Class
// ==============================

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    // 🔸 Constructor
    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id); // Pass name and id to parent
        this.monthlySalary = monthlySalary;
    }

    // 🔸 Salary calculation for full-time employee
    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// ==============================
// 🔹 Part-Time Employee Class
// ==============================

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    // 🔸 Constructor
    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    // 🔸 Salary calculation for part-time employee
    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// ==============================
// 🔹 Payroll System Class
// ==============================

class PayrollSystem {
    private ArrayList<Employee> employeeList;

    // 🔸 Constructor
    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    // 🔸 Add an employee
    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    // 🔸 Remove employee by ID
    public void removeEmployee(int id) {
        Employee employeeToRemove = null;

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }

        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("✅ Employee with ID " + id + " removed.\n");
        } else {
            System.out.println("❌ Employee with ID " + id + " not found.\n");
        }
    }

    // 🔸 Display all employees
    public void displayEmployee() {
        if (employeeList.isEmpty()) {
            System.out.println("⚠️ No employees to display.\n");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);
            }
        }
    }
}

// ==============================
// 🔹 Main Class (Entry Point)
// ==============================

public class Main {
    public static void main(String[] args) {

        // 🌟 Welcome message
        System.out.println("=======================================");
        System.out.println("      💼 PAYROLL MANAGEMENT SYSTEM 💼");
        System.out.println("=======================================\n");

        PayrollSystem payrollSystem = new PayrollSystem();

        // 🔸 Create employees
        FullTimeEmployee emp1 = new FullTimeEmployee("SG", 1, 70000);
        PartTimeEmployee emp2 = new PartTimeEmployee("SS", 2, 40, 1000);

        // 🔸 Add employees
        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);

        // 🔸 Display all employees
        System.out.println("📋 Initial Employee Details:\n----------------------------");
        payrollSystem.displayEmployee();

        // 🔸 Remove an employee
        System.out.println("\n🗑️ Removing Employee with ID 2...");
        payrollSystem.removeEmployee(2);

        // 🔸 Display remaining employees
        System.out.println("📋 Remaining Employee Details:\n------------------------------");
        payrollSystem.displayEmployee();

        // 🌟 Exit message
        System.out.println("\n✅ Payroll processing completed successfully!");
        System.out.println("=======================================\n");
    }
}
