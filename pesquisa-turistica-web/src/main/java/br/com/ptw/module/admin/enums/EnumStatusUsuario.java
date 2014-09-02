package br.com.ptw.module.admin.enums;

/**
 * @author Ady Junior - 30/03/2014
 *
 */
public enum EnumStatusUsuario {

	ATIVO("Ativo"),
	INVATIVO("Inativo");
	
	private String status;
	
	private EnumStatusUsuario(String value){
		this.status = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.status;
	}
	
	public EnumStatusUsuario fromString(String acao) {
		for (EnumStatusUsuario item : values()) {
			if(item.toString().equals(acao)){
				return item;
			}
		}
		
		return null;
	}
	
	
}
