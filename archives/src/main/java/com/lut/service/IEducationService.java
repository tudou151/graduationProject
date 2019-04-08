package com.lut.service;

import com.lut.entity.Education;
import com.lut.entity.Result;

public interface IEducationService {

    /**
     * 获取所有的学历资源
     * @return
     */
    public Result getAllEducations();

    /**
     * 根据ID删除学历
     * @param ids
     * @return
     */
    public Result deleteEducation(String ids);

    /**
     * 新增学历
     * @param education
     * @return
     */
    public Result addEducation(Education education);

    /**
     * 修改学历
     * @param education
     * @return
     */
    public Result modifyEducation(Education education);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询学历
     * @param id
     * @return
     */
    public Result getEducationById(long id);

    /**
     * 根据员工姓名查询证书
     * @param employeeNumber
     * @return
     */
    public Result getEducationByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);
}
