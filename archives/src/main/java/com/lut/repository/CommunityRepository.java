package com.lut.repository;

import com.lut.entity.Community;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommunityRepository {
    @Insert("insert into TB_COMMUNITY(ID,employee_Number,RELATION,NAME,AGE,POLITICAL,NATION,WORK,POST,PHENOMENON,FOUNDER,CREATE_TIME)\n" +
            "VALUES(#{id},#{employeeNumber},#{relation},#{name},#{age},#{political},#{nation},#{work},#{post},#{phenomenon},#{founder},#{createTime})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addCommunity(Community community);

    @Select("select * from TB_COMMUNITY c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0 ")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Community> getAllCommunitys();

    @Select("select * from TB_COMMUNITY c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0  and b.Permissions = 0")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Community> getAllCommunityes(@Param("employeeName") String employeeName);


    @Delete("delete from TB_COMMUNITY where id=#{id}")
    public int deleteCommunity(long id);

    @Update("update TB_COMMUNITY set RELATION=#{relation},employee_Number=#{employeeNumber},NAME=#{name},AGE=#{age},POLITICAL=#{political},NATION=#{nation},WORK=#{work},POST=#{post},PHENOMENON=#{phenomenon},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public int modifyCommunity(Community community);

    @Select("select * from TB_COMMUNITY where id=#{id}")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public Community getCommunityById(@Param("id") long id);

    @Select("select * from TB_COMMUNITY where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Community> getCommunityByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

}
