package com.lut.repository;

import com.lut.entity.AwardAndPunish;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AwardAndPunishRepository {
    @Insert("insert into TB_AWARD_AND_PUNISH(ID,GENRE,COMPANY,REASON,HAPPEN_TIME,FOUNDER,CREATE_TIME,employee_number)\n" +
            "VALUES(#{id},#{genre},#{company},#{reason},#{happenTime},#{founder},#{createTime},#{employeeNumber})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addAwardAndPunish(AwardAndPunish awardAndPunish);

    @Select("select * from tb_award_and_punish a,tb_employee b where a.employee_number = b.employee_name and b.delete_mark=0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "employeeNumber",column = "employee_number")

    })
    public List<AwardAndPunish> getAllAwardAndPunishs();

    @Select("select * from tb_award_and_punish a,tb_employee b where a.employee_number=#{employeeName} and a.employee_number = b.employee_name and b.delete_mark=0 and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "employeeNumber",column = "employee_number")

    })
    public List<AwardAndPunish> getAllAwardAndPunishes(@Param("employeeName") String employeeName);

    @Delete("delete from TB_AWARD_AND_PUNISH where id=#{id}")
    public int deleteAwardAndPunish(long id);

    @Update("update TB_AWARD_AND_PUNISH set GENRE=#{genre},COMPANY=#{company},REASON=#{reason},HAPPEN_TIME=#{happenTime},MODIFY_TIME=#{modifyTime},employee_number=#{employeeNumber},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyAwardAndPunish(AwardAndPunish awardAndPunish);

    @Select("select * from TB_AWARD_AND_PUNISH where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "employeeNumber",column = "employee_number")
    })
    public AwardAndPunish getAwardAndPunishById(@Param("id") long id);

    @Select("select * from TB_AWARD_AND_PUNISH where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<AwardAndPunish> getAwardAndPunishByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

}
