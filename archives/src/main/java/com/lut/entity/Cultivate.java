package com.lut.entity;

import com.lut.utils.Person;

public class Cultivate extends Person{
    private long id;

    /**
     * 培训时间
     */
    private String happenTime;

    /**
     * 培训学时
     */
    private String howLong;

    /**
     * 培训内容
     */
    private String content;

    /**
     * 培训地点
     */
    private String address;

    /**
     * 培训成绩
     */
    private String score;

    private String employeeNumber;


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

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getHowLong() {
        return howLong;
    }

    public void setHowLong(String howLong) {
        this.howLong = howLong;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Cultivate{" +
                "id=" + id +
                ", happenTime='" + happenTime + '\'' +
                ", howLong='" + howLong + '\'' +
                ", content='" + content + '\'' +
                ", address='" + address + '\'' +
                ", score='" + score + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", founder='" + founder + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", modifier='" + modifier + '\'' +
                '}';
    }
}
