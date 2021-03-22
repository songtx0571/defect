package com.howei.util;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@Configuration
public class CorsFilter implements Filter{
        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            HttpServletRequest request=(HttpServletRequest) servletRequest;
            //如果要做细的限制，仅限某域名下的可以进行跨域访问到此，可以将*改为对应的域名。
            response.setHeader("Access-Control-Max-Age", "1728000");
            response.setHeader("Access-Control-Allow-Methods", "HEAD,POST,GET,OPTIONS,DELETE");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Headers", "*");
            filterChain.doFilter(servletRequest, servletResponse);

        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
}
