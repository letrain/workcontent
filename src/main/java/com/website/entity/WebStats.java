package com.website.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by letrain on 2017/11/12.
 */
@Entity
@Data
public class WebStats {

    @Id
    @GeneratedValue
    private int id;

    /**
     * 访问者的ip
     */
    private String ip;

    /**
     * ip对应的城市
     */
    private String city;

    /**
     * 访问开始时间
     */
    private Date visitTime;

    /**
     * 浏览的总时间
     */
    private String stayTime;
}
