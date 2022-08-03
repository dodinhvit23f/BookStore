package com.api.dao;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.api.entity.Book;
import com.base.BaseDataAccess;
import com.common.PaginationRequest;

@Repository
public class BookDAO extends BaseDataAccess<Book> implements BookDAOI {
	

	public Book findByName(String bookName) throws NoResultException, QueryTimeoutException {
		Book book = this.manager.createQuery("SELECT B FROM Book B WHERE B.name = :bookName", Book.class)
				.setParameter("bookName", bookName).getSingleResult();
		return book;
	}

	@Override
	public Book findBookByNameAndAuthorName(String bookName, String authorName)
			throws NoResultException, QueryTimeoutException {
		Book book = this.manager
				.createQuery("SELECT B FROM Book B WHERE B.name = :bookName AND B.author = :authorName", Book.class)
				.setParameter("bookName", bookName).setParameter("authorName", authorName).getSingleResult();
		return book;
	}

	@Override
	public Book findBookForModify(int bookId) throws NoResultException, QueryTimeoutException {
		return this.findById(bookId, Book.class);
	}

	@Override
	public Book findById(int id, Class<Book> clazz) {
		Book book = super.findById(id, clazz);
		if (!isLocked(book)) {
			manager.lock(book, LockModeType.OPTIMISTIC);
		}

		return book;
	}

	@Override
	public int count(boolean isAdminstrator) {
		if (isAdminstrator) {
			return this.countAllBooks();
		}

		return countAvailableBooks();
	}

	private int countAllBooks() {
		return ((Number) this.manager.createNativeQuery("SELECT COUNT(*) FROM Book").getSingleResult()).intValue();
	}

	public int countAvailableBooks() {
		return ((Number) this.manager.createNativeQuery("SELECT COUNT(*) FROM Book WHERE IS_DELETED = FALSE")
				.getSingleResult()).intValue();
	}

	private CriteriaQuery<Book> getBooksQuery(boolean isAdminstrator, String sortBy, boolean isAscension) {

		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
		Root<Book> stud = criteriaQuery.from(Book.class);

		if (!isAdminstrator) {
			criteriaQuery.multiselect(stud.get(Book.ID), stud.get(Book.NAME),
					stud.get(Book.PRICE),stud.get(Book.DISCOUNT), stud.get(Book.AUTHOR));
			criteriaQuery.where(criteriaBuilder.equal(stud.get(Book.IS_DELETED), false));
		}

		if (isAscension) {
			criteriaQuery.orderBy(criteriaBuilder.asc(stud.get(sortBy)));
		}

		if (!isAscension) {
			criteriaQuery.orderBy(criteriaBuilder.desc(stud.get(sortBy)));
		}

		return criteriaQuery.select(stud);
	}

	public List<Book> getBooks(PaginationRequest pagination, boolean isAdminstrator) {

		int startPosition = (pagination.getCurrentPage() - 1) * pagination.getSize();

		return this.manager
				.createQuery(this.getBooksQuery(isAdminstrator, pagination.getSortBy(), pagination.isAscension()))
				.setMaxResults(pagination.getSize()).setFirstResult(startPosition).getResultList();
	}

}
