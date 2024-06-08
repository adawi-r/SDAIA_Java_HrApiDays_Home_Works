package org.example.controller;

import jakarta.ws.rs.*;
import org.example.dao.EmployeeDAO;
import org.example.models.Employee;

import java.util.ArrayList;

@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();

    @GET
    public ArrayList<Employee> selectAllEMP() {

        try {
            return dao.selectAllEMP();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GET
    @Path("{empId}")
    public Employee selectEMP(@PathParam("empId") int empId){

        try {
            return dao.selectEMP(empId);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DELETE
    @Path("{empId}")
    public void deleteEMP(@PathParam("empId") int empId){

        try {
            dao.deleteEMP(empId);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    public void insertEMP(Employee emp){

        try {
            dao.insertEMP(emp);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
    @PUT
    @Path("{empId}")
    public void updateEMP(@PathParam("empId") int empId, Employee emp){

        try {
            emp.setEmployee_id(empId);
            dao.updateEMP(emp);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
