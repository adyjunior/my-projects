package br.com.ptw.module.admin.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ptw.geral.generic.dao.GenericDAO;
import br.com.ptw.geral.generic.service.GenericService;
import br.com.ptw.module.admin.dao.UsuarioDAO;
import br.com.ptw.module.admin.enums.EnumStatusUsuario;
import br.com.ptw.module.admin.model.Usuario;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
@Service
public class UsuarioService extends GenericService<Usuario>{

	public static String NOME_USUARIO_SESSAO = "usuarioSessao";
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
//	public Usuario instanciarOuConsultar(Long id) {
//		Usuario usuario = null;
//		if(!UtilObjeto.ehNumeroNuloOuZero(id)){
//			usuario = consultar(id);
//		}
//
//		if(usuario == null){
//			usuario = new Usuario();
//		}
//
//		return usuario;
//	}

//	public Usuario consultar(Long id) {
//		return usuarioDAO.consultar(id);
//	}
	
//	public void incluirAlterar(Usuario usuario) {
//		if(UtilObjeto.ehNumeroNuloOuZero(usuario.getId())){
//			this.incluir(usuario);
//		}else{
//			usuarioDAO.atualizar(usuario);
//		}
//	}
	
	
//	public Usuario validarLogin(Usuario usuario) throws Exception{
//
//		if(UtilObjeto.ehStringNulaOuVazia(usuario.getLogin())){
//			throw new Exception("Por favor digite seu login !");
//		}else if(UtilObjeto.ehStringNulaOuVazia(usuario.getSenha())){
//			throw new Exception("Por favor digite sua senha !");			
//		}
//
//		Usuario comparator = usuarioDAO.consultar(usuario.getLogin());
//
//		if(comparator == null){
//			throw new Exception("Usuario inexistente !");
//		}else if(!EnumStatusUsuario.ATIVO.getStatus().equals(comparator.getStatus())){
//			throw new Exception("Usuario inativo !");
//		}else if(!comparator.getSenha().equals(usuario.getSenha())){
//			throw new Exception("Senha incorreta !");
//		}
//		
//		return comparator;
//	}
	
	public void executarLogin(HttpSession session, Usuario usuario) {
		alocarUsuarioSessao(session, usuario);
	}
	
	public void executarLogout(HttpSession session) {
		removerUsuarioSessao(session);
	}

	public static void alocarUsuarioSessao(HttpSession session, Usuario usuario){
		session.setAttribute(NOME_USUARIO_SESSAO, usuario);
	}
	
	public static Usuario obterUsuarioSessao(HttpSession session){
		return (Usuario) session.getAttribute(NOME_USUARIO_SESSAO);
	}
	
	public static void removerUsuarioSessao(HttpSession session){
		session.removeAttribute(NOME_USUARIO_SESSAO);
	}
	
	public void incluir(Usuario usuario) {
		usuario.setDataCriacao(new Date());
		usuario.setStatus(EnumStatusUsuario.ATIVO.toString());
		usuarioDAO.insert(usuario);
	}

//	public Usuario consultar(String email){
//		return usuarioDAO.consultar(email);
//	}
	
	public void alterar(Usuario usuario) {
		usuario.setSenha(usuario.getNovaSenha());
		usuarioDAO.update(usuario);
	}

	public void remover(Usuario usuario) {
		usuarioDAO.remover(usuario.getId());
	}

	public List<Usuario> listar() {
		return usuarioDAO.listAll();
	}

//	public Boolean existeUsuarioCadastrado(){
//		return usuarioDAO.existeUsuarioCadastrado();
//	}
	
	public Usuario inserirPrimeiroUsuarioSistema(){
		Usuario usuario = new Usuario();
		usuario.setLogin("admin");
		usuario.setNome("Administrador");
		usuario.setStatus(EnumStatusUsuario.ATIVO.toString());
		usuario.setEmail("admin@guiaturismo.com.br");
		usuario.setSenha("12345");
		usuario.setDataCriacao(new Date());
		
		this.incluir(usuario);
		return usuario;
	}

	@Override
	public GenericDAO<Usuario> getDao() {
		return usuarioDAO;
	}

}
