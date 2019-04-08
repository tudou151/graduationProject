package com.lut.service.impl;

import com.lut.entity.AwardAndPunish;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.AwardAndPunishRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.IAwardAndPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AwardAndPunishServiceImpl implements IAwardAndPunishService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private AwardAndPunishRepository awardAndPunishRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getAwardAndPunishById(long id) {
        AwardAndPunish awardAndPunish = awardAndPunishRepository.getAwardAndPunishById(id);
        if (awardAndPunish==null){
            throw new MyException(-1,"根据id获取奖惩信息为空");
        }
        return new Result(0,"操作成功",awardAndPunish);
    }

    @Override
    public Result getAllAwardAndPunishs(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<AwardAndPunish> awardAndPunishs = null;
        if(employee.getPermissions() == 0){
            awardAndPunishs = awardAndPunishRepository.getAllAwardAndPunishes(employee.getEmployeeName());
            Page<AwardAndPunish> page = new Page<>(awardAndPunishs);
            request.setAttribute("awardAndPunishPage",page);
        }else{
            awardAndPunishs = awardAndPunishRepository.getAllAwardAndPunishs();
            Page<AwardAndPunish> page = new Page<>(awardAndPunishs);
            request.setAttribute("awardAndPunishPage",page);
        }


        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteAwardAndPunish(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = awardAndPunishRepository.deleteAwardAndPunish(id);
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
    public Result addAwardAndPunish(AwardAndPunish awardAndPunish) {

        String employeeNumber = awardAndPunish.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        awardAndPunish.setFounder(employee.getEmployeeName());
        Date now = new Date();
        awardAndPunish.setCreateTime(now);
//        awardAndPunish = setState(now,awardAndPunish);
        int i = awardAndPunishRepository.addAwardAndPunish(awardAndPunish);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyAwardAndPunish(AwardAndPunish awardAndPunish){
        String employeeNumber = awardAndPunish.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"修改失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        awardAndPunish.setModifier(employee.getEmployeeName());
        Date now = new Date();
        awardAndPunish.setModifyTime(now);
        int i = awardAndPunishRepository.modifyAwardAndPunish(awardAndPunish);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<AwardAndPunish> awardAndPunishs  = awardAndPunishRepository.getAllAwardAndPunishs();

        Page<AwardAndPunish> page = new Page<>(awardAndPunishs);
        page.setPage(currentPage);
        request.setAttribute("awardAndPunishPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getAwardAndPunishByEmployeeNumber(String employeeNumber) {
        List<AwardAndPunish> awardAndPunishs = awardAndPunishRepository.getAwardAndPunishByEmployeeNumber(employeeNumber);
        Page<AwardAndPunish> page = new Page<>(awardAndPunishs);
        request.setAttribute("awardAndPunishPage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = awardAndPunishRepository.getEmployeeNumberByEmployeeName(employeeName);
        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);        }
    }
}
