package com.lut.controller;

import com.lut.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YangGang
 * @date: 2018/2/13.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Autowired
    private AwardAndPunishServiceImpl awardAndPunishService;

    @Autowired
    private BulletinResourceServiceImpl bulletinResourceService;

    @Autowired
    private CertificateServiceImpl certificateService;

    @Autowired
    private TitleServiceImpl titleService;

    @Autowired
    private EducationServiceImpl educationService;

    @Autowired
    private CommunityServiceImpl communityService;

    @Autowired
    private ExperienceServiceImpl experienceService;

    @Autowired
    private CultivateServiceImpl cultivateService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/image")
    public ModelAndView image(){
        ModelAndView mav = new ModelAndView("image");
        return mav;
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView index = new ModelAndView("index");
        return index;
    }

    @RequestMapping("/employeeInfo")
    public ModelAndView employeeInfo(){
        ModelAndView employeeInfo = new ModelAndView("employeeInfo");
        return employeeInfo;
    }

    @RequestMapping("/returnBulletinJSP")
    public ModelAndView returnBulletinJSP(){
        ModelAndView returnBulletinJSP = new ModelAndView("bulletin");
        bulletinResourceService.getAllBulletinResources();
        return returnBulletinJSP;
    }

    @RequestMapping("/returnDepartmentJSP")
    public ModelAndView returnDepartmentJSP(){
        ModelAndView returnDepartmentJSP = new ModelAndView("department");
        departmentService.getAllDepartments();
        return returnDepartmentJSP;
    }

    @RequestMapping("/returnAwardAndPunishJSP")
    public ModelAndView returnAwardAndPunishJSP(){
        ModelAndView returnAwardAndPunishJSP = new ModelAndView("awardAndPunish");
        awardAndPunishService.getAllAwardAndPunishs();
        return returnAwardAndPunishJSP;
    }

    @RequestMapping("/returnCertificateJSP")
    public ModelAndView returnCertificateJSP(){
        ModelAndView returnCertificateJSP = new ModelAndView("certificate");
        certificateService.getAllCertificates();
        return returnCertificateJSP;
    }

    @RequestMapping("/returnTitleJSP")
    public ModelAndView returnTitleJSP(){
        ModelAndView returnTitleJSP = new ModelAndView("title");
        titleService.getAllTitles();
        return returnTitleJSP;
    }

    @RequestMapping("/returnEducationJSP")
    public ModelAndView returnEducationJSP(){
        ModelAndView returnEducationJSP = new ModelAndView("education");
        educationService.getAllEducations();
        return returnEducationJSP;
    }

    @RequestMapping("/returnCommunityJSP")
    public ModelAndView returnCommunityJSP(){
        ModelAndView returnCommunityJSP = new ModelAndView("community");
        communityService.getAllCommunitys();
        return returnCommunityJSP;
    }

    @RequestMapping("/returnExperienceJSP")
    public ModelAndView returnExperienceJSP(){
        ModelAndView returnExperienceJSP = new ModelAndView("experience");
        experienceService.getAllExperiences();
        return returnExperienceJSP;
    }

    @RequestMapping("/returnCultivateJSP")
    public ModelAndView returnCultivateJSP(){
        ModelAndView returnCultivateJSP = new ModelAndView("cultivate");
        cultivateService.getAllCultivates();
        return returnCultivateJSP;
    }


}
