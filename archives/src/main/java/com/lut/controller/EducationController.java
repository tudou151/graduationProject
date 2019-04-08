package com.lut.controller;

import com.lut.entity.Education;
import com.lut.entity.Result;
import com.lut.service.IEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/Education")
@RestController
public class EducationController {


    @Autowired
    IEducationService educationService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addEducation")
    public Result addEducation(@RequestParam("educationNumber") String educationNumber,
                               @RequestParam("graduation") String graduation,
                               @RequestParam("major") String major,
                               @RequestParam("genre") String genre,
                               @RequestParam("arrangement") String arrangement,
                               @RequestParam("status") String status,
                               @RequestParam("beginTime") String beginTime,
                               @RequestParam("employeeNumber") String employeeNumber,
                               @RequestParam("endTime") String endTime)throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Education education = new Education();
        education.setEducationNumber(educationNumber);
        education.setGraduation(graduation);
        education.setMajor(major);
        education.setGenre(genre);
        education.setArrangement(arrangement);
        education.setStatus(status);
        education.setBeginTime(beginTime);
        education.setEndTime(endTime);
        education.setEmployeeNumber(employeeNumber);

        return educationService.addEducation(education);
    }


    @GetMapping("/getAllEducations")
    public ModelAndView getAllEducations(){
        educationService.getAllEducations();
        return new ModelAndView("education");
    }

    @PostMapping("/deleteEducation")
    public Result deleteEducation(@RequestParam("ids") String ids){
        return educationService.deleteEducation(ids);
    }

    @PostMapping("/modifyEducation")
    public Result modifyEducation( @RequestParam("educationNumber") String educationNumber,
                                   @RequestParam("graduation") String graduation,
                                   @RequestParam("major") String major,
                                   @RequestParam("genre") String genre,
                                   @RequestParam("arrangement") String arrangement,
                                   @RequestParam("status") String status,
                                   @RequestParam("beginTime") String beginTime,
                                   @RequestParam("endTime") String endTime,
                                   @RequestParam("employeeNumber")String employeeNumber,
                                   @RequestParam("id") long id)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Education education = new Education();
        education.setEducationNumber(educationNumber);
        education.setGraduation(graduation);
        education.setMajor(major);
        education.setGenre(genre);
        education.setArrangement(arrangement);
        education.setStatus(status);
        education.setBeginTime(beginTime);
        education.setEndTime(endTime);
        education.setEmployeeNumber(employeeNumber);
        education.setId(id);
        return educationService.modifyEducation(education);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        educationService.getPageDatas(currentPage);
        return new ModelAndView("education");
    }

    @GetMapping("/getEducationById")
    public Result getEducationById(@RequestParam("id") long id){
        return educationService.getEducationById(id);
    }

    @GetMapping("/getEducationByEmployeeNumber")
    public ModelAndView getEducationByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        educationService.getEducationByEmployeeNumber(employeeNumber);
        return new ModelAndView("education");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  educationService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
