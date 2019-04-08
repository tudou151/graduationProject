package com.lut.repository;

import com.lut.entity.Employee;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface EmployeeRepository {

//    @Select("select * from tb_employee where employee_number = #{employeeNumber} and delete_mark = 0")
    @SelectProvider(type = SqlProvider.class,method = "getEmployees")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public List<Employee> login(Employee employee);

    @Select("select * from tb_employee where employee_name=#{employeeName} and employee_number = #{employeeNumber} and delete_mark = 0")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public List<Employee> logins(Employee employee);

    @Select("select * from tb_employee")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "deleteMark",column = "delete_mark"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public List<Employee> login1(Employee employee);

    @DeleteProvider(type = SqlProvider.class,method = "deleteEmployee")
    public Integer deleteEmployee(Integer id);

    @Insert("insert into tb_employee(id,employee_number,DEPARTMENT_NAME,employee_name,sex,password,founder,create_time,if_on_duty,permissions,delete_mark,tel,id_card,birth,zzmm,nation,marriage,email,root,address,begin_time,become_time,leave_time)" +
            " values(#{id},#{employeeNumber},#{departmentName},#{employeeName},#{sex},#{password},#{founder},#{createTime},#{ifOnDuty},#{permissions},0,#{tel},#{idCard},#{birth},#{zzmm},#{nation},#{marriage},#{email},#{root},#{address},#{beginTime},#{becomeTime},#{leaveTime})")
    @SelectKey(statement = "select SEQ_EMPLOYEE.NEXTVAL from dual",keyProperty="id", resultType=long.class, before=true)
    public Integer addEmployee(Employee employee);

    @Select("select * from tb_employee where id=#{id} and delete_mark = 0")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public Employee getEmployee(Employee employee);

    @Update("update tb_employee set employee_name=#{employeeName},DEPARTMENT_NAME=#{departmentName},sex=#{sex},if_on_duty=#{ifOnDuty},permissions=#{permissions},tel=#{tel},id_card=#{idCard},Modifier=#{modifier},MODIFY_TIME=#{modifyTime},birth=#{birth},zzmm=#{zzmm},nation=#{nation},marriage=#{marriage},email=#{email},root=#{root},address=#{address},begin_time=#{beginTime},become_time=#{becomeTime},leave_Time=#{leaveTime} where id=#{id}")
    public int updateEmployeeById(Employee employee);

    @Update("update tb_employee set delete_mark = 1 where id = #{id}")
    public int updateEmployeeByIds(int id);

    @Update("update tb_employee set employee_name=#{employeeName},DEPARTMENT_NAME=#{departmentName},sex=#{sex},tel=#{tel},id_card=#{idCard},birth=#{birth},zzmm=#{zzmm},nation=#{nation},marriage=#{marriage},email=#{email},root=#{root},address=#{address},begin_time=#{beginTime},become_time=#{becomeTime},leave_Time=#{leaveTime} where employee_number=#{employeeNumber}")
    public int updateEmployeeByNumber(Employee employee);

    @Update("update tb_employee set password=#{newPwd} where employee_number=#{employeeNumber}")
    public int modifyPwdByNumber(@Param("newPwd")String newPwd,@Param("employeeNumber")String employeeNumber);

    @Select("select * from tb_employee where employee_number=#{employeeNumber} ")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public Employee getEmployeeByEmployeeNumber(Employee employee);

    @Select("select employee_number from tb_employee where employee_name=#{employeeName}")
    public String getEmployeeNumberByEmployeeName(@Param("employeeName") String employeeName);

    @Select("select * from tb_employee where employee_number=#{employeeNumber} ")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public List<Employee> getEmployeeByNumber(Employee employee);

    @Select("select * from tb_employee where employee_name=#{employeeName} and delete_mark = 0")
    @Results({
            @Result(property = "employeeNumber",column = "EMPLOYEE_NUMBER"),
            @Result(property = "employeeName",column = "EMPLOYEE_NAME"),
            @Result(property = "imageUrl",column = "MAN_IMAGE_URL"),
            @Result(property = "createTime",column = "CREATE_Time"),
            @Result(property = "ifOnDuty",column = "IF_ON_DUTY"),
            @Result(property = "leaveTime",column = "LEAVE_TIME"),
            @Result(property = "beginTime",column = "Begin_TIME"),
            @Result(property = "departmentName",column = "DEPARTMENT_NAME"),
            @Result(property = "becomeTime",column = "become_TIME"),
            @Result(property = "idCard",column = "id_card")
    })
    public List<Employee> getEmployeeByName(Employee employee);

}
