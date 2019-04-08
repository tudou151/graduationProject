package com.lut.repository;

import com.lut.entity.Employee;

public class SqlProvider {
    public String getEmployees(Employee employee){
        String para=" and delete_mark=0";

        if (employee.getIfOnDuty()!=null){
            para =para + " and if_on_duty=" + employee.getIfOnDuty();
        }
        if(employee.getEmployeeNumber()!=null){
            para = para + " and employee_number='" + employee.getEmployeeNumber()+"'";
        }


        return "select * from tb_employee where 1=1"+para;
    }

    public String deleteEmployee(Integer id){
        return "delete from tb_employee where id="+id;
    }
}
