package br.com.caelum.loja.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Entity
public class Livro implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private double preco;
	private java.util.Date dataAlteracao;
	
	@ManyToMany
	private List<Autor> autores = new ArrayList<Autor>();
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	@Override
	public String toString() {
		
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Date getDataAlteracao() {
		return dataAlteracao;
	}
	
	@PrePersist
	@PreUpdate
	void preAltera(){
		System.out.println("CALLBACK preAltera(): Atualizando data automaticamente.");
		this.dataAlteracao = new Date();
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

}
