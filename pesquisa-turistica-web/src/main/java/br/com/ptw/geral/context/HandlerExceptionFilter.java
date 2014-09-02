package br.com.ptw.geral.context;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import br.com.ptw.geral.notifications.NotificationError;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
public class HandlerExceptionFilter implements Filter{

	public void init(FilterConfig arg0) throws ServletException {
	}
	
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		try {
			chain.doFilter(req, res);			
		} catch (Exception e) {
			NotificationError.notify("Excecao capturada no filtro.", e);
		}
	}
	
	public void destroy() {		
	}


}
