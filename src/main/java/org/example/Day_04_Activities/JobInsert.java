package org.example.Day_04_Activities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
//
public class JobInsert {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String url = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\sdaia_java_projects\\project-01\\hr.db";
        String query = "insert into jobs values (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url);) {

            PreparedStatement st = conn.prepareStatement(query);

            System.out.println("Enter job Id: ");
            int job_id = sc.nextInt();
            st.setInt(1,job_id);
            sc.nextLine();

            System.out.println("Enter job jobName: ");
            String job_title = sc.nextLine();
            st.setString(2,job_title);

            System.out.println("Enter job min_salary: ");
            double min_salary = sc.nextDouble();
            st.setDouble(3,min_salary);

            System.out.println("Enter job max_salary: ");
            double max_salary = sc.nextDouble();
            st.setDouble(4, max_salary);

            int rows = st.executeUpdate();
            System.out.println(rows + " Inserted");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
