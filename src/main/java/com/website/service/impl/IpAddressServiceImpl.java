package com.website.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.website.service.IpAddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by letrain on 2017/11/12.
 */
@Service
@Slf4j
public class IpAddressServiceImpl implements IpAddressService {

    private static String dbPath = "E:\\filedata\\file\\GeoLite2-City.mmdb";
    private static DatabaseReader reader;

    @Autowired
    private Environment env;

    /**
     * 构造方法执行后，初始化，
     */
    @Override
    @PostConstruct
    public void init() {
        try {
            String path = env.getProperty("geolite2.city.db.path");
            if(!StringUtils.isEmpty(path)){
                dbPath = path ;
            }
            File database = new File(dbPath);
            reader = new DatabaseReader.Builder(database).build();

        } catch (Exception e) {
            log.error("ip地址服务初始化异常: msg={}",e.getMessage());
        }
    }

    @Override
    public String getSubCity(String ipAddress) {
        try {
            CityResponse response = reader.city(InetAddress.getByName(ipAddress));
            return response.getMostSpecificSubdivision().getNames().get("zh-CN")+response.getCity().getNames().get("zh-CN");
        } catch (IOException e) {
            log.error("【获取ip城市io异常】,msg={}",e.getMessage());
            return null;
        } catch (GeoIp2Exception e) {
           log.error("根据IP[{}]获取省份失败:{}", ipAddress, e.getMessage());
           return "本机";
        }
    }
}
