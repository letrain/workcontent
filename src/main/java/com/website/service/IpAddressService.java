package com.website.service;

/**
 * Created by letrain on 2017/11/12.
 */
public interface IpAddressService {

    /**
     * 初始化
     */
    void init();

    /**
     * 获取 ip对应的省份和城市
     * @param ipAddress
     * @return
     */
    String getSubCity(String ipAddress);
}
