package com.lut.aspect;

import com.lut.entity.Employee;
import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.exception.ReturnPageException;
import com.lut.service.impl.EmployeeServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class HttpAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /*@Pointcut("execution(public * com.hoperun.controllers.EmployeesController.*(..))")
    public void log(){
        System.out.println("我是切点");
    }*/
    @Before("execution(public * com.lut.controller.PageController.index(..))")
    public void doEmployeesControllerBefore(JoinPoint joinPoint){
        logger.info("在"+joinPoint.getSignature().getName()+"方法执行之前拦截");
        if (!"login".equals(joinPoint.getSignature().getName())){
            HttpSession session = request.getSession();
            Employee employee = (Employee) session.getAttribute("employee");
            if (employee==null){
                throw new ReturnPageException();
            }
        }
    }
}