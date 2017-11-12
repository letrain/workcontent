package com.website.dao;

import com.website.entity.WebStats;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by letrain on 2017/11/12.
 */
@Component
public interface WebStatsMapper {

    int save (WebStats webStats);

    List<WebStats> findAll();

    WebStats findByIp(String ip);
}
