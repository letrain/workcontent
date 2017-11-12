package com.website.service;

import com.website.entity.WebStats;

import java.util.List;

/**
 * Created by letrain on 2017/11/12.
 */

public interface WebStatsService {

    int save (WebStats webStats);

    List<WebStats> findAll();

    WebStats findByIp(String ip);
}
