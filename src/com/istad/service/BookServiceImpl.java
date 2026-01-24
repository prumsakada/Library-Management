package com.istad.service;

import com.istad.dao.BookServiceDao;
import com.istad.dao.BookServiceDaoImpl;
import com.istad.model.Book;
import com.istad.util.ViewUtil;

import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private final BookServiceDao bookServiceDao;

    public BookServiceImpl(){
        bookServiceDao = new BookServiceDaoImpl();
    }

    @Override
    public void deleteByCode(String bookCode) {
        try{
            if (!bookServiceDao.existByCode(bookCode))
                throw new RuntimeException("Product code do not exist...!");
            int affactedRow = bookServiceDao.deleteByCode(bookCode);
            if (affactedRow < 1)
                throw new RuntimeException("Delete Operation failed...");

        } catch (SQLException e) {
            System.out.println("SQL error : "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void save(Book book) {
        try{
            int affectedRow = bookServiceDao.save(book);
            if (affectedRow < 1){
                throw new RuntimeException("Insert new record failed");
            }
        }catch (SQLException e){
            System.out.println("SQL errored: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateByCode(String bookCode, Book book) {
        try {
            Book foundBook = bookServiceDao.findByCode(bookCode)
                    .orElseThrow(()-> new RuntimeException("Book code not found...!"));
            if (!book.getTitle().isBlank())
                foundBook.setTitle(book.getTitle());
            if (!book.getAuthor().isBlank())
                foundBook.setAuthor(book.getAuthor());
            if (!book.getCategory().isBlank())
                foundBook.setCategory(book.getCategory());
            if (!book.getPublisher().isBlank())
                foundBook.setPublisher(book.getPublisher());
            if (book.getPublishYear() != null)
                foundBook.setPublishYear(book.getPublishYear());
            if (book.getPrice() != null)
                foundBook.setPrice(book.getPrice());
            if (book.getQty() != null)
                foundBook.setQty(book.getQty());
            if (!book.getStatus().isBlank())
                foundBook.setStatus(book.getStatus());

            int affactedRow = bookServiceDao.updateByCode(bookCode,foundBook);
            if (affactedRow < 1)
                throw  new RuntimeException("Update operation failed...");

        } catch (SQLException e) {
            ViewUtil.printHeader("SQL error: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<Book> findAll() {
        try{
            return bookServiceDao.findAll();
        }catch (SQLException e){
            System.out.println("SQL errored: "+e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }
}
