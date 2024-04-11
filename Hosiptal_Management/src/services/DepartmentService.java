package services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database_connection.DatabaseConnection;
import entities.Department;

public class DepartmentService {
    private Connection connection;

   
      

    public List<Department> getAllDepartments() throws ClassNotFoundException {
        List<Department> departments = new ArrayList<>();
        try {
            // Query to retrieve all departments
            String sql = "SELECT * FROM department";
            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                // Create Department objects from query results
                Department department = new Department(resultSet.getInt("department_id"), resultSet.getString("department_name"), resultSet.getString("description"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public void addDepartment(Department department) throws ClassNotFoundException {
        try {
            // Query to insert a new department
            String sql = "INSERT INTO department (department_id, department_name, description) VALUES (?, ?, ?)";
            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, department.getDepartment_id());
            statement.setString(2, department.getDepartment_name());
            statement.setString(3, department.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDepartment(Department updatedDepartment) throws ClassNotFoundException {
        try {
            // Query to update an existing department
            String sql = "UPDATE department SET department_name = ?, description = ? WHERE department_id = ?";
            Connection connection =DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, updatedDepartment.getDepartment_name());
            statement.setString(2, updatedDepartment.getDescription());
            statement.setInt(3, updatedDepartment.getDepartment_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDepartment(int departmentId) {
        try {
            // Query to delete a department
            String sql = "DELETE FROM department WHERE department_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, departmentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
