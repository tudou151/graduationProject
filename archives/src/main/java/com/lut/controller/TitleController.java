package com.lut.controller;

import com.lut.entity.Title;
import com.lut.entity.Result;
import com.lut.service.ITitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/Title")
@RestController
public class TitleController {


    @Autowired
    ITitleService titleService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addTitle")
    public Result addTitle(@RequestParam("titleName") String titleName,
                           @RequestParam("happenTime") String happenTime,
                           @RequestParam("organization") String organization,
                           @RequestParam("titleNumber") String titleNumber,
                           @RequestParam("employeeNumber")String employeeNumber)throws ParseException {

        Title title = new Title();
        title.setTitleName(titleName);
        title.setTitleNumber(titleNumber);
        title.setHappenTime(happenTime);
        title.setOrganization(organization);
        title.setEmployeeNumber(employeeNumber);
        return titleService.addTitle(title);
    }


    @GetMapping("/getAllTitles")
    public ModelAndView getAllTitles(){
        titleService.getAllTitles();
//        System.out.println("就是"+request.getSession().getAttribute("titlePage"));
        return new ModelAndView("title");
    }

    @PostMapping("/deleteTitle")
    public Result deleteTitle(@RequestParam("ids") String ids){
        return titleService.deleteTitle(ids);
    }

    @PostMapping("/modifyTitle")
    public Result modifyTitle(@RequestParam("titleName") String titleName,
                              @RequestParam("happenTime") String happenTime,
                              @RequestParam("organization") String organization,
                              @RequestParam("titleNumber") String titleNumber,
                              @RequestParam("employeeNumber")String employeeNumber,
                              @RequestParam("id") long id)throws ParseException{
//        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Title title = new Title();
        title.setTitleName(titleName);
        title.setTitleNumber(titleNumber);
        title.setHappenTime(happenTime);
        title.setOrganization(organization);
        title.setEmployeeNumber(employeeNumber);
        title.setId(id);
//        System.out.println("title修改: "+ title);
        return titleService.modifyTitle(title);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        titleService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("title");
    }

    @GetMapping("/getTitleById")
    public Result getTitleById(@RequestParam("id") long id){
        return titleService.getTitleById(id);
    }

    @GetMapping("/getTitleByEmployeeNumber")
    public ModelAndView getTitleByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        titleService.getTitleByEmployeeNumber(employeeNumber);
        return new ModelAndView("title");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  titleService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
