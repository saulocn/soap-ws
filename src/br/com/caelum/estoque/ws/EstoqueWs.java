package br.com.caelum.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import br.com.caelum.estoque.modelo.collections.ListaItens;
import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ItemValidador;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class EstoqueWs {

	private ItemDao dao = new ItemDao();
/*
	@WebMethod(operationName = "todosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens() {
		System.out.println("Chamando getItens()");
		ListaItens listaItens = new ListaItens(dao.todosItens());
		System.out.println(listaItens);
		return listaItens;// criando uma ListaItens
	}*/

	
	
	/*@WebMethod(operationName = "todosOsItens")
	@ResponseWrapper(localName="itens")
	@WebResult(name = "item")
	@RequestWrapper(localName="listaItens")
	public List<Item> getItens() { 
		System.out.println("Chamando getItens()");
		return dao.todosItens();// criando uma ListaItens
	}*/
	
	

	@WebMethod(action="getItens", operationName = "TodosOsItens")
	@WebResult(name = "itens")
	public ListaItens getItens(@WebParam(name="filtros") Filtros filtros) { // cuidado, plural
		System.out.println("Chamando getItens()");
		List<Filtro> lista = filtros.getLista();
		List<Item> itensResultado = dao.todosItens(lista);
		return new ListaItens(itensResultado);
	}
	
	@WebMethod(action="cadastrarItem", operationName = "CadastrarItem")
	@WebResult(name = "Item")
	public Item cadastrarItem(
			@WebParam(name="tokenUsuario", header=true) TokenUsuario token, 
			@WebParam(name="Item") Item item) 
					throws AutorizacaoException {
		System.out.println("Cadastrando Item: "+ item + "\nToken: "+token);
		boolean valido = new TokenDao().ehValido(token);
		if(!valido){
			throw new AutorizacaoException("Erro de Autorização");
		}
		new ItemValidador(item).validate();
		this.dao.cadastrar(item);
		return item;
	}
}
