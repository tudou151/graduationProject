package com.lut.entity;


import com.lut.utils.Person;

import java.util.Date;

public class Department extends Person{

    private Long id;

    /**
     * 部门编号
     */
    private String departmentNumber;

    /**
     * 部门名称
     */
    private String departmentName;

    /**
     * 部门主管
     */
    private String departmentHead;

    /**
     * 部门地址
     */
    private String departmentAddress;

    /**
     * 部门电话
     */

    private String departmentTel;

    /**
     * 部门传真
     */
    private String departmentFax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public String getDepartmentTel() {
        return departmentTel;
    }

    public void setDepartmentTel(String departmentTel) {
        this.departmentTel = departmentTel;
    }

    public String getDepartmentFax() {
        return departmentFax;
    }

    public void setDepartmentFax(String departmentFax) {
        this.departmentFax = departmentFax;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id='" + id + '\'' +
                "departmentNumber='" + departmentNumber + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentHead='" + departmentHead + '\'' +
                ", departmentAddress='" + departmentAddress + '\'' +
                ", departmentTel='" + departmentTel + '\'' +
                ", departmentFax='" + departmentFax + '\'' +
                '}';
    }
}

