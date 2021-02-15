package com.kuehne.nagel.app.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TBL_CONTACTS")
public class ContactEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@JsonFormat
	private Long id;

	@Column(name = "name")
	@JsonFormat
	private String name;

	@Column(name = "url")
	@JsonFormat
	private String url;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "{\"id=\":" + id + ", \"name\":\"" + name + "\", \"url\":\"" + url + "\"";
	}

	/**
	 * @param name
	 * @param url
	 */
	public ContactEntity(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	/**
	 * 
	 */
	public ContactEntity() {
		super();
	}
	
	
	

}
