package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database_connection.DatabaseConnection;
import entities.Patient;

public class PatientService {

	 
	 public void addPatient(Patient patient) throws ClassNotFoundException {
	        String query = "INSERT INTO patient (patient_id,name, age,gender,contact_information,join_date,disease,medical_history) VALUES (?, ?, ?, ?,?,?,?,?)";
	        try (Connection connection = DatabaseConnection.getConnection();
	        		
	 
	             PreparedStatement statement = connection.prepareStatement(query)) {
	        	statement.setInt(1, patient.getPatient_id());
	            statement.setString(2, patient.getName());
	            statement.setInt(3, patient.getAge());
	            statement.setString(4, patient.getGender());
	            statement.setInt(5, patient.getContact_information());
	            statement.setDate(6, new java.sql.Date(patient.getJoin_date().getTime()));
	            statement.setString(7,patient.getDisease());
	            statement.setString(8,patient.getMedical_history());
	            
	            statement.executeUpdate();
	           
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	 
	 public void updatePatient(Patient patient) throws ClassNotFoundException {
	        String query = "UPDATE patient SET name=?, age=?, gender=?, contact_information=?, join_date=?, disease=?, medical_history=? WHERE patient_id=?";
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, patient.getName());
	            statement.setInt(2, patient.getAge());
	            statement.setString(3, patient.getGender());
	            statement.setInt(4, patient.getContact_information());
	            statement.setDate(5, new java.sql.Date(patient.getJoin_date().getTime()));
	            statement.setString(6, patient.getDisease());
	            statement.setString(7, patient.getMedical_history());
	            statement.setInt(8, patient.getPatient_id());
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
	    public void deletePatient(int patientId) throws ClassNotFoundException {
	        String query = "DELETE FROM patient WHERE patient_id=?";
	        try (Connection connection=DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setInt(1, patientId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            System.out.println(e);
	        }
	    }
	    
	    public List<Patient> getAllPatients() throws ClassNotFoundException {
	        List<Patient> patients = new ArrayList<>();
	        String query = "SELECT * FROM patient";
	        try (Connection connection = DatabaseConnection.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(query)) {
	            while (resultSet.next()) {
	                Patient patient = new Patient();
	                patient.setPatient_id(resultSet.getInt("patient_id"));
	                patient.setName(resultSet.getString("name"));
	                patient.setAge(resultSet.getInt("age"));
	                patient.setGender(resultSet.getString("gender"));
	                patient.setContact_information(resultSet.getInt("contact_information"));
	                patient.setJoin_date(resultSet.getDate("join_date"));
	                patient.setDisease(resultSet.getString("disease"));
	                patient.setMedical_history(resultSet.getString("medical_history"));
	                patients.add(patient);
	               
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return patients;
	    }

	   
	    public Patient getPatientById(int patientId) throws ClassNotFoundException {
	        String query = "SELECT * FROM patient WHERE patient_id=?";
	
	        try {
	           Connection connection = DatabaseConnection.getConnection();
	          PreparedStatement  statement = connection.prepareStatement(query);
	            statement.setInt(1, patientId);
	          ResultSet  resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                Patient patient = new Patient();
	                patient.setPatient_id(resultSet.getInt("patient_id"));
	                patient.setName(resultSet.getString("name"));
	                patient.setAge(resultSet.getInt("age"));
	                patient.setGender(resultSet.getString("gender"));
	                patient.setContact_information(resultSet.getInt("contact_information"));
	                patient.setJoin_date(resultSet.getDate("join_date"));
	                patient.setDisease(resultSet.getString("disease"));
	                patient.setMedical_history(resultSet.getString("medical_history"));
	                return patient;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }  
	        
	        return null;
	    }
}
