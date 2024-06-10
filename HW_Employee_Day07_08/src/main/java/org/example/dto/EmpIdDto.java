package org.example.dto;

public class EmpIdDto {

//        private String deptCode;
        private int empId;
        private int hireYear;

//        public String getDeptCode() {
//            return deptCode;
//        }

//        public void setDeptCode(String deptCode) {
//            this.deptCode = deptCode;
//        }

//        public int getSeq() {
//            return seq;
//        }
//
//        public void setSeq(int seq) {
//            this.seq = seq;
//        }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getHireYear() {
            return hireYear;
        }

        public void setHireYear(int hireYear) {
            this.hireYear = hireYear;
        }


    @Override
    public String toString() {
        return String.valueOf(empId + hireYear);
    }
}
