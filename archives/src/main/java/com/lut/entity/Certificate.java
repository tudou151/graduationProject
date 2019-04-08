package com.lut.entity;

import com.lut.utils.Person;

import java.util.Date;

/**
 * 证书信息
 */
public class Certificate extends Person {

    private long id;

    /**
     * 证书名称
     */
    private String name;

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
    private String certificateNumber;

    private String employeeNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + happenTime + '\'' +
                ", organization='" + organization + '\'' +
                ", employeeNumber='" + employeeNumber + '\'' +
                ", certificateNumber='" + certificateNumber + '\'' +
                '}';
    }
}
