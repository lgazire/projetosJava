package br.com.caelum.loja.client.exemplo;

import java.util.List;

import javax.naming.InitialContext;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.GerenciadorLoja;

public class ClienteGerenciadorPesquisa {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		InitialContext ic = new InitialContext();
		GerenciadorLoja gerenciador = (GerenciadorLoja)ic.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/GerenciadorLojaBean!br.com.caelum.loja.session.GerenciadorLoja"); 
		
		List<Livro> livros = gerenciador.buscarPorAutor("Fowler");
		
		for (Livro livro : livros) {
			System.out.println(livro.getNome());
		}


	}

}
