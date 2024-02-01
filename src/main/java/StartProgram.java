import java.util.List;
import java.util.Scanner;
import controller.EmployeeManager;
import model.Employee;
/**
 * @author aidan - agdavidson
 * CIS175 - Spring 2024
 * Feb 1, 2024
 */
public class StartProgram {

    static Scanner in = new Scanner(System.in);
    static EmployeeManager employeeManager = new EmployeeManager();

    public static void main(String[] args) {
        runMenu();
    }

    public static void runMenu() {
        boolean goAgain = true;
        System.out.println("--- Welcome to Employee Management System! ---");
        while (goAgain) {
            System.out.println("*  Select an option:");
            System.out.println("*  1 -- Add an employee");
            System.out.println("*  2 -- Edit an employee");
            System.out.println("*  3 -- Delete an employee");
            System.out.println("*  4 -- View all employees");
            System.out.println("*  5 -- Exit");
            System.out.print("*  Your selection: ");
            int selection = in.nextInt();
            in.nextLine();

            switch (selection) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    editEmployee();
                    break;
                case 3:
                    deleteEmployee();
                    break;
                case 4:
                    viewAllEmployees();
                    break;
                case 5:
                    employeeManager.cleanUp();
                    goAgain = false;
                    break;
                default:
                    System.out.println("Invalid selection!");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = in.nextLine();
        System.out.print("Enter employee address: ");
        String address = in.nextLine();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setAddress(address);

        employeeManager.insertEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private static void editEmployee() {
        System.out.print("Enter employee ID to edit: ");
        int idToEdit = in.nextInt();
        in.nextLine();

        Employee employee = employeeManager.searchForEmployeeById(idToEdit);
        if (employee != null) {
            System.out.print("Enter new employee name: ");
            String newName = in.nextLine();
            System.out.print("Enter new employee address: ");
            String newAddress = in.nextLine();

            employee.setName(newName);
            employee.setAddress(newAddress);

            employeeManager.updateEmployee(employee);
            System.out.println("Employee updated successfully.");
        } else {
            System.out.println("Employee not found with ID: " + idToEdit);
        }
    }

    private static void deleteEmployee() {
        System.out.print("Enter employee ID to delete: ");
        int idToDelete = in.nextInt();
        in.nextLine();

        Employee employee = employeeManager.searchForEmployeeById(idToDelete);
        if (employee != null) {
            employeeManager.deleteEmployee(employee);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found with ID: " + idToDelete);
        }
    }

    private static void viewAllEmployees() {
        List<Employee> allEmployees = employeeManager.showAllEmployees();
        if (allEmployees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            System.out.println("Employees:");
            for (Employee emp : allEmployees) {
                System.out.println(emp.getId() + ": " + emp.getName() + ", " + emp.getAddress());
            }
        }
    }
}