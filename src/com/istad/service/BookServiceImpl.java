package com.istad.service;

import com.istad.dao.BookServiceDao;
import com.istad.dao.BookServiceDaoImpl;
import com.istad.model.Book;
import com.istad.util.ColorUtil;
import com.istad.util.ViewUtil;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookServiceDao bookServiceDao;

    public BookServiceImpl() {
        bookServiceDao = new BookServiceDaoImpl();
    }

    @Override
    public boolean checkAvailable(String bookCode) throws SQLException {
        try {
            return bookServiceDao.isBookAvailable(bookCode);
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Book> searchBook(String key) {
        try {
            return bookServiceDao.searchBook(key);
        } catch (SQLException e) {
            ViewUtil.printHeader(ColorUtil.RED + "SQL errored: " + e.getMessage() + ColorUtil.RESET);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteByCode(String bookCode) {
        try {
            if (!bookServiceDao.existByCode(bookCode))
                throw new RuntimeException("Book code do not exist...!");
            int affactedRow = bookServiceDao.deleteByCode(bookCode);
            if (affactedRow < 1)
                throw new RuntimeException("Delete Operation failed...");

        } catch (SQLException e) {
            System.out.println("SQL error : " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Book book) {
        try {
            int affectedRow = bookServiceDao.save(book);
            if (affectedRow < 1) {
                throw new RuntimeException("Insert new record failed");
            }
        } catch (SQLException e) {
            System.out.println("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateByCode(String bookCode, Book book) {
        try {
            Book foundBook = bookServiceDao.findByCode(bookCode)
                    .orElseThrow(() -> new RuntimeException("Book code not found...!"));

            if (book.getTitle() != null && !book.getTitle().isBlank())
                foundBook.setTitle(book.getTitle());

            if (book.getAuthor() != null && !book.getAuthor().isBlank())
                foundBook.setAuthor(book.getAuthor());

            if (book.getCategory() != null && !book.getCategory().isBlank())
                foundBook.setCategory(book.getCategory());

            if (book.getPublisher() != null && !book.getPublisher().isBlank())
                foundBook.setPublisher(book.getPublisher());

            if (book.getPublishYear() != null)
                foundBook.setPublishYear(book.getPublishYear());

            if (book.getPrice() != null)
                foundBook.setPrice(book.getPrice());

            if (book.getQty() != null)
                foundBook.setQty(book.getQty());

            if (book.getStatus() != null && !book.getStatus().isBlank())
                foundBook.setStatus(book.getStatus());

            int affectedRow = bookServiceDao.updateByCode(bookCode, foundBook);

            if (affectedRow < 1)
                throw new RuntimeException("Update operation failed...");

        } catch (SQLException e) {
            ViewUtil.printHeader("SQL error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> findAll() {
        try {
            return bookServiceDao.findAll();
        } catch (SQLException e) {
            ViewUtil.printHeader("SQL errored: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
