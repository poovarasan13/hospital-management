package user_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entities.Department;
import entities.Staff;
import services.DepartmentService;
import services.StaffService;

public class MedicalStaff {
    public static void main(String[] args) throws ClassNotFoundException {
        DepartmentService departmentService = new DepartmentService();
        StaffService staffService = new StaffService();
        Scanner scanner = new Scanner(System.in);

        displayMenu(scanner, departmentService, staffService);

        scanner.close();
    }

    private static void displayMenu(Scanner scanner, DepartmentService departmentService, StaffService staffService) throws ClassNotFoundException {
        int choice;
        do {
            System.out.println("\nMedical Staff Menu:");
            System.out.println("1. View All Departments");
            System.out.println("2. Add New Department");
            System.out.println("3. Add New Staff");
            System.out.println("4. Update Staff");
            System.out.println("5. Delete Staff");
            System.out.println("6. View All Staff");
            System.out.println("7.Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllDepartments(departmentService);
                    break;
                case 2:
                    addNewDepartment(scanner, departmentService);
                    break;
                case 3:
                    addNewStaff(scanner, staffService);
                    break;
                case 4:
                    updateStaff(scanner, staffService);
                    break;
                case 5:
                    deleteStaff(scanner, staffService);
                    break;
                case 6:
                	ViewAllStaff(staffService);
                	break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        } while (choice != 7);
    }

    private static void ViewAllStaff(StaffService staffService) throws ClassNotFoundException {
    	System.out.println("\nAll Staffs:");
        List<Staff> allStaff = staffService.getAllStaff();
           for(Staff staff :allStaff) {
        	   System.out.println(staff);
           }
		
	}

	private static void viewAllDepartments(DepartmentService departmentService) throws ClassNotFoundException {
        System.out.println("\nAll Departments:");
        List<Department> allDepartments = departmentService.getAllDepartments();
        for (Department department : allDepartments) {
            System.out.println(department);
        }
    }

    private static void addNewDepartment(Scanner scanner, DepartmentService departmentService) throws ClassNotFoundException {
        System.out.println("\nAdding a new department...");
        System.out.print("Enter department ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter department name: ");
        String name = scanner.nextLine();
        System.out.print("Enter department description: ");
        String description = scanner.nextLine();
        Department newDepartment = new Department(id, name, description);
        departmentService.addDepartment(newDepartment);
        System.out.println("Department added successfully.");
    }

    private static void addNewStaff(Scanner scanner, StaffService staffService) throws ClassNotFoundException {
        System.out.println("\nAdding a new staff member...");
        System.out.print("Enter staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter department ID: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff role: ");
        String role = scanner.nextLine();

        Staff newStaff = new Staff(staffId, role, name, departmentId);
        staffService.addStaff(newStaff);
        System.out.println("Staff added successfully.");
    }

    private static void updateStaff(Scanner scanner, StaffService staffService) throws ClassNotFoundException {
        System.out.println("\nUpdating an existing staff member...");
        System.out.print("Enter staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new department ID: ");
        int departmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new staff role: ");
        String role = scanner.nextLine();

        Staff updatedStaff = new Staff(staffId, role, name, departmentId);
        staffService.updateStaff(updatedStaff);
        System.out.println("Staff updated successfully.");
    }

    private static void deleteStaff(Scanner scanner, StaffService staffService) throws ClassNotFoundException {
        System.out.println("\nDeleting an existing staff member...");
        System.out.print("Enter staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        staffService.deleteStaff(staffId);
        System.out.println("Staff deleted successfully.");
    }
    
}
