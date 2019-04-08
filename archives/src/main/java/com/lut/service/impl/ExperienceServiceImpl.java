package com.lut.service.impl;

import com.lut.entity.Experience;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.EmployeeRepository;
import com.lut.repository.ExperienceRepository;
import com.lut.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExperienceServiceImpl implements IExperienceService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private ExperienceRepository experienceRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getExperienceById(long id) {
        Experience experience = experienceRepository.getExperienceById(id);
        if (experience==null){
            throw new MyException(-1,"根据id获取工作经历为空");
        }
        return new Result(0,"操作成功",experience);
    }

    @Override
    public Result getAllExperiences(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Experience> experiences = null;
        if(employee.getPermissions()==0){
            experiences = experienceRepository.getAllExperiencees(employee.getEmployeeName());
            Page<Experience> page = new Page<>(experiences);
            request.setAttribute("experiencePage",page);
        }else{
            experiences = experienceRepository.getAllExperiences();
            Page<Experience> page = new Page<>(experiences);
            request.setAttribute("experiencePage",page);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteExperience(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = experienceRepository.deleteExperience(id);
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
    public Result addExperience(Experience experience) {
        String employeeNumber = experience.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        experience.setFounder(employee.getEmployeeName());
        Date now = new Date();
        experience.setCreateTime(now);
//        experience = setState(now,experience);
        int i = experienceRepository.addExperience(experience);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyExperience(Experience experience){
//        System.out.println(experience);
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        experience.setModifier(employee.getEmployeeName());
        Date now = new Date();
        experience.setModifyTime(now);
        int i = experienceRepository.modifyExperience(experience);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Experience> experiences  = experienceRepository.getAllExperiences();

        Page<Experience> page = new Page<>(experiences);
        page.setPage(currentPage);
        request.setAttribute("experiencePage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getExperienceByEmployeeNumber(String employeeNumber) {
        List<Experience> experiences = experienceRepository.getExperienceByEmployeeNumber(employeeNumber);
        Page<Experience> page = new Page<>(experiences);
        request.setAttribute("experiencePage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = experienceRepository.getEmployeeNumberByEmployeeName(employeeName);
//        System.out.println("employeeName: "+employeeName);
//        System.out.println("employeeNumber: "+employeeNumber);

        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }


}
