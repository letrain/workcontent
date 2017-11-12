package com.website.controller;

import com.website.utils.IPAddrUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by letrain on 2017/11/12.
 */
@RestController
@RequestMapping("web")
public class WebController {

    @PostMapping("/ip")
    public String getIp(HttpServletRequest request){
        return IPAddrUtil.getIpAddr(request);
    }

    @GetMapping("/addr")
    public String addr(){
        return "addr";
    }

    @GetMapping("/url")
    public String url(){
        return "url";
    }
}
