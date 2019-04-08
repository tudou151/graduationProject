package com.lut.service;

import com.lut.entity.Community;
import com.lut.entity.Result;

public interface ICommunityService {

    /**
     * 获取所有的关系资源
     * @return
     */
    public Result getAllCommunitys();

    /**
     * 根据ID删除关系
     * @param ids
     * @return
     */
    public Result deleteCommunity(String ids);

    /**
     * 新增关系
     * @param community
     * @return
     */
    public Result addCommunity(Community community);

    /**
     * 修改关系
     * @param community
     * @return
     */
    public Result modifyCommunity(Community community);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询关系
     * @param id
     * @return
     */
    public Result getCommunityById(long id);

    /**
     * 根据员工姓名查询关系
     * @param employeeNumber
     * @return
     */
    public Result getCommunityByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);
}
