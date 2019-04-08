package com.lut.controller;

import com.lut.entity.Certificate;
import com.lut.entity.Employee;
import com.lut.entity.Result;
import com.lut.service.ICertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/Certificate")
@RestController
public class CertificateController {


    @Autowired
    ICertificateService certificateService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addCertificate")
    public Result addCertificate(@RequestParam("name") String name,
                                 @RequestParam("happenTime") String happenTime,
                                 @RequestParam("organization") String organization,
                                 @RequestParam("certificateNumber") String certificateNumber,
                                 @RequestParam("employeeNumber") String employeeNumber)throws ParseException {
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Certificate certificate = new Certificate();
        certificate.setName(name);
        certificate.setCertificateNumber(certificateNumber);
        certificate.setHappenTime(happenTime);
        certificate.setOrganization(organization);
        certificate.setEmployeeNumber(employeeNumber);

        return certificateService.addCertificate(certificate);
    }


    @GetMapping("/getAllCertificates")
    public ModelAndView getAllCertificates(){
        certificateService.getAllCertificates();
//        System.out.println("就是"+request.getSession().getAttribute("certificatePage"));
        return new ModelAndView("certificate");
    }

    @PostMapping("/deleteCertificate")
    public Result deleteCertificate(@RequestParam("ids") String ids){
        return certificateService.deleteCertificate(ids);
    }

    @PostMapping("/modifyCertificate")
    public Result modifyCertificate(@RequestParam("name") String name,
                                    @RequestParam("happenTime") String happenTime,
                                    @RequestParam("organization") String organization,
                                    @RequestParam("certificateNumber") String certificateNumber,
                                    @RequestParam("employeeNumber") String employeeNumber,

                                   @RequestParam("id") long id)throws ParseException{
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Certificate certificate = new Certificate();
        certificate.setName(name);
        certificate.setCertificateNumber(certificateNumber);
        certificate.setHappenTime(happenTime);
        certificate.setOrganization(organization);
        certificate.setEmployeeNumber(employeeNumber);
        certificate.setId(id);
//        System.out.println("certificate修改: "+ certificate);
        return certificateService.modifyCertificate(certificate);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        certificateService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("certificate");
    }

    @GetMapping("/getCertificateById")
    public Result getCertificateById(@RequestParam("id") long id){
        return certificateService.getCertificateById(id);
    }

    @GetMapping("/getCertificateByEmployeeNumber")
    public ModelAndView getCertificateByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        certificateService.getCertificateByEmployeeNumber(employeeNumber);
        return new ModelAndView("certificate");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  certificateService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
