package com.lut.repository;

import com.lut.entity.Cultivate;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface CultivateRepository {
    @Insert("insert into TB_CULTIVATE(ID,HOW_LONG,HAPPEN_TIME,CONTENT,ADDRESS,SCORE,FOUNDER,CREATE_TIME,EMPLOYEE_NUMBER)\n" +
            "VALUES(#{id},#{howLong},#{happenTime},#{content},#{address},#{score},#{founder},#{createTime},#{employeeNumber})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addCultivate(Cultivate cultivate);

    @Select("select * from TB_CULTIVATE c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "howLong",column = "HOW_LONG") ,
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Cultivate> getAllCultivates();

    @Select("select * from TB_CULTIVATE c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0 and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "howLong",column = "HOW_LONG") ,
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Cultivate> getAllCultivatees(@Param("employeeName") String employeeName);


    @Delete("delete from TB_CULTIVATE where id=#{id}")
    public int deleteCultivate(long id);

    @Update("update TB_CULTIVATE set HOW_LONG=#{howLong},HAPPEN_TIME=#{happenTime},ADDRESS=#{address},score=#{score},CONTENT=#{content},EMPLOYEE_NUMBER=#{employeeNumber},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyCultivate(Cultivate cultivate);

    @Select("select * from TB_CULTIVATE where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "howLong",column = "HOW_LONG") ,
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public Cultivate getCultivateById(@Param("id") long id);

    @Select("select * from TB_CULTIVATE where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "howLong",column = "HOW_LONG") ,
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Cultivate> getCultivateByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);


}
