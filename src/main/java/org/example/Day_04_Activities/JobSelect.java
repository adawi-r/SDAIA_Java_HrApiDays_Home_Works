package org.example.Day_04_Activities;

import java.sql.*;

public class JobSelect {
//
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\sdaia_java_projects\\project-01\\hr.db";
        String query = "select * from jobs";

        try(Connection conn = DriverManager.getConnection(url);) {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Job j = new Job(rs);
                System.out.println(j);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
