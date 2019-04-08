package com.lut.controller;

import com.lut.entity.Experience;
import com.lut.entity.Result;
import com.lut.service.IExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/Experience")
@RestController
public class ExperienceController {


    @Autowired
    IExperienceService experienceService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addExperience")
    public Result addExperience(@RequestParam("unit") String unit,
                                @RequestParam("post") String post,
                                @RequestParam("beginTime") String beginTime,
                                @RequestParam("endTime") String endTime,
                                @RequestParam("reason") String reason,
                                @RequestParam("ticket") String ticket,
                                @RequestParam("employeeNumber") String employeeNumber)  throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Experience experience = new Experience();
        experience.setUnit(unit);
        experience.setPost(post);
        experience.setBeginTime(beginTime);
        experience.setEndTime(endTime);
        experience.setReason(reason);
        experience.setTicket(ticket);
        experience.setEmployeeNumber(employeeNumber);
        return experienceService.addExperience(experience);
    }


    @GetMapping("/getAllExperiences")
    public ModelAndView getAllExperiences(){
        experienceService.getAllExperiences();
//        System.out.println("就是"+request.getSession().getAttribute("experiencePage"));
        return new ModelAndView("experience");
    }

    @PostMapping("/deleteExperience")
    public Result deleteExperience(@RequestParam("ids") String ids){
        return experienceService.deleteExperience(ids);
    }

    @PostMapping("/modifyExperience")
    public Result modifyExperience(@RequestParam("unit") String unit,
                                   @RequestParam("post") String post,
                                   @RequestParam("beginTime") String beginTime,
                                   @RequestParam("endTime") String endTime,
                                   @RequestParam("reason") String reason,
                                   @RequestParam("ticket") String ticket,
                                   @RequestParam("employeeNumber")String employeeNumber,
                                   @RequestParam("id") long id)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Experience experience = new Experience();
        experience.setUnit(unit);
        experience.setPost(post);
        experience.setBeginTime(beginTime);
        experience.setEndTime(endTime);
        experience.setReason(reason);
        experience.setTicket(ticket);
        experience.setEmployeeNumber(employeeNumber);
        experience.setId(id);
//        System.out.println("experience修改: "+ experience);
        return experienceService.modifyExperience(experience);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        experienceService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("experience");
    }

    @GetMapping("/getExperienceById")
    public Result getExperienceById(@RequestParam("id") long id){
        return experienceService.getExperienceById(id);
    }

    @GetMapping("/getExperienceByEmployeeNumber")
    public ModelAndView getExperienceByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        experienceService.getExperienceByEmployeeNumber(employeeNumber);
        return new ModelAndView("experience");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  experienceService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
