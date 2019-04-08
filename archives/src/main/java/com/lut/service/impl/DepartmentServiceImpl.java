package com.lut.service.impl;

import com.lut.entity.Department;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.DepartmentRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private DepartmentRepository departmentRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getDepartmentById(long id) {
        Department department = departmentRepository.getDepartmentById(id);
        if (department==null){
            throw new MyException(-1,"根据id获取部门为空");
        }
        return new Result(0,"操作成功",department);
    }

    @Override
    public Result getAllDepartments(){
        List<Department> departments = departmentRepository.getAllDepartments();

        Page<Department> page = new Page<>(departments);
        request.setAttribute("departmentPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteDepartment(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = departmentRepository.deleteDepartment(id);
            if (i!=1){
                return new Result(-1,"删除时发生未知错误:"+id,null);
            }
        }
        return new Result(0,"操作成功",null);
    }

    public List<Long> getIds(String ids){
        List<Long> list = new ArrayList<>();
        String[] is = ids.split(",");
        for (String i : is){
            list.add(Long.parseLong(i));
        }
        return list;
    }

    @Override
    public Result addDepartment(Department department) {

        String employeeName = department.getDepartmentHead();
        String employeeNumber = employeeRepository.getEmployeeNumberByEmployeeName(employeeName);
        if (employeeNumber == null){
            throw new MyException(-1,"新增失败，没有此人");
        }
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        department.setFounder(employee.getEmployeeName());
        Date now = new Date();
        department.setCreateTime(now);
//        department = setState(now,department);
        int i = departmentRepository.addDepartment(department);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyDepartment(Department department){
        String employeeName = department.getDepartmentHead();
        String employeeNumber = employeeRepository.getEmployeeNumberByEmployeeName(employeeName);
        if (employeeNumber == null){
            throw new MyException(-1,"修改失败，没有此人");
        }
//        System.out.println(department);
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        department.setModifier(employee.getEmployeeName());
        Date now = new Date();
        department.setModifyTime(now);
        int i = departmentRepository.modifyDepartment(department);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Department> departments  = departmentRepository.getAllDepartments();

        Page<Department> page = new Page<>(departments);
        page.setPage(currentPage);
        request.setAttribute("departmentPage",page);

        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getDepartmentByDepartmentNumber(String departmentNumber) {
        List<Department> departments = departmentRepository.getDepartmentByDepartmentNumber(departmentNumber);
        Page<Department> page = new Page<>(departments);
        System.out.println("departments: "+departments.toString());
        System.out.println("page: "+page);
        request.setAttribute("departmentPage",page);
        System.out.println("1234567:  "+request.getSession().getAttribute("departmentPage"));

        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getDepartmentNumberByDepartmentName(String departmentName){
        String departmentNumber = departmentRepository.getDepartmentNumberByDepartmentName(departmentName);
//        System.out.println("employeeName: "+employeeName);
//        System.out.println("employeeNumber: "+employeeNumber);

        if (departmentNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",departmentNumber);
        }
    }

}
