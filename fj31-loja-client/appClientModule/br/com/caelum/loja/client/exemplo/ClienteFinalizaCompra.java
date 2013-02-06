package br.com.caelum.loja.client.exemplo;

import javax.naming.InitialContext;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.session.Carrinho;

public class ClienteFinalizaCompra {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		InitialContext ic = new InitialContext();
		Carrinho carrinho = (Carrinho)ic.lookup("ejb:fj31-loja-ear/fj31-loja-ejb3/CarrinhoBean!br.com.caelum.loja.session.Carrinho?stateful");
		Livro livro = new Livro();
		livro.setNome("Fausto");
		livro.setPreco(45.0);

		Livro livro2 = new Livro();
		livro2.setNome("Pais e Filhos");
		livro2.setPreco(25.0);
		
		carrinho.addLivro(livro2);
		carrinho.addLivro(livro);
		carrinho.finalizaCompra();
	}

}
