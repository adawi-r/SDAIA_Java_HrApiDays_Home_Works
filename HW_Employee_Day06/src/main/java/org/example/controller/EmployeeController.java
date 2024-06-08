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
import java.sql.SQLException;
import java.util.ArrayList;


@Path("/employees")
public class EmployeeController {

    EmployeeDAO dao = new EmployeeDAO();
    @Context UriInfo uriInfo;
    @Context HttpHeaders headers;

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response selectAllEMP(
            @BeanParam EmployeeFilterDto filter
          ) {

        try {
            GenericEntity<ArrayList<Employee>> emp = new GenericEntity<ArrayList<Employee>>(dao.selectAllEMP(filter)) {
            };
            if (headers.getAcceptableMediaTypes().contains(MediaType.valueOf(MediaType.APPLICATION_XML))) {
                return Response
                        .ok(emp)
                        .type(MediaType.APPLICATION_XML)
                        .build();
            }
            return Response
//                    .ok(job)
//                    .type(MediaType.APPLICATION_JSON)
                    .ok(emp, MediaType.APPLICATION_JSON)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//
    @GET
    @Path("{empId}")
    public Response selectEMP(@PathParam("empId") int empId) throws SQLException {

        try {
            Employee emp = dao.selectEMP(empId);
            if (emp == null) {
                throw new DataNotFoundException("Employee " + empId + "Not found");
            }

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
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

        private void addLinks (EmployeeDto dto){
            URI selfUri = uriInfo.getAbsolutePath();
            URI empsUri = uriInfo.getAbsolutePathBuilder().path(EmployeeController.class).build();

            dto.addLink(selfUri.toString(), "self");
            dto.addLink(empsUri.toString(), "employees");
        }

        @DELETE
        @Path("{empId}")
        public void deleteEMP ( @PathParam("empId") int empId){

            try {
                dao.deleteEMP(empId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @POST
//        @Consumes(MediaType.APPLICATION_XML)

        public Response insertEMP (Employee emp){

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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    @POST
    public Response insertEMPFromForm (@BeanParam Employee emp){

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

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


        @PUT
        @Path("{empId}")
        public void updateEMP ( @PathParam("empId") int empId, Employee emp){

            try {
                emp.setEmployee_id(empId);
                dao.updateEMP(emp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
}
