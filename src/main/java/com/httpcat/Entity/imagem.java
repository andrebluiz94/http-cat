package com.httpcat.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class imagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idImagem;
	@Column(name = "id_imagem")
	private String id;
	private String url;

	@ManyToOne
	private Categories categories;

	@ManyToOne
	private Cat breeds;

	public Long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Long idImagem) {
		this.idImagem = idImagem;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Cat getBreeds() {
		return breeds;
	}

	public void setBreeds(Cat breeds) {
		this.breeds = breeds;
	}
}
