package com.istad.dao;

import com.istad.model.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface BookServiceDao {

    boolean isBookAvailable (String bookCode) throws SQLException;

    List<Book> searchBook(String key) throws SQLException;

    boolean existByCode(String bookCode) throws SQLException;

    int deleteByCode(String bookCode) throws SQLException;

    int save(Book book) throws SQLException;

    int updateByCode(String bookCode, Book book) throws SQLException;

    List<Book> findAll() throws SQLException;

    Optional<Book> findByCode(String bookCode) throws SQLException;

}
