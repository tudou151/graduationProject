package com.lut.entity;

import com.lut.utils.Person;

import java.util.Date;

public class BulletinResource extends Person{
    private long id;
    /**
     * 公告标题
     */
    private String title;
    /**
     * 公告状态（0失效，1生效，2未生效）
     */
    private String state;
    /**
     * 公告内容
     */
    private String content;
    /**
     * 公告起始日期
     */
    private Date startDate;
    /**
     * 公告结束日期
     */
    private Date endDate;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BulletinResource{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", content='" + content + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
