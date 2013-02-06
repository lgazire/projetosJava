package br.com.caelum.loja.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.caelum.loja.entity.Autor;
import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.exception.SalvaLivroException;
@Stateless
@Remote(GerenciadorLoja.class)
@Interceptors(AuditoriaInterceptor.class)
public class GerenciadorLojaBean implements GerenciadorLoja {

	private Map<String, Livro> repositorio;

	@PersistenceContext
	private EntityManager manager;
	
	public GerenciadorLojaBean() {
		this.repositorio = new HashMap<String, Livro>();

		Livro l1 = new Livro();
		l1.setNome("Pais e filhos");
		l1.setPreco(100.0);

		Livro l2 = new Livro();
		l2.setNome("Noites brancas");
		l2.setPreco(200.0);

		this.repositorio.put("1111", l1);
		this.repositorio.put("2222", l2);

	}

	@Override
	public Livro procura(String isbn) {
		return this.repositorio.get(isbn);
	}

	@Override
	public void salva(Livro livro) {
		this.manager.persist(livro);
		System.out.println(livro.getId());
		throw new SalvaLivroException();
		
	}

	@Override
	public Autor salvaAutor(Autor autor) {
		this.manager.persist(autor);
		System.out.println(autor.getId());
		return autor;
		
	}

	@Override
	public Livro procura(Long id) {
		return this.manager.find(Livro.class, id);
	}

	@Override
	public List<Livro> buscarPorAutor(String nome) {
		System.out.println("buscando por autores com o nome: " + nome);
		String jpql = "select livro from Livro as livro join fetch livro.autores as autor where autor.nome like :busca";
		Query query = this.manager.createQuery(jpql);
		query.setParameter("busca", "%" + nome + "%");
		
		return query.getResultList();
		
		
	}

}
