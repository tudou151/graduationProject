package com.lut.service.impl;

import com.lut.entity.Certificate;
import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.CertificateRepository;
import com.lut.repository.EmployeeRepository;
import com.lut.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CertificateServiceImpl implements ICertificateService{

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired(required = false)
    private CertificateRepository certificateRepository;

    @Autowired
    private HttpServletRequest request;

    @Autowired(required = false)
    private  EmployeeRepository employeeRepository;


    @Override
    public Result getCertificateById(long id) {
        Certificate certificate = certificateRepository.getCertificateById(id);
        if (certificate==null){
            throw new MyException(-1,"根据id获取证书为空");
        }
        return new Result(0,"操作成功",certificate);
    }

    @Override
    public Result getCertificateByEmployeeNumber(String employeeNumber) {
        List<Certificate> certificates = certificateRepository.getCertificateByEmployeeNumber(employeeNumber);
        Page<Certificate> page = new Page<>(certificates);
        request.setAttribute("certificatePage",page);
        return new Result(0,"操作成功",null);
    }
    @Override
    public Result getEmployeeNumberByEmployeeName(String employeeName){
        String employeeNumber = certificateRepository.getEmployeeNumberByEmployeeName(employeeName);
        if (employeeNumber==null){
            throw new MyException(-1,"输入的姓名不存在！");
        }else{
            return new Result(0,"操作成功",employeeName);
        }
    }

    @Override
    public Result getAllCertificates(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Certificate> certificates = null;
        if(employee.getPermissions() == 0){
            certificates = certificateRepository.getAllCertificatees(employee.getEmployeeName());
            Page<Certificate> page = new Page<>(certificates);
            request.setAttribute("certificatePage",page);
        }else{
            certificates = certificateRepository.getAllCertificates();

            Page<Certificate> page = new Page<>(certificates);
            request.setAttribute("certificatePage",page);
        }

        return new Result(0,"操作成功",null);
    }

    @Override
    public Result deleteCertificate(String ids){
        List<Long> idss = getIds(ids);
        for (long id:idss){
            int i = certificateRepository.deleteCertificate(id);
            if (i!=1){
                return new Result(-1,"删除时发生未知错误:"+id,null);
            }
        }
        return new Result(0,"操作成功",null);
    }

    public List<Long> getIds(String ids){
        List<Long> list = new ArrayList<>();
        String[] is = ids.split(",");
        for (String i : is){
            list.add(Long.parseLong(i));
        }
        return list;
    }

    @Override
    public Result addCertificate(Certificate certificate) {
        String employeeNumber = certificate.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"新增失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        certificate.setFounder(employee.getEmployeeName());
        Date now = new Date();
        certificate.setCreateTime(now);
//        certificate = setState(now,certificate);
        int i = certificateRepository.addCertificate(certificate);
        if (i!=1){
            throw new MyException(-1,"新增失败，未知异常");
        }
        return new Result(0,"新增成功",null);
    }


    @Override
    public Result modifyCertificate(Certificate certificate){

        System.out.println(certificate.toString());
        String employeeNumber = certificate.getEmployeeNumber();
        String en = employeeRepository.getEmployeeNumberByEmployeeName(employeeNumber);
        System.out.println("em:  " + en);
        if(en == null){
            throw new MyException(-1,"修改失败，没有此人");
        }

        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        certificate.setModifier(employee.getEmployeeName());
        Date now = new Date();
        certificate.setModifyTime(now);
        int i = certificateRepository.modifyCertificate(certificate);
        if (i!=1){
            return new Result(-1,"修改失败",null);
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getPageDatas(Integer currentPage) {
        List<Certificate> certificates  = certificateRepository.getAllCertificates();

        Page<Certificate> page = new Page<>(certificates);
        page.setPage(currentPage);
        request.setAttribute("certificatePage",page);
        return new Result(0,"操作成功",null);
    }

}
