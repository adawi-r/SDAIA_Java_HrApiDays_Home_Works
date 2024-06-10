package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import org.example.dao.EmployeeDAO;
import org.example.dto.EmployeeDto;
import org.example.dto.EmployeeFilterDto;
import org.example.models.Employee;
import org.example.exceptions.DataNotFoundException;

import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectAllEMP(
            @BeanParam EmployeeFilterDto filter
          )  throws SQLException {

        try {
            GenericEntity<ArrayList<Employee>> emp = new GenericEntity<ArrayList<Employee>>(dao.selectAllEMP(filter)) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(emp)
                        .type("text/csv")
                        .build();
            }
            return Response
//                    .ok(job)
//                    .type(MediaType.APPLICATION_JSON)
                    .ok(emp, MediaType.APPLICATION_JSON)
                    .build();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
//
    @GET
    @Path("{empId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})
    public Response selectEMP(@PathParam("empId") int empId) throws SQLException {

        try {
            Employee emp = dao.selectEMP(empId);
            if (emp == null) {
                throw new DataNotFoundException("Employee " + empId + "Not found");
            }

            if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(emp)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            } // return dao.selectEmp(employee_id);

            EmployeeDto dto = new EmployeeDto();
            dto.setEmployee_id(emp.getEmployee_id());
            dto.setFirst_name(emp.getFirst_name());
            dto.setLast_name(emp.getLast_name());
            dto.setEmail(emp.getEmail());
            dto.setNumber(emp.getPhone_number());
            dto.setHire_date(emp.getHire_date());
            dto.setSalary(emp.getSalary());
            dto.setManager_id(emp.getManager_id());
            dto.setDepartment_id(emp.getDepartment_id());

            addLinks(dto);

            return Response.ok(dto).build();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

        private void addLinks (EmployeeDto dto) {
        
            URI selfUri = uriInfo.getAbsolutePath();
            URI empsUri = uriInfo.getAbsolutePathBuilder().path(EmployeeController.class).build();

            dto.addLink(selfUri.toString(), "self");
            dto.addLink(empsUri.toString(), "employees");
        }

        @DELETE
        @Path("{empId}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})

        public Response deleteEMP ( @PathParam("empId") int empId) throws SQLException{

            try {
                dao.deleteEMP(empId);

                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(empId)
                            .type(MediaType.APPLICATION_XML)
                            .build();

                }
                return Response.ok(empId).build();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        @POST
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})

        public Response insertEMP (Employee emp) throws SQLException{

            try {
                dao.insertEMP(emp);
                NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
                URI uri = uriInfo.getAbsolutePathBuilder().path(emp.getJob_id() + "").build();

                return Response
//                    .status(Response.Status.CREATED)
                        .created(uri)
//                    .cookie(new NewCookie("username", "OOOOO"))
                        .cookie(cookie)
                        .header("Created by", "Raghad")
                        .build();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    @POST
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})

    public Response insertEMPFromForm (@BeanParam Employee emp) throws SQLException{

        try {
            dao.insertEMP(emp);
            NewCookie cookie = (new NewCookie.Builder("username")).value("OOOOO").build();
            URI uri = uriInfo.getAbsolutePathBuilder().path(emp.getJob_id() + "").build();

            return Response
//                    .status(Response.Status.CREATED)
                    .created(uri)
//                    .cookie(new NewCookie("username", "OOOOO"))
                    .cookie(cookie)
                    .header("Created by", "Raghad")
                    .build();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


        @PUT
        @Path("{empId}")
        @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, "text/csv"})

        public Response updateEMP ( @PathParam("empId") int empId, Employee emp) throws SQLException{

            try {
                emp.setEmployee_id(empId);
                dao.updateEMP(emp);

                if(headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                    return Response
                            .ok(emp)
                            .type(MediaType.APPLICATION_XML)
                            .build();
                }

                return Response.ok(emp).build();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}
