package br.com.ptw.geral.taglibs;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import br.com.ptw.util.PropriedadesSistema;

public class UrlTag extends TagSupport{

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(UrlTag.class.getSimpleName());
	
	@Override
	public int doStartTag() throws JspException {
		
		try {
			JspWriter out = pageContext.getOut();
			out.append(PropriedadesSistema.URL_SISTEMA + "/");
			
		} catch (Exception e) {
			logger.error("Erro ao imprimir url.", e);
		}
		
		return SKIP_BODY;
	}
	
	
	
}
