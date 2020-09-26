package com.httpcat.dto;



public class RacaDTO {
	private String id;
	private String name;
	private String temperament;
	private String origin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemperament() {
		return temperament;
	}

	public void setTemperament(String temperament) {
		this.temperament = temperament;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("id: ").append(id);
		stringBuilder.append("name: ").append(name);
		stringBuilder.append("temperament: ").append(temperament);
		stringBuilder.append("origin: ").append(origin);
		return stringBuilder.toString();
	}
}
