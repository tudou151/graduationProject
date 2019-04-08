package com.lut.service;

import com.lut.entity.AwardAndPunish;
import com.lut.entity.Result;

public interface IAwardAndPunishService {

    /**
     * 获取所有的奖惩信息资源
     * @return
     */
    public Result getAllAwardAndPunishs();

    /**
     * 根据ID删除奖惩信息
     * @param ids
     * @return
     */
    public Result deleteAwardAndPunish(String ids);

    /**
     * 新增奖惩信息
     * @param awardAndPunish
     * @return
     */
    public Result addAwardAndPunish(AwardAndPunish awardAndPunish);

    /**
     * 修改奖惩信息
     * @param awardAndPunish
     * @return
     */
    public Result modifyAwardAndPunish(AwardAndPunish awardAndPunish);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询奖惩信息
     * @param id
     * @return
     */
    public Result getAwardAndPunishById(long id);

    /**
     * 根据员工姓名查询奖惩信息
     * @param employeeNumber
     * @return
     */
    public Result getAwardAndPunishByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);
}
