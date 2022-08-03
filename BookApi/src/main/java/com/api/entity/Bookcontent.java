package com.api.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the bookcontent database table.
 * 
 */
@Entity
@Table(name="bookcontent")
@NamedQuery(name="Bookcontent.findAll", query="SELECT b FROM Bookcontent b")
public class Bookcontent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=2147483647)
	private String content;

	@Column(name="inserted_by")
	private Integer insertedBy;

	@Column(name="inserted_date")
	private Timestamp insertedDate;

	@Column(name="is_free")
	private Boolean isFree;

	@Column(length=80)
	private String title;

	@Column(name="update_by")
	private Integer updateBy;

	@Column(name="update_date")
	private Timestamp updateDate;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="book_id")
	private Book book;

	public Bookcontent() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(Integer insertedBy) {
		this.insertedBy = insertedBy;
	}

	public Timestamp getInsertedDate() {
		return this.insertedDate;
	}

	public void setInsertedDate(Timestamp insertedDate) {
		this.insertedDate = insertedDate;
	}

	public Boolean getIsFree() {
		return this.isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}