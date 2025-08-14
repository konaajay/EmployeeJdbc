package com.ajay.www;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeApp {

    static String url = "jdbc:mysql://localhost:3306/employee_db?serverTimezone=UTC";
    static String user = "root";
    static String password = "root";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1: addEmployee(con, sc); break;
                    case 2: viewEmployees(con); break;
                    case 3: updateEmployee(con, sc); break;
                    case 4: deleteEmployee(con, sc); break;
                    case 5: con.close(); sc.close(); System.exit(0);
                    default: System.out.println("Invalid choice");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter department: ");
        String dept = sc.nextLine();
        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        String sql = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, salary);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee added successfully");
    }

    static void viewEmployees(Connection con) throws SQLException {
        String sql = "SELECT * FROM employee";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("ID\tName\tDepartment\tSalary");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "\t" +
                               rs.getString("name") + "\t" +
                               rs.getString("department") + "\t" +
                               rs.getDouble("salary"));
        }
    }

    static void updateEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter new name: ");
        String name = sc.nextLine();
        System.out.print("Enter new department: ");
        String dept = sc.nextLine();
        System.out.print("Enter new salary: ");
        double salary = sc.nextDouble();
        sc.nextLine();

        String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, salary);
        ps.setInt(4, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee updated successfully");
    }

    static void deleteEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0) System.out.println("Employee deleted successfully");
    }
}
