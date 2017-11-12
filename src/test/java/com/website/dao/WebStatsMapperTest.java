package com.website.dao;

import com.website.entity.WebStats;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by letrain on 2017/11/12.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class WebStatsMapperTest {
    @Autowired
    private WebStatsMapper webStatsMapper;

    @Test
    public void save() throws Exception {
        WebStats webStats = new WebStats();
        webStats.setIp("192.164.18.111");
        webStats.setCity("xiamen集美");
        webStats.setVisitTime(new Date());
        webStats.setStayTime("3800s");

        int result = webStatsMapper.save(webStats);
        Assert.assertEquals(1,result);


    }

    @Test
    public void findAll() throws Exception {

        List<WebStats> webStats = webStatsMapper.findAll();
        Assert.assertEquals(2,webStats.size());
    }

    @Test
    public void findByIp() throws Exception {
        WebStats webStats = webStatsMapper.findByIp("192.164.18.111");
        Assert.assertEquals("380s",webStats.getStayTime());

    }

}