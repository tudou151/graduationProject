package com.lut.repository;

import com.lut.entity.Certificate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CertificateRepository {
    @Insert("insert into TB_CERTIFICATE(ID,NAME,HAPPEN_TIME,ORGANIZATION,CERTIFICATE_NUMBER,FOUNDER,CREATE_TIME,EMPLOYEE_NUMBER)\n" +
            "VALUES(#{id},#{name},#{happenTime},#{organization},#{certificateNumber},#{founder},#{createTime},#{employeeNumber})")
    @SelectKey(statement = "select SEQ_DEPARTMENT.nextval from dual",keyProperty = "id",resultType = long.class,before = true)
    public int addCertificate(Certificate certificate);

    @Select("select * from TB_CERTIFICATE c,tb_employee b where c.employee_number = b.employee_name and b.delete_mark=0 ")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "certificateNumber",column = "CERTIFICATE_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Certificate> getAllCertificates();

    @Select("select * from TB_CERTIFICATE c,tb_employee b where c.employee_number=#{employeeName} and c.employee_number = b.employee_name and b.delete_mark=0  and b.Permissions = 0")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "certificateNumber",column = "CERTIFICATE_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Certificate> getAllCertificatees(@Param("employeeName") String employeeName);


    @Delete("delete from TB_CERTIFICATE where id=#{id}")
    public int deleteCertificate(long id);

    @Update("update TB_CERTIFICATE set NAME=#{name},HAPPEN_TIME=#{happenTime},CERTIFICATE_NUMBER=#{certificateNumber},ORGANIZATION=#{organization},EMPLOYEE_NUMBER=#{employeeNumber},MODIFY_TIME=#{modifyTime},MODIFIER=#{modifier} where ID=#{id}")
    public int modifyCertificate(Certificate certificate);

    @Select("select * from TB_CERTIFICATE where id=#{id}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "certificateNumber",column = "CERTIFICATE_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public Certificate getCertificateById(@Param("id") long id);

    @Select("select * from TB_CERTIFICATE where employee_number=#{employeeNumber}")
    @Results({
            @Result(property = "createTime",column = "CREATE_TIME"),
            @Result(property = "modifyTime",column = "MODIFY_TIME"),
            @Result(property = "happenTime",column = "HAPPEN_TIME"),
            @Result(property = "certificateNumber",column = "CERTIFICATE_NUMBER"),
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER")
    })
    public List<Certificate> getCertificateByEmployeeNumber(@Param("employeeNumber") String employeeNumber);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);


}
