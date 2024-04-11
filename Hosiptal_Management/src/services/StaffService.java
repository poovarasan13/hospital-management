package services;

import entities.Staff;
import database_connection.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffService {
    

	public void addStaff(Staff staff) throws ClassNotFoundException {
        try {
            // Query to insert a new staff member
            String sql = "INSERT INTO staff (staff_id, department_id, name, role) VALUES (?, ?, ?, ?)";
            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, staff.getStaff_id());
            statement.setString(2, staff.getName());
            statement.setString(3, staff.getRole());
            statement.setInt(4, staff.getDepartment_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStaff(Staff updatedStaff) throws ClassNotFoundException {
        try {
            // Query to update an existing staff member
            String sql = "UPDATE staff SET department_id = ?, name = ?, role = ? WHERE staff_id = ?";
            Connection connection =DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, updatedStaff.getDepartment_id());
            statement.setString(2, updatedStaff.getName());
            statement.setString(3, updatedStaff.getRole());
            statement.setInt(4, updatedStaff.getStaff_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStaff(int staffId) throws ClassNotFoundException {
        try {
            // Query to delete a staff member
            String sql = "DELETE FROM staff WHERE staff_id = ?";
            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, staffId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Staff> getAllStaff() throws ClassNotFoundException {
        List<Staff> allStaff = new ArrayList<>();
        try {
            // Query to select all staff members
            String sql = "SELECT * FROM staff";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and create Staff objects
            while (resultSet.next()) {
                Staff staff = new Staff();
                staff.setStaff_id(resultSet.getInt("staff_id"));
                
                staff.setName(resultSet.getString("name"));
                staff.setRole(resultSet.getString("role"));
                staff.setDepartment_id(resultSet.getInt("department_id"));
                allStaff.add(staff);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStaff;
    }

    }
