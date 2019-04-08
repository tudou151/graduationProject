package com.lut.controller;

import com.lut.entity.Cultivate;
import com.lut.entity.Result;
import com.lut.service.ICultivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

@RequestMapping("/Cultivate")
@RestController
public class CultivateController {


    @Autowired
    ICultivateService cultivateService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addCultivate")
    public Result addCultivate(@RequestParam("happenTime") String happenTime,
                                 @RequestParam("howLong") String howLong,
                                 @RequestParam("content") String content,
                                 @RequestParam("address") String address,
                                 @RequestParam("score") String score,
                                 @RequestParam("employeeNumber") String employeeNumber)throws ParseException {
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Cultivate cultivate = new Cultivate();
        cultivate.setEmployeeNumber(employeeNumber);
        cultivate.setHappenTime(happenTime);
        cultivate.setAddress(address);
        cultivate.setHowLong(howLong);
        cultivate.setContent(content);
        cultivate.setScore(score);

        return cultivateService.addCultivate(cultivate);
    }


    @GetMapping("/getAllCultivates")
    public ModelAndView getAllCultivates(){
        cultivateService.getAllCultivates();
//        System.out.println("就是"+request.getSession().getAttribute("cultivatePage"));
        return new ModelAndView("cultivate");
    }

    @PostMapping("/deleteCultivate")
    public Result deleteCultivate(@RequestParam("ids") String ids){
        return cultivateService.deleteCultivate(ids);
    }

    @PostMapping("/modifyCultivate")
    public Result modifyCultivate(@RequestParam("happenTime") String happenTime,
                                  @RequestParam("howLong") String howLong,
                                  @RequestParam("content") String content,
                                  @RequestParam("address") String address,
                                  @RequestParam("score") String score,
                                  @RequestParam("employeeNumber") String employeeNumber,
                                  @RequestParam("id") long id)throws ParseException{
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Cultivate cultivate = new Cultivate();
        cultivate.setEmployeeNumber(employeeNumber);
        cultivate.setHappenTime(happenTime);
        cultivate.setAddress(address);
        cultivate.setHowLong(howLong);
        cultivate.setContent(content);
        cultivate.setScore(score);
        cultivate.setId(id);
        return cultivateService.modifyCultivate(cultivate);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        cultivateService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("cultivate");
    }

    @GetMapping("/getCultivateById")
    public Result getCultivateById(@RequestParam("id") long id){
        return cultivateService.getCultivateById(id);
    }

    @GetMapping("/getCultivateByEmployeeNumber")
    public ModelAndView getCultivateByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        cultivateService.getCultivateByEmployeeNumber(employeeNumber);
        return new ModelAndView("cultivate");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  cultivateService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
