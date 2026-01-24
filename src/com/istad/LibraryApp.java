package com.istad;

import com.istad.config.Database;
import com.istad.model.Book;
import com.istad.service.BookService;
import com.istad.service.BookServiceImpl;
import com.istad.service.MemberService;
import com.istad.service.MemberServiceImpl;
import com.istad.util.InputUtil;
import com.istad.util.ViewUtil;

import java.math.BigDecimal;
import java.util.List;

public class LibraryApp {
    private final BookService bookService;
    private final MemberService memberService;

    public LibraryApp() {
        Database.init();
        bookService = new BookServiceImpl();
        memberService = new MemberServiceImpl();
    }

    public static void main(String[] args) {
        LibraryApp system = new LibraryApp();
        system.start();
    }

    private void start() {
        do {
            ViewUtil.showMenu();
            int menu = InputUtil.getInteger("Enter Your Option");
            switch (menu) {
                case 0 -> System.exit(0);
                case 1 -> showBookMenu();
                case 2 -> showMemberMenu();
                default -> ViewUtil.printHeader("Invalid Option Please Try Again");
            }
        } while (true);
    }

    private void showBookMenu() {
        do {
            ViewUtil.bookMenu();
            int bookMenu = InputUtil.getInteger("Enter Your Option");
            switch (bookMenu) {
                case 0 -> returnBack();
                case 1 -> listAllBook();
                case 2 -> {
                }
                case 3 -> addBook();
                case 4 -> updateBook();
                case 5 -> deleteBook();
                default -> ViewUtil.printHeader("Invalid Option Please Try Again");
            }

        } while (true);
    }

    private void showMemberMenu() {
        ViewUtil.memberMenu();
        int menu = InputUtil.getInteger("Enter Your Option");
        switch (menu) {
            case 1 -> {
            }
            default -> ViewUtil.printHeader("Invalid Option Please Try Again");
        }
    }

    private void returnBack() {
        ViewUtil.printHeader("You has has return back");
        LibraryApp system = new LibraryApp();
        system.start();
    }

    private void listAllBook() {
        try {
            List<Book> bookList = bookService.findAll();
            ViewUtil.printBookList(bookList);
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    private void addBook() {
        ViewUtil.printHeader("Add Book to Database");
        String bookCode = InputUtil.getText("Enter Code: ");
        String title = InputUtil.getText("Enter Title: ");
        String author = InputUtil.getText("Enter Author: ");
        String category = InputUtil.getText("Enter Category : ");
        String publisher = InputUtil.getText("Enter Publisher : ");
        Integer publishYear = InputUtil.getInteger("Enter Publish Year : ");
        BigDecimal price = InputUtil.getMoney("Enter Price: ");
        Integer qty = InputUtil.getInteger("Enter QTY: ");
        String status = InputUtil.getText("Enter status");

        Book book = new Book(bookCode, title, author, category, publisher, publishYear, price, qty, status);
        try {
            bookService.save(book);
            ViewUtil.printHeader("Book saved successfully..!");
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    private void updateBook() {
        ViewUtil.printHeader("Update a Book by code");
        String bookCode = InputUtil.getText("Enter Code: ");
        String title = InputUtil.getText("Enter Title: ");
        String author = InputUtil.getText("Enter Author: ");
        String category = InputUtil.getText("Enter Category : ");
        String publisher = InputUtil.getText("Enter Publisher : ");
        Integer publishYear = InputUtil.getInteger("Enter Publish Year : ");
        BigDecimal price = InputUtil.getMoney("Enter Price: ");
        Integer qty = InputUtil.getInteger("Enter QTY: ");
        String status = InputUtil.getText("Enter status");

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setCategory(category);
        book.setPublisher(publisher);
        book.setPublishYear(publishYear);
        book.setPrice(price);
        book.setQty(qty);
        book.setStatus(status);

        try {
            bookService.updateByCode(bookCode, book);
            ViewUtil.printHeader("Book update successfully...!");
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    private void deleteBook() {
        ViewUtil.printHeader("Delete a Book By Code");
        String code = InputUtil.getText("Enter Code: ");

        String confirmation = InputUtil.getText("Are you sure to delete? [ Y / N ]");
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
            try {
                bookService.deleteByCode(code);
                ViewUtil.printHeader("Delete Successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        else
            ViewUtil.printHeader("Delete operation cancelled..!");
    }

}
