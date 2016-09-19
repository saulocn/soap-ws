package br.com.caelum.estoque.ws;

import javax.xml.ws.Endpoint;

public class PublicaWebService {

	public static void main(String[] args) {
		System.out.println("Iniciando WebService ...");
		EstoqueWs service = new EstoqueWs();
		RelatorioService relatorioService = new RelatorioService();
		String local = "http://localhost:8087/";
		String url = local + "estoquews";
		String urlRelatorio = local + "relatoriows";
		Endpoint.publish(url, service);
		Endpoint.publish(urlRelatorio, relatorioService);
		System.out.println("WebService Inicializado!");
	}

}
