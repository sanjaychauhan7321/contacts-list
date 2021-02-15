package com.kuehne.nagel.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDto {
	
	private int totalRecords;
	private int totalPages;
	private int pageSize;	
	private int currentPageIndex;
	private int recordsOnCurrentPage;
	
	/**
	 * @return the currentPageIndex
	 */
	public int getCurrentPageIndex() {
		return currentPageIndex;
	}

	/**
	 * @param currentPageIndex the currentPageIndex to set
	 */
	public void setCurrentPageIndex(int currentPageIndex) {
		this.currentPageIndex = currentPageIndex;
	}
	
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

	/**
	 * @return the recordsOnCurrentPage
	 */
	public int getRecordsOnCurrentPage() {
		return recordsOnCurrentPage;
	}

	/**
	 * @param recordsOnCurrentPage the recordsOnCurrentPage to set
	 */
	public void setRecordsOnCurrentPage(int recordsOnCurrentPage) {
		this.recordsOnCurrentPage = recordsOnCurrentPage;
	}

	@JsonProperty(value="data")
	private List<ContactEntity> contacts;

	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	/**
	 * @return the contacts
	 */
	@JsonFormat
	public List<ContactEntity> getContacts() {
		return contacts;
	}

	/**
	 * @param contacts the contacts to set
	 */
	public void setContacts(List<ContactEntity> contacts) {
		this.contacts = contacts;
	}

}
