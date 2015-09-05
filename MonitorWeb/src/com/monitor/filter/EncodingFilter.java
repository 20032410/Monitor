package com.monitor.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter("/*")
public class EncodingFilter implements Filter {


	public void destroy() {
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * ÿһ����������ʱ�򶼻�������������������������д���
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		//GET��ʽ�������߽������HttpServletRequest����ת�����Զ����EncodingHttpServletRequest����
		if("GET".equals(req.getMethod())){			
			EncodingHttpServletRequest wrapper = new EncodingHttpServletRequest(req);		
			chain.doFilter(wrapper, response);
		}
		//POST����ʽ�������÷������Ľ����ʽΪutf-8
		else{
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
