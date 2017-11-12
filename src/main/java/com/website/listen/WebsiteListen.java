package com.website.listen;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by letrain on 2017/11/12.
 */
@WebListener
public class WebsiteListen implements ServletContextListener{


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        Map<String,Integer> ipMap = new HashMap<>();
        context.setAttribute("ipMap",ipMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {


    }
}
