package com.lut.entity;

import com.lut.utils.Person;

import java.util.Date;

public class Employee extends Person{

    private long id;
    /**
     * 员工工号
     */
    private String employeeNumber;

    /**
     *员工姓名
     */
    private String employeeName;

    /**
     * 密码
     */
    private String password;

    /**
     *性别
     */
    private String sex;

    /**
     * 出生年月
     */
    private String birth;

    /**
     * 政治面貌
     */
    private String zzmm;

    /**
     * 民族
     */
    private String nation;

    /**
     * 婚姻状态
     */
    private String marriage;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 籍贯
     */
    private String root;

    /**
     * 现居住地
     */
    private String address;

    /**
     * 入职时间
     */
    private String beginTime;

    /**
     * 转正时间
     */
    private String becomeTime;

    /**
     * 离职时间
     */
    private String leaveTime;

    /**
     * 照片
     */
    private String imageUrl;


    /**
     * 是否在职
     */
    private String ifOnDuty;

    /**
     * 权限等级
     */
    private Integer permissions;

    /**
     * 所属部门
     */
    private String departmentName;

    /**
     * 删除状态
     */
    private Integer deleteMark;

    public Integer getDeleteMark() {
        return deleteMark;
    }

    public void setDeleteMark(Integer deleteMark) {
        this.deleteMark = deleteMark;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Employee (){}
    
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIfOnDuty() {
        return ifOnDuty;
    }

    public void setIfOnDuty(String ifOnDuty) {
        this.ifOnDuty = ifOnDuty;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getZzmm() {
        return zzmm;
    }

    public void setZzmm(String zzmm) {
        this.zzmm = zzmm;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getBecomeTime() {
        return becomeTime;
    }

    public void setBecomeTime(String becomeTime) {
        this.becomeTime = becomeTime;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", birth='" + birth + '\'' +
                ", zzmm='" + zzmm + '\'' +
                ", nation='" + nation + '\'' +
                ", marriage='" + marriage + '\'' +
                ", idCard='" + idCard + '\'' +
                ", tel='" + tel + '\'' +
                ", email='" + email + '\'' +
                ", ifOnDuty='" + ifOnDuty + '\'' +
                ", root='" + root + '\'' +
                ", address='" + address + '\'' +
                ", beginTime=" + beginTime +
                ", departmentName=" + departmentName +
                ", becomeTime=" + becomeTime +
                ", leaveTime=" + leaveTime +
                ", imageUrl='" + imageUrl + '\'' +
                ", permissions=" + permissions + '}';
    }
}
