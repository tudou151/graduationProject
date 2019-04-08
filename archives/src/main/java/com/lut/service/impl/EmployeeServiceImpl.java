package com.lut.service.impl;

import com.lut.entity.Employee;
import com.lut.entity.Page;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.repository.EmployeeRepository;
import com.lut.service.IEmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;


@Service
public class EmployeeServiceImpl implements IEmployeeService{

    private final static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired(required = false)
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ServletContext servletContext;

    @Override
    public Result getPageDatas(Integer currentPage) {
        HttpSession session = request.getSession();
        Page page = (Page) session.getAttribute("page");
        page.setPage(currentPage);
        session.setAttribute("page",page);
        return new Result(0,"操作成功",null);
    }

    /**
     * 根据工号获取员工信息
     * @param employeeNumber
     * @return
     */
    @Override
    public Result getEmployeeByNumber(String employeeNumber) {
        Employee employee = new Employee();
        employee.setEmployeeNumber(employeeNumber);
        List<Employee> es = employeeRepository.login(employee);
        return new Result(0,"操作成功",es.get(0));
    }

    /**
     * 员工登录
     * @param employee
     * @return
     */
    @Override
    public Result login(Employee employee) {
        List<Employee> employees = employeeRepository.login(employee);
        if (employees.size()==0){


            return new Result(-1,"工号或密码错误",null);
        }
        if (employees.get(0).getPassword().equals(employee.getPassword())){
            saveEmployeeLoginInfo(employees.get(0));
            return new Result(0,"",employees.get(0));
        }
        return new Result(-1,"工号或密码错误",null);
    }

    /**
     * 删除员工
     * @param ids
     * @return
     */
    @Override
    public Result deleteEmployees(String ids) {
        String[] idss = ids.split(",");
        for(String id:idss){
            int i =Integer.parseInt(id);
           /* int j = employeeRepository.deleteEmployee(i);*/

           int j = employeeRepository.updateEmployeeByIds(i);
            if (j!=1){
                throw new MyException(-1,"删除员工异常");
            }
        }
        updateAllEmployeeToSession();
        return new Result(0,"操作成功",null);
    }

    /**
     * 获取所有员工信息
     * @return
     */
    public List<Employee> getEmployees(){
        Employee employee = employeeServiceImpl.getEmployeeLoginInfo();
        List<Employee> employees = null;

        if (employee.getPermissions() == 0){
            employees = employeeRepository.logins(employee);
            return employees;
        }else if(employee.getPermissions() == 1){
            employees = employeeRepository.login(new Employee());
            return employees;
        }else{
            employees = employeeRepository.login1(new Employee());
            return employees;
        }
    }

    /**
     * 保存当前员工登录成功的信息
     * @param employee
     */
    public void saveEmployeeLoginInfo(Employee employee){
        HttpSession session = request.getSession();
        log.info("登录成功，正在保存员工信息："+employee);
        session.setAttribute("employee",employee);
    }

    @Override
    public Result addEmployee(Employee employee) {
//        System.out.println("employee增加： " +employee.toString());
        if (judgeEmployeeExistOrNo(employee)){
            throw new MyException(-1,"该员工已存在");
        }
        employee.setPassword("666666");
        employee.setFounder(getEmployeeLoginInfo().getEmployeeName());
        employee.setCreateTime(new Date());
        if ("0".equals(employee.getIfOnDuty())){
            employee.setIfOnDuty("在职");
        }else {
            employee.setIfOnDuty("离职");
        }
        System.out.println("employee增加： " +employee.toString());
        int i = employeeRepository.addEmployee(employee);
        if (i!=1){
            throw new MyException(-1,"添加失败");
        }
        return new Result(0,"操作成功",null);
    }

    /**
     * 获取当前登录员工的信息
     * @return
     */
    public Employee getEmployeeLoginInfo(){
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("employee");
        if (employee==null){
            throw new MyException(-1,"未检测到登录员工");
        }
        return employee;
    }

