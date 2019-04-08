package com.lut.controller;

import com.lut.entity.Department;
import com.lut.entity.Result;
import com.lut.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/Department")
@RestController
public class DepartmentController {


    @Autowired
    IDepartmentService departmentService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addDepartment")
    public Result addDepartment(@RequestParam("departmentNumber") String departmentNumber, @RequestParam("departmentName") String departmentName,
                                      @RequestParam("departmentHead") String departmentHead, @RequestParam("departmentAddress") String departmentAddress,
                                      @RequestParam("departmentTel") String departmentTel, @RequestParam("departmentFax") String departmentFax)throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDepartmentNumber(departmentNumber);
        department.setDepartmentAddress(departmentAddress);
        department.setDepartmentHead(departmentHead);
        department.setDepartmentTel(departmentTel);
        department.setDepartmentFax(departmentFax);

        return departmentService.addDepartment(department);
    }


    @GetMapping("/getAllDepartments")
    public ModelAndView getAllDepartments(){
        departmentService.getAllDepartments();
//        System.out.println("就是"+request.getSession().getAttribute("departmentPage"));
        return new ModelAndView("department");
    }

    @PostMapping("/deleteDepartment")
    public Result deleteDepartment(@RequestParam("ids") String ids){
        return departmentService.deleteDepartment(ids);
    }

    @PostMapping("/modifyDepartment")
    public Result modifyDepartment(@RequestParam("departmentNumber") String departmentNumber, @RequestParam("departmentName") String departmentName,
                                   @RequestParam("departmentHead") String departmentHead, @RequestParam("departmentAddress") String departmentAddress,
                                   @RequestParam("departmentTel") String departmentTel, @RequestParam("departmentFax") String departmentFax,
                                   @RequestParam("id") long id)throws ParseException{
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDepartmentNumber(departmentNumber);
        department.setDepartmentAddress(departmentAddress);
        department.setDepartmentHead(departmentHead);
        department.setDepartmentTel(departmentTel);
        department.setDepartmentFax(departmentFax);
        department.setId(id);
//        System.out.println("department修改: "+ department);
        return departmentService.modifyDepartment(department);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        departmentService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("department");
    }

    @GetMapping("/getDepartmentById")
    public Result getDepartmentById(@RequestParam("id") long id){
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/getDepartmentByDepartmentNumber")
    public ModelAndView getDepartmentByDepartmentNumber(@RequestParam("departmentNumber") String departmentNumber)throws ParseException{
        departmentService.getDepartmentByDepartmentNumber(departmentNumber);
        return new ModelAndView("department");
    }

    @PostMapping("/getDepartmentNumberByDepartmentName")
    public Result getDepartmentNumberByDepartmentName(@RequestParam("departmentName") String departmentName)throws ParseException{
        return  departmentService.getDepartmentNumberByDepartmentName(departmentName);
    }
}
