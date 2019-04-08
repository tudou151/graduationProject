package com.lut.service.impl;

import com.lut.entity.BulletinResource;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.BulletinResourceRepository;
import com.lut.service.IBulletinResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BulletinResourceServiceImpl implements IBulletinResourceService {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private BulletinResourceRepository bulletinResourceRepository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public Result getBulletinById(long id) {
        BulletinResource bulletinResource = bulletinResourceRepository.getBulletinById(id);
        if (bulletinResource==null){
            throw new MyException(-1,"根据id获取公告为空");
        }
        return new Result(0,"操作成功",bulletinResource);
    }

    @Override
    public Result getAllBulletinResources(){
        List<BulletinResource> bulletinResources = bulletinResourceRepository.getAllBulletinResources();
        for (int i=0;i<bulletinResources.size();i++){
            bulletinResources.set(i,updateBulletinResourceState(bulletinResources.get(i)));
        }
        Page<BulletinResource> page = new Page<>(bulletinResources);
        request.setAttribute("bulletinPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteBulletinResource(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = bulletinResourceRepository.deleteBulletinResource(id);
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
    public Result addBulletinResource(BulletinResource bulletinResource) {
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        bulletinResource.setFounder(employee.getEmployeeName());
        Date now = new Date();
        bulletinResource.setCreateTime(now);
        bulletinResource = setState(now,bulletinResource);
        int i = bulletinResourceRepository.addBulletinResource(bulletinResource);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }

    /**
     * 设置公告当前状态
     * @param now
     * @param bulletinResource
     * @return
     */
    public BulletinResource setState(Date now,BulletinResource bulletinResource){
        if (now.after(bulletinResource.getStartDate())){
            if (now.after(bulletinResource.getEndDate())){
                bulletinResource.setState("已失效");
            }else{
                bulletinResource.setState("生效中");
            }
        }else{
            bulletinResource.setState("未生效");
        }
        return bulletinResource;
    }

    /**
     * 更新公告的状态
     * @param bulletinResource
     */
    public BulletinResource updateBulletinResourceState(BulletinResource bulletinResource){
        Date now = new Date();
        if (now.after(bulletinResource.getStartDate())){
            if (now.after(bulletinResource.getEndDate())){
                if (!("已失效".equals(bulletinResource.getState()))){
                    bulletinResource.setState("已失效");
                    int i = bulletinResourceRepository.updateState("已失效",bulletinResource.getId());
                    if (i!=1){
                        throw new MyException();
                    }
                    return bulletinResource;
                }
                return bulletinResource;
            }else{
                if (!("生效中".equals(bulletinResource.getState()))){
                    bulletinResource.setState("生效中");
                    int i = bulletinResourceRepository.updateState("生效中",bulletinResource.getId());
                    if (i!=1){
                        throw new MyException();
                    }
                    return bulletinResource;
                }
                return bulletinResource;
            }
        }else{
            if (!("未生效".equals(bulletinResource.getState()))){
                bulletinResource.setState("未生效");
                int i = bulletinResourceRepository.updateState("未生效",bulletinResource.getId());
                if (i!=1){
                    throw new MyException();
                }
                return bulletinResource;
            }
            return bulletinResource;
        }
    }

    @Override
    public Result modifyBulletinResource(BulletinResource bulletinResource){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        bulletinResource.setModifier(employee.getEmployeeName());
        Date now = new Date();
        bulletinResource.setModifyTime(now);
        int i = bulletinResourceRepository.modifyBulletinResource(bulletinResource);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<BulletinResource> bulletinResources = bulletinResourceRepository.getAllBulletinResources();
        for (int i=0;i<bulletinResources.size();i++){
            bulletinResources.set(i,updateBulletinResourceState(bulletinResources.get(i)));
        }
        Page<BulletinResource> page = new Page<>(bulletinResources);
        page.setPage(currentPage);
        request.setAttribute("bulletinPage",page);
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getBulletinResourceById(long id) {
        List<BulletinResource> bulletinResources = bulletinResourceRepository.getBulletinResourceById(id);
        Page<BulletinResource> page = new Page<>(bulletinResources);
        request.setAttribute("bulletinPage",page);
        System.out.println("bulletinResources:    "+bulletinResources.toString());

        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getIdByTitle(String title){
        System.out.println("title：：" +title );
        String id = bulletinResourceRepository.getIdByTitle(title);
        System.out.println("id: "+id);


        if (id==null){
            throw new MyException(-1,"输入的标题不存在！");
        }else{
            return new Result(0,"操作成功",id);
        }
    }
}
