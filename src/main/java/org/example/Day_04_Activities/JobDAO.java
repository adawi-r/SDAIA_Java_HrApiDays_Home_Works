package org.example.Day_04_Activities;

//import java.sql.PreparedStatement;
//import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
//
public class JobDAO {
    private static final String URL = "jdbc:sqlite:C:\\Users\\dev\\Desktop\\sdaia_java_projects\\project-01\\hr.db";
    private static final String SELECT_ALL_JOBS = "select * from jobs";
    private static final String SELECT_ONE_JOB = "select * from jobs where job_id = ?";
    private static final String INSERT_JOB = "insert into jobs values (?, ?, ?)";
    private static final String UPDATE_JOB = "update jobs set job_id = ?, job_title = ? where max_salary = ?";
    private static final String DELETE_JOB = "delete from jobs where job_id = ?";

    public void insertJob(Job j) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(INSERT_JOB);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3, j.getMax_salary());
        st.executeUpdate();
    }

    public void updateJob(Job j) throws SQLException {

        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(UPDATE_JOB);
        st.setInt(1, j.getJob_id());
        st.setString(2, j.getJob_title());
        st.setDouble(3, j.getMax_salary());
        st.executeUpdate();
    }

    public void deleteJob(int job_id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(DELETE_JOB);
        st.setInt(1, job_id);
        st.executeUpdate();
    }

    public Job selectJob(int Job_id) throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ONE_JOB);
        st.setInt(1, Job_id);
        ResultSet rs = st.executeQuery();
        if(rs.next()) {
            return new Job(rs);
        }
        else {
            return null;
        }
    }

    public ArrayList<Job> selectAllJob() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);
        PreparedStatement st = conn.prepareStatement(SELECT_ALL_JOBS);
        ResultSet rs = st.executeQuery();
        ArrayList<Job> job = new ArrayList<>();
        while (rs.next()) {
            job.add(new Job(rs));
        }

        return job;
    }
}

