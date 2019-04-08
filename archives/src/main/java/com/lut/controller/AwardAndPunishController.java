package com.lut.controller;

import com.lut.entity.AwardAndPunish;
import com.lut.entity.Result;
import com.lut.service.IAwardAndPunishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/AwardAndPunish")
@RestController
public class AwardAndPunishController {


    @Autowired
    IAwardAndPunishService awardAndPunishService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addAwardAndPunish")
    public Result addAwardAndPunish(@RequestParam("genre") String genre,
                                    @RequestParam("company") String company,
                                    @RequestParam("reason") String reason,
                                    @RequestParam("employeeNumber") String employeeNumber,
                                    @RequestParam("happenTime") String happenTime
                                    )throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        AwardAndPunish awardAndPunish = new AwardAndPunish();
        awardAndPunish.setCompany(company);
        awardAndPunish.setGenre(genre);
        awardAndPunish.setHappenTime(happenTime);
        awardAndPunish.setReason(reason);
        awardAndPunish.setEmployeeNumber(employeeNumber);

        return awardAndPunishService.addAwardAndPunish(awardAndPunish);
    }


    @GetMapping("/getAllAwardAndPunishs")
    public ModelAndView getAllAwardAndPunishs(){
        awardAndPunishService.getAllAwardAndPunishs();
//        System.out.println("就是"+request.getSession().getAttribute("awardAndPunishPage"));
        return new ModelAndView("awardAndPunish");
    }

    @PostMapping("/deleteAwardAndPunish")
    public Result deleteAwardAndPunish(@RequestParam("ids") String ids){
        return awardAndPunishService.deleteAwardAndPunish(ids);
    }

    @PostMapping("/modifyAwardAndPunish")
    public Result modifyAwardAndPunish(@RequestParam("genre") String genre,
                                       @RequestParam("company") String company,
                                       @RequestParam("reason") String reason,
                                       @RequestParam("happenTime") String happenTime,
                                       @RequestParam("employeeNumber") String employeeNumber,
                                       @RequestParam("id") long id)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        AwardAndPunish awardAndPunish = new AwardAndPunish();
        awardAndPunish.setCompany(company);
        awardAndPunish.setGenre(genre);
        awardAndPunish.setHappenTime(happenTime);
        awardAndPunish.setReason(reason);
        awardAndPunish.setEmployeeNumber(employeeNumber);

        awardAndPunish.setId(id);
//        System.out.println("awardAndPunish修改: "+ awardAndPunish);
        return awardAndPunishService.modifyAwardAndPunish(awardAndPunish);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        awardAndPunishService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("awardAndPunish");
    }

    @GetMapping("/getAwardAndPunishById")
    public Result getAwardAndPunishById(@RequestParam("id") long id){
        return awardAndPunishService.getAwardAndPunishById(id);
    }

    @GetMapping("/getAwardAndPunishByEmployeeNumber")
    public ModelAndView getAwardAndPunishByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        awardAndPunishService.getAwardAndPunishByEmployeeNumber(employeeNumber);
        return new ModelAndView("awardAndPunish");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  awardAndPunishService.getEmployeeNumberByEmployeeName(employeeName);
    }

}
