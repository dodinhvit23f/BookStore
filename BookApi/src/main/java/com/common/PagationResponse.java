package com.common;

import java.io.Serializable;

public class PagationResponse  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1950923020436628579L;

	private int currentPage;

	private int size;
	
	private int totalPage;

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

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
}
