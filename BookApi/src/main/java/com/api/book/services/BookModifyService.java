package com.api.book.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.book.dto.BookDTO;
import com.api.book.dto.BookModifycationDTO;
import com.api.book.request.BookAdditionRequest;
import com.api.book.request.BookModificationRequest;
import com.api.book.responses.BookPaginationResponse;
import com.api.config.ApiConstantsConfiguration;
import com.api.dao.BookDAOI;
import com.api.entity.Book;
import com.api.entity.Image;
import com.common.MessageCode;
import com.common.PaginationRequest;
import com.common.Utility;

@Service
public class BookModifyService implements BookModifyServiceI {

    private static final String HASH_SAULT = "Book";

    private static final int MAX_RECORD = 15;
    private static final int DEFAULT_RECORD = 10;

    @Autowired
    private BookDAOI bookDAO;

    @Autowired
    private BookImageServiceI imageService;

    private String getHashKey(int id) {
        return String.format("%s%d", HASH_SAULT, id);
    }

    private void getBookDisplayForClient(List<Book> books, List<BookDTO> booksDTO) {

        books.stream().forEach(book -> {
            BookDTO dto = new BookDTO();

            dto.setId(book.getId());
            dto.setAuthorName(book.getAuthor());
            dto.setBookName(book.getName());
            dto.setPrice(book.getPrice());
            dto.setAuthorName(book.getAuthor());

            if (!Utility.isNull(book.getDiscount())) {

                final long sellPrice = book.getPrice() * book.getDiscount() / 100;
                if (sellPrice > 0) {
                    // dto.setSellPrice(sellPrice);
                }
            }

            booksDTO.add(dto);
        });
    }

    private void getBookDisplayForAdmin(List<Book> books, List<BookModifycationDTO> booksDTO) {

        books.stream().forEach(book -> {
            BookModifycationDTO dto = new BookModifycationDTO();

            dto.setId(book.getId());
            dto.setAuthorName(book.getAuthor());
            dto.setBookName(book.getName());
            dto.setPrice(book.getPrice());
            dto.setAuthorName(book.getAuthor());

            if (!Utility.isNull(book.getDiscount())) {
                dto.setDiscount(book.getDiscount());
            }

            dto.setIsDelete(book.isDelete());

            booksDTO.add(dto);
        });
    }

    private List<BookModifycationDTO> getBooksForAdmin(PaginationRequest pagination, boolean isAdmin) {

        List<BookModifycationDTO> booksDTO = new ArrayList<BookModifycationDTO>();

        List<Book> books = this.bookDAO.getBooks(pagination, isAdmin);

        this.getBookDisplayForAdmin(books, booksDTO);

        return booksDTO;
    }

    private void uploadImages(BookAdditionRequest request, final Book book, final List<Image> images)
            throws FileUploadException {

        boolean isImages = true;

        for (MultipartFile imageFile : request.getImageFiles()) {
            if (isImages) {
                isImages = Utility.isImageFile(imageFile);
            }

        }

        if (!isImages) {
            throw new FileUploadException();
        }

        for (MultipartFile file : request.getImageFiles()) {

            final String fileName = String.format("%s.%s", UUID.randomUUID(), Utility.IMAGE_EXTENSION);
            final Path filePath = Paths.get(ApiConstantsConfiguration.IMAGE_PATH, fileName);

            try {
                file.transferTo(filePath);
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
            }

            final Image image = new Image();
            image.setUrl(fileName);
            image.setBook(book);
            images.add(image);
        }

        imageService.addBookImages(images);
    }

    private void deleteImages(BookModificationRequest request) {
        Arrays.asList(request.getDeletedImageFiles()).stream()
                .forEach(imageName -> {
                    if (imageName.contains(".png")) {
                        imageService.deleteBookImage(imageName);
                    }
                });
    }

    @Override
    public void addNewBook(BookAdditionRequest request)
            throws EntityExistsException, QueryTimeoutException, FileUploadException {

        try {
            bookDAO.findBookByNameAndAuthorName(request.getBookName(), request.getAuthorName());
            throw new EntityExistsException(MessageCode.Error.ERB009);
        } catch (NoResultException e) {

        }

        Timestamp insertTime = new Timestamp(System.currentTimeMillis());

        final Book book = new Book();
        final List<Image> images = new ArrayList<Image>();

        book.setName(request.getBookName());
        book.setAuthor(request.getAuthorName());
        book.setPrice(request.getPrice());
        book.setInsertedDate(insertTime);
        book.setInsertedBy(1);
        book.setDescription(request.getDescription());
        book.delete(false);

        this.bookDAO.insert(book);

        uploadImages(request, book, images);
    }

