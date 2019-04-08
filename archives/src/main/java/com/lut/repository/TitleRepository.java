package com.lut.repository;

import com.lut.entity.Title;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TitleRepository {
    @Insert("insert into TB_TITLES(ID,TITLE_NAME,HAPPEN_TIME,ORGANIZATION,employee_Number,TITLES_NUMBER,FOUNDER,CREATE_TIME)\n" +
            "VALUES(#{id},#{titleName},#{happenTime},#{organization},#{employeeNumber},#{titleNumber},#{founder},#{createTime})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addTitle(Title title);

    @Select("select * from TB_TITLES c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "titleName",column = "TITLE_NAME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "titleNumber",column = "TITLES_NUMBER"),
            @Result(property = "employeeNumber",column = "employee_Number")
    })
    public List<Title> getAllTitles();

    @Select("select * from TB_TITLES c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0 and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "titleName",column = "TITLE_NAME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "titleNumber",column = "TITLES_NUMBER"),
            @Result(property = "employeeNumber",column = "employee_Number")
    })
    public List<Title> getAllTitlees(@Param("employeeName") String employeeName);


    @Delete("delete from TB_TITLES where id=#{id}")
    public int deleteTitle(long id);

    @Update("update TB_TITLES set TITLE_NAME=#{titleName},HAPPEN_TIME=#{happenTime},TITLES_NUMBER=#{titleNumber},employee_number=#{employeeNumber},ORGANIZATION=#{organization},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyTitle(Title title);

    @Select("select * from TB_TITLES where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "titleName",column = "TITLE_NAME"),
            @Result(property = "titleNumber",column = "TITLES_NUMBER"),
            @Result(property = "employeeNumber",column = "employee_Number")

    })
    public Title getTitleById(@Param("id") long id);

    @Select("select * from TB_TITLES where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "titleName",column = "TITLE_NAME"),
            @Result(property = "titleNumber",column = "TITLES_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Title> getTitleByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

}
