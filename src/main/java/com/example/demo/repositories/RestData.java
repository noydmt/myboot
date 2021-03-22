package com.example.demo.repositories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="restData")
public class RestData {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column
	private String restName;

	@Column
	private String community;

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return restName
	 */
	public String getRestName() {
		return restName;
	}

	/**
	 * @param restName セットする restName
	 */
	public void setRestName(String restName) {
		this.restName = restName;
	}

	/**
	 * @return community
	 */
	public String getCommunity() {
		return community;
	}

	/**
	 * @param community セットする community
	 */
	public void setCommunity(String community) {
		this.community = community;
	}

}
