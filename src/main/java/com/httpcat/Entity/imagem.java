package com.httpcat.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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
}
