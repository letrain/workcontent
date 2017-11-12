package com.website.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by letrain on 2017/11/12.
 */
@Data
public class WebContent {

    @Id
    @GeneratedValue
    private int id;

    /**
     * 同一个人访问的所有内容
     */
    private List<String> content;
}
