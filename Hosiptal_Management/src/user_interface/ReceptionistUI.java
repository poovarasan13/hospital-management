package user_interface;

import entities.Appointment;
import entities.Patient;
import services.PatientService;
import services.AppointmentService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReceptionistUI {
    private final PatientService patientService;
    private final AppointmentService appointmentService;
    private final Scanner scanner;

    public ReceptionistUI() {
        this.patientService = new PatientService();
        this.appointmentService = new AppointmentService();
        this.scanner = new Scanner(System.in);
    }

    public void start() throws ClassNotFoundException, ParseException {
        int choice;
        do {
            System.out.println("Receptionist Menu:");
            System.out.println("1. Add Patient");
            System.out.println("2. Update Patient");
            System.out.println("3. Delete Patient");
            System.out.println("4. Get All Patients");
            System.out.println("5. Schedule Appointment");
            System.out.println("6. ReSchedule Appointment");
            System.out.println("7. Cancel Appointment");
            System.out.println("8.Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    updatePatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    getAllPatients();
                    break;
                case 5:
                    scheduleAppointment();
                    break;
                case 6:
                	rescheduleAppointment();
                	break;
                case 7:
                	cancelAppointment();
                	break;
                case 8:
                	 System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        } while (choice != 6);
    }

    public void addPatient() throws ClassNotFoundException, ParseException {
        System.out.println("Enter patient details:");
        System.out.print("Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Contact Information: ");
        int contactInformation = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Join Date (YYYY-MM-DD): ");
        String joinDateStr = scanner.nextLine();
        Date joinDate = java.sql.Date.valueOf(joinDateStr);
        System.out.print("Disease: ");
        String disease = scanner.nextLine();
        System.out.print("Medical History: ");
        String medicalHistory = scanner.nextLine();

        Patient patient = new Patient();
        patient.setPatient_id(id);
        patient.setName(name);
        patient.setAge(age);
        patient.setGender(gender);
        patient.setContact_information(contactInformation);
        patient.setJoin_date((java.sql.Date) joinDate);
        patient.setDisease(disease);
        patient.setMedical_history(medicalHistory);

        patientService.addPatient(patient);
        System.out.println("Patient added successfully!");

        // Schedule an appointment for the added patient
        System.out.println("Do you want to schedule an appointment for this patient? (yes/no)");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("yes")) {
            scheduleAppointment();
        }
    }

    public void updatePatient() throws ClassNotFoundException {
        System.out.println("Enter patient ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if the patient with the given ID exists
        Patient existingPatient = patientService.getPatientById(id);
        if (existingPatient == null) {
            System.out.println("Patient with ID " + id + " does not exist.");
            return;
        }

        System.out.println("Enter new patient details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Contact Information: ");
        int contactInformation = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Join Date (YYYY-MM-DD): ");
        String joinDateStr = scanner.nextLine();
        Date joinDate = java.sql.Date.valueOf(joinDateStr);
        System.out.print("Disease: ");
        String disease = scanner.nextLine();
        System.out.print("Medical History: ");
        String medicalHistory = scanner.nextLine();

        // Update the patient object
        existingPatient.setName(name);
        existingPatient.setAge(age);
        existingPatient.setGender(gender);
        existingPatient.setContact_information(contactInformation);
        existingPatient.setJoin_date((java.sql.Date) joinDate);
        existingPatient.setDisease(disease);
        existingPatient.setMedical_history(medicalHistory);

        // Update patient in the database
        patientService.updatePatient(existingPatient);
        System.out.println("Patient updated successfully!");
    }

    public void deletePatient() throws ClassNotFoundException {
        System.out.println("Enter patient ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Delete patient from the database
        patientService.deletePatient(id);
        System.out.println("Patient with ID " + id + " deleted successfully!");
    }

    public void getAllPatients() throws ClassNotFoundException {
        List<Patient> patients = patientService.getAllPatients();
        System.out.println("All Patients:");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    public void scheduleAppointment() throws ClassNotFoundException, ParseException {
        System.out.println("Enter Appointment Details:");

        System.out.print("Appointment ID: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Staff ID: ");
        int staffId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Date (yyyy-MM-dd): ");
       String dateString = scanner.nextLine();
        Date date=java.sql.Date.valueOf(dateString);
       

        System.out.print("Status: ");
        String status = scanner.nextLine();

        // Parse date and time strings into Date object
      
       
        Appointment appointment = new Appointment(appointmentId, patientId, staffId, date, status);

        // Call appointment service to schedule the appointment
        boolean success = appointmentService.scheduleAppointment(appointment);
        if (success) {
            System.out.println("Appointment scheduled successfully.");
        } else {
            System.out.println("Failed to schedule appointment. Please try again.");
        }
    }
    private void rescheduleAppointment() throws ClassNotFoundException {
        System.out.println("Enter Appointment ID to reschedule:");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new date (yyyy-MM-dd HH:mm:ss): ");
        String newDateString = scanner.nextLine();

        // Parse date string into Date object
        Date newDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newDate = dateFormat.parse(newDateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd HH:mm:ss format.");
            return;
        }

        boolean success = appointmentService.rescheduleAppointment(appointmentId, newDate);
        if (success) {
            System.out.println("Appointment rescheduled successfully.");
        } else {
            System.out.println("Failed to reschedule appointment. Please try again.");
        }
    }

    private void cancelAppointment() throws ClassNotFoundException {
        System.out.println("Enter Appointment ID to cancel:");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = appointmentService.cancelAppointment(appointmentId);
        if (success) {
            System.out.println("Appointment canceled successfully.");
        } else {
            System.out.println("Failed to cancel appointment. Please try again.");
        }
    }


    public static void main(String[] args) throws ClassNotFoundException, ParseException {
        ReceptionistUI receptionistUI = new ReceptionistUI();
        receptionistUI.start();
    }
}
