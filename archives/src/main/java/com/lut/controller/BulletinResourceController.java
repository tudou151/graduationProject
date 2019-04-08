package com.lut.controller;

import com.lut.entity.BulletinResource;
import com.lut.entity.Result;
import com.lut.service.IBulletinResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequestMapping("/BulletinResource")
@RestController
public class BulletinResourceController {

    @Autowired
    IBulletinResourceService bulletinResourceService;

    @PostMapping("/addBulletinResource")
    public Result addBulletinResource(@RequestParam("title") String title,
                                      @RequestParam("startDate") String startDate,
                                      @RequestParam("endDate") String endDate,
                                      @RequestParam("content") String content)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDate = startDate.replace("T"," ")+":00";
        Date startDate1=formatter.parse(startDate);
        endDate = endDate.replace("T"," ")+":00";
        Date endDate1=formatter.parse(endDate);
        BulletinResource bulletinResource = new BulletinResource();
        bulletinResource.setStartDate(startDate1);
        bulletinResource.setEndDate(endDate1);
        bulletinResource.setTitle(title);
        bulletinResource.setContent(content);
        return bulletinResourceService.addBulletinResource(bulletinResource);
    }

    @GetMapping("/getAllBulletinResources")
    public ModelAndView getAllBulletinResources(){
        bulletinResourceService.getAllBulletinResources();
        return new ModelAndView("bulletin");
    }

    @PostMapping("/deleteBulletinResource")
    public Result deleteBulletinResource(@RequestParam("ids") String ids){
        return bulletinResourceService.deleteBulletinResource(ids);
    }

    @PostMapping("/modifyBulletinResource")
    public Result modifyBulletinResource(@RequestParam("title") String title,@RequestParam("startDate") String startDate,
                                         @RequestParam("endDate") String endDate,@RequestParam("content") String content,@RequestParam("id") long id)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startDate = startDate.replace("T"," ")+":00";
        Date startDate1=formatter.parse(startDate);
        endDate = endDate.replace("T"," ")+":00";
        Date endDate1=formatter.parse(endDate);
        BulletinResource bulletinResource = new BulletinResource();
        bulletinResource.setStartDate(startDate1);
        bulletinResource.setEndDate(endDate1);
        bulletinResource.setTitle(title);
        bulletinResource.setContent(content);
        bulletinResource.setId(id);
        return bulletinResourceService.modifyBulletinResource(bulletinResource);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        bulletinResourceService.getPageDatas(currentPage);
//        System.out.println("bulletin currentPage : "+currentPage);

        return new ModelAndView("bulletin");
    }

    @GetMapping("/getBulletinById")
    public Result getBulletinById(@RequestParam("id") long id){
        return bulletinResourceService.getBulletinById(id);
    }

    @GetMapping("/getBulletinResourceById")
    public ModelAndView getBulletinResourceById(@RequestParam("id") long id)throws ParseException{
        bulletinResourceService.getBulletinResourceById(id);
        return new ModelAndView("bulletin");
    }

    @PostMapping("/getIdByTitle")
    public Result getIdByTitle(@RequestParam("bulletintitle") String bulletintitle)throws ParseException{
        return  bulletinResourceService.getIdByTitle(bulletintitle);
    }
}
