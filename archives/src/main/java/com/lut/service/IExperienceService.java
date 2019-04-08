package com.lut.service;

import com.lut.entity.Experience;
import com.lut.entity.Result;

public interface IExperienceService {

    /**
     * 获取所有的工作经历资源
     * @return
     */
    public Result getAllExperiences();

    /**
     * 根据ID删除工作经历
     * @param ids
     * @return
     */
    public Result deleteExperience(String ids);

    /**
     * 新增工作经历
     * @param experience
     * @return
     */
    public Result addExperience(Experience experience);

    /**
     * 修改工作经历
     * @param experience
     * @return
     */
    public Result modifyExperience(Experience experience);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询工作经历
     * @param id
     * @return
     */
    public Result getExperienceById(long id);

    /**
     * 根据员工姓名查询经历
     * @param employeeNumber
     * @return
     */
    public Result getExperienceByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);
}
