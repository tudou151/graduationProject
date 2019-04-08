package com.lut.repository;

import com.lut.entity.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DepartmentRepository {
    @Insert("insert into TB_DEPARTMENT(ID,DEPARTMENT_NUMBER,DEPARTMENT_NAME,DEPARTMENT_HEAD,DEPARTMENT_ADDRESS,DEPARTMENT_TEL,DEPARTMENT_FAX,FOUNDER,CREATE_TIME)\n" +
            "VALUES(#{id},#{departmentNumber},#{departmentName},#{departmentHead},#{departmentAddress},#{departmentTel},#{departmentFax},#{founder},#{createTime})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addDepartment(Department department);

    @Select("select * from TB_DEPARTMENT ")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "departmentNumber",column = "department_Number"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "departmentHead",column = "department_Head"),
            @Result(property = "departmentAddress",column = "department_Address"),
            @Result(property = "departmentTel",column = "department_Tel"),
            @Result(property = "departmentFax",column = "department_Fax")

    })
    public List<Department> getAllDepartments();


    @Delete("delete from TB_DEPARTMENT where id=#{id}")
    public int deleteDepartment(long id);

    @Update("update TB_DEPARTMENT set DEPARTMENT_NUMBER=#{departmentNumber},DEPARTMENT_NAME=#{departmentName},DEPARTMENT_HEAD=#{departmentHead},DEPARTMENT_ADDRESS=#{departmentAddress},DEPARTMENT_TEL=#{departmentTel},DEPARTMENT_FAX=#{departmentFax},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyDepartment(Department department);

    @Select("select * from TB_DEPARTMENT where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "departmentNumber",column = "department_Number"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "departmentHead",column = "department_Head"),
            @Result(property = "departmentAddress",column = "department_Address"),
            @Result(property = "departmentTel",column = "department_Tel"),
            @Result(property = "departmentFax",column = "department_Fax")
    })
    public Department getDepartmentById(@Param("id") long id);

    @Select("select * from TB_DEPARTMENT where department_Number=#{departmentNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "departmentNumber",column = "department_Number"),
            @Result(property = "departmentName",column = "department_name"),
            @Result(property = "departmentHead",column = "department_Head"),
            @Result(property = "departmentAddress",column = "department_Address"),
            @Result(property = "departmentTel",column = "department_Tel"),
            @Result(property = "departmentFax",column = "department_Fax")
    })
    public List<Department> getDepartmentByDepartmentNumber(@Param("departmentNumber") String departmentNumber);

    @Select("select department_number from TB_DEPARTMENT where Department_name=#{departmentName}")
    public String getDepartmentNumberByDepartmentName(@Param("departmentName") String departmentName);

}
