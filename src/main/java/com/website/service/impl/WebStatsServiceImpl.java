package com.website.service.impl;

import com.website.dao.WebStatsMapper;
import com.website.entity.WebStats;
import com.website.service.WebStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by letrain on 2017/11/12.
 */
@Service
public class WebStatsServiceImpl implements WebStatsService {

    @Autowired
    private WebStatsMapper webStatsMapper;

    @Override
    public int save(WebStats webStats) {
        return webStatsMapper.save(webStats);
    }

    @Override
    public List<WebStats> findAll() {
        return webStatsMapper.findAll();
    }

    @Override
    public WebStats findByIp(String ip) {
        return webStatsMapper.findByIp(ip);
    }
}
