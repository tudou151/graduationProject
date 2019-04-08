package com.lut.entity;

import com.lut.utils.Person;

/**
 * 工作经历
 */
public class Experience extends Person{

    private long id;

    /**
     * 工作单位
     */
    private String unit;

    /**
     * 工作岗位
     */
    private String post;

    /**
     * 入职时间
     */
    private String beginTime;

    /**
     * 离职时间
     */
    private String endTime;

    /**
     * 离职原因
     */
    private String reason;

    /**
     * 证明材料
     */
    private String ticket;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "id=" + id +
                ", unit='" + unit + '\'' +
                ", post='" + post + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", reason='" + reason + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", ticket='" + ticket + '\'' +
                '}';
    }
}
