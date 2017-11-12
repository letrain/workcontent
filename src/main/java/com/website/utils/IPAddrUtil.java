package com.website.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取IP地址工具
 * Created by letrain on 2017/11/12.
 */
@Slf4j
public class IPAddrUtil {
    private final static String X_FORWARDED = "x-forwarded-for";
    private final static String PROXY_CLIENT = "Proxy-Client-IP";
    private final static String WL_PROXY_CLIENT = "WL-Proxy-Client-IP";
    private final static String UNKONWN = "unknown";
    private final static String LOCALHOST = "127.0.0.1";


    public static String getIpAddr(HttpServletRequest request) {

        String ipAddress = request.getHeader(X_FORWARDED);
        if(StringUtils.isEmpty(ipAddress) || ipAddress.equals(UNKONWN)){
            ipAddress = request.getHeader(PROXY_CLIENT);
        }
        //if(StringUtils.isEmpty(ipAddress) || ipAddress.equals(UNKONWN)){
       //     ipAddress = request.getHeader("X-Real-IP");
       // }
        if(StringUtils.isEmpty(ipAddress) || ipAddress.equals(UNKONWN)){
            ipAddress = request.getHeader(WL_PROXY_CLIENT);
        }
        if(StringUtils.isEmpty(ipAddress) || ipAddress.equals(UNKONWN)){
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals(LOCALHOST) || ipAddress.equals("0:0:0:0:0:0:0:1")){
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    log.error("【获取IP地址】异常，msg={}",e.getMessage());
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;

    }
}
