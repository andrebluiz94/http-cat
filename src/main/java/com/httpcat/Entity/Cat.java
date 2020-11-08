package com.httpcat.Entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Builder
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

	public Cat(Long idCat, String id, String name, String temperament, String origin) {
		this.idCat = idCat;
		this.id = id;
		this.name = name;
		this.temperament = temperament;
		this.origin = origin;
	}

	public Cat() {
	}
}
