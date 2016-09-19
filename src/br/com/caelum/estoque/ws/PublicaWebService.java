package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaWebService {

	public static void main(String[] args) {
		System.out.println("Iniciando WebService ...");
		EstoqueWs service = new EstoqueWs();
		String url = "http://localhost:8087/estoquews";

		Endpoint.publish(url, service);
		System.out.println("WebService Inicializado!");
	}

}
