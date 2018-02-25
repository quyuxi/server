package com.resthome.filter;

import com.resthome.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by 一缕仙缘 on 2017-08-01.
 */
//@Order(1)
//@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class MyFilter implements Filter
{
    private static Logger LOGGER = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        if(httpRequest.getRequestURL().toString().contains("/login"))
        {
            chain.doFilter(request, response);
            return;
        }

        String jsonWebToken = httpRequest.getHeader("Authorization");
        if ((jsonWebToken != null &&JWTUtils.validate(jsonWebToken,JWTUtils.key)))
        {
            LOGGER.info("the token corrent");



            chain.doFilter(request, response);
            return;
        }

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
       // httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("NO_PERMISSION");
        return;
    }

    @Override
    public void destroy() {

    }
}
