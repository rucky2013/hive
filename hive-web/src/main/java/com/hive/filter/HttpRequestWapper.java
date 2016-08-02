package com.hive.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpRequestWapper extends HttpServletRequestWrapper{

	public HttpRequestWapper(HttpServletRequest request) {
		super(request);
	}
	
	
}
