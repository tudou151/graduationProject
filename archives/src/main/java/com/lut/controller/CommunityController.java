package com.lut.controller;

import com.lut.entity.Community;
import com.lut.entity.Result;
import com.lut.service.ICommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequestMapping("/Community")
@RestController
public class CommunityController {


    @Autowired
    ICommunityService communityService;

    @Autowired
    private HttpServletRequest request;

    @PostMapping("/addCommunity")
    public Result addCommunity(@RequestParam("relation") String relation,
                               @RequestParam("name") String name,
                               @RequestParam("age") String age,
                               @RequestParam("political") String political,
                               @RequestParam("nation") String nation,
                               @RequestParam("work") String work,
                               @RequestParam("post") String post,
                               @RequestParam("phenomenon") String phenomenon,
                               @RequestParam("employeeNumber")String employeeNumber)throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Community community = new Community();
        community.setRelation(relation);
        community.setName(name);
        community.setAge(age);
        community.setPolitical(political);
        community.setNation(nation);
        community.setWork(work);
        community.setPost(post);
        community.setPhenomenon(phenomenon);
        community.setEmployeeNumber(employeeNumber);

        return communityService.addCommunity(community);
    }


    @GetMapping("/getAllCommunitys")
    public ModelAndView getAllCommunitys(){
        communityService.getAllCommunitys();
//        System.out.println("就是Community："+request.getSession().getAttribute("communityPage"));
        return new ModelAndView("community");
    }

    @PostMapping("/deleteCommunity")
    public Result deleteCommunity(@RequestParam("ids") String ids){
        return communityService.deleteCommunity(ids);
    }

    @PostMapping("/modifyCommunity")
    public Result modifyCommunity(@RequestParam("relation") String relation,
                                  @RequestParam("name") String name,
                                  @RequestParam("age") String age,
                                  @RequestParam("political") String political,
                                  @RequestParam("nation") String nation,
                                  @RequestParam("work") String work,
                                  @RequestParam("post") String post,
                                  @RequestParam("phenomenon") String phenomenon,
                                  @RequestParam("employeeNumber") String employeeNumber,
                                  @RequestParam("id") long id)throws ParseException{
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Community community = new Community();

        community.setRelation(relation);
        community.setName(name);
        community.setAge(age);
        community.setPolitical(political);
        community.setNation(nation);
        community.setWork(work);
        community.setPost(post);
        community.setPhenomenon(phenomenon);
        community.setEmployeeNumber(employeeNumber);
        community.setId(id);
        return communityService.modifyCommunity(community);
    }

    @GetMapping("/getPage")
    public ModelAndView getPage(@RequestParam("currentPage") Integer currentPage){
        communityService.getPageDatas(currentPage);
//        System.out.println("currentPage: "+currentPage);
        return new ModelAndView("community");
    }

    @GetMapping("/getCommunityById")
    public Result getCommunityById(@RequestParam("id") long id){
        return communityService.getCommunityById(id);
    }

    @GetMapping("/getCommunityByEmployeeNumber")
    public ModelAndView getCommunityByEmployeeNumber(@RequestParam("employeeNumber") String employeeNumber)throws ParseException{
        communityService.getCommunityByEmployeeNumber(employeeNumber);
        return new ModelAndView("community");
    }

    @PostMapping("/getEmployeeNumberByEmployeeName")
    public Result getEmployeeNumberByEmployeeName(@RequestParam("employeeName") String employeeName)throws ParseException{
        return  communityService.getEmployeeNumberByEmployeeName(employeeName);
    }
}
