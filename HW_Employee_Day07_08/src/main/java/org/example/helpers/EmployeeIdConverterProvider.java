package org.example.helpers;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.example.dto.EmpIdDto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class EmployeeIdConverterProvider  implements ParamConverterProvider{

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if(rawType.getName().equals(EmpIdDto.class.getName())) {
            return new EmployeeIdParamConverter();
        }
        return null;
    }


    public class EmployeeIdParamConverter implements ParamConverter {

        @Override
        public Object fromString(String value) {

            EmpIdDto empId = new EmpIdDto();
            empId.setEmpId(Integer.parseInt(value.substring(0, 3)));
            empId.setHireYear(Integer.parseInt(value.substring(3)));

            return empId;
        }

        @Override
        public String toString(Object value) {
            return value.toString();
        }
    }

}
