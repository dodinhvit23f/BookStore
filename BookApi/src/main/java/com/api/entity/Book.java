package com.api.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@Table(name="book")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String PRICE = "price";
	public static final String AUTHOR = "author";
	public static final String DISCOUNT = "discount";
	public static final String IS_DELETED = "isDelete";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(length=70)
	private String author;

	@Column(length=2147483647)
	private String description;

	private Integer discount;

	@Column(name="inserted_by")
	private Integer insertedBy;

	@Column(name="inserted_date")
	private Timestamp insertedDate;

	@Column(name="is_delete")
	private Boolean isDelete;

	@Column(length=150)
	private String name;

	private Long price;

	@Column(name="updated_by")
	private Integer updatedBy;

	@Column(name="updated_date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to Bookcontent
	@OneToMany(mappedBy="book")
	private List<Bookcontent> bookcontents;

	//bi-directional many-to-one association to Comment
	@OneToMany(mappedBy="book")
	private List<Comment> comments;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="book")
	private List<Image> images;

	public Book() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDiscount() {
		return this.discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
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

	public Boolean isDelete() {
		return this.isDelete;
	}

	public void delete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrice() {
		return this.price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updatedBy = updateBy;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updateDate) {
		this.updatedDate = updateDate;
	}

	public List<Bookcontent> getBookcontents() {
		return this.bookcontents;
	}

	public void setBookcontents(List<Bookcontent> bookcontents) {
		this.bookcontents = bookcontents;
	}

	public Bookcontent addBookcontent(Bookcontent bookcontent) {
		getBookcontents().add(bookcontent);
		bookcontent.setBook(this);

		return bookcontent;
	}

	public Bookcontent removeBookcontent(Bookcontent bookcontent) {
		getBookcontents().remove(bookcontent);
		bookcontent.setBook(null);

		return bookcontent;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Comment addComment(Comment comment) {
		getComments().add(comment);
		comment.setBook(this);

		return comment;
	}

	public Comment removeComment(Comment comment) {
		getComments().remove(comment);
		comment.setBook(null);

		return comment;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setBook(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setBook(null);

		return image;
	}

}