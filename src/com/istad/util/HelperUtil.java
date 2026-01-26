package com.istad.util;

import com.istad.model.Book;
import com.istad.model.Member;
import com.istad.service.BookService;
import com.istad.service.BookServiceImpl;
import com.istad.service.MemberService;
import com.istad.service.MemberServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class HelperUtil {
    static BookService bookService = new BookServiceImpl();
    static MemberService memberService = new MemberServiceImpl();

    public static void listAllBook() {
        try {
            List<Book> bookList = bookService.findAll();
            ViewUtil.printBookList(bookList);
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void updateBook() {
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

    public static void deleteBook() {
        ViewUtil.printHeader("Delete a Book By Code");
        String bookCode = InputUtil.getText("Enter Code: ");

        String confirmation = InputUtil.getText("Are you sure to delete? [ Y / N ]");
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
            try {
                bookService.deleteByCode(bookCode);
                ViewUtil.printHeader("Delete Successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        else
            ViewUtil.printHeader("Delete operation cancelled..!");
    }

    public static void addBook() {
        ViewUtil.printHeader("Add Member to Database");
        String bookCode = InputUtil.getText("Enter Code: ");
        String title = InputUtil.getText("Enter Name: ");
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

    public static void listAllMember() {
        try {
            List<Member> memberList = memberService.findAll();
            ViewUtil.printMemberList(memberList);
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void addMember() {
        ViewUtil.printHeader("Add Member to Database");
        String memberCode = InputUtil.getText("Enter Code : ");
        String fullName = InputUtil.getText("Enter Name : ");
        String gender = InputUtil.getText("Enter Gender : ");
        String phone = InputUtil.getText("Enter Phone : ");
        String email = InputUtil.getText("Enter Email : ");
        String address = InputUtil.getText("Enter Address : ");
        LocalDate joinDate = InputUtil.getLocalDate("Enter Join Date : ");
        String status = InputUtil.getText("Enter status");

        Member member = new Member(memberCode, fullName, gender, phone
                , email, address , joinDate, status);
        try {
            memberService.save(member);
            ViewUtil.printHeader("Member saved successfully..!");
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void updateMember() {
        ViewUtil.printHeader("Add Member to Database");
        String memberCode = InputUtil.getText("Enter Code : ");
        String fullName = InputUtil.getText("Enter Name : ");
        String gender = InputUtil.getText("Enter Gender : ");
        String phone = InputUtil.getText("Enter Phone : ");
        String email = InputUtil.getText("Enter Email : ");
        String address = InputUtil.getText("Enter Address : ");
        LocalDate joinDate = InputUtil.getLocalDate("Enter Join Date : ");
        String status = InputUtil.getText("Enter status");

        Member member = new Member();
        member.setFullName(fullName);
        member.setGender(gender);
        member.setPhone(phone);
        member.setEmail(email);
        member.setAddress(address);
        member.setJoinDate(joinDate);
        member.setStatus(status);

        try {
            memberService.updateByCode(memberCode, member);
            ViewUtil.printHeader("Member update successfully...!");
        } catch (RuntimeException e) {
            ViewUtil.printHeader(e.getMessage());
        }
    }

    public static void deleteMember() {
        ViewUtil.printHeader("Delete a Member By Code");
        String memberCode = InputUtil.getText("Enter Code: ");

        String confirmation = InputUtil.getText("Are you sure to delete? [ Y / N ]");
        if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes"))
            try {
                memberService.deleteByCode(memberCode);
                ViewUtil.printHeader("Delete Successfully...!");
            } catch (RuntimeException e) {
                ViewUtil.printHeader(e.getMessage());
            }
        else
            ViewUtil.printHeader("Delete operation cancelled..!");
    }

    public static void searchBooks(){
        ViewUtil.printHeader("Search Book By << code , title , author " +
                ", category , publisher, publishYar >>");
        String key = InputUtil.getText("Enter your Option");
        try {
            List<Book> bookList = bookService.searchBook(key);
            ViewUtil.printBookList(bookList);
        }catch (RuntimeException e){
            ViewUtil.printHeader(e.getMessage());
        }
    }

}
