package org.example.dto;


import jakarta.ws.rs.QueryParam;

public class EmployeeFilterDto {
//
    @QueryParam("empId") Integer depId;
    @QueryParam("limit") Integer limit;
    @QueryParam("offset") int offset;

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
