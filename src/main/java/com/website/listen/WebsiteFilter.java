package com.website.listen;

import com.website.entity.WebStats;
import com.website.service.IpAddressService;
import com.website.service.WebStatsService;
import com.website.utils.IPAddrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by letrain on 2017/11/12.
 */
@WebFilter(urlPatterns = "/web/*",filterName = "webFilter")
@Slf4j
public class WebsiteFilter implements Filter{

    @Autowired
    private WebStatsService webStatsService;

    @Autowired
    private IpAddressService ipAddressService;

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        log.info("init webFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFiter......");
        ServletContext context = filterConfig.getServletContext();
        Map<String,Integer> ipMap = (Map<String, Integer>) context.getAttribute("ipMap");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ipAddress = IPAddrUtil.getIpAddr(request);
        //如果ipmap中不包含此ip 则保存到数据库
        if(!ipMap.containsKey(ipAddress)){
            setwebStats(ipAddress);
            //不同的ip 保存不同的访问量
            ipMap.put(ipAddress,1);
        }else {
            //统计此ip的访问量
            Integer count = ipMap.get(ipAddress);
            ipMap.put(ipAddress,count+1);
            log.info("ipaddress count={}",count+1);
        }
        context.setAttribute("ipMap",ipMap);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private void setwebStats(String ipAddress){
        WebStats webStats = new WebStats();
        webStats.setIp(ipAddress);
        webStats.setCity(ipAddressService.getSubCity(ipAddress));
        webStats.setVisitTime(new Date());
        webStats.setStayTime("1000s");
        webStatsService.save(webStats);
        log.info("save.....");
    }
}
