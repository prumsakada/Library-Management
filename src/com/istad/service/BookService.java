package com.istad.service;

import com.istad.dao.BookServiceDao;
import com.istad.model.Book;

import java.util.List;

public interface BookService {

    void deleteByCode(String bookCode);

    void save(Book book);

    void updateByCode(String bookCode , Book book);

    List<Book>findAll();

}
