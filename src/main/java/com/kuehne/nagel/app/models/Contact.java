package com.kuehne.nagel.app.models;

public class Contact {

	private String name;
	private String url;
	
	/**
	 * 
	 */
	public Contact() {
		super();
	}

	public Contact(String name2, String url2) {
		super();
		this.name = name2;
		this.url = url2;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
