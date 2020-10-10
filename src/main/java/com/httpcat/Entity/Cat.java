package com.httpcat.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Cat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idCat;
	@Column(name = "idCat")
	private String id;
	private String name;
	private String temperament;
	private String origin;
}
