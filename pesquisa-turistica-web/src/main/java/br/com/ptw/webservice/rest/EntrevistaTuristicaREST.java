package br.com.ptw.webservice.rest;

import javax.ws.rs.Path;

@Path("/entrevista-turistica")
public class EntrevistaTuristicaREST {
 
//	@POST
//	@Path("/add-lista-entrevistas")
//	@Consumes(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	@Produces(MediaType.APPLICATION_JSON + "; charset=UTF-8")
//	public RespostaJSON post(List<EntrevistaTuristicaJSON> entrevistas) {
//		
//		if(CollectionUtils.isEmpty(entrevistas)){
//			RespostaJSON resp = RespostaJSONBuilder.buildMsgErroWithString("Nenhuma entrevista recebida no servidor!");
//			return resp;
//		}
//		
//		try {
//			RespostaJSON resp = getEntrevistaTuristicaService().incluirEntidadesFromWebservice(entrevistas);
//			return resp;
//		} catch (Exception e) {
//			RespostaJSON resp = RespostaJSONBuilder.buildMsgErroWithString(e.getMessage());
//			return resp;
//		}
//	}
	
//	@POST
//	@Path("/post-all")
//	@Consumes(MediaType.APPLICATION_JSON)
//	public Response postAll(List<Usuario> usuarios) {
//		
//		//String result = "Track saved : " + usuarios;
//		return Response.status(201).entity(usuarios.size()).build();
//	}
	
}