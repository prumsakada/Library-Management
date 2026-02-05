package com.istad.dao;

import com.istad.config.Database;
import com.istad.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class BookServiceDaoImpl implements BookServiceDao {


    private final Connection conn;

    public BookServiceDaoImpl() {
        conn = Database.getConn();
    }


    @Override
    public boolean isBookAvailable(String bookCode) throws SQLException {
        String sql = """
        SELECT quantity, status
        FROM books
        WHERE book_code = ?
    """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookCode);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                String status = rs.getString("status");

                return quantity > 0 && "AVAILABLE".equalsIgnoreCase(status);
            }
        }
        return false;
    }

    @Override
    public List<Book> searchBook(String key) throws SQLException {
        List<Book> books = new ArrayList<>();
        Integer publishYear = null;
        try {
            publishYear = Integer.parseInt(key);
        } catch (NumberFormatException ignored) {

        }
        String sql = """
                SELECT *
                FROM books
                WHERE
                book_code ILIKE ?
                OR title ILIKE ?
                OR author ILIKE ?
                OR category ILIKE ?
                OR publisher ILIKE ?
                OR (? IS NOT NULL AND publish_year = ?)
                ORDER BY title;
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);

        String keyword = "%" + key + "%";

        pstmt.setString(1,keyword);
        pstmt.setString(2,keyword);
        pstmt.setString(3,keyword);
        pstmt.setString(4,keyword);
        pstmt.setString(5,keyword);
        if (publishYear != null){
            pstmt.setInt(6,publishYear);
            pstmt.setInt(7,publishYear);
        }else {
            pstmt.setNull(6,Types.INTEGER);
            pstmt.setNull(7,Types.INTEGER);
        }

        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setBookCode(rs.getString("book_code"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishYear(rs.getInt("publish_year"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setQty(rs.getInt("quantity"));
            book.setStatus(rs.getString("status"));

            books.add(book);
        }
        return books;
    }

    @Override
    public boolean existByCode(String bookCode) throws SQLException {
        String sql = """
                SELECT EXISTS(
                SELECT *
                FROM books
                WHERE book_code = ?
                )
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookCode);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next())
            return rs.getBoolean("exists");

        return false;
    }

    @Override
    public int deleteByCode(String bookCode) throws SQLException {
        String sql = """
                DELETE 
                FROM books
                WHERE book_code = ?
                """;

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookCode);

        return pstmt.executeUpdate();
    }

    @Override
    public int save(Book book) throws SQLException {
        String sql = """
                INSERT INTO books (book_code,title,author,category,publisher
                ,publish_year,price, quantity, status)
                VALUES(?,?,?,?,?,?,?,?,?)
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getBookCode());
        pstmt.setString(2, book.getTitle());
        pstmt.setString(3, book.getAuthor());
        pstmt.setString(4, book.getCategory());
        pstmt.setString(5, book.getPublisher());
        pstmt.setInt(6, book.getPublishYear());
        pstmt.setBigDecimal(7, book.getPrice());
        pstmt.setInt(8, book.getQty());
        pstmt.setString(9, book.getStatus());


        return pstmt.executeUpdate();
    }

    @Override
    public int updateByCode(String bookCode, Book book) throws SQLException {
        String sql = """
                UPDATE books
                SET title = ?, author = ? , category = ? , publisher = ? 
                  , publish_year = ? , price = ? , quantity = ? , status = ?
                WHERE book_code = ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, book.getAuthor());
        pstmt.setString(3, book.getCategory());
        pstmt.setString(4, book.getPublisher());
        pstmt.setInt(5, book.getPublishYear());
        pstmt.setBigDecimal(6, book.getPrice());
        pstmt.setInt(7, book.getQty());
        pstmt.setString(8, book.getStatus());
        pstmt.setString(9, book.getBookCode());

        return pstmt.executeUpdate();
    }

    @Override
    public Optional<Book> findByCode(String bookCode) throws SQLException {
        String sql = """
                SELECT * 
                FROM books
                WHERE book_code = ?
                """;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, bookCode);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setBookCode(rs.getString("book_code"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishYear(rs.getInt("publish_year"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setQty(rs.getInt("quantity"));
            book.setStatus(rs.getString("status"));

            return Optional.of(book);
        }

        return Optional.empty();
    }

    @Override
    public List<Book> findAll() throws SQLException {
        Statement stmt = conn.createStatement();

        String sql = """
                SELECT *
                FROM books
                """;
        ResultSet rs = stmt.executeQuery(sql);
        List<Book> books = new ArrayList<>();
        while (rs.next()) {
            Book book = new Book();
            book.setBookId(rs.getInt("book_id"));
            book.setBookCode(rs.getString("book_code"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setPublisher(rs.getString("publisher"));
            book.setPublishYear(rs.getInt("publish_year"));
            book.setPrice(rs.getBigDecimal("price"));
            book.setQty(rs.getInt("quantity"));
            book.setStatus(rs.getString("status"));

            books.add(book);
        }
        return books;
    }
}
