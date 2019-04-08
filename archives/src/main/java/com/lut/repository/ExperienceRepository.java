package com.lut.repository;

import com.lut.entity.Experience;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExperienceRepository {
    @Insert("insert into TB_EXPERIENCE(ID,UNIT,POST,BEGIN_TIME,END_TIME,REASON,TICKET,EMPLOYEE_NUMBER,FOUNDER,CREATE_TIME)\n" +
            "VALUES(#{id},#{unit},#{post},#{beginTime},#{endTime},#{reason},#{ticket},#{employeeNumber},#{founder},#{createTime})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addExperience(Experience experience);

    @Select("select * from TB_EXPERIENCE c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")

    })
    public List<Experience> getAllExperiences();

    @Select("select * from TB_EXPERIENCE c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0 and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")

    })
    public List<Experience> getAllExperiencees(@Param("employeeName") String employeeName);


    @Delete("delete from TB_EXPERIENCE where id=#{id}")
    public int deleteExperience(long id);

    @Update("update TB_EXPERIENCE set UNIT=#{unit},POST=#{post},BEGIN_TIME=#{beginTime},employee_Number=#{employeeNumber},END_TIME=#{endTime},REASON=#{reason},TICKET=#{ticket},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyExperience(Experience experience);

    @Select("select * from TB_EXPERIENCE where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public Experience getExperienceById(@Param("id") long id);

    @Select("select * from TB_EXPERIENCE where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Experience> getExperienceByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

}
