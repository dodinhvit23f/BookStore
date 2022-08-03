package com.common;

import java.io.Serializable;

public class PaginationRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int currentPage;

	private int size;
	
	private String sortBy;
	
	private boolean asc;
	
	public PaginationRequest() {
		asc = true;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public boolean isAscension() {
		return asc;
	}

	public void setAscension(boolean ascension) {
		this.asc = ascension;
	}
}
