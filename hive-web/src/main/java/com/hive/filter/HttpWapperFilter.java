package com.hive.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/** 
 * 参数解密
 * @author zhangyl
 *
 */
public class HttpWapperFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request=new HttpRequestWapper((HttpServletRequest)request);
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}

}
