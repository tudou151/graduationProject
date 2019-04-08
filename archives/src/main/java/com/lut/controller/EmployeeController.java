package com.lut.controller;

import com.lut.entity.Employee;
import com.lut.entity.Result;
import com.lut.service.IEmployeeService;
import com.lut.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/login")
    public ModelAndView login(Employee employee, HttpServletRequest request){
        Result result = employeeService.login(employee);
        if (result.getCode()==0){
            return new ModelAndView("redirect:/page/index");
        }
        request.setAttribute("pageMsg",result.getMsg());
        return new ModelAndView("forward:/page/login");
    }

    @PostMapping("/del_employees")
    @ResponseBody
    public ModelAndView deleteEmployees(@RequestParam("ids") String ids){
        employeeService.deleteEmployees(ids);
        return new ModelAndView("employeeInfo");
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        employeeService.getPageDatas(currentPage);
        return new ModelAndView("employeeInfo");
    }

    @PostMapping("/addEmployee")
    @ResponseBody
    public Result addEmployee(Employee employee){
        return employeeService.addEmployee(employee);
    }

    @GetMapping("/getUpdateEmployeeInfo")
    public ModelAndView getUpdateEmployeeInfo(){
        employeeServiceImpl.updateAllEmployeeToSession();
        return new ModelAndView("employeeInfo");
    }

    @GetMapping("/getMyAccount")
    public ModelAndView getMyAccount(){
        return new ModelAndView("myAccount");
    }

    @GetMapping("/getEmployee")
    @ResponseBody
    public Result getEmployee(@RequestParam("id") long id){
        Employee employee = new Employee();
        employee.setId(id);
        return employeeService.getEmployee(employee);
    }

    @PostMapping("/updateEmployeeById")
    @ResponseBody
    public Result updateEmployeeById(Employee employee){
        return employeeService.updateEmployeeById(employee);
    }

    @PostMapping("/getEmployeeByNumber")
    @ResponseBody
    public Result getEmployeeByNumber(Employee employee){
        return employeeService.getEmployeeByNumber(employee.getEmployeeNumber());
    }

    @PostMapping("/updateEmployeeByNumber")
    @ResponseBody
    public Result updateEmployeeByNumber(Employee employee){
        return employeeService.updateEmployeeByNumber(employee);
    }

    @PostMapping("/uploadMyImage")
    @ResponseBody
    public Result upLoadMyImage(){
        return employeeService.upLoadMyImage();
    }

    @GetMapping("/clearLogin")
    @ResponseBody
    public Result clearLogin(){
        return employeeServiceImpl.clearLogin();
    }

    @PostMapping("/modifyPwd")
    @ResponseBody
    public Result modifyPwd(@RequestParam("oldPwd") String oldPwd,@RequestParam("newPwd") String newPwd){
        return employeeService.modifyPwd(oldPwd,newPwd);
    }

    @GetMapping("/loginYesOrNo")
    @ResponseBody
    public Result loginYesOrNo(){
        employeeServiceImpl.getEmployeeLoginInfo();
        return new Result(0,"已登录",null);
    }

    @GetMapping("/getEmployeeByEmployeeNumber")
    @ResponseBody
    public Result getEmployeeByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException {
        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeNumber);
        return employeeService.getEmployeeByEmployeeNumber(employee);
    }

    @PostMapping("/getEmployeeByName")
    @ResponseBody
    public Result getEmployeeByName(Employee employee)throws ParseException{
        return  employeeService.getEmployeeByName(employee.getEmployeeName());
    }

    @GetMapping("/getPersonByEmployeeNumber")
    @ResponseBody
    public ModelAndView getPersonByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber){
        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeNumber);
        employeeServiceImpl.getPersonByEmployeeNumber(employee);
        return new ModelAndView("employeeInfo");
    }
}
