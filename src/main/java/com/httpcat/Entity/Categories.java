package com.httpcat.Entity;

import javax.persistence.*;

@Entity
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idCategories;
	@Column(name = "id_categories")
	private Long id;
	private String name;
}
