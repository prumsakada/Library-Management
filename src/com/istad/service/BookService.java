package com.istad.service;

import com.istad.dao.BookServiceDao;
import com.istad.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookService {

    boolean checkAvailable(String bookCode) throws SQLException;

    List<Book> searchBook(String key);

    void deleteByCode(String bookCode);

    void save(Book book);

    void updateByCode(String bookCode , Book book);

    List<Book>findAll();

}
