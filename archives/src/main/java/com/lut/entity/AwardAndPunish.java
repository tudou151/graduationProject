package com.lut.entity;

import com.lut.utils.Person;
/**
 * 惩奖信息
 */
public class AwardAndPunish extends Person {

    private Long id;

    /**
     * 惩奖类型
     */
    private String genre ;

    /**
     * 惩奖单位
     */
    private String company;

    /**
     * 惩奖原因
     */
    private String reason;

    /**
     * 惩奖时间
     */
    private String happenTime;

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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    @Override
    public String toString() {
        return "AwardAndPunish{" +
                "id=" + id +
                ", genre='" + genre + '\'' +
                ", company='" + company + '\'' +
                ", reason='" + reason + '\'' +
                ", happenTime='" + happenTime + '\'' +
                ", founder='" + founder + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", modifier='" + modifier +
                '\'' + '}';
    }
}
