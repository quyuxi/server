package com.resthome.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 一缕仙缘 on 2017-08-01.
 */
@RestController
@RequestMapping("/")
public class Error implements ErrorController
{
    @Override
    @RequestMapping("/error")
    public String getErrorPath(){return "ERROR";}

}
