package com.lut.entity;

import com.lut.utils.Person;

/**
 * 学历信息
 */
public class Education extends Person{

    private long id;

    /**
     * 毕业院校
     */
    private String graduation;

    /**
     * 专业名称
     */
    private String major;

    /**
     * 类型
     */
    private String genre;

    /**
     * 层次
     */
    private String arrangement;

    /**
     * 入学时间
     */
    private String beginTime;

    /**
     * 毕业时间
     */
    private String endTime;

    /**
     * 证书编号
     */
    private String educationNumber;

    /**
     * 学籍状态
     */
    private String status;

    private  String employeeNumber;

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGraduation() {
        return graduation;
    }

    public void setGraduation(String graduation) {
        this.graduation = graduation;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArrangement() {
        return arrangement;
    }

    public void setArrangement(String arrangement) {
        this.arrangement = arrangement;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEducationNumber() {
        return educationNumber;
    }

    public void setEducationNumber(String educationNumber) {
        this.educationNumber = educationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Education{" +
                "id=" + id +
                ", graduation='" + graduation + '\'' +
                ", major='" + major + '\'' +
                ", genre='" + genre + '\'' +
                ", arrangement='" + arrangement + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", educationNumber='" + educationNumber + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
