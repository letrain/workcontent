package com.website.service.impl;

import com.website.service.IpAddressService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.rmi.runtime.Log;

import static org.junit.Assert.*;

/**
 * Created by letrain on 2017/11/12.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IpAddressServiceImplTest {

    @Autowired
    private IpAddressService ipAddressService;

    String ipaddress = "58.24.30.17"; //上海
    @Test
    public void getSubCity() throws Exception {
        String result = ipAddressService.getSubCity(ipaddress);
        System.out.println(result);
        Assert.assertNotNull(result);
    }

}