package com.lut.service;

import com.lut.entity.Cultivate;
import com.lut.entity.Result;

public interface ICultivateService {

    /**
     * 获取所有的培训资源
     * @return
     */
    public Result getAllCultivates();

    /**
     * 根据ID删除培训
     * @param ids
     * @return
     */
    public Result deleteCultivate(String ids);

    /**
     * 新增培训
     * @param cultivate
     * @return
     */
    public Result addCultivate(Cultivate cultivate);

    /**
     * 修改培训
     * @param cultivate
     * @return
     */
    public Result modifyCultivate(Cultivate cultivate);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询培训
     * @param id
     * @return
     */
    public Result getCultivateById(long id);

    /**
     * 根据员工姓名查询培训
     * @param employeeNumber
     * @return
     */
    public Result getCultivateByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);



}
