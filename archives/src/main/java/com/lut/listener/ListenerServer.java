package com.lut.listener;

import com.lut.service.impl.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ListenerServer implements HttpSessionListener, ServletContextListener, ServletRequestListener {

    private final static Logger log = LoggerFactory.getLogger(ListenerServer.class);

    @Autowired
    private EmployeeServiceImpl employeeService;

    // 创建 session
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.info("新创建一个session, ID为: " + session.getId());
    }

    // 销毁 session
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        log.info("销毁一个session, ID为: " + session.getId());
    }

    // 加载 application
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        log.info("即将启动服务器:" + servletContext.getContextPath());
    }

    // 卸载 application
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        log.info("即将关闭服务器:" + servletContext.getContextPath());
    }

    // 创建 request
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        String uri = request.getRequestURI();
        uri = request.getQueryString() == null ? uri : (uri + "?" + request.getQueryString());

        request.setAttribute("dateCreated", System.currentTimeMillis());

        log.info("请求IP: " + request.getRemoteAddr() + " 请求路径: " + uri);
    }

    // 销毁 request
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        long time = System.currentTimeMillis() - (Long) request.getAttribute("dateCreated");

        log.info(request.getRemoteAddr() + "请求处理结束, 用时" + time + "毫秒. ");
    }

}
