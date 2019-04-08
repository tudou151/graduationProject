package com.lut.service;

import com.lut.entity.Certificate;
import com.lut.entity.Employee;
import com.lut.entity.Result;

public interface ICertificateService {

    /**
     * 获取所有的证书资源
     * @return
     */
    public Result getAllCertificates();

    /**
     * 根据ID删除证书
     * @param ids
     * @return
     */
    public Result deleteCertificate(String ids);

    /**
     * 新增证书
     * @param certificate
     * @return
     */
    public Result addCertificate(Certificate certificate);

    /**
     * 修改证书
     * @param certificate
     * @return
     */
    public Result modifyCertificate(Certificate certificate);

    /**
     * 根据页数获取分页
     * @param currentPage
     * @return
     */
    public Result getPageDatas(Integer currentPage);

    /**
     * 根据id查询证书
     * @param id
     * @return
     */
    public Result getCertificateById(long id);

    /**
     * 根据员工姓名查询证书
     * @param employeeNumber
     * @return
     */
    public Result getCertificateByEmployeeNumber(String employeeNumber);

    /**
     * 根据员工姓名查询员工工号
     * @param employeeName
     * @return
     */
    public Result getEmployeeNumberByEmployeeName(String employeeName);



}
