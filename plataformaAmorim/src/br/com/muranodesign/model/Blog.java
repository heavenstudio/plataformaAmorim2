package br.com.muranodesign.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "blog")
@XmlRootElement
public class Blog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Basic(optional = false)
	 @Column(name = "Idblog")
	 private int Idblog;
	 
	 @Column(name = "titulo")
	 private String titulo;
	 
	 @Column(name = "Descricao")
	 private String Descricao;
	 
	 @Column(name = "imagem")
	 private String imagem;

	public int getIdblog() {
		return Idblog;
	}

	public void setIdblog(int idblog) {
		Idblog = idblog;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	 
	 
	 
	 
	
}