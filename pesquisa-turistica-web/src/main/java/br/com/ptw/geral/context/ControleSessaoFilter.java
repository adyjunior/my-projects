package br.com.ptw.geral.context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.ptw.module.admin.enums.EnumStatusUsuario;
import br.com.ptw.module.admin.model.Usuario;
import br.com.ptw.module.admin.service.UsuarioService;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
public class ControleSessaoFilter implements Filter {

	
	private static List<String> urlsPermitidas = new ArrayList<String>();
	private static List<String> pastasPermitidas = new ArrayList<String>();
	
	public void destroy() {
	}
	
	private void prepararAcoesPermitidas(HttpServletRequest request) {
		if(!urlsPermitidas.isEmpty()){
			return;
		}
		
		String urlLogin = request.getContextPath() + "/pages/admin/login";
		urlsPermitidas.add("inserir-configuracao-inicial.sp");
		urlsPermitidas.add("javax.faces.resource");
		urlsPermitidas.add("/rest/");
		urlsPermitidas.add(urlLogin);
	}
	
	private void prepararPastasPermitidas(HttpServletRequest request) {
		if(!pastasPermitidas.isEmpty()){
			return;
		}
		
		pastasPermitidas.add("/resources/");
	}
	
	private Boolean contemAcaoPermitida(String uri) {
		for (String acao : urlsPermitidas) {
			if(uri.contains(acao)){
				return true;
			}
		}
		return false;
	}
	
	private Boolean contemPastaPermitida(String uri) {
		for (String pasta : pastasPermitidas) {
			if(uri.contains(pasta)){
				return true;
			}
		}
		return false;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		this.prepararAcoesPermitidas(request);
		this.prepararPastasPermitidas(request);
		
		String urlLogin = request.getContextPath() + "/pages/admin/login.jsf";
		String uri = request.getRequestURI();
		
		Usuario usuario = UsuarioService.obterUsuarioSessao(request.getSession());
		
		if((usuario == null || !EnumStatusUsuario.ATIVO.toString().equals(usuario.getStatus()))
				&& !this.contemAcaoPermitida(uri)
				&& !this.contemPastaPermitida(uri)){
			
			
/*			if(!uri.contains(urlLogin) || !uri.contains("logout.sp")){
				urlLogin += "?erro=Efetue o Login!";
			}
*/
			
			response.sendRedirect(urlLogin);
			
			//RequestDispatcher rd = req.getRequestDispatcher();
            //rd.forward(req, res);
            return;	
       }
		
        chain.doFilter(req, res);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
