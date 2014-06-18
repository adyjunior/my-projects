package com.scwcd.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PrimeiroFiltro implements Filter{

	@Override
	public void init(FilterConfig fc) throws ServletException {
		System.out.println(fc.getServletContext().getInitParameter("primeiroContextParam"));
		System.out.println(fc.getInitParameter("initParamFiltro"));
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		System.out.println(">");
		chain.doFilter(request, response);
		System.out.println("<");
		
	}
	
	@Override
	public void destroy() {
		
	}
	
}