    /**
     * 根据员工id查询员工信息
     * @param employee
     * @return
     */
    @Override
    public Result getEmployee(Employee employee) {
        Employee e = employeeRepository.getEmployee(employee);
        System.out.println("xiugai : " + e.toString());
        if (e!=null){
            return new Result(0,"操作成功",e);
        }
        return new Result(-1,"操作失败",null);
    }

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    @Override
    public Result updateEmployeeById(Employee employee) {
       /* Employee e = new Employee();
        e.setEmployeeNumber(employee.getEmployeeNumber());*/
//        System.out.println("e: "+ e);

/*
        List<Employee> list = employeeRepository.login(e);
*/
        /*System.out.println("employee修改B : " + list.toString());
        System.out.println("employee修改B : " + list.size());*/
//        if (list.size()!=0){
//            if (list.get(0).getId()!=employee.getId()){
//                throw new MyException(-1,"该员工已存在");
//            }
//        }
        if ("0".equals(employee.getIfOnDuty())){
            employee.setIfOnDuty("在职");
        }else{
            employee.setIfOnDuty("离职");
        }
        Employee e = employeeServiceImpl.getEmployeeLoginInfo();
        employee.setModifier(e.getEmployeeName());
        Date now = new Date();
        employee.setModifyTime(now);
        int i = employeeRepository.updateEmployeeById(employee);
        if (i!=1){
            throw new MyException(-1,"修改失败");
        }
        if (employee.getEmployeeNumber().equals(getEmployeeLoginInfo().getEmployeeNumber())){
            updataSessionEmployee((Employee) getEmployeeByNumber(employee.getEmployeeNumber()).getData());
        }
        return new Result(0,"操作成功",null);
    }

    /**
     * 更新session中的所有员工信息
     */
    public void updateAllEmployeeToSession(){
        Page<Employee> page = new Page<>(getEmployees());
        request.getSession().setAttribute("page",page);
    }
    //更新当前登录的session的员工信息
    public void updataSessionEmployee(Employee employee){
        request.getSession().setAttribute("employee",employee);
    }

    /**
     * 根据工号更新员工信息
     * @param employee
     * @return
     */
    @Override
    public Result updateEmployeeByNumber(Employee employee) {
        int i = employeeRepository.updateEmployeeByNumber(employee);
        if (i!=1){
            throw new MyException(-1,"更新失败，未知错误");
        }
        updataSessionEmployee((Employee) getEmployeeByNumber(employee.getEmployeeNumber()).getData());
        return new Result(0,"操作成功",null);
    }

    /**
     * 判断指定员工号是否存在
     * @param employee
     * @return
     */
    public Boolean judgeEmployeeExistOrNo(Employee employee){
        String employeeNumber = employee.getEmployeeNumber();
        List<Employee>  es= employeeRepository.getEmployeeByNumber(employee);
        if (es != null && es.size() != 0){
            return true;
        }
        return false;
    }

    /**
     * 上传头像
     */
   /* @Override
    public Result upLoadMyImage() {
        //获得上传目录的绝对路径
        String realpath = servletContext.getRealPath("/images/imdex/my_image");
        System.out.println("realpath:"+realpath);
        try {
            //构造一个文件上传处理对象
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upLoad = new ServletFileUpload(factory);
            //获得表单中提交内容
            List<FileItem> list = upLoad.parseRequest(request);
            log.info("list.size:"+list.size());
            for (FileItem fileItem : list) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    fileName = System.currentTimeMillis()+fileName;
                    File file = new File(realpath, fileName);
                    log.info("上传的头像开始写入服务器");
                    fileItem.write(file);
                }else{
                    String fieldName = fileItem.getFieldName();
                    String content = fileItem.getString("UTF-8");
                    System.out.println(fieldName+" : "+content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(0,"操作成功",null);
    }*/

    @Override
    public Result upLoadMyImage() {
        return new Result(-1,"上传功能暂不能用",null);
    }

    public Result clearLogin(){
        request.getSession().invalidate();
        return new Result(0,"清除成功",null);
    }

    @Override
    public Result modifyPwd(String oldPwd, String newPwd) {
        if(!getEmployeeLoginInfo().getPassword().equals(oldPwd)){
            throw new MyException(-1,"当前密码输入错误");
        }
        int i = employeeRepository.modifyPwdByNumber(newPwd,getEmployeeLoginInfo().getEmployeeNumber());
        if(i!=1){
            throw new MyException(-1,"修改密码时发生未知错误");
        }
        return new Result(0,"操作成功",null);
    }

    @Override
    public Result getEmployeeByEmployeeNumber(Employee employee) {
       List<Employee> employee1 = employeeRepository.getEmployeeByNumber(employee);
        if (employee1 != null){
            return new Result(0,"操作成功",employee1.get(0));
        }else{
            return new Result(0,"操作失败",null);

        }

    }
    @Override
    public Result getEmployeeByName(String employeeName){
        Employee employee = new Employee();
        employee.setEmployeeName(employeeName);
        List<Employee> es = employeeRepository.getEmployeeByName(employee);
        if (es.size()==0){
            throw new MyException(-1,"输入的姓名不存在！");
        }
        String employeeNumber = es.get(0).getEmployeeNumber();

        return new Result(0,"操作成功",es.get(0));
    }

    @Override
    public Result getPersonByEmployeeNumber(Employee employee){

        List<Employee> employee1 = employeeRepository.getEmployeeByNumber(employee);

        Page<Employee> page = new Page<>(employee1);
        request.getSession().setAttribute("page",page);

        return new Result(0,"操作成功",employee);
    }

}
