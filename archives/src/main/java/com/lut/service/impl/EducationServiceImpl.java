package com.lut.service.impl;

import com.lut.entity.Education;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.EducationRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EducationServiceImpl implements IEducationService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private EducationRepository educationRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getEducationById(long id) {
        Education education = educationRepository.getEducationById(id);
        if (education==null){
            throw new MyException(-1,"根据id获取学历为空");
        }
        return new Result(0,"操作成功",education);
    }

    @Override
    public Result getAllEducations(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();

        List<Education> educations =null ;
        if(employee.getPermissions()==0){
            educations = educationRepository.getAllEducationes(employee.getEmployeeName());
            Page<Education> page = new Page<>(educations);
            request.setAttribute("educationPage",page);
        }else{
            educations = educationRepository.getAllEducations();
            Page<Education> page = new Page<>(educations);
            request.setAttribute("educationPage",page);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteEducation(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = educationRepository.deleteEducation(id);
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
    public Result addEducation(Education education) {

        String employeeNumber = education.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        education.setFounder(employee.getEmployeeName());
        Date now = new Date();
        education.setCreateTime(now);
//        education = setState(now,education);
        int i = educationRepository.addEducation(education);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyEducation(Education education){
//        System.out.println(education);
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        education.setModifier(employee.getEmployeeName());
        Date now = new Date();
        education.setModifyTime(now);
        int i = educationRepository.modifyEducation(education);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Education> educations  = educationRepository.getAllEducations();

        Page<Education> page = new Page<>(educations);
        page.setPage(currentPage);
        request.setAttribute("educationPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getEducationByEmployeeNumber(String employeeNumber) {
        List<Education> educations = educationRepository.getEducationByEmployeeNumber(employeeNumber);
        Page<Education> page = new Page<>(educations);
        request.setAttribute("educationPage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = educationRepository.getEmployeeNumberByEmployeeName(employeeName);
        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }

}
