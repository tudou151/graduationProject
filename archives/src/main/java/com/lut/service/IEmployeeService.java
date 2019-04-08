package com.lut.service;

import com.lut.entity.Employee;
import com.lut.entity.Result;

public interface IEmployeeService {
    public Result login(Employee employee);
    public Result deleteEmployees(String ids);
    public Result getPageDatas(Integer currentPage);
    public Result addEmployee(Employee employee);
    public Result getEmployee(Employee employee);
    public Result updateEmployeeById(Employee employee);
    public Result getEmployeeByNumber(String employeeNumber);
    public Result updateEmployeeByNumber(Employee employee);
    public Result upLoadMyImage();
    public Result modifyPwd(String oldPwd,String newPwd);
    /**
     * 根据员工姓名查询员工
     * @param employee
     * @return
     */
    public Result getEmployeeByEmployeeNumber(Employee employee);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeByName(String employeeName);

    /**
     * 根据员工姓名查询员工工号
     * @param employee
     * @return
     */
    public Result getPersonByEmployeeNumber(Employee employee);
}
