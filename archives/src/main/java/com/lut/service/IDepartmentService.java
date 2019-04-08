package com.lut.service;

import com.lut.entity.Department;
import com.lut.entity.Result;

public interface IDepartmentService {

    /**
     * 获取所有的部门资源
     * @return
     */
    public Result getAllDepartments();

    /**
     * 根据ID删除部门
     * @param ids
     * @return
     */
    public Result deleteDepartment(String ids);

    /**
     * 新增部门
     * @param department
     * @return
     */
    public Result addDepartment(Department department);

    /**
     * 修改部门
     * @param department
     * @return
     */
    public Result modifyDepartment(Department department);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Result getDepartmentById(long id);

    /**
     * 根据部门名称查询部门
     * @param departmentNumber
     * @return
     */
    public Result getDepartmentByDepartmentNumber(String departmentNumber);

    /**
     * 根据部门名称查询
     * @param departmentName
     * @return
     */
    public Result getDepartmentNumberByDepartmentName(String departmentName);
}
