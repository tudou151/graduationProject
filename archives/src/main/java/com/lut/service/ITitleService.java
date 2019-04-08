package com.lut.service;

import com.lut.entity.Title;
import com.lut.entity.Result;

public interface ITitleService {

    /**
     * 获取所有的职称资源
     * @return
     */
    public Result getAllTitles();

    /**
     * 根据ID删除职称
     * @param ids
     * @return
     */
    public Result deleteTitle(String ids);

    /**
     * 新增职称
     * @param title
     * @return
     */
    public Result addTitle(Title title);

    /**
     * 修改职称
     * @param title
     * @return
     */
    public Result modifyTitle(Title title);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询职称
     * @param id
     * @return
     */
    public Result getTitleById(long id);

    /**
     * 根据员工姓名查询职称
     * @param employeeNumber
     * @return
     */
    public Result getTitleByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);
}
