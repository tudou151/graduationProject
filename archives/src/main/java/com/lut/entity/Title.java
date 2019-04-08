package com.lut.entity;

import com.lut.utils.Person;

/**
 * 职称信息
 */
public class Title extends Person{

    private Long id;

    /**
     * 职称名称
     */
    private String titleName ;

    /**
     * 发证时间
     */
    private String happenTime;

    /**
     * 发证机构
     */
    private String organization;

    /**
     * 证书编号
     */
    private String titleNumber;

    private  String employeeNumber;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitleNumber() {
        return titleNumber;
    }

    public void setTitleNumber(String titleNumber) {
        this.titleNumber = titleNumber;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "id=" + id +
                ", titleName='" + titleName + '\'' +
                ", happenTime='" + happenTime + '\'' +
                ", organization='" + organization + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", titleNumber='" + titleNumber + '\'' +
                '}';
    }
}
