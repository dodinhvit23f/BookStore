package com.api.book.dto;

import java.io.Serializable;

public class BookModifycationDTO extends BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean isDelete;
	
	public boolean getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}
}
