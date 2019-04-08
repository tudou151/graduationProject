package com.lut.service.impl;

import com.lut.entity.Community;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.CommunityRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommunityServiceImpl implements ICommunityService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private CommunityRepository communityRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Override
    public Result getCommunityById(long id) {
        Community community = communityRepository.getCommunityById(id);
        if (community==null){
            throw new MyException(-1,"根据id获取关系为空");
        }
        return new Result(0,"操作成功",community);
    }

    @Override
    public Result getAllCommunitys(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Community> communities = null;
        if (employee.getPermissions() == 0){
            communities = communityRepository.getAllCommunityes(employee.getEmployeeName());
            Page<Community> page = new Page<>(communities);
            request.setAttribute("communityPage",page);
        }else {
            communities = communityRepository.getAllCommunitys();
            Page<Community> page = new Page<>(communities);
            request.setAttribute("communityPage",page);
        }

        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteCommunity(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = communityRepository.deleteCommunity(id);
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
    public Result addCommunity(Community community) {
        String employeeNumber = community.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        community.setFounder(employee.getEmployeeName());
        Date now = new Date();
        community.setCreateTime(now);
//        community = setState(now,community);
        int i = communityRepository.addCommunity(community);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyCommunity(Community community){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        community.setModifier(employee.getEmployeeName());
        Date now = new Date();
        community.setModifyTime(now);
        int i = communityRepository.modifyCommunity(community);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Community> communitys  = communityRepository.getAllCommunitys();

        Page<Community> page = new Page<>(communitys);
        page.setPage(currentPage);
        request.setAttribute("communityPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getCommunityByEmployeeNumber(String employeeNumber) {
        List<Community> communitys = communityRepository.getCommunityByEmployeeNumber(employeeNumber);
        Page<Community> page = new Page<>(communitys);
        request.setAttribute("communityPage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = communityRepository.getEmployeeNumberByEmployeeName(employeeName);
        System.out.println("employeeName: "+employeeName);
        System.out.println("employeeNumber: "+employeeNumber);

        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }

}
