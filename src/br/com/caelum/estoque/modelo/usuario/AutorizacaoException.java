package br.com.caelum.estoque.modelo.usuario;

import javax.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault")
public class AutorizacaoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42889115267785820L;

	public AutorizacaoException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public String getFaultInfo(){
		return "Token Inválido!";
	}
}
