package com.lut.entity;

import com.lut.utils.Person;

/**
 * 社会关系
 */
public class Community extends Person{

    private long id;

    /**
     * 关系
     */
    private String relation;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private String age;

    /**
     * 政治面貌
     */
    private String political;

    /**
     * 民族
     */
    private String nation;

    /**
     * 工作单位
     */
    private String work;

    /**
     * 职务
     */
    private String post;

    /**
     * 联系电话
     */
    private String phenomenon;

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

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhenomenon() {
        return phenomenon;
    }

    public void setPhenomenon(String phenomenon) {
        this.phenomenon = phenomenon;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", relation='" + relation + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", political='" + political + '\'' +
                ", nation='" + nation + '\'' +
                ", work='" + work + '\'' +
                ", post='" + post + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", phenomenon='" + phenomenon + '\'' +
                '}';
    }
}
