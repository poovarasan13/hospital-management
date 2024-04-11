package services;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import database_connection.DatabaseConnection;
import entities.Appointment;

public class AppointmentService {

    // Method to schedule an appointment
	public boolean scheduleAppointment(Appointment appointment) throws ClassNotFoundException {
	    String query = "INSERT INTO appointment (appointment_id, patient_id, staff_id, date, status) VALUES (?, ?, ?, ?,  ?)";
	    try (Connection connection = DatabaseConnection.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, appointment.getAppointment_id());
	        statement.setInt(2, appointment.getPatient_id());
	        statement.setInt(3, appointment.getStaff_id());
	        statement.setDate(4, new java.sql.Date(appointment.getDate().getTime())); // Convert java.util.Date to java.sql.Date
	        statement.setString(5, appointment.getStatus());

	        return statement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    // Method to retrieve all appointments for a specific staff member
    public List<Appointment> getAppointmentsForStaff(int staff_id) throws ClassNotFoundException {
        List<Appointment> appointments = new ArrayList<>();
        String query = "SELECT * FROM appointments WHERE staff_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, staff_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int appointment_id = resultSet.getInt("appointment_id");
                int patient_id = resultSet.getInt("patient_id");
                Date date = resultSet.getDate("date");

                String status = resultSet.getString("status");
                // Assuming you have a constructor in Appointment class that accepts these parameters
                appointments.add(new Appointment(appointment_id, patient_id, staff_id, date, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointments;
    }
    
    public boolean rescheduleAppointment(int appointmentId, Date newDate) throws ClassNotFoundException {
        String query = "UPDATE appointments SET date = ? WHERE appointment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, new java.sql.Date(newDate.getTime())); // Convert java.util.Date to java.sql.Date
            statement.setInt(2, appointmentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean cancelAppointment(int appointmentId) throws ClassNotFoundException {
        String query = "DELETE FROM appointments WHERE appointment_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, appointmentId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
