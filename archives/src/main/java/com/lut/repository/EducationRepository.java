package com.lut.repository;

import com.lut.entity.Education;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EducationRepository {
    @Insert("insert into TB_EDUCATION(ID,employee_Number,GRADUATION,MAJOR,GENRE,ARRANGEMENT,BEGIN_TIME,END_TIME,EDUCATION_NUMBER,STATUS,FOUNDER,CREATE_TIME)\n" +
            "VALUES(#{id},#{employeeNumber},#{graduation},#{major},#{genre},#{arrangement},#{beginTime},#{endTime},#{educationNumber},#{status},#{founder},#{createTime})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addEducation(Education education);

    @Select("select * from TB_EDUCATION c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "educationNumber",column = "EDUCATION_NUMBER"),
            @Result(property = "employeeNumber",column = "employee_Number")

    })
    public List<Education> getAllEducations();

    @Select("select * from TB_EDUCATION c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0 and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "BEGIN_TIME"),
            @Result(property = "endTime",column = "END_TIME"),
            @Result(property = "educationNumber",column = "EDUCATION_NUMBER"),
            @Result(property = "employeeNumber",column = "employee_Number")

    })
    public List<Education> getAllEducationes(@Param("employeeName") String employeeName);

    @Delete("delete from TB_EDUCATION where id=#{id}")
    public int deleteEducation(long id);

    @Update("update TB_EDUCATION set EDUCATION_NUMBER=#{educationNumber},employee_Number=#{employeeNumber},GRADUATION=#{graduation},MAJOR=#{major},GENRE=#{genre},ARRANGEMENT=#{arrangement},BEGIN_TIME=#{beginTime},END_TIME=#{endTime},STATUS=#{status},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyEducation(Education education);

    @Select("select * from TB_EDUCATION where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "begin_time"),
            @Result(property = "endTime",column = "end_Time"),
            @Result(property = "educationNumber",column = "EDUCATION_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public Education getEducationById(@Param("id") long id);

    @Select("select * from TB_EDUCATION where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "beginTime",column = "begin_time"),
            @Result(property = "endTime",column = "end_Time"),
            @Result(property = "educationNumber",column = "EDUCATION_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Education> getEducationByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

}
