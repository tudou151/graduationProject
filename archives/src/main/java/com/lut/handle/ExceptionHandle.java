package com.lut.handle;

import com.lut.entity.Result;
import com.lut.exception.MyException;
import com.lut.exception.ReturnPageException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Autowired
    private HttpServletRequest request;

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myHandle(MyException e){
        logger.info("抛出异常:"+e.getMessage());
        return new Result(e.getCode(),e.getMessage(),null);
    }

    @ExceptionHandler(value = ReturnPageException.class)
    public ModelAndView returnPageException(ReturnPageException e){
        logger.info("员工未登录异常");
        request.setAttribute("pageMsg","请先登录系统");
        return new ModelAndView("redirect:/page/login");
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result ExceptionHandle(Exception e){
        logger.debug("抛出异常:"+e.getMessage());
        e.printStackTrace();
        return new Result(-1,"系统异常，未知错误",null);
    }
}