    @Override
    public void updateBook(BookModificationRequest request, HttpSession session)
            throws OptimisticLockException, QueryTimeoutException, FileUploadException {

        Book book = null;
        final List<Image> images = new ArrayList<Image>();
        Timestamp updatedTime = new Timestamp(System.currentTimeMillis());

        try {
            book = bookDAO.findById(request.getBookId(), Book.class);
        } catch (NoResultException e) {
            throw new NoResultException(MessageCode.Error.ERB010);
        }

        if (!Utility.isEmpty(request.getAuthorName()) &&
                !book.getAuthor().equals(request.getAuthorName())) {
            book.setAuthor(request.getAuthorName());
        }

        if (!Utility.isEmpty(request.getBookName()) &&
                !book.getName().equals(request.getBookName())) {
            book.setName(request.getBookName());
        }

        if (request.getPrice() > 0 &&
                book.getPrice() != request.getPrice()) {
            book.setPrice(request.getPrice());
        }

        if (!Utility.isEmpty(request.getDescription()) &&
                !book.getDescription().equals(request.getDescription())) {
            book.setDescription(request.getDescription());
        }

        if (bookDAO.isLocked(book)) {

            Timestamp lastUpdateTime = (Timestamp) session.getAttribute(getHashKey(book.getId()));

            if (!Utility.isNull(book.getUpdatedDate()) && !Utility.isNull(lastUpdateTime)) {
                if (!lastUpdateTime.equals(book.getUpdatedDate())) {
                    throw new OptimisticLockException(MessageCode.Error.ERUPDATE);
                }
            }
        }

        book.setUpdatedDate(updatedTime);

        this.bookDAO.update(book);

        if (!Utility.isNull(request.getImageFiles())) {
            uploadImages(request, book, images);
        }

        if (!Utility.isNull(request.getDeletedImageFiles())) {
            deleteImages(request);
        }
    }

    @Override
    public void deleteBook(int bookId) throws OptimisticLockException, QueryTimeoutException {
        Book book = bookDAO.findById(bookId, Book.class);
        book.delete(!book.isDelete());
        this.bookDAO.update(book);
    }

    @Override
    public BookModifycationDTO findBookForModify(int bookId, HttpSession session)
            throws OptimisticLockException, QueryTimeoutException {
        Book book = bookDAO.findBookForModify(bookId);

        BookModifycationDTO bookDTO = new BookModifycationDTO();

        bookDTO.setId(book.getId());
        bookDTO.setAuthorName(book.getAuthor());
        bookDTO.setBookName(book.getName());
        bookDTO.setPrice(book.getPrice());
        bookDTO.setDescription(book.getDescription());

        if (!Utility.isNull(book.getDiscount())) {
            bookDTO.setDiscount(book.getDiscount());
        }

        // bookDTO.setIsDelete(book.isDelete());

        String hashCodeKey = getHashKey(book.getId());

        Timestamp lastUpdateTime = (Timestamp) session.getAttribute(hashCodeKey);

        if (!Utility.isNull(lastUpdateTime)) {
            if (!lastUpdateTime.equals(book.getUpdatedDate())) {
                session.setAttribute(hashCodeKey, book.getUpdatedDate());
            }
        }

        if (Utility.isNull(lastUpdateTime)) {
            session.setAttribute(hashCodeKey, book.getUpdatedDate());
        }

        bookDTO.setBookImage(
                book.getImages().stream().map(Image::getUrl).collect(Collectors.toList()).toArray(String[]::new));

        return bookDTO;
    }

    @Override
    public BookPaginationResponse paging(PaginationRequest pagination, boolean isAdmin) {

        BookPaginationResponse response = new BookPaginationResponse();

        if (pagination.getSize() > MAX_RECORD || pagination.getSize() <= Utility.ZERO) {
            pagination.setSize(DEFAULT_RECORD);
        }

        if (pagination.getCurrentPage() <= Utility.ZERO) {
            pagination.setCurrentPage(1);
        }

        if (Utility.isEmpty(pagination.getSortBy())) {
            pagination.setSortBy(Book.ID);
        }

        if (!Utility.isEmpty(pagination.getSortBy())) {
            switch (pagination.getSortBy()) {
                case Book.ID:
                    break;

                case Book.NAME:
                    break;

                case Book.PRICE:
                    break;

                case Book.AUTHOR:
                    break;

                default:
                    pagination.setSortBy(Book.ID);
                    break;
            }
        }

        response.setBooks(this.getBooksForAdmin(pagination, isAdmin));
        response.setSize(pagination.getSize());

        final int totalPage = (int) Math.ceil((float) this.bookDAO.count(isAdmin) / pagination.getSize());
        response.setTotalPage(totalPage);

        response.setCurrentPage(pagination.getCurrentPage());

        return response;
    }

}
