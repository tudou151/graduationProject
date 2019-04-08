package com.lut.service.impl;

import com.lut.entity.Cultivate;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.CultivateRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.ICultivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CultivateServiceImpl implements ICultivateService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private CultivateRepository cultivateRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private  EmployeeRepository employeeRepository;


    @Override
    public Result getCultivateById(long id) {
        Cultivate cultivate = cultivateRepository.getCultivateById(id);
        if (cultivate==null){
            throw new MyException(-1,"根据id获取培训为空");
        }
        return new Result(0,"操作成功",cultivate);
    }

    @Override
    public Result getCultivateByEmployeeNumber(String employeeNumber) {
        List<Cultivate> cultivates = cultivateRepository.getCultivateByEmployeeNumber(employeeNumber);
        Page<Cultivate> page = new Page<>(cultivates);
        request.setAttribute("cultivatePage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = cultivateRepository.getEmployeeNumberByEmployeeName(employeeName);

        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }

    @Override
    public Result getAllCultivates(){

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Cultivate> cultivates = null;

        if(employee.getPermissions() == 0){
            cultivates = cultivateRepository.getAllCultivatees(employee.getEmployeeName());
            Page<Cultivate> page = new Page<>(cultivates);
            request.setAttribute("cultivatePage",page);
        }
        else{
            cultivates = cultivateRepository.getAllCultivates();
            Page<Cultivate> page = new Page<>(cultivates);
            request.setAttribute("cultivatePage",page);
        }

        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteCultivate(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = cultivateRepository.deleteCultivate(id);
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
    public Result addCultivate(Cultivate cultivate) {
        String employeeNumber = cultivate.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        cultivate.setFounder(employee.getEmployeeName());
        Date now = new Date();
        cultivate.setCreateTime(now);
//        cultivate = setState(now,cultivate);
        int i = cultivateRepository.addCultivate(cultivate);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyCultivate(Cultivate cultivate){
//        System.out.println(cultivate);
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        cultivate.setModifier(employee.getEmployeeName());
        Date now = new Date();
        cultivate.setModifyTime(now);
        int i = cultivateRepository.modifyCultivate(cultivate);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Cultivate> cultivates  = cultivateRepository.getAllCultivates();

        Page<Cultivate> page = new Page<>(cultivates);
        page.setPage(currentPage);
        request.setAttribute("cultivatePage",page);
        return new Result(0,"操作成功",null);
    }

}
