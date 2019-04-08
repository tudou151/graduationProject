package com.lut.service.impl;

import com.lut.entity.Title;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.EmployeeRepository;
import com.lut.repository.TitleRepository;
import com.lut.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TitleServiceImpl implements ITitleService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private TitleRepository titleRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getTitleById(long id) {
        Title title = titleRepository.getTitleById(id);
        if (title==null){
            throw new MyException(-1,"根据id获取职称为空");
        }
        return new Result(0,"操作成功",title);
    }

    @Override
    public Result getAllTitles(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Title> titles = null;
        if (employee.getPermissions()==0){
            titles = titleRepository.getAllTitlees(employee.getEmployeeName());
            Page<Title> page = new Page<>(titles);
            request.setAttribute("titlePage",page);
        }else{
            titles = titleRepository.getAllTitles();
            Page<Title> page = new Page<>(titles);
            request.setAttribute("titlePage",page);

        }


        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteTitle(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = titleRepository.deleteTitle(id);
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
    public Result addTitle(Title title) {
        String employeeNumber = title.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        title.setFounder(employee.getEmployeeName());
        Date now = new Date();
        title.setCreateTime(now);
//        title = setState(now,title);
        int i = titleRepository.addTitle(title);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyTitle(Title title){
//        System.out.println(title);
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        title.setModifier(employee.getEmployeeName());
        Date now = new Date();
        title.setModifyTime(now);
        int i = titleRepository.modifyTitle(title);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Title> titles  = titleRepository.getAllTitles();

        Page<Title> page = new Page<>(titles);
        page.setPage(currentPage);
        request.setAttribute("titlePage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getTitleByEmployeeNumber(String employeeNumber) {
        List<Title> titles = titleRepository.getTitleByEmployeeNumber(employeeNumber);
        Page<Title> page = new Page<>(titles);
        request.setAttribute("titlePage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = titleRepository.getEmployeeNumberByEmployeeName(employeeName);
//        System.out.println("employeeName: "+employeeName);
//        System.out.println("employeeNumber: "+employeeNumber);

        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }


}
