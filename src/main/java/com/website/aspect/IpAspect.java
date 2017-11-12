package com.website.aspect;

import com.website.entity.WebStats;
import com.website.service.IpAddressService;
import com.website.service.WebStatsService;
import com.website.utils.IPAddrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by letrain on 2017/11/12.
 */
@Aspect
@Slf4j
//@Component
public class IpAspect {

    @Autowired
    private WebStatsService webStatsService;

    @Autowired
    private IpAddressService ipAddressService;

    private static WebStats webStats;

    private static Map<String,Integer> map;

    private static List<String> list;

    static {
        webStats = new WebStats();
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    @Pointcut("execution(public * com.website.controller.WebController.*(..))")
    public void cut(){}

    @Before("cut()")
    public void doBefore(JoinPoint joinPoint){
        log.info("开始记录。。。");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ipAddress = IPAddrUtil.getIpAddr(request);
        request.getSession();

        //如果map中不包含该ip 则添加
        if(!map.containsKey(ipAddress)){
            setWebStats(ipAddress);
            webStatsService.save(webStats);
        }

        //如果list不包含该url则添加
        if(!list.contains(request.getRequestURL().toString())){
            list.add(request.getRequestURL().toString());
        }
        log.info("map={}",map.size());
        log.info("lsit.size={},content={}",list.size(),list.toString());

    }

    public void setWebStats(String ipAddress){
        webStats.setIp(ipAddress);
        webStats.setCity(ipAddressService.getSubCity(ipAddress));
        webStats.setVisitTime(new Date());
        webStats.setStayTime("8000s");
    }
}
